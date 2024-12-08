package duth.dip.cse.view.panel.sidebar;

import duth.dip.cse.action.MenuActionListener;
import duth.dip.cse.util.property.UI;

import javax.swing.JButton;

@UI
public class Button extends JButton {
    public Button() {
        super();
    }

    public void addListener(MenuActionListener actionListener) {
        this.addActionListener(actionListener);
    }
}
