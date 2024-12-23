package duth.dip.cse;

import duth.dip.cse.engine.api.EngineConfig;
import duth.dip.cse.engine.api.EngineImpl;
import org.apache.commons.imaging.Imaging;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class FileTypeTest {

    static String[] paths;

    @BeforeAll
    public static void init(){
        paths = new String[]{
                "test_path/resources/my_resource.png",
                "test_path/resources/my_resource.tiff",
                "test_path/resources/my_resource.jpg",
                "test_path/resources/my_resource.jpeg",
                "test_path/resources/my_resource.webp"
        };
    }

    @Test
    public void testFileType(){
        for(String path : paths){
            var fileType = ImageFileType.fromPath(path);
            assertInstanceOf(ImageFileType.class,fileType);
        }
    }

    @Test
    public void testMatByBuffer() throws IOException {
        var path = "/home/george/Pictures/demo_image (Copy).tiff";
        var imageEncodedBuffer = Files.readAllBytes(Path.of(path));
        new EngineImpl(new EngineConfig()).start();
        var mat = Imgcodecs.imdecode(new MatOfByte(imageEncodedBuffer), Imgcodecs.IMREAD_UNCHANGED);
        var labMat = new Mat();
        Imgproc.cvtColor(mat, labMat, Imgproc.COLOR_BGR2HSV);
        var written = Imgcodecs.imwrite(path,labMat);

        var file = Files.readAllBytes(Path.of(path));
        Imaging.getImageInfo(file).getColorType();
    }
}
