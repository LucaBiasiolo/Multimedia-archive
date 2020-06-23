import hashlib
import os

class new_file:
    def __init__(self,name,path):
        self.name=name
        self.path=path
        self.eof=self.name.split(".")[1]
        f=open(self.path,'rb')
        h=hashlib.sha1() #nuovo oggetto sha-1
        h.update(f.read())
        f.close()
        self.hash=h.hexdigest()     #hash del file

class archive_file:
    def __init__(self,name,path):
        self.name=name
        self.path=path
        self.day=self.name.split("-")[0]
        self.month=self.name.split("-")[1]
        self.year=self.name.split("-")[2]
        finalpiece=self.name.split("-")[3]
        self.pn=finalpiece.split(".")[0]
        self.eof=self.name.split(".")[1]
        f=open(self.path,'rb')
        h=hashlib.sha1() #nuovo oggetto sha-1
        h.update(f.read())
        f.close()
        self.hash=h.hexdigest()     #hash del file