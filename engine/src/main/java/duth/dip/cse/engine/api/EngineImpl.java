package duth.dip.cse.engine.api;

import duth.dip.cse.ImageFileType;
import duth.dip.cse.engine.core.LoggerConfig;
import duth.dip.cse.utils.ImgConverter;
import nu.pattern.OpenCV;
import org.apache.commons.imaging.Imaging;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;

import javax.swing.ImageIcon;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EngineImpl implements Engine{

    private final Logger logger; // TODO
    private Mat currentMatrix;
    private String matrixFilePath;
    private ImageFileType fileType;
    private VideoCapture videoCapture;



    public EngineImpl(EngineConfig config) throws IOException {
        logger = LoggerConfig.logger(this.getClass(),"/home/george/ColorSpaceEditor/logs/engine.log",Level.ALL);
        //TODO applyConfig()
    }


    @Override
    public void start() {
        logger.info("Starting Color Space Engine...");
        OpenCV.loadLocally();
        logger.info("Native library was loaded successfully");
        currentMatrix = new Mat();
    }

    public void startVideoCapture(){
        videoCapture = new VideoCapture(0);
        logger.info("Video capturing started properly");
    }

    public void stopVideoCapture(){
        if(videoCapture!=null){
            videoCapture.release();
            logger.info("Video capturing stopped properly");
        }
    }

    @Override
    public ImageIcon loadImage(ImageIcon imageIcon, File file) throws IOException {
        var imageBuffer = Files.readAllBytes(file.toPath());
        var imageData = Imaging.getMetadata(imageBuffer);

        fileType = ImageFileType.fromPath(file.getAbsolutePath());
        currentMatrix = Imgcodecs.imread(file.getAbsolutePath());
        if(currentMatrix.empty()){
            logger.warning("Something went wrong during loading of: " + file.getAbsolutePath());
        }
        var bufferedImage = ImgConverter.matrixToBufferedImage(currentMatrix);
        //var imageIcon = new ImageIcon();
        //imageIcon.setImage(bufferedImage);
        //return imageIcon;
        return new ImageIcon(bufferedImage);
    }

    @Override
    public ImageIcon captureImage(ImageIcon imageIcon, boolean isCaptured) {
        if(videoCapture !=null && videoCapture.isOpened()){
            videoCapture.read(currentMatrix);
            var image = ImgConverter.matrixToBufferedImage(currentMatrix);
            imageIcon.setImage(image);
            return imageIcon;
        }
        else throw new RuntimeException(); //TODO ClosedCameraStream;
    }


    @Override
    public ImageIcon clearImage(ImageIcon imageIcon) {
        currentMatrix = null;
        matrixFilePath = null;
        fileType = null;
        imageIcon.setImage(null);
        return imageIcon;
    }

    @Override
    public boolean saveImage() {
        if(matrixFilePath != null){
            return Imgcodecs.imwrite(matrixFilePath,currentMatrix);
        }
        return false;
    }

    @Override
    public boolean saveImage(String path){
        return Imgcodecs.imwrite(path,currentMatrix);
    }

    @Override
    public void stop() {

    }
}
