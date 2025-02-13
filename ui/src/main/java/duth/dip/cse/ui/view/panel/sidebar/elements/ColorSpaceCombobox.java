package duth.dip.cse.ui.view.panel.sidebar.elements;

import duth.dip.cse.engine.domain.ColorModel;
import duth.dip.cse.engine.utils.Property;
import duth.dip.cse.ui.view.common.Injectable;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.plaf.ComboBoxUI;
import javax.swing.plaf.ScrollBarUI;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;


public class ColorSpaceCombobox extends JComboBox<ColorModel> implements Injectable {

    @Property(name = "sidebar.colorSpaceProcessor.combobox.background.color")
    private int comboSelectionBackgroundColor;

    @Property(name = "sidebar.colorSpaceProcessor.combobox.foreground.color")
    private int comboSelectionForegroundColor;

    @Property(name = "sidebar.background.color")
    private int backgroundColor;

    @Property(name = "font")
    private String font;

    public ColorSpaceCombobox() {
        super(ColorModel.values());
        injectPropertiesTo(this);
        configure();
    }

    private void configure() {
        this.setBorder(BorderFactory.createEmptyBorder());
        this.setBackground(new Color(comboSelectionBackgroundColor));
        this.setForeground(new Color(comboSelectionForegroundColor));
        this.setFont(new Font(font, Font.BOLD, 14));
        this.setFocusable(false);
        this.setUI(createBasicComboBoxUI());
        var verticalScrollBar = ((JScrollPane) ((BasicComboPopup) this.getUI().getAccessibleChild(this, 0)).getComponents()[0]).getVerticalScrollBar();
        verticalScrollBar.setBackground(new Color(comboSelectionBackgroundColor));
        verticalScrollBar.setUI(createBasicScrollBarUI());

    }

    private ComboBoxUI createBasicComboBoxUI() {
        return new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(0, 0));
                button.setVisible(false);
                return button;
            }
        };
    }

    private ScrollBarUI createBasicScrollBarUI() {
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
