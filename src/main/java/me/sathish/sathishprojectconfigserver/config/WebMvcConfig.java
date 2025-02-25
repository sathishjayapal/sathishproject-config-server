package me.sathish.sathishprojectconfigserver.config;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {
    private final ApplicationConfigProperties applicationConfigProperties;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping(applicationConfigProperties.getCors().getPathPattern())
                .allowedMethods(applicationConfigProperties.getCors().getAllowedMethods())
                .allowedHeaders(applicationConfigProperties.getCors().getAllowedHeaders())
                .allowedOriginPatterns(
                        applicationConfigProperties.getCors().getAllowedOriginPatterns())
                .allowCredentials(applicationConfigProperties.getCors().isAllowCredentials());
    }
}

@Data
@Component
@ConfigurationProperties("application")
class ApplicationConfigProperties {
    @NestedConfigurationProperty private Cors cors = new Cors();

    @Data
    public static class Cors {
        private String pathPattern = "/api/**";
        private String allowedMethods = "*";
        private String allowedHeaders = "*";
        private String allowedOriginPatterns = "*";
        private boolean allowCredentials = true;
    }
}
