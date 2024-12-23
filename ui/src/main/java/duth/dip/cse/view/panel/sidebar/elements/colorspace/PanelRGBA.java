package duth.dip.cse.view.panel.sidebar.elements.colorspace;

import duth.dip.cse.view.panel.sidebar.elements.SliderPanel;

import javax.swing.*;

public class PanelRGBA extends PanelRGB{

    private final SliderPanel alphaPanel;

    public PanelRGBA(int rInitial, int gInitial, int bInitial, int aInitial) {
        super(rInitial, gInitial, bInitial);
        alphaPanel = new SliderPanel(SwingConstants.VERTICAL,0,255,aInitial,"A");
        this.add(alphaPanel);
    }

    public void setAlpha(int value) {
        alphaPanel.setValue(value);
    }

    public int getAlpha(){
        return alphaPanel.getValue();
    }

}
