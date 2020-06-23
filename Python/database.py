#creazione database associato all'archivio di foto/video. Comincio creando una singola tabella 

import os
import sqlite3
import hashlib
from functions import *
path=r"C:\Users\utente\Pictures\2020-06-02"#=input("Inserisci path con foto e video: ")
archivepath=r"C:\Users\utente\Desktop\Luca\Archivio foto-video"

conn=sqlite3.connect("archive.db")
c=conn.cursor()
c.execute("drop table Files")
c.execute("CREATE TABLE IF NOT EXISTS Files (File_id INTEGER PRIMARY KEY, File_name TEXT UNIQUE, Day INTEGER, Month INTEGER, Year INTEGER, Prog_number INTEGER, eof TEXT,hash TEXT UNIQUE)")

years=os.scandir(archivepath)
for year in years:
    if len(year.name)==4:
        months=os.scandir(year.path)
        for month in months:
            if month.is_dir():
                files=os.scandir(month.path)
                filelist=[]
                for file in files:
                    if len(file.name.split("-"))==4:
                        file=archive_file(file.name,file.path)
                        okay=check_file(file)
                        filelist.append((int(file.day),int(file.pn),file.eof))
                filelist.sort() #ordina i file in ordine numerico crescente
                for file in filelist:
                    day=file[0]
                    oldpn=file[1]
                    eof=file[2]
                    c.execute("select max(Prog_number) from Files where Day=? and Month=? and Year=?",(day,month.name,year.name[2:])) #cerco massimo numero progressivo nel database (lo popolo dinamicamente)
                    maxpn=c.fetchone()[0]
                    if maxpn is None:
                        newpn=1
                    else:
                        newpn=maxpn+1
                    oldname="%s-%s-%s-%s.%s" %(day,month.name,year.name[2:],oldpn,eof)
                    file=archive_file(oldname,month.path+"\\"+oldname)
                    newname="%s-%s-%s-%s.%s" %(day,month.name,year.name[2:],newpn,eof)
                    if newname!=oldname:
                        print("Rinomino ", oldname, "come ",newname)
                        os.rename(month.path+"\\"+oldname,month.path+"\\"+newname) #rinomino il file
                    c.execute("insert into Files values (?,?,?,?,?,?,?,?)",(None,newname,day,month.name,year.name[2:],newpn,eof,file.hash)) #popolo il database coi dati dei file
conn.commit()
conn.close()