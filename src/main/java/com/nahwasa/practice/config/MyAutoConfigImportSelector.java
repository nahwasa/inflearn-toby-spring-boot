package com.nahwasa.practice.config;

import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MyAutoConfigImportSelector implements DeferredImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[] {
                "com.nahwasa.practice.config.autoconfig.DispatcherServletConfig",
                "com.nahwasa.practice.config.autoconfig.TomcatWebServerConfig"
        };
    }
}
