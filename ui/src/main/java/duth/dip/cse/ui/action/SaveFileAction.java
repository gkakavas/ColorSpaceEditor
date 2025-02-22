package duth.dip.cse.ui.action;

import duth.dip.cse.engine.domain.ImageFileType;
import duth.dip.cse.engine.exception.UnsupportedFileFormatException;
import duth.dip.cse.ui.controller.MenubarController;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.io.File;

public class SaveFileAction extends AbstractAction {

    private final MenubarController menubarController;

    public SaveFileAction(final MenubarController menubarController) {
        this.menubarController = menubarController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var pathToSave = menubarController.engineApi().createFilePath();
        var fileChooser = new JFileChooser();
        fileChooser.setSelectedFile(new File(pathToSave));
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        for(ImageFileType imageFileType: ImageFileType.values()) {
            fileChooser.addChoosableFileFilter(new FileNameExtensionFilter(
                    imageFileType.name() + " Image (*." + imageFileType.name() + ")", imageFileType.extension));
        }
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addPropertyChangeListener(JFileChooser.FILE_FILTER_CHANGED_PROPERTY, evt -> {
            FileNameExtensionFilter selectedFilter = (FileNameExtensionFilter) fileChooser.getFileFilter();
            var fileExtension = pathToSave.substring(pathToSave.lastIndexOf('.') + 1);
            var newPathToSave = pathToSave.replace(fileExtension,selectedFilter.getExtensions()[0]);
            fileChooser.setSelectedFile(new File(newPathToSave));
        });

        if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            var selectedPath = fileChooser.getSelectedFile();
            try {
                menubarController.engineApi().saveImage(selectedPath.getAbsolutePath());
            } catch (UnsupportedFileFormatException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
