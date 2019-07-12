package com.nchu.blogmx.config;

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
public class Swagger2Config {

    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.nchu.blogmx.controller") )
                .paths(PathSelectors.any()).build();

    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("个人博客系统")
                .description("后端的调试界面")
                .termsOfServiceUrl("http://localhost:8190")
                .version("1.0").build();
    }

}