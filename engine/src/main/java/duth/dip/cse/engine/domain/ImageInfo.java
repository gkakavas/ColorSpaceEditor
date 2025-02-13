package duth.dip.cse.engine.domain;

import org.opencv.core.Mat;

import java.nio.file.Path;

public class ImageInfo {

    private ImageFileType fileType;
    private ColorModel colorModel;
    private Mat matImageMatrix;
    private String path;

    public ImageInfo() {}
    private ImageInfo(Builder builder){
        fileType = builder.fileType;
        colorModel = builder.colorModel;
        matImageMatrix = builder.matImageMatrix;
        path = builder.filePath;
    }

    public static class Builder{

        private ImageFileType fileType;
        private ColorModel colorModel;
        private Mat matImageMatrix;
        private String filePath;

        public Builder withFileType(ImageFileType fileType){
            this.fileType = fileType;
            return this;
        }

        public Builder withColorModel(ColorModel colorModel){
            this.colorModel = colorModel;
            return this;
        }

        public Builder withMatImageMatrix(Mat matImageMatrix){
            this.matImageMatrix = matImageMatrix;
            return this;
        }

        public Builder withFilePath(String filePath){
            this.filePath = filePath;
            return this;
        }

        public ImageInfo build(){
            return new ImageInfo(this);
        }

    }

    public ImageFileType getFileType() {
        return fileType;
    }

    public ColorModel getColorModel() {
        return colorModel;
    }

    public String getFilePath() {
        return path;
    }

    public void setColorModel(ColorModel colorModel) {
        this.colorModel = colorModel;
    }

    public Mat getMatImageMatrix() {
        return matImageMatrix;
    }

    public void setMatImageMatrix(Mat matImageMatrix) {
        this.matImageMatrix = matImageMatrix;
    }

}
