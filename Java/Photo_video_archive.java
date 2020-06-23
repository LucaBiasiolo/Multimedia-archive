public class Photo_video_archive{
    String path="C:\\Users\\utente\\Pictures\\2020-06-02";
    String archivepath="C:\\Users\\utente\\Desktop\\Luca\\Archivio foto-video";
    String[] eofs={"jpg","JPG","jpeg","png","gif","mp4","opus","mpeg","mp3"};
    public static boolean check_file(New_file file){
        boolean okay=true;
        return okay;
    }
    // public static Archive_file rename(new_file file){
        
    // }
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
        System.out.println(archive.eofs);
    }
}