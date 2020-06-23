public class Photo_video_archive{
    String path="C:\\Users\\utente\\Pictures\\2020-06-02";
    String archivepath="C:\\Users\\utente\\Desktop\\Luca\\Archivio foto-video";
    String[] eofs={"jpg","JPG","jpeg","png","gif","mp4","opus","mpeg","mp3"};
    public static boolean check_file(New_file file){
        boolean okay=true;
    }
    public static Archive_file rename(new_file file){
        String[] dmy;
        if (file.name.split("\\.")[0].length==10){
            dmy=rename_timestamp(file)
        }
        else if (file.name.startsWith("IMG-")){
            dmy=rename_IMG(file)
        }
        else if (file.name.startsWith("WP")){
            dmy=rename_WP(file)
        }
        else{
            dmy=rename_mdate(file)
        }
        //
    }
    // public static String[] rename_annomesegiornoora(new_file file){
        
    // }
    // public static String[] rename_timestamp(new_file file){
        
    // }
    // public static String[] rename_IMG(new_file file){
        
    // }
    // public static String[] rename_WP(new_file file){
        
    // }
    // public static String[] rename_mdate(new_file file){
        
    // }
    // public static void move(Archive_file file){
        
    // }
    public static void main(String[] args){
        Photo_video_archive archive=new Photo_video_archive();
        New_file newfile=new New_file("31-5-20-5.jpg",archive.path+"\\"+"31-5-20-5.jpg");
        System.out.println(newfile.fname);
        System.out.println(newfile.fpath);
        System.out.println(newfile.feof);
    }
}