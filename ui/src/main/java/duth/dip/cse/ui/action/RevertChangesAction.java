package duth.dip.cse.ui.action;

import duth.dip.cse.engine.domain.ImageDataDTO;
import duth.dip.cse.engine.domain.IntensityImageDTO;
import duth.dip.cse.ui.controller.MenubarController;
import duth.dip.cse.ui.controller.SidebarController;

import javax.swing.AbstractAction;
import javax.swing.SwingWorker;
import java.awt.event.ActionEvent;
import java.util.concurrent.ExecutionException;

public class RevertChangesAction extends AbstractAction {

    private final MenubarController menubarController;
    private final SidebarController sidebarController;

    public RevertChangesAction(MenubarController menubarController, SidebarController sidebarController) {
        this.menubarController = menubarController;
        this.sidebarController = sidebarController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new RevertChangesTask().execute();
    }

    public class RevertChangesTask extends SwingWorker<IntensityImageDTO, Void> {

        @Override
        public IntensityImageDTO doInBackground() {
            return menubarController.engineApi().revertChanges();
        }

        @Override
        public void done() {
            try {
                var intensityImageDTO = get();
                menubarController.getImage().updateImage(intensityImageDTO.image());
                sidebarController.setSliderValues(intensityImageDTO.values());
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
