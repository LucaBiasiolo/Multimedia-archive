#Il seguente script prende i file nuovi da aggiungere da una cartella, li rinomina e li sposta opportunamente andando a pescare il numero progressivo giusto dal database associato all'archivio
import sqlite3
import os
from functions import *
path=r"C:\Users\utente\Pictures\2020-06-02"#=input("Inserisci path con foto e video: ")
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
        move(rename(file))
conn.rollback()
conn.close()