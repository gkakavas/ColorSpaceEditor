package duth.dip.cse.view.menu;

import duth.dip.cse.view.common.FontIcon;
import duth.dip.cse.view.common.Injectable;
import duth.dip.cse.util.property.Property;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuItem extends JButton implements Injectable {

    @Property(name = "menuItem.background.color")
    private int backgroundColor;

    @Property(name = "menuItem.hover.color")
    private int hoverColor;

    @Property(name = "menuItem.hover.enabled")
    private boolean hoverEnabled;

    public MenuItem(FontIcon icon, String tooltip) {
        super();
        injectPropertiesTo(this);
        configure(icon,tooltip);
    }

    private void configure(FontIcon icon, String tooltip) {
        this.setIcon(icon);
        this.setBackground(new Color(backgroundColor));
        this.setToolTipText(tooltip);
        this.setFocusable(false);
        this.setBorderPainted(false);
        if (hoverEnabled)
            enableHover(new Color(hoverColor),this.getBackground());
    }

    private void enableHover(Color hoverColor, Color normalColor) {
        var toolbarItem = this;
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                toolbarItem.setBackground(hoverColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                toolbarItem.setBackground(normalColor);
            }
        });
    }

}
