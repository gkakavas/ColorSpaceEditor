package duth.dip.cse.view.panel.sidebar.elements.colorspace;

import duth.dip.cse.view.panel.sidebar.elements.SliderPanel;

import javax.swing.*;
import java.awt.*;

public class PanelRGB extends JPanel {

    private final SliderPanel redPanel;
    private final SliderPanel greenPanel;
    private final SliderPanel bluePanel;

    public PanelRGB(int rInitial,int gInitial,int bInitial){
        super(new GridLayout(1,3,10,10));
        redPanel = new SliderPanel(SwingConstants.VERTICAL,0,255,rInitial,"R");
        greenPanel = new SliderPanel(SwingConstants.VERTICAL,0,255,gInitial,"G");
        bluePanel = new SliderPanel(SwingConstants.VERTICAL,0,255,bInitial,"B");
        configure();
    }

    private void configure(){
        this.setBackground(Color.BLACK);
        this.add(redPanel);
        this.add(greenPanel);
        this.add(bluePanel);
    }

    public void setRed(int red){redPanel.setValue(red);}
    public void setGreen(int green){greenPanel.setValue(green);}
    public void setBlue(int blue){bluePanel.setValue(blue);}
    public int getRed() {return redPanel.getValue();}
    public int getGreen() {return greenPanel.getValue();}
    public int getBlue() {return bluePanel.getValue();}

}
