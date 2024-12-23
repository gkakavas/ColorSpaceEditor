package duth.dip.cse.view.panel.sidebar.elements;

import duth.dip.cse.util.property.Property;
import duth.dip.cse.view.common.Injectable;
import javax.swing.JSlider;
import javax.swing.plaf.basic.BasicSliderUI;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;

public class Slider extends JSlider implements Injectable {

    @Property(name = "sidebar.colorSpaceProcessor.slider.thumb.color")
    private int sliderThumbColor;

    public Slider(int direction, int minVal, int maxVal, int initialVal) {
        super(direction, minVal, maxVal, initialVal);
        injectPropertiesTo(this);
        configure();
    }

    private void configure() {
        this.setUI(createBasicSliderUI());
        this.setBackground(Color.BLACK);
        this.setFocusable(false);
    }

    private BasicSliderUI createBasicSliderUI() {
        return new BasicSliderUI() {

            @Override
            public void paintThumb(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(new Color(sliderThumbColor));
                int x = thumbRect.x;
                int y = thumbRect.y;
                int width = thumbRect.width;
                int height = thumbRect.height;
                g2d.fill(new Rectangle2D.Float(x, y, width, height));
            }
        };
    }
}

