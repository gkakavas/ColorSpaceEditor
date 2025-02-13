package duth.dip.cse.engine.core;

import duth.dip.cse.engine.domain.ColorModel;

import java.io.File;
import java.util.Arrays;
import java.util.Optional;

public class ColorSpaceFileEditor {

    private static final String COLOR_MODEL_SPLITERATOR = "_";
    private static final String FILE_EXT_SPLITERATOR = ".";

    public static Optional<ColorModel> identifyColorSpace(StringBuilder filePathBuilder) {
        var colorModelStartIndex = filePathBuilder.lastIndexOf(COLOR_MODEL_SPLITERATOR);
        var colorModelEndIndex = filePathBuilder.lastIndexOf(FILE_EXT_SPLITERATOR);
        var colorModel = filePathBuilder.substring(colorModelStartIndex + COLOR_MODEL_SPLITERATOR.length(), colorModelEndIndex);
        var resolvedColorModel = Arrays.stream(ColorModel.values())
                .filter(colorModel1 -> colorModel1.name().equalsIgnoreCase(colorModel))
                .findFirst();
        resolvedColorModel.ifPresent((colorModel1) -> filePathBuilder.delete(colorModelStartIndex, colorModelEndIndex));
        return resolvedColorModel;
    }

    public static String putColorSpaceStamp(ColorModel colorModel, String filePath) {
        if(filePath.contains(FILE_EXT_SPLITERATOR)){
            var pathPart = filePath.substring(0,filePath.lastIndexOf(FILE_EXT_SPLITERATOR));
            var extensionPart = filePath.substring(filePath.lastIndexOf(FILE_EXT_SPLITERATOR));
            return pathPart.concat(COLOR_MODEL_SPLITERATOR)
                    .concat(colorModel.name().toLowerCase())
                    .concat(extensionPart);
        }
        throw new RuntimeException("Failed to put color space stamp");
    }
}
