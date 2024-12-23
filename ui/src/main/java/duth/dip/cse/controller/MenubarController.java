package duth.dip.cse.controller;

import duth.dip.cse.action.ClearWorkspaceAction;
import duth.dip.cse.service.EngineApiClient;
import duth.dip.cse.util.tool.FileNameGenerator;
import duth.dip.cse.view.menu.CaptureDialog;
import duth.dip.cse.view.menu.Menubar;
import duth.dip.cse.view.panel.viewer.Viewer;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;

public class MenubarController {

    private final Menubar menubar;
    private final Viewer viewer;
    private final EngineApiClient engineApiClient;

    public MenubarController(Menubar menubar, Viewer viewer, EngineApiClient model) {
        this.menubar = menubar;
        this.viewer = viewer;
        this.engineApiClient = model;
        registerLoadFileListener();
        registerCaptureImageListener();
        registerClearWorkspaceListener();
    }

    private void registerLoadFileListener() {
        menubar.getOpenItem().addActionListener(actionEvent -> {
            final JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int returnValue = fileChooser.showOpenDialog(null);
            //fileChooser.setFileFilter(new FileNameExtensionFilter()); TODO
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                new SwingWorker<>() {

                    @Override
                    protected ImageIcon doInBackground() throws IOException{
                        return engineApiClient.loadImage(viewer.getImage(),selectedFile);
                    }

                    @Override
                    protected void done(){
                        try {
                            var image = (ImageIcon) get();
                            viewer.updateImage(image);
                        } catch (InterruptedException | ExecutionException e) {
                            throw new RuntimeException(e);
                        }
                    }

                }.execute();
            }
        });
    }

    public void registerCaptureImageListener(){
       //var action = new CaptureImageAction(model,viewer);
        menubar.getCaptureItem().addActionListener(actionEvent -> {
            var captureImgDialog = new CaptureDialog();
            var isCaptured = new AtomicBoolean(false);
            captureImgDialog.getCaptureBtn().addActionListener(actionEvent1 -> {
                isCaptured.set(true);
                captureImgDialog.dispose();
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Save File");
                var suggestedFilename = FileNameGenerator.generateFilename();
                fileChooser.setSelectedFile(new File("/home/george/Pictures/" + suggestedFilename + ".png"));
                int userSelection = fileChooser.showSaveDialog(null);
                if (userSelection == JFileChooser.CANCEL_OPTION) {
                    return;
                }
                var selectedfile = fileChooser.getSelectedFile();
                if(!selectedfile.exists()){
                    engineApiClient.saveImageOnDeclaredFile(selectedfile.getAbsolutePath());
                    try {
                        engineApiClient.loadImage(viewer.getImage(),selectedfile);
                        viewer.updateImage();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    return;
                }

                int overwrite = JOptionPane.showConfirmDialog(
                        null,
                        "File already exists. Overwrite?",
                        "File Exists",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );
                if (overwrite != -1) {
                    engineApiClient.saveImageOnDeclaredFile(selectedfile.getAbsolutePath());
                }

            });

            new SwingWorker<Void, Void>(){

                @Override
                protected Void doInBackground() throws Exception {
                    engineApiClient.startCapturing();
                    while (!isCaptured.get()){
                        engineApiClient.capturePhoto(captureImgDialog.getImage(),false);
                        publish((Void) null);
                    }
                    engineApiClient.capturePhoto(captureImgDialog.getImage(),true);
                    engineApiClient.stopVideoCapturing();
                    return null;
                }

                @Override
                protected void process(List<Void> chunks){
                    captureImgDialog.refresh();
                }
            }.execute();
        });
    }

    private void registerClearWorkspaceListener() {
        var action = new ClearWorkspaceAction(viewer, engineApiClient);
        menubar.getClearItem().addActionListener(action);
    }


    public void showCaptureDialog(){

    }


}
