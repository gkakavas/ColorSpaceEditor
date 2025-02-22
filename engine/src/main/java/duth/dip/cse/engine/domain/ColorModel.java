package duth.dip.cse.engine.domain;

public enum ColorModel {
    GRAYSCALE(new String[]{"Intensity"}),  // Single channel
    RGBA(new String[]{"Red", "Green", "Blue", "Alpha"}),
    sRGB(new String[]{"Red", "Green", "Blue"}),
    HSV(new String[]{"Hue", "Saturation", "Value"}),
    LAB(new String[]{"Lightness", "A", "B"}),
    YCrCb(new String[]{"Y", "Cr", "Cb"}),
    LUV(new String[]{"Lightness", "U", "V"});

    private final String[] channels;

    ColorModel(String[] channels) {
        this.channels = channels;
    }

    public String[] getChannels() {
        return channels;
    }
}
