package duth.dip.cse.ui.action;

import duth.dip.cse.ui.controller.MenubarController;
import duth.dip.cse.ui.view.menu.CaptureDialog;

import javax.swing.AbstractAction;
import javax.swing.SwingWorker;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class CaptureImageAction extends AbstractAction {

    private final MenubarController menubarController;
    private final AtomicBoolean isCaptured;

    public CaptureImageAction(MenubarController menubarController, AtomicBoolean isCaptured){
        this.menubarController = menubarController;
        this.isCaptured = isCaptured;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        menubarController.engineApi().startCapturing();
        menubarController.getCaptureDialog().setVisible(true);
        var captureTask  = new CaptureImageTask(isCaptured,menubarController.getCaptureDialog());
        captureTask.execute();
    }

    private class CaptureImageTask extends SwingWorker<Void,Void> {

        private final AtomicBoolean isCaptured;
        private final CaptureDialog captureDialog;

        public CaptureImageTask(AtomicBoolean isCaptured, CaptureDialog captureDialog){
            this.isCaptured = isCaptured;
            this.captureDialog = captureDialog;
        }

        @Override
        public Void doInBackground() {
            while (!isCaptured.get()){
                menubarController.engineApi().capturePhoto(captureDialog.getImage(),false);
                publish((Void) null);
            }
            menubarController.engineApi().capturePhoto(captureDialog.getImage(),true);
            menubarController.engineApi().stopVideoCapturing();
            return null;
        }

        @Override
        public void process(List<Void> chunks) {
            captureDialog.refresh();
        }

    }

}
