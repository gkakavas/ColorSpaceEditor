package duth.dip.cse.ui.action;

import duth.dip.cse.ui.controller.SidebarController;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.image.BufferedImage;
import java.util.concurrent.ExecutionException;

public class AdaptParametersAction implements ChangeListener {

    private final SidebarController sidebarController;

    public AdaptParametersAction(SidebarController sidebarController) {
        this.sidebarController = sidebarController;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        var values = sidebarController.getSlidersValues();
        var adaptParametersTask = new AdaptParametersTask(values);
        adaptParametersTask.execute();
    }

    public class AdaptParametersTask extends SwingWorker<BufferedImage, Void> {

        private final int[] values;

        public AdaptParametersTask(int[] values) {
            this.values = values;
        }

        @Override
        protected BufferedImage doInBackground() {
            return sidebarController.apiClient().applyColorFactor(values);
        }

        @Override
        protected void done() {
            try {
                var image = get();
                sidebarController.getImage().updateImage(image);
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
