package duth.dip.cse.engine.api;

import duth.dip.cse.engine.domain.ColorModel;
import duth.dip.cse.engine.domain.ImageDataDTO;
import duth.dip.cse.engine.domain.IntensityImageDTO;
import duth.dip.cse.engine.exception.UnsupportedFileFormatException;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Optional;

public interface Engine {
    void start();
    ImageDataDTO captureImage(ImageIcon imageIcon, boolean isCaptured);
    ImageDataDTO loadImage(File file);
    void clearImage();
    void startVideoCapture();
    void stopVideoCapture();
    void exportFiles(String path) throws UnsupportedFileFormatException;
    String createFilePath();
    IntensityImageDTO revertChanges();
    Optional<ImageDataDTO> convertColorSpace(ColorModel colorModel);
    IntensityImageDTO applyColorFactor(int[] values);
}
