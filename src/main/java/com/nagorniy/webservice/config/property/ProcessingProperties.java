package com.nagorniy.webservice.config.property;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class to map properties from application.yml
 */
@Configuration
@ConfigurationProperties(prefix = "application.processing")
@NoArgsConstructor
@Data
public class ProcessingProperties {

    private int maxResultsAmount;
}
