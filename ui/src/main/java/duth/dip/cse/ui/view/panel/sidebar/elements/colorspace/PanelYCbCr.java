package duth.dip.cse.ui.view.panel.sidebar.elements.colorspace;

import duth.dip.cse.engine.domain.ColorModel;
import duth.dip.cse.ui.view.panel.sidebar.elements.Slider;
import duth.dip.cse.ui.view.panel.sidebar.elements.SliderPanel;

import javax.swing.*;
import java.util.AbstractMap;
import java.util.List;

public class PanelYCbCr extends ColorSpacePanel{

    private final SliderPanel luminance;
    private final SliderPanel chrominanceRed;
    private final SliderPanel chrominanceBlue;



    public PanelYCbCr(int yInitial,int cbInitial,int crInitial){
        luminance = new SliderPanel(SwingConstants.VERTICAL,0,255,yInitial,"Y");
        chrominanceRed = new SliderPanel(SwingConstants.VERTICAL,0,255,crInitial,"Cr");
        chrominanceBlue = new SliderPanel(SwingConstants.VERTICAL,0,255,cbInitial,"Cb");
        configure();
    }

    private void configure(){
        this.add(luminance);
        this.add(chrominanceRed);
        this.add(chrominanceBlue);
    }

    public void setLuminance(int luminance){this.luminance.setValue(luminance);}
    public void setChrominanceRed(int chrominanceRed){this.chrominanceRed.setValue(chrominanceRed);}
    public void setChrominanceBlue(int chrominanceBlue){this.chrominanceBlue.setValue(chrominanceBlue);}

    public void set(int luminance,int chrominanceBlue,int chrominanceRed){
        setLuminance(luminance);
        setChrominanceRed(chrominanceRed);
        setChrominanceBlue(chrominanceBlue);
    }

    @Override
    public int[] getColorSpaceData() {
        return new int[]{
                        luminance.getValue(),
                        chrominanceBlue.getValue(),
                        chrominanceRed.getValue()
                };
    }

    @Override
    public Slider[] getSliders() {
        return new Slider[]{
            luminance.getSlider(),
            chrominanceBlue.getSlider(),
            chrominanceRed.getSlider()
        };
    }
}
