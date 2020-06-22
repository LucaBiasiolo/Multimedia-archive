#Il seguente script prende i file nuovi da aggiungere da una cartella, li rinomina e li sposta opportunamente andando a pescare il numero progressivo giusto dal database associato all'archivio
import sqlite3
import os
import hashlib
from datetime import datetime
from functions import *
from class_objects import *
path=r"C:\Users\utente\Pictures\2020-06-02"#=input("Inserisci path con foto e video: ")
archivepath=r"C:\Users\utente\Desktop\Luca\Archivio foto-video"
eofs=["jpg","JPG","jpeg","png","gif","mp4","opus","mpeg"]
conn=sqlite3.connect("archive.db")
c=conn.cursor()

#newfolder=os.scandir(path)
files=os.scandir(path)
newfiles=[]
for file in files:
    file=new_file(file.name,file.path)
    newfiles.append(file)
for file in newfiles:
    okay=check_file(file)
    if okay:
        newname=rename(file)
        file=archive_file(newname,file.path)
        move(file)
conn.rollback()
conn.close()