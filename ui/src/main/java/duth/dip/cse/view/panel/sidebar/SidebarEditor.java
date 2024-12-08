package duth.dip.cse.view.panel.sidebar;

import duth.dip.cse.view.common.Injectable;
import duth.dip.cse.util.property.Property;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import java.awt.Color;

public class SidebarEditor extends JPanel implements Injectable {

    @Property(name = "sidebar.background.color")
    private int backgroundColor;

    public SidebarEditor() {
        super();
        configure();
    }

    public SidebarEditor configure() {
        Injectable.super.injectPropertiesTo(this);
        this.setBackground(new Color(backgroundColor));
        this.setBorder(BorderFactory.createEmptyBorder());
        return this;
    }

}
