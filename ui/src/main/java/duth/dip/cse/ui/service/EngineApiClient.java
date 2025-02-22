package duth.dip.cse.ui.service;

import duth.dip.cse.engine.domain.ColorModel;
import duth.dip.cse.engine.api.Engine;
import duth.dip.cse.engine.domain.ImageDataDTO;
import duth.dip.cse.engine.domain.IntensityImageDTO;
import duth.dip.cse.engine.exception.UnsupportedFileFormatException;

import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Optional;

public class EngineApiClient {

    private final Engine engine;

    public EngineApiClient(Engine engine){
        this.engine = engine;
        engine.start();
    }

    public void startCapturing(){
        engine.startVideoCapture();
    }

    public void stopVideoCapturing(){
        engine.stopVideoCapture();
    }

    public ImageDataDTO loadImage(File file) {
        return engine.loadImage(file);
    }

    public ImageDataDTO capturePhoto(ImageIcon icon, boolean isCaptured){
        return engine.captureImage(icon,isCaptured);
    }

    public void clearImage() {
        engine.clearImage();
    }

    public void saveImage(String pathToSave) throws UnsupportedFileFormatException { engine.exportFiles(pathToSave); }

    public String createFilePath(){
        return engine.createFilePath();
    }

    public Optional<ImageDataDTO> convertColorSpace(ColorModel colorModel){ return engine.convertColorSpace(colorModel); }

    public IntensityImageDTO applyColorFactor(int[] values){ return engine.applyColorFactor(values); }

    public IntensityImageDTO revertChanges(){
        return engine.revertChanges();
    }
}
