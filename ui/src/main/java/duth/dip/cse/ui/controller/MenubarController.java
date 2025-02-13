package duth.dip.cse.ui.controller;

import duth.dip.cse.engine.domain.ColorModel;
import duth.dip.cse.engine.utils.SystemProperties;
import duth.dip.cse.ui.action.*;
import duth.dip.cse.ui.model.Image;
import duth.dip.cse.ui.service.EngineApiClient;
import duth.dip.cse.ui.view.menu.CaptureDialog;
import duth.dip.cse.ui.view.menu.Menubar;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

public class MenubarController {

    private final Menubar menubar;
    private final Image image;
    private final SidebarController sidebarController;
    private final EngineApiClient engineApiClient;
    private final LoadImageAction loadImageAction;
    private final CaptureImageAction captureImageAction;
    private final LaunchCaptureDialogAction launchCaptureDialogAction;
    private final CloseCaptureImageAction closeCaptureImageAction;
    private final ClearFileAction clearFileAction;
    private final SaveFileAction saveFileAction;

    public MenubarController(Menubar menubar, Image image, SidebarController sidebarController, EngineApiClient engineApiClient) {

        this.menubar = menubar;
        this.image = image;
        this.sidebarController = sidebarController;
        this.engineApiClient = engineApiClient;

        this.loadImageAction = new LoadImageAction(this);
        this.launchCaptureDialogAction = new LaunchCaptureDialogAction(this);
        this.captureImageAction = new CaptureImageAction(this,launchCaptureDialogAction.getIsCaptured());
        this.closeCaptureImageAction = new CloseCaptureImageAction(this);
        this.clearFileAction = new ClearFileAction(this);
        this.saveFileAction = new SaveFileAction(this);

        menubar.getOpenItem().addActionListener(loadImageAction);
        menubar.getCaptureItem().addActionListener(captureImageAction);
        menubar.getCaptureDialog().getCaptureBtn().addActionListener(launchCaptureDialogAction);
        menubar.getCaptureDialog().getCloseBtn().addActionListener(closeCaptureImageAction);
        menubar.getClearItem().addActionListener(clearFileAction);
        menubar.getSaveItem().addActionListener(saveFileAction);

        addMenubarOnCloseListener();

    }

    private void addMenubarOnCloseListener(){
        menubar.getCaptureDialog().addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                engineApiClient.stopVideoCapturing();
                launchCaptureDialogAction.getIsCaptured().set(false);
            }
        });
    }

    public int showFileChooser(){
        menubar.getFileChooser().setSelectedFile(new File(SystemProperties.HOME_DIR));
        return menubar.getFileChooser().showOpenDialog(null);
    }

    public File getFileChooserInput(){
        return menubar.getFileChooser().getSelectedFile();
    }

    public EngineApiClient engineApi(){
        return engineApiClient;
    }

    public CaptureDialog getCaptureDialog(){
        return menubar.getCaptureDialog();
    }

    public void setSelectedItem(ColorModel colorModel){
        sidebarController.setSelectedItem(colorModel);
    }

    public Image getImage(){
        return image;
    }

    public void updateColorSpacePanel(){
        sidebarController.updateSlidersPanel();
    }

}
