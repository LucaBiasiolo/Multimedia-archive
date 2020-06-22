#Collezione di funzioni per rinominare i file partendo direttamente dal loro nome. Da sistemare

import sqlite3
import os
import hashlib
from datetime import datetime
path=r"C:\Users\utente\Pictures\2020-06-02"#=input("Inserisci path con foto e video: ")
archivepath=r"C:\Users\utente\Desktop\Luca\Archivio foto-video"
eofs=["jpg","JPG","jpeg","png","gif","mp4","opus","mpeg"]
conn=sqlite3.connect("archive.db")
c=conn.cursor()

def check_file(file):
    okay=True
    if file.eof not in eofs:
        print("Impossibile processare",file.name,"in quanto estensione non valida")
        okay=False
    else:
        c.execute("select File_name from Files where hash=?",(file.hash,)) #cerco possibili doppioni
        doubles=c.fetchone()
        if doubles is not None:
            print(file.name,"doppione di ",doubles[0])
            okay=False
        return okay
        
#funzione generale per rinominare i file
def rename(file):
    if len(file.name.split(".")[0])==10:
        [day,month,year]=rename_timestamp(file)
    elif file.name.startswith("IMG-"):
        [day,month,year]=rename_IMG(file)
    elif file.name.startswith("WP"):
        [day,month,year]=rename_WP(file)
    else:
        [day,month,year]=rename_mdate(file)
    c.execute("select max(Prog_number) from Files where Day=? and Month=? and Year=?",(day,month,year[2:]))
    maxpn=c.fetchone()[0]
    if maxpn is None:
        number=1
    else:
        number=maxpn+1
    newname="%s-%s-%s-%s.%s" %(day,month,year[2:],str(number),file.eof)
    return newname

#rinomino file che hanno nome tipo annomesegiorno_ora
def rename_annomesegiornoora(file):
    pieces=file.name.split("_")
    year=pieces[0][0:4]
    month=pieces[0][4:6].lstrip('0')
    day=pieces[0][6:].lstrip('0')
    return [day,month,year]
        
#rinomino file che hanno nome tipo timestamp
def rename_timestamp(file):
    timestamp=int(file.name.split(".")[0])
    date=datetime.fromtimestamp(timestamp) #data ultima modifica file
    day=str(mdate.day)
    month=str(mdate.month)
    year=str(mdate.year)
    return [day,month,year]
        
#rinomino file che hanno nome tipo IMG-annomesegiorn-numero
def rename_IMG(file):
    pieces=file.name.split("-")
    year=pieces[1][0:4]
    month=pieces[1][4:6].lstrip('0')
    day=pieces[1][6:].lstrip('0')
    return [day,month,year]
    
#rinomino file che hanno nome tipo WP_annomesegiorno_ora_minuti_secondi
def rename_WP(file):
    pieces=file.name.split("_")
    year=pieces[1][0:4]
    month=pieces[1][4:6].lstrip('0')
    day=pieces[1][6:].lstrip('0')
    return [day,month,year]

#rinomino file usando data di ultima modifica del path
def rename_mdate(file):
    mtimestamp=os.path.getmtime(file.path)
    mdate=datetime.fromtimestamp(mtimestamp) #data ultima modifica file
    day=mdate.day
    month=mdate.month
    year=str(mdate.year)
    return [day,month,year]
    
def move(file):
    years=os.listdir(archivepath)
    if "20"+file.year in years:
        months=os.listdir(archivepath+"\\"+"20"+file.year)
        if file.month in months:
            print("Rinomino come",file.name,"e lo sposto in ",archivepath+"\\%s\\%s\\%s" %("20"+file.year,file.month,file.name))
        else:
            print("Creo la cartella del mese ","20"+file.year, file.month)
            os.mkdir(archivepath+"\\%s\\%s" %("20"+file.year,file.month)) #creo cartella mese se questa non esiste
    else:
        print("Creo la cartella anno/mese","20"+file.year, file.month)
        os.mkdir(archivepath+"\\%s\\%s" %("20"+file.year,file.month)) #creo cartella anno/mese se questa non esiste
        #os.rename(file.path,archivepath+"\\%s\\%s\\%s" %(year,month,file.name)) #sposto i nuovi file
        c.execute("insert into Files values (?,?,?,?,?,?,?,?)",(None,file.name,day,month,year[2:],number,eof,fhash))