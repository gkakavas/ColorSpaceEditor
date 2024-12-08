package duth.dip.cse.util.property;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfoList;
import io.github.classgraph.ScanResult;
import java.io.IOException;
import java.util.List;

public class AnnotationProcessor {
    public static ClassInfoList getAnnotatedClasses(Package packazz, Class<?> annotation) throws IOException {
        try (ScanResult scanResult = new ClassGraph()
                .enableAllInfo()
                .acceptPackages(packazz.getName())
                .scan()) {
         return scanResult.getClassesWithAnnotation(annotation.getName()).getStandardClasses();
        }
    }
}
