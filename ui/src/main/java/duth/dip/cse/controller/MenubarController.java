package duth.dip.cse.controller;

import duth.dip.cse.service.MenuService;
import duth.dip.cse.view.menu.MenuItem;
import duth.dip.cse.view.menu.Menubar;
import duth.dip.cse.view.panel.viewer.Viewer;

import javax.swing.*;
import java.io.File;
import java.util.concurrent.CompletableFuture;

public class MenubarController {

    private final Menubar menubar;
    private final Viewer viewer;

    public MenubarController(Menubar menubar, Viewer viewer) {
        this.menubar = menubar;
        this.viewer = viewer;
        init();
    }

    public void init(){
        menubar.getOpenItem().addListener(
                () -> {
                    final JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                    int returnValue = fileChooser.showOpenDialog(menubar);
                    /*fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
                    "Image Files", "jpg", "png", "gif", "bmp"));*/ //TODO

                    if (returnValue == JFileChooser.APPROVE_OPTION) {
                        File selectedFile = fileChooser.getSelectedFile();
                        String path = selectedFile.getAbsolutePath();
                        viewer.setIcon(new ImageIcon(path));
                    }
                }
        );

        menubar.getClearItem().addListener(
                () -> viewer.setIcon(null)
        );
    }

}
