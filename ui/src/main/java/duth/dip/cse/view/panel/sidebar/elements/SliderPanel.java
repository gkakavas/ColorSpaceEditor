package duth.dip.cse.view.panel.sidebar.elements;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Component;

public class SliderPanel extends JPanel {

    private final Slider slider;
    private final JLabel indicator;
    private final JLabel valueLbl;

    public SliderPanel(int direction, int minVal, int maxVal, int currentVal, String indicatorText){
        super();
        slider = new Slider(direction, minVal, maxVal, currentVal);
        indicator = new JLabel(indicatorText);
        valueLbl = new JLabel(String.valueOf(currentVal));
        configure();
    }

    private void configure(){
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        indicator.setAlignmentX(Component.CENTER_ALIGNMENT);
        slider.setAlignmentX(Component.CENTER_ALIGNMENT);
        valueLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(indicator);
        this.add(slider);
        this.add(valueLbl);
    }

    public void setValue(int value){
        slider.setValue(value);
        valueLbl.setText(String.valueOf(value));
    }

    public int getValue(){
        return slider.getValue();
    }

}
