package mini_music_streaming.music_streaming.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig
{
    @Bean
    public OpenAPI customOpenAPI()
    {
        return new OpenAPI()

        .info(

            new Info()

            .title("Mini Music Streaming API")

            .version("1.0")

            .description(
                "JWT Authentication Enabled"))

        .addSecurityItem(

            new SecurityRequirement()

            .addList(
                "BearerAuth"))

        .components(

            new Components()

            .addSecuritySchemes(

                "BearerAuth",

                new SecurityScheme()

                .type(
                    SecurityScheme.Type.HTTP)

                .scheme(
                    "bearer")

                .bearerFormat(
                    "JWT")
            ));
    }
}