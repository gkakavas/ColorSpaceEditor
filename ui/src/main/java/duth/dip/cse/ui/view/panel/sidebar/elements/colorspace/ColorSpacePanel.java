package duth.dip.cse.ui.view.panel.sidebar.elements.colorspace;

import duth.dip.cse.engine.domain.ColorModel;
import duth.dip.cse.ui.view.panel.sidebar.elements.Slider;

import javax.swing.*;
import java.awt.*;
import java.util.AbstractMap;
import java.util.List;

public abstract class ColorSpacePanel extends JPanel {

    public ColorSpacePanel(){
        super(new GridLayout(1,3,10,10));
    }

    public abstract int[] getColorSpaceData();

    public abstract Slider[] getSliders();
}
