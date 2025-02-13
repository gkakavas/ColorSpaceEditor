package duth.dip.cse.ui.conf;

import duth.dip.cse.engine.api.Engine;
import duth.dip.cse.engine.core.EngineImpl;

import java.io.IOException;

public class Configuration {

    public static Engine engine() throws IOException {
        return new EngineImpl();
    }

}
