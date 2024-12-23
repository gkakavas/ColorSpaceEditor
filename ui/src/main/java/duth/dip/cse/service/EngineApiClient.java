package duth.dip.cse.service;

import duth.dip.cse.engine.api.Engine;

import javax.swing.ImageIcon;
import java.io.File;
import java.io.IOException;

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

    public ImageIcon loadImage(ImageIcon viewerImage, File file) throws IOException {
        return engine.loadImage(viewerImage, file);
    }

    public ImageIcon capturePhoto(ImageIcon icon, boolean isCaptured){
        return engine.captureImage(icon,isCaptured);
    }

    public ImageIcon clearImage(ImageIcon imageIcon) {
        return engine.clearImage(imageIcon);
    }


    public boolean saveImageOnExistingFile(){
        return engine.saveImage();
    }

    public boolean saveImageOnDeclaredFile(String path){
        return engine.saveImage(path);
    }

    public void undoOperation(){

    }

    public void redoOperation(){

    }


}
