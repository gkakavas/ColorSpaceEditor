package duth.dip.cse.ui.view.panel.viewer;

import duth.dip.cse.ui.view.common.Injectable;
import duth.dip.cse.engine.utils.Property;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Viewer extends JLabel implements Injectable {

    @Property(name = "viewer.background.color")
    private int backgroundColor;
    private final ImageIcon imageIcon;

    public Viewer() {
        super();
        this.imageIcon = new ImageIcon();
        configure();
    }

    public void configure(){
        injectPropertiesTo(this);
        this.setBackground(new Color(backgroundColor));
        this.setBorder(BorderFactory.createEmptyBorder());
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setVerticalAlignment(JLabel.CENTER);
        this.setIcon(imageIcon);
    }


    public void updateImage(BufferedImage image) {
        imageIcon.setImage(image);
        this.revalidate();
        this.repaint();
    }

    public BufferedImage getImage(){
        return (BufferedImage) imageIcon.getImage();
    }

}
