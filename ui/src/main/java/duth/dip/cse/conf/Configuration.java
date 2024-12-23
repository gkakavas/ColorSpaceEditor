package duth.dip.cse.conf;

import duth.dip.cse.engine.api.Engine;
import duth.dip.cse.engine.api.EngineConfig;
import duth.dip.cse.engine.api.EngineImpl;

import java.io.IOException;

public class Configuration {

    public static Engine engine() throws IOException {
        var engineConf = new EngineConfig();
        return new EngineImpl(engineConf);
    }

}
