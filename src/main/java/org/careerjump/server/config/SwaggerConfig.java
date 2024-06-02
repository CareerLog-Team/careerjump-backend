package org.careerjump.server.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi api() {
        String[] paths = {"/api/**"};
        String[] packagesToScan = {"org.careerjump.server"};
        return GroupedOpenApi.builder()
                .group("CareerJump")
                .pathsToMatch(paths)
                .packagesToScan(packagesToScan)
                .build();
    }


    @Bean
    public OpenAPI openAPI() {

        /*
        SecurityScheme apiKey = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .type(SecurityScheme.Type.APIKEY)
                .in(SecurityScheme.In.HEADER)
                .name("Authorization")
                .scheme("bearer")
                .bearerFormat("JWT");

        SecurityRequirement securityRequirement = new SecurityRequirement()
                .addList("Bearer Token");

        return new OpenAPI()
                .components(new Components().addSecuritySchemes("Bearer Token", apiKey))
                .addSecurityItem(securityRequirement)
                .info(new Info()
                        .title("CareerLog API")
                        .version("v0")
                        .description("커리어 로그의 서비스 API swagger-ui 화면 입니다."));
         */

        return new OpenAPI()
                .info(new Info()
                        .title("CareerJump API")
                        .version("v0")
                        .description("커리어 점프의 서비스 API swagger-ui 화면 입니다."));
    }
}
