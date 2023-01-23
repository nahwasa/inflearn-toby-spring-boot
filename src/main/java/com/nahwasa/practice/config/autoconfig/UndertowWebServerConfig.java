package com.nahwasa.practice.config.autoconfig;

import com.nahwasa.practice.config.MyAutoConfiguration;
import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.type.AnnotatedTypeMetadata;

@MyAutoConfiguration
@Conditional(UndertowWebServerConfig.UnderTowCondition.class)
public class UndertowWebServerConfig {
    @Bean("undertowWebServerFactory")
    public ServletWebServerFactory servletWebServerFactory() {
        return new UndertowServletWebServerFactory();
    }

    static class UnderTowCondition implements Condition {

        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            return true;
        }
    }
}
