package duth.dip.cse.engine.exception;

import duth.dip.cse.engine.domain.ImageFileType;

public class UnsupportedFileFormatException extends Exception {
    public UnsupportedFileFormatException(String message, ImageFileType imageFileType){
        super(message + " File type: " + imageFileType.name());
    }
}
