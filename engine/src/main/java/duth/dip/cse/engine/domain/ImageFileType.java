package duth.dip.cse.engine.domain;

public enum ImageFileType {

    PNG("png"),
    JPEG("jpeg"),
    JPG("jpg"),
    WEBP("webp"),
    TIFF("tiff"),
    BMP("bmp");

    public final String extension;

    ImageFileType(String extension){
        this.extension = extension;
    }

    public String extension(){
        return extension;
    }

    public static ImageFileType fromPath(String path){

        var dotIndex = path.lastIndexOf('.');
        if(dotIndex == -1) {
            throw new RuntimeException();//TODO UndefinedFileFormatException();
        }

        var extension = path.substring(dotIndex + 1);
        for(ImageFileType type : ImageFileType.values()){
            if(type.extension().equals(extension)) {
                return type;
            }
        }
        throw new RuntimeException();
    }

}
