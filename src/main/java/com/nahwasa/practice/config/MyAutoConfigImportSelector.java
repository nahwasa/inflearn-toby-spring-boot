package com.nahwasa.practice.config;

import org.springframework.boot.context.annotation.ImportCandidates;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.ArrayList;
import java.util.List;

public class MyAutoConfigImportSelector implements DeferredImportSelector {
    private final ClassLoader classLoader;

    public MyAutoConfigImportSelector(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        List<String> autoConfigs = new ArrayList<>();
        ImportCandidates.load(MyAutoConfiguration.class, classLoader).forEach(autoConfigs::add);

//        위와 동일
//        ImportCandidates.load(MyAutoConfiguration.class, classLoader).forEach(candidate ->
//                autoConfigs.add(candidate)
//        );

        return autoConfigs.toArray(new String[0]);
//        위와 동일
//        return autoConfigs.stream().toArray(String[]::new);
    }
}
