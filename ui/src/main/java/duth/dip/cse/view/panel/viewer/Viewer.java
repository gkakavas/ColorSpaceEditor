package duth.dip.cse.view.panel.viewer;

import duth.dip.cse.view.common.Injectable;
import duth.dip.cse.util.property.Property;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Color;

public class Viewer extends JLabel implements Injectable {

    @Property(name = "viewer.background.color")
    private int backgroundColor;

    private ImageIcon image;

    public Viewer() {
        super();
        this.image = new ImageIcon();
        configure();
    }

    public void configure(){
        injectPropertiesTo(this);
        this.setBackground(new Color(backgroundColor));
        this.setBorder(BorderFactory.createEmptyBorder());
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setVerticalAlignment(JLabel.CENTER);
        this.updateImage(image);
    }

    public void updateImage(ImageIcon image) {
        this.image = image;
        this.setIcon(this.image);
    }

    public ImageIcon getImage(){
        return image;
    }
}
