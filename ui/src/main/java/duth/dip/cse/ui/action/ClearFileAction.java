package duth.dip.cse.ui.action;

import duth.dip.cse.ui.controller.MenubarController;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ClearFileAction extends AbstractAction {

    private final MenubarController menubarController;

    public ClearFileAction(MenubarController menubarController) {
        this.menubarController = menubarController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        menubarController.engineApi().clearImage();
        menubarController.getImage().clear();
        menubarController.updateColorSpacePanel();
    }
}
