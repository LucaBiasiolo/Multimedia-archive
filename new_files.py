#Il seguente script prende i file nuovi da aggiungere da una cartella, elimina quelli con estensioni non adatte e li rinomina e sposta opportunamente andando a pescare il numero progressivo giusto dal database associato all'archivio
import sqlite3
import os
from datetime import datetime
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

#rinomino i file con data di ultima modifica e li sposto              
for file in newfiles: #rinominare prima usando il nome del file e dopo con la data di ultima modifica in caso non si riuscisse
    mtimestamp=os.path.getmtime(file.path)
    mdate=datetime.fromtimestamp(mtimestamp) #data ultima modifica file
    day=mdate.day
    month=mdate.month
    year=str(mdate.year)
    eof=file.name.split(".")[1] #estensione file
    c.execute("select max(Prog_number) from Files where Day=? and Month=? and Year=?",(day,month,year[2:]))
    maxpn=c.fetchone()[0]
    if maxpn is None:
        number=1
    else:
        number=maxpn+1
    if file.name.endswith(".opus"):
        type_flag=1
    elif file.name.endswith(".mp4") or file.name.endswith(".mpeg"):
        type_flag=2
    else:
        type_flag=0
    newname="%s-%s-%s-%s.%s" %(day,month,year[2:],str(number),eof)
    print("Rinomino ",file.name,"come",newname,"e lo sposto in ",archivepath+"\\%s\\%s\\%s" %(year,month,newname))
    #os.rename(file.path,archivepath+"\\%s\\%s\\%s" %(year,month,newname)) #sposto i nuovi file
    #creare cartella anno/mese se lo spostamento non va a buon fine
    
    #aggiorno database per aggiornare automaticamente numero progressivo
    print("insert into Files values (%s,%d,%d,%s,%d,%s,%s,%d)" %(newname,day,month,year[2:],number,archivepath+"\\%s\\%s\\%s" %(year,month,newname),eof,type_flag))
    c.execute("insert into Files values (?,?,?,?,?,?,?,?,?)",(None,newname,day,month,year[2:],number,archivepath+"\\%s\\%s\\%s" %(year,month,newname),eof,type_flag))
conn.rollback()
conn.close()