package duth.dip.cse.ui.view.panel.sidebar.elements.colorspace;

import duth.dip.cse.engine.domain.ColorModel;
import duth.dip.cse.ui.view.panel.sidebar.elements.Slider;
import duth.dip.cse.ui.view.panel.sidebar.elements.SliderPanel;

import javax.swing.SwingConstants;
import java.util.AbstractMap;

public class PanelRGBA extends PanelRGB{

    private final SliderPanel alpha;

    public PanelRGBA(int rInitial, int gInitial, int bInitial, int aInitial) {
        super(rInitial, gInitial, bInitial);
        alpha = new SliderPanel(SwingConstants.VERTICAL,0,255,aInitial,"A");
        this.add(alpha);
    }

    public void setAlpha(int value) {
        alpha.setValue(value);
    }

    public void set(int red, int green,int blue, int alpha){
        set(red,green,blue);
        setAlpha(alpha);
    }

    @Override
    public int[] getColorSpaceData() {
        return new int[]{
                        red.getValue(),
                        green.getValue(),
                        blue.getValue(),
                        alpha.getValue()
                };
    }

    @Override
    public Slider[] getSliders(){
        return new Slider[]{
                red.getSlider(),
                green.getSlider(),
                blue.getSlider(),
                alpha.getSlider()
        };
    }
}
