package duth.dip.cse.view;

import duth.dip.cse.controller.MenubarController;
import duth.dip.cse.view.common.Injectable;
import duth.dip.cse.util.property.Property;
import duth.dip.cse.view.menu.Menubar;
import duth.dip.cse.view.panel.MainPanel;

import javax.swing.JFrame;
import java.awt.BorderLayout;

public class MainFrame extends JFrame implements Injectable {

    @Property(name="mainFrame.title")
    private String title;

    @Property(name="mainFrame.resizable")
    private boolean resizable;

    private final MainPanel mainPanel;
    private final Menubar menuBar;

    public MainFrame() {
        super();
        mainPanel = new MainPanel();
        menuBar = new Menubar();
        new MenubarController(menuBar,mainPanel.getViewer());
    }

    public MainFrame configure(){

        injectPropertiesTo(this);
        this.setLayout(new BorderLayout());
        this.setTitle(title);
        this.setResizable(resizable);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        this.add(menuBar, BorderLayout.NORTH);
        this.add(mainPanel, BorderLayout.CENTER);

        return this;
    }
}
