package duth.dip.cse.ui.view.menu;

import duth.dip.cse.engine.utils.Property;
import duth.dip.cse.ui.view.common.FontIcon;
import duth.dip.cse.ui.view.common.Injectable;
import org.kordamp.ikonli.material.Material;

import javax.swing.*;
import java.awt.*;


public class Menubar extends JToolBar implements Injectable {

    @Property(name = "menu.background.color")
    private int backgroundColor;

    @Property(name="menu.item.open.tooltip")
    private String openItemTooltip;

    @Property(name="menu.item.capture.tooltip")
    private String captureItemTooltip;

    @Property(name="menu.item.clear.tooltip")
    private String clearItemTooltip;

    @Property(name="menu.item.save.tooltip")
    private String saveItemTooltip;

    @Property(name="menu.item.revert.tooltip")
    private String revertItemTooltip;

    private final MenuItem openItem;
    private final MenuItem captureItem;
    private final MenuItem clearItem;
    private final MenuItem exportItem;
    private final MenuItem revertItem;
    private final JFileChooser fileChooser;
    private final CaptureDialog captureDialog;

    public Menubar() {
        super();
        injectPropertiesTo(this);

        var openIcon = new FontIcon(Material.FOLDER_OPEN);
        var captureIcon = new FontIcon(Material.PHOTO_CAMERA);
        var clearIcon = new FontIcon(Material.CLEAR);
        var saveIcon = new FontIcon(Material.PUBLISH);
        var revertIcon = new FontIcon(Material.REPLAY_CIRCLE_FILLED);

        openItem = new MenuItem(openIcon,openItemTooltip);
        captureItem = new MenuItem(captureIcon,captureItemTooltip);
        clearItem = new MenuItem(clearIcon,clearItemTooltip);
        exportItem = new MenuItem(saveIcon,saveItemTooltip);
        revertItem = new MenuItem(revertIcon,revertItemTooltip);

        fileChooser = new JFileChooser();
        captureDialog = new CaptureDialog();
        configure();
    }

    public void configure(){
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.setBorderPainted(false);
        this.setFloatable(false);
        this.setBackground(new Color(backgroundColor));
        this.add(openItem);
        this.add(captureItem);
        this.add(exportItem);
        this.add(revertItem);
        this.add(clearItem);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    }

    public MenuItem getOpenItem() {
        return openItem;
    }

    public MenuItem getCaptureItem(){return captureItem;}

    public MenuItem getClearItem(){return clearItem;}

    public MenuItem getExportItem() {
        return exportItem;
    }

    public MenuItem getRevertItem() { return revertItem; }

    public JFileChooser getFileChooser(){
        return fileChooser;
    }

    public CaptureDialog getCaptureDialog(){
        return captureDialog;
    }

}
