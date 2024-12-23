package duth.dip.cse.view.panel;

import duth.dip.cse.view.common.Injectable;
import duth.dip.cse.util.property.Property;
import duth.dip.cse.view.panel.sidebar.SidebarEditor;
import duth.dip.cse.view.panel.viewer.Viewer;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel implements Injectable {

    @Property(name = "mainPanel.background.color")
    private int backgroundColor;

    private final SidebarEditor sidebarEditor;
    private final Viewer viewer;

    public MainPanel() {
        super(new BorderLayout());
        sidebarEditor = new SidebarEditor();
        viewer = new Viewer();
        configure();
    }

    public MainPanel configure() {

        injectPropertiesTo(this);
        this.setBackground(new Color(backgroundColor));
        this.setBorder(BorderFactory.createEmptyBorder());

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

        var sidebarEditorWidth = dimension.width*0.25;
        var viewerWidth = dimension.width*0.75;

        viewer.setPreferredSize(new Dimension((int)viewerWidth,dimension.height));
        sidebarEditor.setPreferredSize(new Dimension((int)sidebarEditorWidth,dimension.height));

        this.add(viewer, BorderLayout.CENTER);
        this.add(sidebarEditor, BorderLayout.WEST);
        return this;
    }

    public SidebarEditor getSidebarEditor() {
        return sidebarEditor;
    }

    public Viewer getViewer() {
        return viewer;
    }
}
