package duth.dip.cse.action;

import duth.dip.cse.service.EngineApiClient;
import duth.dip.cse.util.tool.FileNameGenerator;
import duth.dip.cse.util.tool.Flag;
import duth.dip.cse.view.menu.CaptureDialog;
import duth.dip.cse.view.panel.viewer.Viewer;

import javax.swing.*;

import java.awt.event.ActionEvent;

public class CaptureBtnAction extends AbstractAction{

    private final CaptureDialog captureDialog;
    private final EngineApiClient engineClient;
    private final Viewer viewer;
    private final Flag isCaptured;

    public CaptureBtnAction(CaptureDialog captureDialog, EngineApiClient engineClient, Viewer viewer, Flag flag){
        this.captureDialog = captureDialog;
        this.engineClient = engineClient;
        this.viewer = viewer;
        this.isCaptured = flag;
    }

    @Override
    protected void onActionEvent(ActionEvent event) {
        var button = (JButton) event.getSource();
        button.setEnabled(false);
        var capturedImage = engineClient.capturePhoto(captureDialog.getImage(),true);
        isCaptured.set(true);
        var suggestedFilename = FileNameGenerator.generateFilename();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save File");
        int userSelection = fileChooser.showSaveDialog(null);
        if (userSelection != JFileChooser.CANCEL_OPTION) {
            return;
        }
        var selectedfile = fileChooser.getSelectedFile();
        if (selectedfile.exists()) {
            int overwrite = JOptionPane.showConfirmDialog(
                    null,
                    "File already exists. Overwrite?",
                    "File Exists",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }

    @Override
    protected void afterActionEvent(ActionEvent event) {
        var button = (JButton) event.getSource();
        button.setEnabled(true);
    }
}
