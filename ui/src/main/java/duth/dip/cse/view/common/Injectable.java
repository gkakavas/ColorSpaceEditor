package duth.dip.cse.view.common;

import duth.dip.cse.util.property.PropertyAnnotationProcessor;

public interface Injectable {

    default void injectPropertiesTo(Object object) {
        PropertyAnnotationProcessor.process(object);
    };
}
