package duth.dip.cse.ui.action;

import duth.dip.cse.engine.domain.ColorModel;
import duth.dip.cse.engine.domain.ImageDataDTO;
import duth.dip.cse.ui.controller.SidebarController;
import duth.dip.cse.ui.view.panel.sidebar.elements.colorspace.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

public class SelectColorModelAction extends AbstractAction {

    private final SidebarController sidebarController;

    public SelectColorModelAction(SidebarController sidebarController) {
        this.sidebarController = sidebarController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getModifiers() == 0) {
            return;
        }
        var selectedColorModel = sidebarController.getSelectedValue();
        var convertColorModelTask = new ConvertColorModelTask(selectedColorModel);
        convertColorModelTask.execute();
    }


    public class ConvertColorModelTask extends SwingWorker<Optional<ImageDataDTO>, Void> {

        private final ColorModel colorModel;

        public ConvertColorModelTask(ColorModel colorModel) {
            this.colorModel = colorModel;
        }

        @Override
        public Optional<ImageDataDTO> doInBackground() {
            return sidebarController.apiClient().convertColorSpace(colorModel);
        }

        @Override
        public void done() {
            try {
                var imageData = get();
                imageData.ifPresent(imageDataDTO -> {
                    sidebarController.getImage().updateImage(imageDataDTO.bufferedImage());
                    sidebarController.getImage().updateColorModel(imageDataDTO.colorModel());
                    sidebarController.updateSlidersPanel();
                });
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
