package duth.dip.cse.engine.core;

import duth.dip.cse.engine.domain.ColorModel;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;

import java.util.ArrayList;
import java.util.List;

public class ColorConverter {

    public static void applyColorFactor( int[] channelValues, Mat matrix) {
            applyFactorToRGB(matrix,channelValues[0],channelValues[1],channelValues[2]);
    }


    public static void applyFactorToRGB(Mat matrix, int rFactor, int gFactor, int bFactor) {

        var r = 1.0 + (rFactor / 100.0);
        var b = 1.0 + (gFactor / 100.0);
        var g = 1.0 + (bFactor / 100.0);

        List<Mat> channels = new ArrayList<>();
        Core.split(matrix, channels);

        // Ensure factors are applied correctly
        Core.multiply(channels.get(0), new Scalar(b), channels.get(0)); // Blue
        Core.multiply(channels.get(1), new Scalar(g), channels.get(1)); // Green
        Core.multiply(channels.get(2), new Scalar(r), channels.get(2)); // Red

        // Merge the modified channels back
        Core.merge(channels, matrix);
    }

}