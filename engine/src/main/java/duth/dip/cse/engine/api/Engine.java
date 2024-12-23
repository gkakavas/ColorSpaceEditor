package duth.dip.cse.engine.api;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public interface Engine {
    void start();
    ImageIcon captureImage(ImageIcon imageIcon, boolean isCaptured);
    ImageIcon loadImage(ImageIcon iconToPopulate, File file) throws IOException;
    ImageIcon clearImage(ImageIcon imageIcon);
    void startVideoCapture();
    void stopVideoCapture();
    public boolean saveImage();
    public boolean saveImage(String path);
    void stop();
}
