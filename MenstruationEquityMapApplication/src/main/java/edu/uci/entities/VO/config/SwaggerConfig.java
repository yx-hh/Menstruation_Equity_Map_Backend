/**
 * Configuration file for Swagger2.
 *
 * @author ruiyan ma
 */

package edu.uci.entities.VO.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo()) // generate API information
                .select() // returns an ApiSelectorBuilder instance
                .apis(RequestHandlerSelectors.basePackage("edu.uci.controller")) // specify controller package
                .paths(PathSelectors.any()) // select all api
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Menstruation Equity Map API")
                .description("Menstruation Equity Map Swagger API Management")
                .termsOfServiceUrl("") // define service domain
                .version("1.0")
                .build();
    }
}
