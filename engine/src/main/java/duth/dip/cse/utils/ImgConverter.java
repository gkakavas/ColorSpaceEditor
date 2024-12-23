package duth.dip.cse.utils;

import org.opencv.core.Mat;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

import static org.opencv.core.CvType.CV_8UC1;

public class ImgConverter {

    public static BufferedImage matrixToBufferedImage(Mat matrix) {

        int bufferSize = matrix.channels() * matrix.cols() * matrix.rows();

        byte[] buffer = new byte[bufferSize];
        matrix.get(0,0, buffer);

        int type = matrix.channels() == 1 ? BufferedImage.TYPE_BYTE_GRAY : BufferedImage.TYPE_3BYTE_BGR;

        BufferedImage image = new BufferedImage(matrix.cols(), matrix.rows(), type);
        final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        System.arraycopy(buffer,0,targetPixels,0,buffer.length);
        return image;
    }

}
