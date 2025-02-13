package duth.dip.cse.ui.view.panel.sidebar.elements.colorspace;
import duth.dip.cse.engine.domain.ColorModel;
import duth.dip.cse.ui.view.panel.sidebar.elements.Slider;
import duth.dip.cse.ui.view.panel.sidebar.elements.SliderPanel;

import javax.swing.SwingConstants;
import java.awt.Color;
import java.util.AbstractMap;
import java.util.List;

public class PanelRGB extends ColorSpacePanel {

    protected final SliderPanel red;
    protected final SliderPanel green;
    protected final SliderPanel blue;

    public PanelRGB(int rInitial,int gInitial,int bInitial){
        red = new SliderPanel(SwingConstants.VERTICAL,-100, 100, 0,"R");
        green = new SliderPanel(SwingConstants.VERTICAL,-100, 100, 0,"G");
        blue = new SliderPanel(SwingConstants.VERTICAL,-100, 100, 0,"B");
        configure();
    }

    private void configure(){
        this.setBackground(Color.BLACK);
        this.add(red);
        this.add(green);
        this.add(blue);
    }

    public void setRed(int red){
        this.red.setValue(red);
    }

    public void setGreen(int green){
        this.green.setValue(green);
    }

    public void setBlue(int blue){
        this.blue.setValue(blue);
    }

    public void set(int red,int green,int blue){
        setRed(red);
        setGreen(green);
        setBlue(blue);
    }

    @Override
    public int[] getColorSpaceData() {
        return new int[]{
                        red.getValue(),
                        green.getValue(),
                        blue.getValue()
                };
    }

    @Override
    public Slider[] getSliders() {
        return new Slider[]{
                red.getSlider(),
                green.getSlider(),
                blue.getSlider()
        };
    }

}
