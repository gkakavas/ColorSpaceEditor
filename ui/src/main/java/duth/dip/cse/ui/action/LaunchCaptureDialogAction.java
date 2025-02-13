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
    private final EngineApiClient engineApiClient;
    private final MenubarController menubarController;

    public LaunchCaptureDialogAction(MenubarController menubarController) {
        this.isCaptured = new AtomicBoolean(false);
        this.menubarController = menubarController;
        this.captureImgDialog = menubarController.getCaptureDialog();
        this.engineApiClient = menubarController.engineApi();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        isCaptured.set(true);
        captureImgDialog.dispose();

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save File");
        var suggestedFilename = FileNameGenerator.generateFilename();
        fileChooser.setSelectedFile(new File(SystemProperties.HOME_DIR + suggestedFilename + ".png"));
        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.CANCEL_OPTION) {
            isCaptured.set(false);
            return;
        }

        var selectedfile = fileChooser.getSelectedFile();
        var saveAndLoadAction = new SaveAndLoadImageAction(selectedfile);
        saveAndLoadAction.execute();
    }

    public AtomicBoolean getIsCaptured(){
        return isCaptured;
    }

    public class SaveAndLoadImageAction extends SwingWorker<ImageDataDTO,Void> {

        private final File selectedFile;

        public SaveAndLoadImageAction(File selectedFile) {
            this.selectedFile = selectedFile;
        }

        @Override
        public ImageDataDTO doInBackground() throws UnsupportedFileFormatException, FileAlreadyExistsException {
            if(!selectedFile.exists()) {
                var path = engineApiClient.saveImage(selectedFile.getAbsolutePath());
                return engineApiClient.loadImage(new File(path));
            } else{
                throw new FileAlreadyExistsException(selectedFile.getAbsolutePath());
            }

        }

        @Override
        public void done(){
            try {
                var imageData = get();
                menubarController.getImage().updateImage(imageData.bufferedImage());
                menubarController.getImage().updateColorModel(imageData.colorModel());
            } catch (ExecutionException e) {
                if(e.getCause() instanceof UnsupportedFileFormatException){
                    JOptionPane.showMessageDialog(
                            null,
                            "File has not been saved. Unsupported file format to be saved. Please choose a different format.",
                            "Error",
                            JOptionPane.WARNING_MESSAGE
                    );
                } else if(e.getCause() instanceof FileAlreadyExistsException){
                    JOptionPane.showMessageDialog(
                            null,
                            "File has not been saved. You cannot overwrite it.",
                            "Error",
                            JOptionPane.WARNING_MESSAGE
                    );
                }
            } catch (InterruptedException e){
                e.printStackTrace();
            } finally {
                isCaptured.set(false);
            }
        }
    }
}
