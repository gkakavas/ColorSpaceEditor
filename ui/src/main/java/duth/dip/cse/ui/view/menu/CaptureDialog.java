package duth.dip.cse.ui.view.menu;

import duth.dip.cse.engine.utils.Property;
import duth.dip.cse.ui.view.common.Injectable;

import javax.swing.*;
import java.awt.*;

public class CaptureDialog extends JDialog implements Injectable {

    @Property(name = "captureDiag.title")
    private String title;

    @Property(name = "captureDiag.background.color")
    private int backgroundColor;

    private final JLabel cameraViewLabel;
    private final ImageIcon image;
    private final JPanel buttonPanel;
    private final JButton captureBtn;
    private final JButton closeBtn;

    public CaptureDialog(){
        super();
        injectPropertiesTo(this);
        image = new ImageIcon();
        cameraViewLabel = new JLabel(image);
        buttonPanel = new JPanel();
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
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(captureBtn);
        buttonPanel.add(closeBtn);
        this.add(buttonPanel,BorderLayout.SOUTH);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
    }

    private void configureElements(){
        cameraViewLabel.setBorder(BorderFactory.createEmptyBorder());
        captureBtn.setText("Capture");
        closeBtn.setText("Close");
    }

    public void refresh(){
        cameraViewLabel.setIcon(image);
        this.revalidate();
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
