#creazione database associato all'archivio di foto/video. Comincio creando una singola tabella 

import os
import sqlite3
path=r"C:\Users\utente\Pictures\2020-06-02"#=input("Inserisci path con foto e video: ")
archivepath=r"C:\Users\utente\Desktop\Luca\Archivio"

conn=sqlite3.connect("archive.db")
c=conn.cursor()
c.execute("CREATE TABLE IF NOT EXISTS Files (File_id INTEGER PRIMARY KEY, File_name TEXT UNIQUE, Day INTEGER, Month INTEGER, Year INTEGER, Prog_number INTEGER, path TEXT UNIQUE, eof TEXT,type_flag INTEGER)")

years=os.scandir(archivepath)
for year in years:
    if len(year.name)==4:
        months=os.scandir(year.path)
        for month in months:
            if month.is_dir():
                files=os.scandir(month.path)
                for file in files:
                    if len(file.name.split("-"))==4:
                        day=file.name.split("-")[0]
                        month=file.name.split("-")[1]
                        year=file.name.split("-")[2]
                        final_piece=file.name.split("-")[3]
                        prog_number=final_piece.split(".")[0]
                        path=file.path
                        eof=file.name.split(".")[1]
                        if file.name.endswith(".opus"):
                            type_flag=1
                        elif file.name.endswith(".mp4") or file.name.endswith(".mpeg"):
                            type_flag=2
                        else:
                            type_flag=0
                        c.execute("insert or ignore into Files values (?,?,?,?,?,?,?,?,?)",(None,file.name,day,month,year,prog_number,path,eof,type_flag)) #ignora l'aggiunta se questa viola un constraint del database
conn.commit()
c.execute("Select * from Files")
print(c.fetchall())