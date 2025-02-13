package duth.dip.cse.engine.core;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;

public class ImageAnalyzer {

    public static double[] analyzeChannelsFactors(Mat matrix){
        Scalar meanValue = Core.mean(matrix);

        double meanBlue = meanValue.val[0];
        double meanGreen = meanValue.val[1];
        double meanRed = meanValue.val[2];

        return new double[]{meanBlue, meanGreen, meanRed};
    }
}
