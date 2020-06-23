public class Archive_file{
    String name,path,day,month,year,pn,eof,hash;
    public Archive_file(String name, String path){
        name=name;
        path=path;
        day=name.split("-")[0];
        month=name.split("-")[1];
        year=name.split("-")[2];
        String finalpiece=name.split("-")[3];
        pn=finalpiece.split(".")[0];
        eof=name.split(".")[1];
        //hash
    }
}