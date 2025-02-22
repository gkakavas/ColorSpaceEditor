package duth.dip.cse.engine.core;

import duth.dip.cse.engine.domain.*;
import duth.dip.cse.engine.api.Engine;
import duth.dip.cse.engine.config.LoggerConfig;
import duth.dip.cse.engine.exception.UnsupportedFileFormatException;
import duth.dip.cse.engine.utils.FileNameGenerator;
import duth.dip.cse.engine.utils.SystemProperties;
import nu.pattern.OpenCV;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;

import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;


public class EngineImpl implements Engine {


    private final String logFilePath = "engine.log";
    private final Lock lock;
    private final Logger logger; // TODO
    private final ContextHolder contextHolder;
    private VideoCapture videoCapture;
    private Mat capturingMatrix;


    public EngineImpl() throws IOException {
        this.logger = LoggerConfig.logger(this.getClass(),logFilePath,Level.ALL);
        contextHolder = new ContextHolder(10);
        lock = new ReentrantLock();
    }

    @Override
    public void start() {
        logger.info("Engine starting...");
        OpenCV.loadLocally();
        capturingMatrix = new Mat();
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
            var imageInfo = new ImageInfo.Builder()
                    .withFileType(ImageFileType.fromPath(file.getAbsolutePath()))
                    .withColorModel(colorModel.orElse(ColorModel.sRGB))
                    .withMatImageMatrix(Imgcodecs.imread(file.getAbsolutePath()))
                    .withFilePath(filePathBuilder.toString())
                    .withIntensityValues(new int[]{0,0,0,0})
                    .build();
            contextHolder.clear();
            contextHolder.add(imageInfo);
            logger.info("Image loaded successfully");

            var optionalImageInfo = contextHolder.getCurrent().orElseThrow(() -> new RuntimeException("Error loading image info"));
            var bufferedImage =  ImgConverter.matrixToBufferedImage(optionalImageInfo.getMatImageMatrix());
            return new ImageDataDTO(optionalImageInfo.getColorModel(),bufferedImage);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public ImageDataDTO captureImage(ImageIcon imageIcon, boolean isCaptured) {
        lock.lock();
        try{
            if(videoCapture !=null && videoCapture.isOpened()){
                videoCapture.read(capturingMatrix);
                var image = ImgConverter.matrixToBufferedImage(capturingMatrix);
                imageIcon.setImage(image);
                if(isCaptured){
                    contextHolder.clear();
                    contextHolder.add(
                            new ImageInfo.Builder()
                                    .withColorModel(ColorModel.sRGB)
                                    .withFilePath(SystemProperties.HOME_DIR + FileNameGenerator.generateFilename() + ".png")
                                    .withIntensityValues(new int[]{0,0,0,0})
                                    .withMatImageMatrix(capturingMatrix)
                                    .build()
                    );
                    logger.info("Image captured successfully!");
                    var colorModel = contextHolder.getCurrent().orElseThrow().getColorModel();
                    var mat = contextHolder.getCurrent().orElseThrow().getMatImageMatrix();
                    var bufferedImg = ImgConverter.matrixToBufferedImage(mat);
                    return new ImageDataDTO(colorModel,bufferedImg);
                }
            }
        } finally {
            lock.unlock();
        }
        return null;
    }


    @Override
    public void clearImage() {
        lock.lock();
        try{
            contextHolder.clear();
            logger.info("Clear image successfully");
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void exportFiles(String pathToSave) {
        lock.lock();
        try {
            if(contextHolder.getCurrent().isPresent()){
                var imageInfo = contextHolder.getCurrent().get();
                var hasBeenSaved = Imgcodecs.imwrite(pathToSave, imageInfo.getMatImageMatrix());
                var channels = ColorConverter.getChannels(imageInfo.getMatImageMatrix(),imageInfo.getColorModel());
                for(Map.Entry<String,Mat> entry: channels.entrySet()){
                    var path = ColorSpaceFileEditor.createChannelPath(entry.getKey(),pathToSave);
                    hasBeenSaved = Imgcodecs.imwrite(path, entry.getValue());
                    entry.getValue().release();
                }
                if(hasBeenSaved){
                    logger.info("Image saved successfully");
                }
            }
        } finally {
            lock.unlock();
        }
    }


    @Override
    public String createFilePath(){
        lock.lock();
        try{
            var imageInfo = contextHolder.getCurrent().orElseThrow(() -> new RuntimeException("Error loading image info"));
            return ColorSpaceFileEditor.putColorSpaceStamp(imageInfo.getColorModel(),imageInfo.getFilePath());
        }finally {
            lock.unlock();
        }
    }

    @Override
    public IntensityImageDTO revertChanges() {
        lock.lock();
        try{
            var imageInfo = contextHolder.getPrevious().orElse(contextHolder.getCurrent().orElseThrow());
            var bufferedImage = ImgConverter.matrixToBufferedImage(imageInfo.getMatImageMatrix());
            return new IntensityImageDTO(imageInfo.getIntensityValues(),bufferedImage);
        }finally {
            lock.unlock();
        }
    }

    @Override
    public Optional<ImageDataDTO> convertColorSpace(ColorModel colorModel) {
        lock.lock();
        var imageInfo = contextHolder.getCurrent().orElseThrow(() -> new RuntimeException("Error converting color space"));
        if (imageInfo != null) {
            try {
                var isColorModelChanged = imageInfo.getColorModel() != colorModel;
                if (isColorModelChanged) {
                    var rgbMatrix = ImgConverter.convertToBGR(imageInfo.getColorModel(), imageInfo.getMatImageMatrix());
                    var convertedMatrix = ImgConverter.convertBgrToColorModel(colorModel, rgbMatrix);
                    imageInfo.setColorModel(colorModel);
                    imageInfo.setMatImageMatrix(convertedMatrix);
                    logger.info("Color space converted successfully");
                    var image = ImgConverter.matrixToBufferedImage(imageInfo.getMatImageMatrix());
                    return Optional.of(new ImageDataDTO(colorModel, image));
                }
                return Optional.empty();
            } finally {
                lock.unlock();
            }
        }
        return Optional.empty();
    }

    @Override
    public IntensityImageDTO applyColorFactor(int[] values){
        var imageInfo = contextHolder.getCurrent().orElseThrow(() -> new RuntimeException("Error loading image info"));
        var clonedMatrix = imageInfo.getMatImageMatrix().clone();
        ColorConverter.applyColorFactor(values, clonedMatrix);
        var convertedImageInfo = new ImageInfo.Builder()
                .withColorModel(imageInfo.getColorModel())
                .withMatImageMatrix(clonedMatrix)
                .withFilePath(imageInfo.getFilePath())
                .withFileType(imageInfo.getFileType())
                .withIntensityValues(values)
                .build();
        contextHolder.add(convertedImageInfo);
        if(imageInfo.getColorModel() != ColorModel.sRGB){
            var convertedMat = ImgConverter.convertToBGR(imageInfo.getColorModel(), imageInfo.getMatImageMatrix());
            imageInfo.setMatImageMatrix(convertedMat);
        }
        var image = ImgConverter.matrixToBufferedImage(contextHolder.getCurrent().orElseThrow().getMatImageMatrix());
        return new IntensityImageDTO(values,image);
    }


}