package duth.dip.cse.controller;

import duth.dip.cse.view.panel.viewer.Viewer;

import javax.swing.ImageIcon;

public class ViewerController {

    private final Viewer viewer;

    public ViewerController(Viewer viewer){
        this.viewer = viewer;
    }

    public void updateImage(ImageIcon imageIcon){
        viewer.updateImage(imageIcon);
    }

}
