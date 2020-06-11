#Collezione di funzioni per rinominare i file partendo direttamente dal loro nome. Da sistemare

import os
from datetime import datetime
path=r"C:\Users\utente\Pictures\2020-06-02"#=input("Inserisci path con foto e video: ")
archivepath=r"C:\Users\utente\Desktop\Luca\Archivio"

files=os.scandir(path)

#popolo dizionario per numeri progressivi
years=os.scandir(archivepath)
for year in years:
    if len(year.name)==4:
        archivedict[year.name]={}
        months=os.scandir(year.path)
        for month in months:
            if month.is_dir():
                archivedict[year.name][month.name]={} #dizionario con giorni e num max raggiunto
                files=os.scandir(month.path)
                for file in files:
                    if len(file.name.split("-"))==4:
                        day=file.name.split("-")[0]
                        archivedict[year.name][month.name][day]=archivedict[year.name][month.name].get(day,0)+1 #popola il dizionario
                        
#rinomino file che hanno nome tipo annomesegiorno_ora
pieces=file.name.split("_")
year=pieces[0][0:4]
month=pieces[0][4:6]
day=pieces[0][6:]
eof=file.name.split(".")[1]
number=archivedict[year][month][day]=archivedict[year][month].get(day,0)+1 #popola il dizionario
newname="%s-%s-%s-%s.%s" %(day,month,year[2:],number,eof)
print("Rinomino", file.name, "come",newname)
#os.rename(file.path,path+"\\"+newname)
        
#rinomino file che hanno nome tipo timestamp
if len(file.name)==17:
    timestamp=file.name.split(".")[0]
    date=datetime.fromtimestamp(timestamp) #data ultima modifica file
    day=str(mdate.day)
    month=str(mdate.month)
    year=str(mdate.year)
    eof=file.name.split(".")[1]
    number=archivedict[year][month][day]=archivedict[year][month].get(day,0)+1 #aggiorna il dizionario
    newname="%s-%s-%s-%s.%s" %(day,month,year[2:],number,eof)
    print("Rinomino", file.name, "come",newname)
    #os.rename(file.path,path+"\\"+newname)
        
#rinomino file che hanno nome tipo IMG-annomesegiorn-numero
if file.name.startswith("IMG"):
    pieces=file.name.split("-")
    year=pieces[1][0:4]
    month=pieces[1][4:6]
    day=pieces[1][6:]
    eof=file.name.split(".")[1]
    number=archivedict[year][month][day]=archivedict[year][month].get(day,0)+1 #aggiorna il dizionario
    newname="%s-%s-%s-%s.%s" %(day,month,year[2:],number,eof)
    print("Rinomino", file.name, "come",newname)
    #os.rename(file.path,path+"\\"+newname)
    
#rinomino file che hanno nome tipo WP_annomesegiorno_ora_minuti_secondi
if file.name.startswith("WP"):
    pieces=file.name.split("_")
    year=pieces[1][0:4]
    month=pieces[1][4:6]
    day=pieces[1][6:]
    eof=file.name.split(".")[1]
    number=archivedict[year][month][day]=archivedict[year][month].get(day,0)+1 #aggiorna il dizionario
    newname="%s-%s-%s-%s.%s" %(day,month,year[2:],number,eof)
    print("Rinomino", file.name, "come",newname)
    #os.rename(file.path,path+"\\"+newname)