import hashlib
import os

class archive_file:
    def __init__(self,name,parent_path):
        self.name=name
        self.path=parent_path+"\\"+name
        self.day=self.name.split("-")[0]
        self.month=self.name.split("-")[1]
        self.year=self.name.split("-")[2]
        self.pn=self.name.split("-")[3]
        self.eof=self.name.split(".")[1]
        f=open(self.path,'rb')
        h=hashlib.sha1() #nuovo oggetto sha-1
        h.update(f.read())
        f.close()
        self.hash=h.hexdigest() #hash del file
    
class archive:
    def __init__(self,name,root_path):
        self.name=name
        self.root_path=root_path
        #self.years=
    #def rename_files:
        
    #attributi:anni,mesi,lista di file all'interno del mese
    #metodi: rinomina file, aggiorna database
class database:
    def __init__(self,name):
        self.name=name
    #attributi:
    #metodi:
    
    
    
    
    
path=r"C:\Users\utente\Pictures\2020-06-02"#=input("Inserisci path con foto e video: ")
newfile=archive_file("31-5-20-5.jpg",path)
print(type(newfile),newfile.eof,newfile.name)