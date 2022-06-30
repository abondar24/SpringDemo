package org.abondar.experimental.springsecurity.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI jwtAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Security API")
                        .description("Demo API showing how spring security works")
                        .termsOfService("http://swagger.io/terms/")
                        .license(new License()
                                .name("MIT")))
                .components(new Components()
                        .addSecuritySchemes("basicScheme",
                                new SecurityScheme().type(SecurityScheme.Type.HTTP)
                                        .scheme("basic")
                                        .in(SecurityScheme.In.HEADER))
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme().type(SecurityScheme.Type.APIKEY)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                                        .in(SecurityScheme.In.HEADER))
                        .addSecuritySchemes("oauth-key",
                                new SecurityScheme().type(SecurityScheme.Type.APIKEY)
                                        .in(SecurityScheme.In.HEADER).name("X-OAUTH-TOKEN")))
                .addSecurityItem(new SecurityRequirement().addList("oauth-key"));

    }

}
