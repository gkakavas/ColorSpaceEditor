package duth.dip.cse.ui.view.panel.sidebar;

import duth.dip.cse.engine.domain.ColorModel;
import duth.dip.cse.ui.view.common.Injectable;
import duth.dip.cse.engine.utils.Property;
import duth.dip.cse.ui.view.panel.sidebar.elements.ColorSpaceCombobox;
import duth.dip.cse.ui.view.panel.sidebar.elements.Slider;
import duth.dip.cse.ui.view.panel.sidebar.elements.SliderPanel;
import duth.dip.cse.ui.view.panel.sidebar.elements.colorspace.*;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Map;

public class SidebarEditor extends JPanel implements Injectable {

    @Property(name = "sidebar.background.color")
    private int backgroundColor;

    @Property(name = "sidebar.colorSpaceProcessor.label.foreground.color")
    private int labelForegroundColor;

    @Property(name = "sidebar.colorSpaceProcessor.label.text")
    private String mainLabelText;

    @Property(name = "sidebar.colorSpaceProcessor.combobox.label.text")
    private String comboBoxLblText;

    @Property(name = "font")
    private String font;

    @Property(name = "sidebar.colorSpaceProcessor.slider.thumb.color")
    private int sliderThumbColor;

    private final JLabel editorLabel;
    private final JLabel comboboxLabel;
    private final ColorSpaceCombobox combobox;
    private ColorSpacePanel slidersPanel;

    private final Map<ColorModel,ColorSpacePanel> panels;

    public SidebarEditor() {
        super();
        injectPropertiesTo(this);
        this.setBackground(new Color(backgroundColor));
        this.setLayout(new GridBagLayout());
        this.setBorder(BorderFactory.createEmptyBorder());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.weightx = 1.0;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.insets = new Insets(5, 5, 5, 5);

        constraints.gridy = 0;
        constraints.weighty = 0.005;
        editorLabel = new JLabel(mainLabelText);
        editorLabel.setForeground(new Color(labelForegroundColor));
        editorLabel.setFont(new Font(font, Font.BOLD, 16));
        this.add(editorLabel, constraints);

        constraints.insets = new Insets(0, 5, 20, 5);
        constraints.gridy = 1;
        constraints.weighty = 0.005;
        var separator = new JSeparator(SwingConstants.HORIZONTAL);
        separator.setBackground(Color.BLACK);
        this.add(separator, constraints);

        constraints.insets = new Insets(5, 5, 0, 5);
        constraints.gridy = 2;
        constraints.weighty = 0.005;
        comboboxLabel = new JLabel(comboBoxLblText);
        comboboxLabel.setForeground(new Color(labelForegroundColor));
        comboboxLabel.setFont(new Font(font, Font.BOLD, 14));
        this.add(comboboxLabel, constraints);

        constraints.gridy = 3;
        constraints.weighty = 0.005;
        combobox = new ColorSpaceCombobox();
        combobox.setSelectedItem(ColorModel.sRGB);
        this.add(combobox, constraints);

        constraints.insets = new Insets(0, 5, 5, 5);
        constraints.gridy = 4;
        constraints.weighty = 0.75;

        panels = initSliderPanels();
        updateSlidersPanel(ColorModel.sRGB);
    }

    public ColorSpaceCombobox getCombobox(){
        return combobox;
    }


    public void updateSlidersPanel(ColorModel colorModel){

        if(slidersPanel!=null){
            this.remove(slidersPanel);
        }
        slidersPanel = panels.get(colorModel);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.weightx = 1.0;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.insets = new Insets(0, 5, 5, 5);
        constraints.gridy = 4;
        constraints.weighty = 0.75;
        this.add(slidersPanel,constraints);
        this.revalidate();
        this.repaint();
    }

    public int[] getSliderValues(){
        return slidersPanel.getColorSpaceData();
    }

    public Slider[] getSliders(){
       return slidersPanel.getSliders();
    }

    private Map<ColorModel,ColorSpacePanel> initSliderPanels(){
        return Map.of(
                ColorModel.GRAYSCALE, new PanelGrayscale(100),
                ColorModel.RGBA, new PanelRGBA(100,100,100,100),
                ColorModel.sRGB, new PanelRGB(100,100,100),
                ColorModel.HSV, new PanelHSV(100,100,100),
                ColorModel.LAB, new PanelLAB(100,100,100),
                ColorModel.YCrCb, new PanelYCbCr(100,100,100),
                ColorModel.LUV, new PanelLUV(100,100,100)
        );
    }

    public Map<ColorModel, ColorSpacePanel> getPanels() {
        return panels;
    }
}

