package io.github.gfumagali.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
    @Bean
    Docket docket() {
        return new Docket(springfox.documentation.spi.DocumentationType.SWAGGER_2)
            .useDefaultResponseMessages(false)
            .apiInfo(apiInfo())
            .select()
            .apis(RequestHandlerSelectors.basePackage("io.github.gfumagali.rest.controller"))
            .paths(PathSelectors.any())
            .build()
            .securityContexts(List.of(securityContext()))
            .securitySchemes(List.of(apiKey()))
            .apiInfo(apiInfo());
    }  

    private Contact contact() {
        return new Contact("Guilherme Fumagali", "https://github.com/Guilherme-Fumagali", "guilhermefumarques@gmail.com");
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("Vendas API")
            .description("API do projeto de Vendas")
            .version("1.0")
            .contact(contact())
            .build();
    }

    public ApiKey apiKey(){
        return new  ApiKey("JWT", "Authorization", "header");
    }

    private List<SecurityReference> defaultAuth(){
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] scopes = new AuthorizationScope[1];
        scopes[0] = authorizationScope;
        SecurityReference reference = new SecurityReference("JWT", scopes);
        List<SecurityReference> auths = new java.util.ArrayList<>();
        auths.add(reference);
        return auths;
    }

    private SecurityContext securityContext(){
        return SecurityContext.builder()
            .securityReferences(defaultAuth())
            .forPaths(PathSelectors.any())
            .build();
    }
}