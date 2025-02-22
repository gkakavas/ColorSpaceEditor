package duth.dip.cse.engine.core;

import duth.dip.cse.engine.domain.ColorModel;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ColorConverter {

    public static void applyColorFactor( int[] channelValues, Mat matrix) {
            applyFactorToRGB(matrix,channelValues[0],channelValues[1],channelValues[2]);
    }


    public static void applyFactorToRGB(Mat matrix, int rFactor, int gFactor, int bFactor) {

        var r = 1.0 + (rFactor / 100.0);
        var g = 1.0 + (gFactor / 100.0);
        var b = 1.0 + (bFactor / 100.0);

        List<Mat> channels = new ArrayList<>();
        Core.split(matrix, channels);

        Core.multiply(channels.get(0), new Scalar(b), channels.get(0));
        Core.multiply(channels.get(1), new Scalar(g), channels.get(1));
        Core.multiply(channels.get(2), new Scalar(r), channels.get(2));

        Core.merge(channels, matrix);
        channels.forEach(Mat::release);

    }


    public static Map<String,Mat> getChannels(Mat matrix, ColorModel colorModel){
        List<Mat> channels = new ArrayList<>();
        Core.split(matrix, channels);
        Map<String,Mat> channelMap = new HashMap<>();
        return switch(colorModel){
            case GRAYSCALE:
                channelMap.put("GRAYSCALE",matrix);
                yield channelMap;
            case RGBA:
                channelMap.put(colorModel.getChannels()[0], channels.get(3));
                channelMap.put(colorModel.getChannels()[1], channels.get(2));
                channelMap.put(colorModel.getChannels()[2], channels.get(1));
                channelMap.put(colorModel.getChannels()[3], channels.getFirst());
                yield channelMap;
            case sRGB:
                channelMap.put(colorModel.getChannels()[0], channels.get(2));
                channelMap.put(colorModel.getChannels()[1], channels.get(1));
                channelMap.put(colorModel.getChannels()[2], channels.getFirst());
                yield channelMap;
            default:
                channelMap.put(colorModel.getChannels()[0], channels.getFirst());
                channelMap.put(colorModel.getChannels()[1], channels.get(1));
                channelMap.put(colorModel.getChannels()[2], channels.get(2));
                yield channelMap;
        };
    }



}