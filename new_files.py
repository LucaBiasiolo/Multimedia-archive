#Il seguente script prende i file nuovi da aggiungere da una cartella, li rinomina e li sposta opportunamente andando a pescare il numero progressivo giusto dal database associato all'archivio
import sqlite3
import os
from datetime import datetime
from rename_functions import *
path=r"C:\Users\utente\Pictures\2020-06-02"#=input("Inserisci path con foto e video: ")
archivepath=r"C:\Users\utente\Desktop\Luca\Archivio"
eofs=[".jpg",".JPG",".jpeg",".png",".gif",".mp4",".opus",".mpeg"]
conn=sqlite3.connect("archive.db")
c=conn.cursor()

newfolder=os.scandir(path)
newfiles=[]
for file in newfolder:
    truefalse=list()
    for eof in eofs:
        truefalse.append(file.name.endswith(eof))
    if any(truefalse):
        newfiles.append(file)
    else:
        print("Impossibile processare",file.name,"in quanto estensione non valida")
          
for file in newfiles: 
    #controllare se la foto che sto rinominando è un doppione oppure no
    if len(file.name.split(".")[0])==10:
        [day,month,year]=rename_timestamp(file)
    elif file.name.startswith("IMG-"):
        [day,month,year]=rename_IMG(file)
    elif file.name.startswith("WP"):
        [day,month,year]=rename_WP(file)
    else:
        [day,month,year]=rename_mdate(file)
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
            #os.mkdir(archivepath+"\\%s\\%s" %(year,month)) #creo cartella anno/mese se questa non esiste
    else:
        print("Creo la cartella anno/mese", year, month)
        #os.mkdir(archivepath+"\\%s\\%s" %(year,month)) #creo cartella anno/mese se questa non esiste
    #os.rename(file.path,archivepath+"\\%s\\%s\\%s" %(year,month,newname)) #sposto i nuovi file
    #print("insert into Files values (%s,%d,%d,%d,%d,%s)" %(newname,int(day),int(month),int(year[2:]),number,eof))
    c.execute("insert into Files values (?,?,?,?,?,?,?)",(None,newname,day,month,year[2:],number,eof))
conn.rollback()
conn.close()