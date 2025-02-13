package duth.dip.cse.ui.view.common;

import duth.dip.cse.engine.utils.PropertyAnnotationProcessor;
import duth.dip.cse.engine.utils.PropertyLoader;

public interface Injectable {

    default void injectPropertiesTo(Object object) {
        PropertyLoader.addProperties("default-application.properties");
        PropertyAnnotationProcessor.process(object);
    };
}
