package duth.dip.cse.ui.view.panel.sidebar.elements.colorspace;

import duth.dip.cse.ui.view.panel.sidebar.elements.Slider;
import duth.dip.cse.ui.view.panel.sidebar.elements.SliderPanel;

import javax.swing.SwingConstants;
import java.awt.Color;

public class PanelLUV extends ColorSpacePanel{

    private final SliderPanel lightness;
    private final SliderPanel blueYellowChromaticity;
    private final SliderPanel redGreenChromaticity;


    public PanelLUV(int hInitial, int sInitial, int vInitial) {
        lightness = new SliderPanel(SwingConstants.VERTICAL, 0, 255, hInitial, "L");
        blueYellowChromaticity = new SliderPanel(SwingConstants.VERTICAL,0, 255, sInitial, "U");
        redGreenChromaticity = new SliderPanel(SwingConstants.VERTICAL, 0, 255, vInitial, "V");
        configure();
    }

    private void configure() {
        this.setBackground(Color.BLACK);
        this.add(lightness);
        this.add(blueYellowChromaticity);
        this.add(redGreenChromaticity);
    }

    public void setLightness(int lightness) {
        this.lightness.setValue(lightness);
    }

    public void setBlueYellowChromaticity(int blueYellowChromaticity){
        this.blueYellowChromaticity.setValue(blueYellowChromaticity);
    }

    public void setRedGreenChromaticity(int redGreenChromaticity){
        this.redGreenChromaticity.setValue(redGreenChromaticity);
    }

    @Override
    public int[] getColorSpaceData() {
        return new int[]{
                        lightness.getValue(),
                        blueYellowChromaticity.getValue(),
                        redGreenChromaticity.getValue(),
                        -1,
                };
    }

    @Override
    public Slider[] getSliders() {
        return new Slider[]{
                lightness.getSlider(),
                blueYellowChromaticity.getSlider(),
                redGreenChromaticity.getSlider()
        };
    }

    @Override
    public void set(int[] colorSpaceData) {
        setLightness(colorSpaceData[0]);
        setBlueYellowChromaticity(colorSpaceData[1]);
        setRedGreenChromaticity(colorSpaceData[2]);
    }

}
