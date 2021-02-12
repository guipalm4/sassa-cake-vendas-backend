package com.sassacakes.sales.config;

import java.security.Principal;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


@Configuration
public class SpringFoxConfig {

    private static final String DESCRIPTION = "Sassa Cakes Sales API";

    @Bean
    Docket api() {

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getApiInfo("1.0", DESCRIPTION))
                .select()
                .paths(PathSelectors.any())
                .build()
                .ignoredParameterTypes(Principal.class);
    }

    private ApiInfo getApiInfo(String version, String applicationName) {
        return new ApiInfoBuilder().title(applicationName).description(DESCRIPTION).version(version).build();
    }

}
