#Collezione di funzioni per rinominare i file partendo direttamente dal loro nome. Da sistemare

import os
from datetime import datetime
            
#rinomino file che hanno nome tipo annomesegiorno_ora
def rename_annomesegiornoora(file):
    pieces=file.name.split("_")
    year=pieces[0][0:4]
    month=pieces[0][4:6]
    day=pieces[0][6:]
    return [day,month,year]
        
#rinomino file che hanno nome tipo timestamp
def rename_timestamp(file):
    timestamp=file.name.split(".")[0]
    date=datetime.fromtimestamp(int(timestamp)) #data ultima modifica file
    day=str(mdate.day)
    month=str(mdate.month)
    year=str(mdate.year)
    return [day,month,year]
        
#rinomino file che hanno nome tipo IMG-annomesegiorn-numero
def rename_IMG(file):
    pieces=file.name.split("-")
    year=pieces[1][0:4]
    month=pieces[1][4:6]
    day=pieces[1][6:]
    return [day,month,year]
    
#rinomino file che hanno nome tipo WP_annomesegiorno_ora_minuti_secondi
def rename_WP(file):
    pieces=file.name.split("_")
    year=pieces[1][0:4]
    month=pieces[1][4:6]
    day=pieces[1][6:]
    return [day,month,year]

#rinomino file usando data di ultima modifica del path
def rename_mdate(file):
    mtimestamp=os.path.getmtime(file.path)
    mdate=datetime.fromtimestamp(mtimestamp) #data ultima modifica file
    day=mdate.day
    month=mdate.month
    year=str(mdate.year)
    return [day,month,year]