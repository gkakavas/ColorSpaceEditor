package duth.dip.cse.ui.controller;

import duth.dip.cse.ui.model.Image;
import duth.dip.cse.ui.model.ImageListener;
import duth.dip.cse.ui.view.panel.viewer.Viewer;

public class ViewerController implements ImageListener {

    private final Viewer viewer;
    private final Image image;

    public ViewerController(Viewer viewer, Image image){
        this.viewer = viewer;
        this.image = image;
    }

    @Override
    public void onImageRefresh() {
        viewer.updateImage(image.getImage());
    }

}
