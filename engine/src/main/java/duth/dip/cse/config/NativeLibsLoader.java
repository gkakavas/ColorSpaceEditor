package duth.dip.cse.config;

import org.opencv.core.Core;

public class NativeLibsLoader {
    public static void load(){
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }
}
