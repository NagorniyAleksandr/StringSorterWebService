package com.nagorniy.webservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Use this class to configure common beans.
 * It helps us to keep all configurations of common beans in one place.
 */
@Configuration
public class AppBeanConfiguration {

    /**
     * This method configure common {@code ObjectMapper} that will be autowired to other components.
     * In our case we just add Pretty Printing option to have more readable json.
     *
     * @return configured ObjectMapper
     */
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        return mapper;
    }
}
