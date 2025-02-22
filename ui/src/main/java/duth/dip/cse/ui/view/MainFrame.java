package duth.dip.cse.ui.view;

import duth.dip.cse.engine.domain.ColorModel;
import duth.dip.cse.ui.conf.Configuration;
import duth.dip.cse.ui.controller.MenubarController;
import duth.dip.cse.ui.controller.SidebarController;
import duth.dip.cse.ui.controller.ViewerController;
import duth.dip.cse.ui.model.Image;
import duth.dip.cse.ui.service.EngineApiClient;
import duth.dip.cse.ui.view.common.Injectable;
import duth.dip.cse.engine.utils.Property;
import duth.dip.cse.ui.view.menu.Menubar;
import duth.dip.cse.ui.view.panel.MainPanel;

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
    private final ViewerController viewerController;
    private final EngineApiClient engineApiClient;
    private final SidebarController sidebarController;
    private final Image image;

    public MainFrame() throws IOException {
        super();
        mainPanel = new MainPanel();
        menuBar = new Menubar();
        engineApiClient = new EngineApiClient(Configuration.engine());
        image = new Image(mainPanel.getViewer().getImage(),new int[4], ColorModel.sRGB);
        viewerController = new ViewerController(mainPanel.getViewer(),image);
        image.attachImageListener(viewerController);
        sidebarController = new SidebarController(mainPanel.getSidebarEditor(),image, engineApiClient);
        new MenubarController(menuBar,image,sidebarController, engineApiClient);

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
