package duth.dip.cse.ui.action;

import duth.dip.cse.ui.controller.MenubarController;
import duth.dip.cse.ui.service.EngineApiClient;
import duth.dip.cse.ui.view.menu.CaptureDialog;

import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.util.concurrent.atomic.AtomicBoolean;

public class CloseCaptureImageAction extends AbstractAction {

    private final AtomicBoolean isCaptured;
    private final CaptureDialog captureImgDialog;
    private final EngineApiClient engineApiClient;
    private final MenubarController menubarController;

    public CloseCaptureImageAction(MenubarController menubarController) {
        this.isCaptured = new AtomicBoolean();
        this.menubarController = menubarController;
        this.captureImgDialog = menubarController.getCaptureDialog();
        this.engineApiClient = menubarController.engineApi();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        engineApiClient.stopVideoCapturing();
        isCaptured.set(false);
        captureImgDialog.setVisible(false);
    }
}
