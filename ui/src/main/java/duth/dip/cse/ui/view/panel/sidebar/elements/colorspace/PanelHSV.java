package duth.dip.cse.ui.view.panel.sidebar.elements.colorspace;

import duth.dip.cse.engine.domain.ColorModel;
import duth.dip.cse.ui.view.panel.sidebar.elements.Slider;
import duth.dip.cse.ui.view.panel.sidebar.elements.SliderPanel;

import javax.swing.*;
import java.awt.*;
import java.util.AbstractMap;
import java.util.List;

public class PanelHSV extends ColorSpacePanel{

    protected final SliderPanel hue;
    protected final SliderPanel saturation;
    protected final SliderPanel value;

    public PanelHSV(int hInitial, int sInitial, int vInitial) {
        hue = new SliderPanel(SwingConstants.VERTICAL, 0, 179, hInitial, "H");
        saturation = new SliderPanel(SwingConstants.VERTICAL, 0, 255, sInitial, "S");
        value = new SliderPanel(SwingConstants.VERTICAL, 0, 255, vInitial, "V");
        configure();
    }

    private void configure() {
        this.setBackground(Color.BLACK);
        this.add(hue);
        this.add(saturation);
        this.add(value);
    }

    public void setHue(int hue) {
        this.hue.setValue(hue);
    }

    public void setSaturation(int saturation){
        this.saturation.setValue(saturation);
    }

    public void setValue(int value){
        this.value.setValue(value);
    }

    public void set(int hue, int saturation, int value) {
        setHue(hue);
        setSaturation(saturation);
        setValue(value);
    }

    @Override
    public int[] getColorSpaceData() {
        return new int[]{
                hue.getValue(),
                saturation.getValue(),
                value.getValue()
        };
    }

    @Override
    public Slider[] getSliders() {
        return new Slider[]{
                hue.getSlider(),
                saturation.getSlider(),
                value.getSlider()
        };
    }
}
