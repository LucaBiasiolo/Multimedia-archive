#Il seguente script prende i file nuovi da aggiungere da una cartella, li rinomina e li sposta opportunamente andando a pescare il numero progressivo giusto dal database associato all'archivio
import sqlite3
import os
from functions import *
conn=sqlite3.connect("archive.db")
c=conn.cursor()

files=os.scandir(path)
newfiles=[]
for file in files:
    file=new_file(file.name,file.path)
    newfiles.append(file)
for file in newfiles:
    okay=file.check_file()
    if okay:
        file.rename().move()
conn.rollback()
conn.close()