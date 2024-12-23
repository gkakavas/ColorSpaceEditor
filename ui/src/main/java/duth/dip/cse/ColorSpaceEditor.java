package duth.dip.cse;

import duth.dip.cse.view.MainFrame;
import duth.dip.cse.view.menu.CaptureDialog;

import java.io.IOException;

public class ColorSpaceEditor {
    public static void launch() throws IOException {
       try {
            var mainFraim = new MainFrame();
            mainFraim.setVisible(true);
            //new CaptureDialog();
        } catch (IOException e) {
                throw new RuntimeException(e);
        }
//        EngineApiClient engineApiClient = new EngineApiClient(Configuration.engine());
//        engineApiClient.capturePhoto(new ImageIcon(),true);
//        engineApiClient.saveImage(true);
    }

}
