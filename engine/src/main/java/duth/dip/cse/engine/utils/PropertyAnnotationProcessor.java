package duth.dip.cse.engine.utils;

import java.lang.reflect.Field;

public class PropertyAnnotationProcessor {



    public static void process(Object object) {
        var clazz = object.getClass();
        // Iterate over all fields in the class
        for (Field field : clazz.getDeclaredFields()) {
            // Check if the field is annotated with @Value
            if (field.isAnnotationPresent(Property.class)) {
                Property property = field.getAnnotation(Property.class);
                String propertyKey = property.name();
                String propertyValue = PropertyLoader.getProperty(propertyKey);

                if (propertyValue == null) {
                    throw new RuntimeException("Property not found for key: " + propertyKey);
                }

                try {
                    field.setAccessible(true);
                    if (field.getType() == String.class) {
                        field.set(object, propertyValue);
                    } else if (field.getType() == int.class || field.getType() == Integer.class) {
                        field.set(object, (int) Long.parseLong(propertyValue, 16));
                    } else if(field.getType() == float.class || field.getType() == Float.class) {
                        field.set(object, Float.parseFloat(propertyValue));
                    } else if (field.getType() == boolean.class || field.getType() == Boolean.class) {
                        field.set(object, Boolean.parseBoolean(propertyValue));
                    } else {
                        throw new RuntimeException("Unsupported field type: " + field.getType());
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Failed to inject property for field: " + field.getName(), e);
                }
            }
        }
    }
}
