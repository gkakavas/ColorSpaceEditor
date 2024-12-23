package duth.dip.cse.action;

import duth.dip.cse.service.EngineApiClient;
import duth.dip.cse.view.panel.viewer.Viewer;

import javax.swing.JButton;
import java.awt.event.ActionEvent;

public class ClearWorkspaceAction extends AbstractAction {

    private final Viewer viewer;
    private final EngineApiClient engineClient;

    public ClearWorkspaceAction(Viewer viewer, EngineApiClient engineClient) {
        this.viewer = viewer;
        this.engineClient = engineClient;
    }

    @Override
    protected void onActionEvent(ActionEvent event) {
        var button = (JButton) event.getSource();
        button.setEnabled(false);
        var clearImage = engineClient.clearImage(viewer.getImage());
        viewer.updateImage(clearImage);
    }

    @Override
    protected void afterActionEvent(ActionEvent event) {
        var button = (JButton) event.getSource();
        button.setEnabled(true);
    }
}
