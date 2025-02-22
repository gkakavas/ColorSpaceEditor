package duth.dip.cse.ui.view.panel.sidebar.elements.colorspace;

import duth.dip.cse.engine.domain.ColorModel;
import duth.dip.cse.ui.view.panel.sidebar.elements.Slider;
import duth.dip.cse.ui.view.panel.sidebar.elements.SliderPanel;

import javax.swing.SwingConstants;
import java.awt.Color;
import java.util.AbstractMap;
import java.util.List;

public class PanelLAB extends ColorSpacePanel{

    private final SliderPanel lightness;
    private final SliderPanel greenRed;
    private final SliderPanel blueYellow;

    public PanelLAB(int lInitial, int aInitial, int bInitial){
        lightness = new SliderPanel(SwingConstants.VERTICAL,0,255,lInitial,"L");
        greenRed = new SliderPanel(SwingConstants.VERTICAL,0,255,aInitial,"A");
        blueYellow = new SliderPanel(SwingConstants.VERTICAL,0,255,bInitial,"B");
        configure();
    }

    private void configure(){
        this.setBackground(Color.BLACK);
        this.add(lightness);
        this.add(greenRed);
        this.add(blueYellow);
    }

    public void setLightness(int lightness){this.lightness.setValue(lightness);}
    public void setGreenRed(int greenRed){this.greenRed.setValue(greenRed);}
    public void setBlueYellow(int blueYellow){this.blueYellow.setValue(blueYellow);}

    public void set(int lightness,int greenRed,int blueYellow){
        setLightness(lightness);
        setGreenRed(greenRed);
        setBlueYellow(blueYellow);
    }

    @Override
    public int[] getColorSpaceData() {
        return new int[]{
                        lightness.getValue(),
                        greenRed.getValue(),
                        blueYellow.getValue(),
                        -1
                };
    }

    @Override
    public Slider[] getSliders() {
        return new Slider[]{
                lightness.getSlider(),
                greenRed.getSlider(),
                blueYellow.getSlider()
        };
    }

    @Override
    public void set(int[] colorSpaceData) {
        setLightness(colorSpaceData[0]);
        setGreenRed(colorSpaceData[1]);
        setBlueYellow(colorSpaceData[2]);
    }
}
