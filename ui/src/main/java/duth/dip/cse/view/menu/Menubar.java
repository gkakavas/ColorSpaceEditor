package duth.dip.cse.view.menu;

import duth.dip.cse.util.property.Property;
import duth.dip.cse.util.property.PropertyAnnotationProcessor;
import duth.dip.cse.view.common.FontIcon;
import org.kordamp.ikonli.material.Material;

import javax.swing.JToolBar;
import java.awt.Color;
import java.awt.FlowLayout;


public class Menubar extends JToolBar {

    @Property(name = "menu.background.color")
    private int backgroundColor;

    @Property(name="menu.item.open.tooltip")
    private String openItemTooltip;

    @Property(name="menu.item.clear.tooltip")
    private String clearItemTooltip;

    @Property(name="menu.item.save.tooltip")
    private String saveItemTooltip;

    @Property(name="menu.item.undo.tooltip")
    private String undoItemTooltip;

    @Property(name="menu.item.redo.tooltip")
    private String redoItemTooltip;

    private final MenuItem openItem;
    private final MenuItem clearItem;
    private final MenuItem saveItem;
    private final MenuItem undoItem;
    private final MenuItem redoItem;

    public Menubar() {
        super();
        var openIcon = new FontIcon().configure(Material.FOLDER_OPEN);
        var clearIcon = new FontIcon().configure(Material.CLEAR);
        var saveIcon = new FontIcon().configure(Material.SAVE);
        var undoIcon = new FontIcon().configure(Material.UNDO);
        var redoIcon = new FontIcon().configure(Material.REDO);

        openItem = new MenuItem().configure(openIcon,openItemTooltip);
        clearItem = new MenuItem().configure(clearIcon,clearItemTooltip);
        saveItem = new MenuItem().configure(saveIcon,saveItemTooltip);
        undoItem = new MenuItem().configure(undoIcon,undoItemTooltip);
        redoItem = new MenuItem().configure(redoIcon,redoItemTooltip);

        configure(null);
    }

    public Menubar configure(MenuItem[] items){
        PropertyAnnotationProcessor.process(this);
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.setBorderPainted(false);
        this.setFloatable(false);
        this.setBackground(new Color(backgroundColor));
        this.add(openItem);
        this.add(clearItem);
        this.add(saveItem);
        this.add(undoItem);
        this.add(redoItem);
        return this;
    }

    public MenuItem getOpenItem() {
        return openItem;
    }

    public MenuItem getClearItem(){return clearItem;}

    public MenuItem getSaveItem() {
        return saveItem;
    }

    public MenuItem getUndoItem() {
        return undoItem;
    }

    public MenuItem getRedoItem() {
        return redoItem;
    }
}
