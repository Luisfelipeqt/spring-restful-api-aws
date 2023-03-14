package br.com.erudio.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfiguration {
    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info() // -> Depois dessa chamada vamos definir as informações da API
                        .title("RESTful API with Java 17 and Spring Boot 3.0.1") // -> Titulo da API
                        .version("v1") // -> Versão da API
                        .description("Some description about this API") // -> Descrição da API
                        .termsOfService("https://pub.erudio.com.br/meus-cursos") // -> Os termos de serviços, deve-se passar uma URL com os termos
                        .license(new License() // -> Qual tipo de licença
                                .name("Apache 2.0") // -> O nome da licença
                                .url("https://pub.erudio.com.br/meus-cursos")) // -> a URL da licença

                );
    }
}
