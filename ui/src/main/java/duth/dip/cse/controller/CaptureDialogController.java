package duth.dip.cse.controller;

import duth.dip.cse.action.CaptureBtnAction;
import duth.dip.cse.action.CloseBtnAction;
import duth.dip.cse.service.EngineApiClient;
import duth.dip.cse.util.tool.Flag;
import duth.dip.cse.view.menu.CaptureDialog;
import duth.dip.cse.view.panel.viewer.Viewer;

public class CaptureDialogController {

    private final CaptureDialog captureDialog;
    private final EngineApiClient apiClient;
    private final Viewer viewer;
    private final Flag isCaptured;

    public CaptureDialogController(CaptureDialog captureDialog, EngineApiClient apiClient, Viewer viewer, Flag isCaptured){
        this.captureDialog = captureDialog;
        this.apiClient = apiClient;
        this.viewer = viewer;
        this.isCaptured = isCaptured;
        registerCaptureBtnListener();
        registerCloseBtnListener();
    }

    private void registerCaptureBtnListener(){
        var action = new CaptureBtnAction(captureDialog,apiClient,viewer,isCaptured);
        captureDialog.getCaptureBtn().addActionListener(action);
    }

    private void registerCloseBtnListener(){
        var action = new CloseBtnAction();
        captureDialog.getCloseBtn().addActionListener(action);
    }



}
