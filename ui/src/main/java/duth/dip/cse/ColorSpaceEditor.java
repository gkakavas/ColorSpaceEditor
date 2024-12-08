package duth.dip.cse;

import duth.dip.cse.view.MainFrame;

import java.io.IOException;

public class ColorSpaceEditor {
    public static void launch() throws IOException {
        new MainFrame().configure().setVisible(true);
    }
}
