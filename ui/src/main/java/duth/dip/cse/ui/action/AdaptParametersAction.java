package duth.dip.cse.ui.action;

import duth.dip.cse.engine.domain.IntensityImageDTO;
import duth.dip.cse.ui.controller.SidebarController;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.concurrent.ExecutionException;

public class AdaptParametersAction implements ChangeListener {

    private final SidebarController sidebarController;

    public AdaptParametersAction(SidebarController sidebarController) {
        this.sidebarController = sidebarController;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider) e.getSource();
        if(!source.getValueIsAdjusting()) {
            var values = sidebarController.getSlidersValues();
            var adaptParametersTask = new AdaptParametersTask(values);
            adaptParametersTask.execute();
        }
    }

    public class AdaptParametersTask extends SwingWorker<IntensityImageDTO, Void> {

        private final int[] values;

        public AdaptParametersTask(int[] values) {
            this.values = values;
        }

        @Override
        protected IntensityImageDTO doInBackground() {
            return sidebarController.apiClient().applyColorFactor(values);
        }

        @Override
        protected void done() {
            try {
                var image = get();
                sidebarController.getImage().updateImage(image.image());
                sidebarController.setSliderValues(image.values());
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
