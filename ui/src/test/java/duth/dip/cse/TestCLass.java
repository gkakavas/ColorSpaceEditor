package duth.dip.cse;

import duth.dip.cse.engine.api.EngineConfig;
import duth.dip.cse.engine.api.EngineImpl;
import duth.dip.cse.util.property.PropertyAnnotationProcessor;
import duth.dip.cse.view.MainFrame;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TestCLass {
    @Test
    public void test() throws IOException {
       TestCLass123 testCLass123 = new TestCLass123();
        System.out.println(testCLass123);
       PropertyAnnotationProcessor.process(testCLass123);
        System.out.println(testCLass123);
    }

    @Test
    public void test1() throws IOException {
        /*var item = new CSEMenuItem();
        item.configure(new CSEFontIcon(Material.FOLDER_OPEN),"A tooltip");

        var toolbar = new CSEMenubar();
        toolbar.configure();
        var col2 = new Color(0x121212);
*/      for(UIManager.LookAndFeelInfo laf :UIManager.getInstalledLookAndFeels()){
            System.out.println(laf.getName());
        };
    }

    @Test
    public void test2() throws IOException, InterruptedException {

    }
}
