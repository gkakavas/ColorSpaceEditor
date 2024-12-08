package duth.dip.cse.view.panel.viewer;

import duth.dip.cse.view.common.Injectable;
import duth.dip.cse.util.property.Property;

import javax.swing.*;
import java.awt.*;

public class Viewer extends JLabel implements Injectable {

    @Property(name = "viewer.background.color")
    private int backgroundColor;

    private ImageIcon icon;

    public Viewer() {
        super();
        this.icon = new ImageIcon();
        configure();
    }

    public void configure(){
        injectPropertiesTo(this);
        this.setBackground(new Color(backgroundColor));
        this.setBorder(BorderFactory.createEmptyBorder());
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setVerticalAlignment(JLabel.CENTER);
        this.setIcon(icon);
    }

    public void setIcon(ImageIcon icon) {
        this.icon = icon;
        super.setIcon(icon);
    }
}
