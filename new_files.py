#Il seguente script prende i file nuovi da aggiungere da una cartella, elimina quelli con estensioni non adatte e li rinomina opportunamente andando a pescare il numero progressivo giusto dall'archivio

import os
from datetime import datetime
path=r"C:\Users\utente\Pictures\2020-06-02"#=input("Inserisci path con foto e video: ")
archivepath=r"C:\Users\utente\Desktop\Luca\Archivio"
eofs=[".jpg",".JPG",".jpeg",".png",".gif",".mp4",".opus",".mpeg"]
archivedict={}

newfolder=os.scandir(path)
newfiles=[]
for file in newfiles:
    truefalse=list()
    for eof in eofs:
        truefalse.append(file.name.endswith(eof))
    if any(truefalse):
        newfiles.append(file)
    else:
        print("Impossibile processare",file.name,"in quanto estensione non valida")

#trovo numero progressivo
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

#rinomino i file con data di ultima modifica e li sposto   
newfiles=os.scandir(path)             
for file in newfiles:
    if file.name!="Thumbs.db":
        mtimestamp=os.path.getmtime(file.path)
        mdate=datetime.fromtimestamp(mtimestamp) #data ultima modifica file
        day=str(mdate.day)
        month=str(mdate.month)
        year=str(mdate.year)
        eof=file.name.split(".")[1] #estensione file
        try:
            number=archivedict[year][month][day]=archivedict[year][month].get(day,0)+1 #popola il dizionario
        except:
            print("Provo a creare la cartella anno\mese \\%s\\%s" %(year,month))
            os.mkdir(archivepath+"\\%s\\%s" %(year,month)) #creo cartella anno/mese se non esiste
            archivedict[year][month]={}
            number=archivedict[year][month][day]=archivedict[year][month].get(day,0)+1 #popola il dizionario
        newname="%s-%s-%s-%s.%s" %(day,month,year[2:],str(number),eof)
        #print("Rinomino ",file.name,"come",newname,"e lo sposto in ",archivepath+"\\%s\\%s\\%s" %(year,month,newname))
        #os.rename(file.path,archivepath+"\\%s\\%s\\%s" %(year,month,newname)) #sposto i nuovi file