package duth.dip.cse.engine.core;

import duth.dip.cse.ColorModel;
import duth.dip.cse.ImageFileType;
import org.apache.commons.imaging.Imaging;
import java.io.IOException;

public class ImageFileInfoExtractor {

    public static ImageFileType extractOriginalFileType(byte[] imageBuffer) throws IOException {
        var imageInfo = Imaging.getImageInfo(imageBuffer);
        var formatExtensions = imageInfo.getFormat().getExtensions();
        for(String extension : formatExtensions){
            for(ImageFileType fileType : ImageFileType.values()){
                if(fileType.extension().equals(extension))
                    return fileType;
            }
        }
        throw new RuntimeException(); //unsupportedFileException
    }

    public static ColorModel extractColorModel(byte[] imageBuffer) throws IOException {

        var imageInfo = Imaging.getImageInfo(imageBuffer);
        var colorSpace = imageInfo.getColorType();

        switch(colorSpace){
            case GRAYSCALE: return ColorModel.GRAYSCALE;
            case RGB: return imageInfo.isTransparent() ? ColorModel.RGBA : ColorModel.sRGB;
            case YCbCr: return ColorModel.YCbCr;
            case CMYK: return ColorModel.CMYK;
            //case :

        }
        return null;
    }
}
