package duth.dip.cse.view.panel.sidebar;

import duth.dip.cse.ColorModel;
import duth.dip.cse.view.common.Injectable;
import duth.dip.cse.util.property.Property;
import duth.dip.cse.view.panel.sidebar.elements.colorspace.PanelRGB;
import duth.dip.cse.view.panel.sidebar.elements.colorspace.PanelRGBA;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.plaf.ComboBoxUI;
import javax.swing.plaf.ScrollBarUI;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

public class SidebarEditor extends JPanel implements Injectable {

    @Property(name = "sidebar.background.color")
    private int backgroundColor;

    @Property(name = "sidebar.colorSpaceProcessor.label.foreground.color")
    private int labelForegroundColor;

    @Property(name = "sidebar.colorSpaceProcessor.label.text")
    private String mainLabelText;

    @Property(name= "sidebar.colorSpaceProcessor.combobox.label.text")
    private String comboBoxLblText;

    @Property(name = "font")
    private String font;

    @Property(name = "sidebar.colorSpaceProcessor.slider.thumb.color")
    private int sliderThumbColor;


    private final JLabel editorLabel;
    private final JLabel comboboxLabel;
    private final ColorSpaceCombobox combobox;
    //private final JPanel sliderPanel;

    public SidebarEditor(){
        super();
        injectPropertiesTo(this);
        this.setBackground(new Color(backgroundColor));
        this.setLayout(new GridBagLayout());
        this.setBorder(BorderFactory.createEmptyBorder());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx=0;
        constraints.weightx = 1.0;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.insets = new Insets(5,5,5,5);

        constraints.gridy = 0;
        constraints.weighty = 0.005;
        editorLabel = new JLabel(mainLabelText);
        editorLabel.setForeground(new Color(labelForegroundColor));
        editorLabel.setFont(new Font(font, Font.BOLD, 16));
        this.add(editorLabel,constraints);

        constraints.insets = new Insets(0,5,20,5);
        constraints.gridy = 1;
        constraints.weighty = 0.005;
        var separator = new JSeparator(SwingConstants.HORIZONTAL);
        separator.setBackground(Color.BLACK);
        this.add(separator,constraints);

        constraints.insets = new Insets(5,5,0,5);
        constraints.gridy = 2;
        constraints.weighty = 0.005;
        comboboxLabel = new JLabel(comboBoxLblText);
        comboboxLabel.setForeground(new Color(labelForegroundColor));
        comboboxLabel.setFont(new Font(font, Font.BOLD, 14));
        this.add(comboboxLabel,constraints);

        constraints.gridy = 3;
        constraints.weighty = 0.005;
        combobox = new ColorSpaceCombobox();
        this.add(combobox, constraints);

        constraints.insets = new Insets(0,5,5,5);
        constraints.gridy = 4;
        constraints.weighty = 0.75;

        //PanelRGB rgbPanel = new PanelRGB(28,56,12);
        PanelRGBA rgba = new PanelRGBA(25,235,234,2);
        this.add(rgba,constraints);
        /*sliderPanel = new JPanel();
        sliderPanel.setBorder(BorderFactory.createEmptyBorder());
        sliderPanel.setLayout(new GridLayout(1,4,10,10));
        sliderPanel.setBackground(new Color(backgroundColor));*/

        /*for(int i = 0;i<4;i++){
            JPanel singleSliderPanel = new JPanel();
            singleSliderPanel.setBackground(new Color(backgroundColor));
            singleSliderPanel.setLayout(new BoxLayout(singleSliderPanel,BoxLayout.Y_AXIS));

            JSlider slider = new Slider(JSlider.VERTICAL,0,100,50);
            slider.setBackground(new Color(backgroundColor));
            slider.setFocusable(false);
            singleSliderPanel.add(slider);


            JLabel sliderLabel = new JLabel("R");
            sliderLabel.setForeground(new Color(labelForegroundColor));
            singleSliderPanel.add(sliderLabel);

            sliderPanel.add(singleSliderPanel);
        }*/

        //this.add(sliderPanel,constraints);


    }


    public class ColorSpaceCombobox extends JComboBox<ColorModel> implements Injectable {

        @Property(name = "sidebar.colorSpaceProcessor.combobox.background.color")
        private int comboSelectionBackgroundColor;

        @Property(name = "sidebar.colorSpaceProcessor.combobox.foreground.color")
        private int comboSelectionForegroundColor;

        public ColorSpaceCombobox() {
            super(ColorModel.values());
            injectPropertiesTo(this);
            configure();
        }

        private void configure() {
            this.setBorder(BorderFactory.createEmptyBorder());
            this.setBackground(new Color(comboSelectionBackgroundColor));
            this.setForeground(new Color(comboSelectionForegroundColor));
            this.setSelectedItem(ColorModel.sRGB);
            this.setFont(new Font(font, Font.BOLD, 14));
            this.setFocusable(false);
            this.setUI(createBasicComboBoxUI());
            var verticalScrollBar = ((JScrollPane) ((BasicComboPopup) this.getUI().getAccessibleChild(this, 0)).getComponents()[0]).getVerticalScrollBar();
            verticalScrollBar.setBackground(new Color(comboSelectionBackgroundColor));
            verticalScrollBar.setUI(createBasicScrollBarUI());
        }

        private ComboBoxUI createBasicComboBoxUI(){
            return new BasicComboBoxUI(){
                @Override
                protected JButton createArrowButton() {
                    JButton button = new JButton();
                    button.setPreferredSize(new Dimension(0, 0));
                    button.setVisible(false);
                    return button;
                }
            };
        }

        private ScrollBarUI createBasicScrollBarUI(){
            return new BasicScrollBarUI() {

                @Override
                protected void configureScrollBarColors() {
                    this.thumbColor = new Color(backgroundColor);
                }

                @Override
                protected JButton createDecreaseButton(int orientation) {
                    return createEmptyButton(); // Return an empty button
                }

                @Override
                protected JButton createIncreaseButton(int orientation) {
                    return createEmptyButton(); // Return an empty button
                }

                private JButton createEmptyButton() {
                    JButton button = new JButton();
                    button.setPreferredSize(new Dimension(0, 0)); // Zero size
                    button.setMinimumSize(new Dimension(0, 0));
                    button.setMaximumSize(new Dimension(0, 0));
                    return button;
                }
            };
        }
    }
}
