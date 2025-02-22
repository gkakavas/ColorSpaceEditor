package duth.dip.cse.ui.action;

import duth.dip.cse.engine.domain.ImageDataDTO;
import duth.dip.cse.engine.utils.SystemProperties;
import duth.dip.cse.engine.utils.FileNameGenerator;
import duth.dip.cse.engine.exception.UnsupportedFileFormatException;
import duth.dip.cse.ui.controller.MenubarController;
import duth.dip.cse.ui.service.EngineApiClient;
import duth.dip.cse.ui.view.menu.CaptureDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.nio.file.FileAlreadyExistsException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;

public class LaunchCaptureDialogAction extends AbstractAction {

    private final AtomicBoolean isCaptured;
    private final CaptureDialog captureImgDialog;

    public LaunchCaptureDialogAction(MenubarController menubarController) {
        this.isCaptured = new AtomicBoolean(false);
        this.captureImgDialog = menubarController.getCaptureDialog();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        isCaptured.set(true);
        captureImgDialog.dispose();
    }

    public AtomicBoolean getIsCaptured(){
        return isCaptured;
    }
}
