package duth.dip.cse.engine.domain;

import org.opencv.core.Mat;

import java.nio.file.Path;

public class ImageInfo {

    private ImageFileType fileType;
    private ColorModel colorModel;
    private Mat matImageMatrix;
    private String path;
    private int[] intensityValues;

    public ImageInfo() {}
    private ImageInfo(Builder builder){
        fileType = builder.fileType;
        colorModel = builder.colorModel;
        matImageMatrix = builder.matImageMatrix;
        path = builder.filePath;
        intensityValues = builder.intensityValues;
    }

    public static class Builder{

        private ImageFileType fileType;
        private ColorModel colorModel;
        private Mat matImageMatrix;
        private String filePath;
        private int[] intensityValues;

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

        public Builder withIntensityValues(int[] intensityValues){
            this.intensityValues = new int[4];
            System.arraycopy(intensityValues, 0, this.intensityValues, 0, intensityValues.length);
            switch (intensityValues.length){
                case 1:
                    this.intensityValues[1] = -1;
                    this.intensityValues[2] = -1;
                    this.intensityValues[3] = -1;
                    break;
                case 3:
                    this.intensityValues[3] = -1;
                    break;
            }
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

    public int[] getIntensityValues() {
        return intensityValues;
    }

    public void setIntensityValues(int[] intensityValues) {
        this.intensityValues = intensityValues;
    }

    public void releaseMat(){
        matImageMatrix.release();
    }

}
