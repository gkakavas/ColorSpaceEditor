package duth.dip.cse.view.menu;

import duth.dip.cse.util.property.Property;
import duth.dip.cse.view.common.Injectable;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Color;

public class CaptureDialog extends JDialog implements Injectable {

    @Property(name = "captureDiag.title")
    private String title;

    @Property(name = "captureDiag.background.color")
    private int backgroundColor;

    private final JLabel cameraViewLabel;
    private final ImageIcon image;
    private final JButton captureBtn;
    private final JButton closeBtn;

    public CaptureDialog(){
        super();
        injectPropertiesTo(this);
        image = new ImageIcon();
        cameraViewLabel = new JLabel(image);
        captureBtn = new JButton();
        closeBtn = new JButton();
        configure();
    }

    private void configure(){
        configureElements();
        this.setTitle(title);
        this.setModal(false);
        this.setSize(600, 420);
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(backgroundColor));
        this.add(cameraViewLabel,BorderLayout.CENTER);
        this.add(captureBtn,BorderLayout.SOUTH);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    private void configureElements(){
        cameraViewLabel.setBorder(BorderFactory.createEmptyBorder());
        captureBtn.setText("Capture");
        closeBtn.setText("Close");
    }

    public void refresh(){
        cameraViewLabel.setIcon(image);
        this.repaint();
    }

    public ImageIcon getImage(){
        return (ImageIcon) cameraViewLabel.getIcon();
    }

    public JButton getCaptureBtn(){
        return captureBtn;
    }

    public JButton getCloseBtn(){
        return closeBtn;
    }

}
