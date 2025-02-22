package duth.dip.cse.ui.view.panel.sidebar.elements;

import duth.dip.cse.engine.utils.Property;
import duth.dip.cse.ui.view.common.Injectable;

import javax.swing.*;
import java.awt.*;

public class SliderPanel extends JPanel implements Injectable {

    @Property(name = "sidebar.colorSpaceProcessor.slider.background.color")
    private int backgroundColor;


    private final Slider slider;
    private final JLabel indicator;
    private final JLabel valueLbl;

    public SliderPanel(int direction, int minVal, int maxVal, int currentVal, String indicatorText){
        super();
        valueLbl = new JLabel(String.valueOf(currentVal));
        slider = new Slider(direction, minVal, maxVal, currentVal,valueLbl);
        indicator = new JLabel(indicatorText);
        configure();
    }

    private void configure(){
        injectPropertiesTo(this);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        // Center align all components horizontally
        indicator.setAlignmentX(Component.CENTER_ALIGNMENT);
        slider.setAlignmentX(Component.CENTER_ALIGNMENT);
        valueLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        indicator.setHorizontalAlignment(SwingConstants.CENTER);
        valueLbl.setHorizontalAlignment(SwingConstants.CENTER);

        // Add spacing between components (optional)
        this.add(Box.createVerticalStrut(10)); // Space above the indicator
        this.add(indicator);

        this.add(Box.createVerticalStrut(10));
        this.add(slider);

        this.add(Box.createVerticalStrut(10));
        this.add(valueLbl);
        this.setBackground(new Color(backgroundColor));
    }

    public void setValue(int value){
        slider.setValue(value);
        valueLbl.setText(String.valueOf(value));
    }

    public int getValue(){
        return slider.getValue();
    }

    public Slider getSlider(){
        return slider;
    }

}
