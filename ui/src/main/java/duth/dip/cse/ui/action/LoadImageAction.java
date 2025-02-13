package duth.dip.cse.ui.action;

import duth.dip.cse.engine.domain.ColorModel;
import duth.dip.cse.engine.domain.ImageDataDTO;
import duth.dip.cse.ui.controller.MenubarController;
import duth.dip.cse.ui.service.EngineApiClient;

import javax.swing.*;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class LoadImageAction extends AbstractAction {

    private final MenubarController menubarController;
    private final EngineApiClient engineApiClient;

    public LoadImageAction(MenubarController menubarController){
        this.menubarController = menubarController;
        this.engineApiClient = menubarController.engineApi();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        var fileChooserOption = menubarController.showFileChooser();
        if (fileChooserOption == JFileChooser.APPROVE_OPTION) {
            File selectedFile = menubarController.getFileChooserInput();
            var loadImageTask = new LoadImageTask(selectedFile);
            loadImageTask.execute();
        }
    }

    private class LoadImageTask extends SwingWorker<ImageDataDTO,Void>{

        private final File selectedFile;

        public LoadImageTask(File selectedFile){
            this.selectedFile = selectedFile;
        }

        @Override
        public ImageDataDTO doInBackground() {
            return engineApiClient.loadImage(selectedFile);
        }

        @Override
        public void done(){
            try {
                var imageData = get();
                menubarController.setSelectedItem(imageData.colorModel());
                menubarController.getImage().updateImage(imageData.bufferedImage());
                menubarController.getImage().updateColorModel(imageData.colorModel());
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
