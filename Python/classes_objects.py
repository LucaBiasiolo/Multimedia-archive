#Collezione di funzioni e classi per rinominare i file partendo direttamente dal loro nome.

import sqlite3
import os
from datetime import datetime
import hashlib
archivepath="C:\\Users\\utente\\Desktop\\Luca\\Archivio foto-video"
eofs=["jpg","JPG","jpeg","png","gif","mp4","opus","mpeg","mp3"]
conn=sqlite3.connect("archive.db")
c=conn.cursor()

class new_file:
    def __init__(self,name,path):
        self.name=name
        self.path=path
        self.eof=self.name.split(".")[1]
        self.hash=self._calculatehash()
        
    def _calculatehash(self): #il singolo underscore significa metodo protetto
        f=open(self.path,'rb')
        h=hashlib.sha1() #nuovo oggetto sha-1
        h.update(f.read())
        f.close()
        hash=h.hexdigest()     #hash del file
        return hash
        
    def check_file(self): #metodo pubblico, senza underscore
        okay=True
        if self.eof not in eofs:
            print("Impossibile processare",self.name,"in quanto estensione non valida")
            okay=False
        else:
            c.execute("select File_name from Files where hash=?",(self.hash,)) #cerco possibili doppioni
            doubles=c.fetchone()
            if doubles is not None:
                print(self.name,"doppione di ",doubles[0])
                okay=False
        return okay
        
    #funzione generale per rinominare i file
    def rename(self):
        if len(self.name.split(".")[0])==10:
            [day,month,year]=self.__rename_timestamp()
        elif self.name.startswith("IMG-") or self.name.startswith("VID-"):
            [day,month,year]=self.__rename_IMGVID()
        elif self.name.startswith("WP_"):
            [day,month,year]=self.__rename_WP()
        elif len(self.name.split(".")[0])==15:
            [day,month,year]=self.__rename_annomesegiornoora()
        else:
            [day,month,year]=self.__rename_mdate()
        c.execute("select max(Prog_number) from Files where Day=? and Month=? and Year=?",(day,month,year[2:]))
        maxpn=c.fetchone()[0]
        if maxpn is None:
            number='1'
        else:
            number=str(maxpn+1)
        newname="%s-%s-%s-%s.%s" %(day,month,year[2:],number,self.eof)
        os.rename(self.path,path+"\\"+newname)
        self=archive_file(newname,path+"\\"+newname)
        return self
    
    #rinomino file che hanno nome tipo annomesegiorno_ora
    def __rename_annomesegiornoora(self):
        pieces=self.name.split("_")
        year=pieces[0][0:4]
        month=pieces[0][4:6].lstrip('0')
        day=pieces[0][6:].lstrip('0')
        return [day,month,year]
            
    #rinomino file che hanno nome tipo timestamp
    def __rename_timestamp(self):
        timestamp=int(self.name.split(".")[0])
        date=datetime.fromtimestamp(timestamp) #data ultima modifica file
        day=str(mdate.day)
        month=str(mdate.month)
        year=str(mdate.year)
        return [day,month,year]

    #rinomino file che hanno nome tipo IMG-annomesegiorn-numero
    def __rename_IMGVID(self):
        pieces=self.name.split("-")
        year=pieces[1][0:4]
        month=pieces[1][4:6].lstrip('0')
        day=pieces[1][6:].lstrip('0')
        return [day,month,year]
        
    #rinomino file che hanno nome tipo WP_annomesegiorno_ora_minuti_secondi
    def __rename_WP(self):
        pieces=self.name.split("_")
        year=pieces[1][0:4]
        month=pieces[1][4:6].lstrip('0')
        day=pieces[1][6:].lstrip('0')
        return [day,month,year]

    #rinomino file usando data di ultima modifica del path
    def __rename_mdate(self):
        mtimestamp=os.path.getmtime(self.path)
        mdate=datetime.fromtimestamp(mtimestamp) #data ultima modifica file
        day=mdate.day
        month=mdate.month
        year=str(mdate.year)
        return [day,month,year]

    #funzione generica per spostare i file
    def move(self):
        years=os.listdir(archivepath)
        if self.year in years:
            months=os.listdir(archivepath+"\\"+self.year)
            if self.month in months:
                print(self.name,"in ",archivepath+"\\%s\\%s\\%s" %(self.year,self.month,self.name))
            else:
                print("Creo la cartella del mese ",self.year, self.month)
                os.mkdir(archivepath+"\\%s\\%s" %(self.year,self.month)) #creo cartella mese se questa non esiste
        else:
            print("Creo la cartella anno/mese",self.year, self.month)
            os.mkdir(archivepath+"\\%s\\%s" %(self.year,self.month)) #creo cartella anno/mese se questa non esiste
        os.rename(self.path,archivepath+"\\%s\\%s\\%s" %(self.year,self.month,self.name)) #sposto i nuovi file
        c.execute("insert into Files values (?,?,?,?,?,?,?,?)",(None,self.name,self.day,self.month,self.year,self.pn,self.eof,self.hash))

class archive_file(new_file):
    def __init__(self,name,path):
        super().__init__(name,path)
        self.day=self.name.split("-")[0]
        self.month=self.name.split("-")[1]
        self.year=self.name.split("-")[2]
        finalpiece=self.name.split("-")[3]
        self.pn=finalpiece.split(".")[0]
        
    def rename(self):
        c.execute("select max(Prog_number) from Files where Day=? and Month=? and Year=?",(self.day,self.month,self.year[2:]))
        maxpn=c.fetchone()[0]
        if maxpn is None:
            number=1
        else:
            number=maxpn+1
        newname="%s-%s-%s-%s.%s" %(self.day,self.month,self.year,str(number),self.eof)
        os.rename(self.path,path+"\\"+newname)
        self=archive_file(newname,self.path)
        
