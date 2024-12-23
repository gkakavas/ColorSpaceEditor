package duth.dip.cse;

import java.util.Set;

public enum ImageFileType {

    PNG("png", Set.of(ColorModel.GRAYSCALE,ColorModel.sRGB,ColorModel.RGBA)),
    JPEG("jpeg", Set.of(ColorModel.GRAYSCALE,ColorModel.sRGB,ColorModel.YCbCr)),
    JPG("jpg", Set.of(ColorModel.GRAYSCALE,ColorModel.sRGB,ColorModel.YCbCr)),
    WEBP("webp", Set.of(ColorModel.sRGB,ColorModel.RGBA,ColorModel.YCbCr)),
    TIFF("tiff", Set.of(ColorModel.GRAYSCALE,ColorModel.sRGB,ColorModel.RGBA,
            ColorModel.CMYK,ColorModel.YCbCr,ColorModel.LAB)),
    BMP("bmp",Set.of(ColorModel.GRAYSCALE,ColorModel.sRGB));

    public final String extension;

    ImageFileType(String extension, Set<ColorModel> supportedColorModels){
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
        throw new RuntimeException(); //TODO UnsupportedImageContainerException();
    }

}
