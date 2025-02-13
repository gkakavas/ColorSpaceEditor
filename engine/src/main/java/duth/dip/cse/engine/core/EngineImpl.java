package duth.dip.cse.engine.core;

import duth.dip.cse.engine.domain.ColorModel;
import duth.dip.cse.engine.api.Engine;
import duth.dip.cse.engine.domain.ImageDataDTO;
import duth.dip.cse.engine.domain.ImageFileType;
import duth.dip.cse.engine.domain.ImageInfo;
import duth.dip.cse.engine.config.LoggerConfig;
import duth.dip.cse.engine.exception.UnsupportedFileFormatException;
import nu.pattern.OpenCV;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;

import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;


public class EngineImpl implements Engine {


    private final String logFilePath = "engine.log";
    private final Lock lock;
    private final Logger logger; // TODO
    private Mat viewableMatrix;
    private ImageInfo imageInfo;
    private VideoCapture videoCapture;


    public EngineImpl() throws IOException {
        this.logger = LoggerConfig.logger(this.getClass(),logFilePath,Level.ALL);
        imageInfo = new ImageInfo();
        lock = new ReentrantLock();
    }

    @Override
    public void start() {
        logger.info("Engine starting...");
        OpenCV.loadLocally();
        viewableMatrix = new Mat();
        logger.info("Engine started successfully");
    }


    @Override
    public void startVideoCapture(){
        lock.lock();
        try{
            videoCapture = new VideoCapture(0);
            logger.info("Video capturing started properly");
        }finally {
            lock.unlock();
        }

    }

    @Override
    public void stopVideoCapture(){
        lock.lock();
        try {
            if(videoCapture!=null){
                videoCapture.release();
                logger.info("Video capturing stopped properly");
            }
        }finally {
            lock.unlock();
        }
    }

    @Override
    public ImageDataDTO loadImage(File file) {
        lock.lock();
        try{
            var filePathBuilder = new StringBuilder(file.getAbsolutePath());
            var colorModel = ColorSpaceFileEditor.identifyColorSpace(filePathBuilder);
            imageInfo = new ImageInfo.Builder()
                    .withFileType(ImageFileType.fromPath(file.getAbsolutePath()))
                    .withColorModel(colorModel.orElse(ColorModel.sRGB))
                    .withMatImageMatrix(Imgcodecs.imread(file.getAbsolutePath()))
                    .withFilePath(filePathBuilder.toString())
                    .build();
            logger.info("Image loaded successfully");
            viewableMatrix = imageInfo.getColorModel() != ColorModel.sRGB
                    ? ImgConverter.convertToBGR(imageInfo.getColorModel(), imageInfo.getMatImageMatrix())
                    : imageInfo.getMatImageMatrix();
            var bufferedImage = ImgConverter.matrixToBufferedImage(viewableMatrix);
            return new ImageDataDTO(imageInfo.getColorModel(),bufferedImage);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void captureImage(ImageIcon imageIcon, boolean isCaptured) {
        lock.lock();
        try{
            if(videoCapture !=null && videoCapture.isOpened()){
                videoCapture.read(viewableMatrix);
                var image = ImgConverter.matrixToBufferedImage(viewableMatrix);
                imageIcon.setImage(image);
                if(isCaptured){
                    imageInfo.setMatImageMatrix(viewableMatrix.clone());
                    imageInfo.setColorModel(ColorModel.sRGB);
                    logger.info("Image captured successfully!");
                }
            }
        } finally {
            lock.unlock();
        }
    }


    @Override
    public void clearImage() {
        lock.lock();
        try{
            imageInfo = new ImageInfo();
            viewableMatrix = new Mat();
            logger.info("Clear image successfully");
        } finally {
            lock.unlock();
        }
    }

    @Override
    public String saveImage(String pathToSave) throws UnsupportedFileFormatException {
        lock.lock();
        try {
            if (imageInfo != null) {
                //var pathWithExtension = ColorSpaceFileEditor.putColorSpaceStamp(imageInfo.getColorModel(),pathToSave);
                var hasBeenSaved = Imgcodecs.imwrite(pathToSave, imageInfo.getMatImageMatrix());
                if (!hasBeenSaved) {
                    throw new UnsupportedFileFormatException("Failed to save file of type: ", imageInfo.getFileType());
                }
                logger.info("Image saved successfully");
                return pathToSave;
            }
        } finally {
            lock.unlock();
        }
        return null;
    }

    @Override
    public String createFilePath(){
        lock.lock();
        try{
            return ColorSpaceFileEditor.putColorSpaceStamp(imageInfo.getColorModel(),imageInfo.getFilePath());
        }finally {
            lock.unlock();
        }
    }

    @Override
    public Optional<ImageDataDTO> convertColorSpace(ColorModel colorModel){
        lock.lock();
        try{
            var isColorModelChanged = imageInfo.getColorModel() != colorModel;
            if(isColorModelChanged){
                viewableMatrix = ImgConverter.convertToBGR(imageInfo.getColorModel(), imageInfo.getMatImageMatrix());
                var image = ImgConverter.matrixToBufferedImage(viewableMatrix);
                logger.info("Color space converted successfully");
                var convertedMatrix = ImgConverter.convertBgrToColorModel(colorModel,viewableMatrix);
                imageInfo.setMatImageMatrix(convertedMatrix);
                imageInfo.setColorModel(colorModel);
                return Optional.of(new ImageDataDTO(colorModel,image));
            }
            return Optional.empty();
        } finally {
           lock.unlock();
        }
    }

    @Override
    public BufferedImage applyColorFactor(int[] values){
        ColorConverter.applyColorFactor(values, imageInfo.getMatImageMatrix());
        if(imageInfo.getColorModel() != ColorModel.sRGB){
            viewableMatrix = ImgConverter.convertToBGR(imageInfo.getColorModel(), imageInfo.getMatImageMatrix());
        }
        viewableMatrix = imageInfo.getMatImageMatrix();
        return ImgConverter.matrixToBufferedImage(viewableMatrix);
    }

}