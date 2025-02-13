package duth.dip.cse.engine.api;

import duth.dip.cse.engine.domain.ColorModel;
import duth.dip.cse.engine.domain.ImageDataDTO;
import duth.dip.cse.engine.exception.UnsupportedFileFormatException;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Map;
import java.util.Optional;

public interface Engine {
    void start();
    void captureImage(ImageIcon imageIcon, boolean isCaptured);
    ImageDataDTO loadImage(File file);
    void clearImage();
    void startVideoCapture();
    void stopVideoCapture();
    String saveImage(String path) throws UnsupportedFileFormatException;
    String createFilePath();
    Optional<ImageDataDTO> convertColorSpace(ColorModel colorModel);
    BufferedImage applyColorFactor(int[] values);
}
