package duth.dip.cse.ui.controller;

import duth.dip.cse.engine.domain.ColorModel;
import duth.dip.cse.engine.domain.ImageDataDTO;
import duth.dip.cse.ui.action.AdaptParametersAction;
import duth.dip.cse.ui.action.SelectColorModelAction;
import duth.dip.cse.ui.model.Image;
import duth.dip.cse.ui.service.EngineApiClient;
import duth.dip.cse.ui.view.panel.sidebar.SidebarEditor;
import duth.dip.cse.ui.view.panel.sidebar.elements.Slider;
import duth.dip.cse.ui.view.panel.sidebar.elements.colorspace.*;

import java.util.Arrays;

public class SidebarController {

    private final SidebarEditor sidebarEditor;
    private final Image image;
    private final EngineApiClient apiClient;
    private final SelectColorModelAction selectColorModelAction;
    private final AdaptParametersAction adaptParametersAction;

    public SidebarController(SidebarEditor sidebarEditor, Image image, EngineApiClient apiClient){
        this.sidebarEditor = sidebarEditor;
        this.image = image;
        this.apiClient = apiClient;
        this.selectColorModelAction = new SelectColorModelAction(this);
        this.adaptParametersAction = new AdaptParametersAction(this);
        sidebarEditor.getPanels().forEach((colorModel, colorSpacePanel) -> {
            Arrays.stream(colorSpacePanel.getSliders()).forEach(slider -> slider.addChangeListener(adaptParametersAction));
        });
        configure();
    }




    private void configure(){
        sidebarEditor.getCombobox().addActionListener(selectColorModelAction);
    }

    public ColorModel getSelectedValue() {
       return (ColorModel) sidebarEditor.getCombobox().getSelectedItem();
    }

    public EngineApiClient apiClient(){
        return apiClient;
    }

    public Image getImage(){
        return image;
    }

    public void updateSlidersPanel() {
        sidebarEditor.updateSlidersPanel(image.getColorModel());
        resetSliders();
    }

    public void setSelectedItem(ColorModel colorModel){
        sidebarEditor.getCombobox().setSelectedItem(colorModel);
    }

    public int[] getSlidersValues() {
        return sidebarEditor.getSliderValues();
    }

    public void resetSliders(){
        sidebarEditor.getPanels()
                .forEach((colorModel,panel) ->
                        Arrays.stream(panel.getSliders())
                                .forEach(Slider::reset));
    }

}
