package duth.dip.cse.domain;

import duth.dip.cse.ColorModel;
import duth.dip.cse.ImageFileType;
import java.nio.file.Path;

public class ImageMetaData {

    private Path path;
    private ImageFileType fileType;
    private ColorModel colorModel;
    private long size;

    private ImageMetaData(Builder builder){
        this.path = builder.path;
        this.fileType = builder.fileType;
        this.colorModel = builder.colorModel;
        this.size = builder.size;
    }

    public static class Builder {

        private Path path;
        private ImageFileType fileType;
        private ColorModel colorModel;
        private long size;

        public Builder withPath(Path path){
            this.path = path;
            return this;
        }

        public Builder withFileType(ImageFileType fileType){
            this.fileType = fileType;
            return this;
        }

        public Builder withColorModel(ColorModel colorModel){
            this.colorModel = colorModel;
            return this;
        }

        public Builder withSize(long size){
            this.size = size;
            return this;
        }

        public ImageMetaData build(){
            return new ImageMetaData(this);
        }
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public ImageFileType getFileType() {
        return fileType;
    }

    public void setFileType(ImageFileType fileType) {
        this.fileType = fileType;
    }

    public ColorModel getColorModel() {
        return colorModel;
    }

    public void setColorModel(ColorModel colorModel) {
        this.colorModel = colorModel;
    }

    public long getSize(){
        return size;
    }

    public void setSize(long size){
        this.size = size;
    }

}
