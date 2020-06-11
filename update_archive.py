#Il seguente script aggiorna correttamente e dinamicamente i numeri delle foto quando queste vengono cancellate dall'archivio

import os
from datetime import datetime
path=r"C:\Users\utente\Pictures\2020-06-02"#=input("Inserisci path con foto e video: ")
archivepath=r"C:\Users\utente\Desktop\Luca\Archivio"
archivedict={}

years=os.scandir(archivepath)
for year in years:
    if len(year.name)==4:
        archivedict[year.name]={}
        months=os.scandir(year.path)
        for month in months:
            if month.is_dir():
                archivedict[year.name][month.name]={} #dizionario con giorni e num max raggiunto
                files=os.listdir(month.path)
                filelist=[]
                for file in files:
                    if len(file.split("-"))==4:
                        day=int(file.split("-")[0])
                        piece=file.split("-")[3]
                        number=int(piece.split(".")[0])
                        eof=file.split(".")[1]
                        filelist.append((day,number,eof))
                filelist.sort()
                for file in filelist:
                    day=file[0]
                    eof=file[2]
                    number=archivedict[year.name][month.name][day]=archivedict[year.name][month.name].get(day,0)+1 #popola il dizionario
                    oldname="%s-%s-%s-%s.%s" %(day,month.name,year.name[2:],file[1],eof)
                    newname="%s-%s-%s-%s.%s" %(day,month.name,year.name[2:],number,eof)
                    print(oldname,newname)
                    #os.rename(month.path+"\\"+oldname,month.path+"\\"+newname)