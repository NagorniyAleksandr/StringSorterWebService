package com.nagorniy.webservice.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@ConditionalOnProperty(prefix = "application.swagger", name = "enabled")
public class SwaggerConfig {

    /**
     * Method to construct bean for swagger support
     *
     * @return configured {@code Docket} bean
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.nagorniy.webservice.controller"))
                .paths(PathSelectors.any())
                .build()
                .forCodeGeneration(true)
                .useDefaultResponseMessages(false);
    }

    /**
     * Method provides general information about documentation api
     *
     * @return documentation {@code ApiInfo}
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("'Strings sorter' swagger UI")
                .description("You can use this UI for using application endpoints")
                .contact(new Contact("Nagorniy Aleksandr", "", "a.v.nagorniy@gmail.com"))
                .build();
    }
}