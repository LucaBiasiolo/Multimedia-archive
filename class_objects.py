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
        self.pn=self.name.split("-")[3]
        self.eof=self.name.split(".")[1]
        f=open(self.path,'rb')
        h=hashlib.sha1() #nuovo oggetto sha-1
        h.update(f.read())
        f.close()
        self.hash=h.hexdigest()     #hash del file
    
# class archive:
    # def __init__(self,root_path):
        # self.root_path=root_path
        # self.years=[]
        # self.months=[]
        # self.files=[]
        # years=os.scandir(root_path)
        # for year in years:
            # if len(year.name)==4:
                # self.years.append(year.name)
                # months=os.scandir(year.path)
                # for month in months:
                    # if month.is_dir():
                        # self.months.append(month.name)
                        # files=os.scandir(month.path)
                        # for file in files:
                            # if len(file.name.split("-"))==4:
                                # self.files.append(file.name)
        #def rename_files:
        #def update_pn:
        #def access_archive:
        #attributi:anni,mesi,lista di file all'interno del mese
        #metodi: rinomina file, aggiorna database
# class database:
    # def __init__(self,name):
        # self.name=name
    #attributi:
    #def populate_database
    #def update_database
