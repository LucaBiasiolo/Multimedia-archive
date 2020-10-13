public class ArchiveFile extends NewFile{

    private String name;
    private String day;
    private String month;
    private String year;
    private String progressiveNumber;

    public ArchiveFile(String name, String path) {
        super(name, path);
        this.day = this.name.split("-")[0];
        this.month = this.name.split("-")[1];
        this.year = this.name.split("-")[2];
        String finalPiece = this.name.split("-")[3];
        this.progressiveNumber = finalPiece.split(".")[0];
    }

    public void rename(){
        //
    }
}
