#Il seguente script prende i file nuovi da aggiungere da una cartella, li rinomina e li sposta opportunamente andando a pescare il numero progressivo giusto dal database associato all'archivio
import sqlite3
import os
import hashlib
from datetime import datetime
from rename_functions import *
path=r"C:\Users\utente\Pictures\2020-06-02"#=input("Inserisci path con foto e video: ")
archivepath=r"C:\Users\utente\Desktop\Luca\Archivio"
eofs=["jpg","JPG","jpeg","png","gif","mp4","opus","mpeg"]
conn=sqlite3.connect("archive.db")
c=conn.cursor()

newfolder=os.scandir(path)
newfiles=[]
for file in newfolder:
    eof=file.name.split(".")[1]
    if eof in eofs:
        newfiles.append(file)
    else:
        print("Impossibile processare",file.name,"in quanto estensione non valida")
          
for file in newfiles:
    f=open(file.path,'rb')
    h=hashlib.sha1() #nuovo oggetto sha-1
    h.update(f.read())
    fhash=h.hexdigest() #hash del file
    f.close()
    c.execute("select File_name from Files where hash=?",(fhash,)) #cerco possibili doppioni
    doubles=c.fetchone()
    if doubles is not None:
        print(file.name,"doppione di ",doubles[0])
        continue
    [day,month,year]=rename_general(file)
    eof=file.name.split(".")[1]
    c.execute("select max(Prog_number) from Files where Day=? and Month=? and Year=?",(day,month,year[2:]))
    maxpn=c.fetchone()[0]
    if maxpn is None:
        number=1
    else:
        number=maxpn+1
    newname="%s-%s-%s-%s.%s" %(day,month,year[2:],str(number),eof)
    years=os.listdir(archivepath)
    if str(year) in years:
        months=os.listdir(archivepath+"\\"+year)
        if str(month) in months:
            print("Rinomino ",file.name,"come",newname,"e lo sposto in ",archivepath+"\\%s\\%s\\%s" %(year,month,newname))
        else:
            print("Creo la cartella del mese ",year, month)
            os.mkdir(archivepath+"\\%s\\%s" %(year,month)) #creo cartella mese se questa non esiste
    else:
        print("Creo la cartella anno/mese", year, month)
        os.mkdir(archivepath+"\\%s\\%s" %(year,month)) #creo cartella anno/mese se questa non esiste
    #os.rename(file.path,archivepath+"\\%s\\%s\\%s" %(year,month,newname)) #sposto i nuovi file
    c.execute("insert into Files values (?,?,?,?,?,?,?,?)",(None,newname,day,month,year[2:],number,eof,fhash))
conn.rollback()
conn.close()