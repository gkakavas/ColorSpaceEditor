package duth.dip.cse.ui.model;

import duth.dip.cse.engine.domain.ColorModel;

import java.awt.image.BufferedImage;

public class Image {

    private final int[] channelsValues;
    private BufferedImage image;
    private ImageListener imageChangeListener;
    private ColorModel colorModel;
    private static final int EMPTY = -1;


    public Image(BufferedImage image, int[] channelsValues, ColorModel colorModel) {
        this.image = image;
        this.channelsValues = channelsValues;
        this.colorModel = colorModel;
    }

    public void attachImageListener(ImageListener imageChangeListener){
        this.imageChangeListener = imageChangeListener;
    }


    public void updateImage(BufferedImage image){
        this.image = image;
        imageChangeListener.onImageRefresh();
    }

    public void updateChannelsValues(int channel1,int channel2,int channel3,int channel4){
        channelsValues[0] = channel1;
        channelsValues[1] = channel2;
        channelsValues[2] = channel3;
        channelsValues[3] = channel4;
    }

    public void updateColorModel(ColorModel colorModel){
        this.colorModel = colorModel;
    }

    public void updateChannels(int[] values){
        try{
            if(colorModel == ColorModel.GRAYSCALE){
                updateChannelsValues(values[0],EMPTY,EMPTY,EMPTY);
            } else if (colorModel == ColorModel.RGBA) {
                updateChannelsValues(values[0],values[1],values[2],values[3]);
            }
            updateChannelsValues(values[0],values[1],values[2],EMPTY);
        }catch (IndexOutOfBoundsException ex){
            updateChannelsValues(EMPTY,EMPTY,EMPTY,EMPTY);
        }
    }

    public int[] getChannelsValues() {
        return channelsValues;
    }

    public ColorModel getColorModel(){
        return colorModel;
    }

    public BufferedImage getImage(){
        return image;
    }

    public void clear(){
        for(int index = channelsValues.length-1; index >= 0; index--) {
            channelsValues[index] = EMPTY;
        }
        image = new BufferedImage(1,1,BufferedImage.TYPE_INT_RGB);
        colorModel = ColorModel.sRGB;
        imageChangeListener.onImageRefresh();
    }

    public int channel1(){
        return channelsValues[0];
    }

    public int channel2(){
        return channelsValues[1];
    }

    public int channel3(){
        return channelsValues[2];
    }

    public int channel4(){
        return channelsValues[3];
    }

}
