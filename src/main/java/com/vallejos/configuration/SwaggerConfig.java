package com.vallejos.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

  @Bean
  public Docket api() {
      return new Docket(DocumentationType.SWAGGER_2)
              .select()
              .apis(RequestHandlerSelectors.any()) // Reemplaza con el paquete de tus controladores
              .paths(PathSelectors.any())
              .build()
              .pathMapping("/")
              .apiInfo(apiInfo());
  }

  private ApiInfo apiInfo() {
      return new ApiInfoBuilder()
              .title("API de Usuarios")
              .description("Documentación de la API de Usuarios")
              .version("1.0")
              .build();
  }

}

