package duth.dip.cse.ui;

import duth.dip.cse.ui.view.MainFrame;

import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;
import java.io.IOException;

public class ColorSpaceEditor {
    public static void launch() {
        SwingUtilities.invokeLater(() -> {
            try {
                new MainFrame().setVisible(true);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
