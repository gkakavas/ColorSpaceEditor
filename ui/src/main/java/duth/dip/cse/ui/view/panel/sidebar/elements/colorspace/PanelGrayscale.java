package duth.dip.cse.ui.view.panel.sidebar.elements.colorspace;

import duth.dip.cse.engine.domain.ColorModel;
import duth.dip.cse.ui.view.panel.sidebar.elements.Slider;
import duth.dip.cse.ui.view.panel.sidebar.elements.SliderPanel;

import javax.swing.*;
import java.awt.*;
import java.util.AbstractMap;
import java.util.List;

public class PanelGrayscale extends ColorSpacePanel{

    private final SliderPanel gray;

    public PanelGrayscale(int gInitial){
        gray = new SliderPanel(SwingConstants.VERTICAL,0,255,gInitial,"G");
        configure();
    }

    private void configure(){
        this.setBackground(Color.BLACK);
        this.add(gray);
    }

    public void set(int gray){this.gray.setValue(gray);}

    @Override
    public int[] getColorSpaceData() {
        return new int[]{gray.getValue()};
    }

    @Override
    public Slider[] getSliders() {
        return new Slider[]{gray.getSlider()};
    }

}
