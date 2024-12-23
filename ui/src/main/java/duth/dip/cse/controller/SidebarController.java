package duth.dip.cse.controller;

import duth.dip.cse.engine.api.Engine;
import duth.dip.cse.service.EngineApiClient;
import duth.dip.cse.view.panel.sidebar.SidebarEditor;
import duth.dip.cse.view.panel.viewer.Viewer;

public class SidebarController {

    private final SidebarEditor sidebarEditor;
    private final Viewer viewer;
    private final EngineApiClient apiClient;

    public SidebarController(SidebarEditor sidebarEditor, Viewer viewer, EngineApiClient apiClient){
        this.sidebarEditor = sidebarEditor;
        this.viewer = viewer;
        this.apiClient = apiClient;
    }




}
