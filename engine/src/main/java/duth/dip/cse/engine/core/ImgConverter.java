package duth.dip.cse.engine.core;

import duth.dip.cse.engine.domain.ColorModel;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

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

    public static Mat convertToBGR(ColorModel sourceColorModel, Mat matrixToConvert){

        var convertedMatrix = new Mat();
        int converterCode = switch (sourceColorModel) {
            case GRAYSCALE -> Imgproc.COLOR_GRAY2BGR;
            case RGBA -> Imgproc.COLOR_RGBA2BGR;
            case HSV -> Imgproc.COLOR_HSV2BGR;
            case LAB -> Imgproc.COLOR_Lab2BGR;
            case YCrCb -> Imgproc.COLOR_YCrCb2BGR;
            case LUV -> Imgproc.COLOR_Luv2BGR;
            case sRGB -> 22197;
        };

        if(converterCode == 22197)
            return matrixToConvert;

        Imgproc.cvtColor(matrixToConvert,convertedMatrix,converterCode);
        return convertedMatrix;
    }

    public static Mat convertBgrToColorModel(ColorModel destinationColorModel, Mat matrixToConvert){

        var convertedMatrix = new Mat();
        int converterCode = switch (destinationColorModel) {
            case GRAYSCALE -> Imgproc.COLOR_BGR2GRAY;
            case RGBA -> Imgproc.COLOR_BGR2RGBA;
            case HSV -> Imgproc.COLOR_BGR2HSV;
            case LAB -> Imgproc.COLOR_BGR2Lab;
            case YCrCb -> Imgproc.COLOR_BGR2YCrCb;
            case LUV -> Imgproc.COLOR_BGR2Luv;
            default -> 22198;
        };

        if(converterCode == 22198){
            throw new RuntimeException("Unsupported Color Model");
        }
        Imgproc.cvtColor(matrixToConvert,convertedMatrix,converterCode);
        return convertedMatrix;
    }



}
