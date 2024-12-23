package duth.dip.cse.view;

import duth.dip.cse.conf.Configuration;
import duth.dip.cse.controller.MenubarController;
import duth.dip.cse.service.EngineApiClient;
import duth.dip.cse.view.common.Injectable;
import duth.dip.cse.util.property.Property;
import duth.dip.cse.view.menu.Menubar;
import duth.dip.cse.view.panel.MainPanel;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.io.IOException;

public class MainFrame extends JFrame implements Injectable {

    @Property(name="mainFrame.title")
    private String title;

    @Property(name="mainFrame.resizable")
    private boolean resizable;

    private final MainPanel mainPanel;
    private final Menubar menuBar;
    private final EngineApiClient engineApiClient;

    public MainFrame() throws IOException {
        super();
        mainPanel = new MainPanel();
        menuBar = new Menubar();
        engineApiClient = new EngineApiClient(Configuration.engine());
        new MenubarController(menuBar,mainPanel.getViewer(), engineApiClient);
        configure();
    }

    private void configure(){
        injectPropertiesTo(this);
        this.setLayout(new BorderLayout());
        this.setTitle(title);
        this.setResizable(resizable);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.add(menuBar, BorderLayout.NORTH);
        this.add(mainPanel, BorderLayout.CENTER);
    }
}
