#provo a sostituire il dizionario con il database che ho creato
import sqlite3
import os
path=r"C:\Users\utente\Pictures\2020-06-02"#=input("Inserisci path con foto e video: ")
archivepath=r"C:\Users\utente\Desktop\Luca\Archivio"
conn=sqlite3.connect("archive.db")
c=conn.cursor()
#trovo numero progressivo
years=os.scandir(archivepath)
for year in years:
    if len(year.name)==4:
        months=os.scandir(year.path)
        for month in months:
            if month.is_dir():
                files=os.scandir(month.path)
                for file in files:
                    if len(file.name.split("-"))==4:
                        c.execute("select Prog_number from Files where File_name=?",(file.name,))
                        pg=c.fetchone()[0]
                        print(pg)