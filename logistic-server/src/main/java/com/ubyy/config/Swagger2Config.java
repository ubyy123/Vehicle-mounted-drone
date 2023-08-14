package com.ubyy.config;

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

/**
 * Swagger2 配置类
 */

@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ubyy.controller")).
                paths(PathSelectors.any())
                .build();
    }

    // 基本的信息配置，信息会在api文档上显示
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("前后端项目接口文档")
                .contact(new Contact("yangbiao", "http://localhost:8092/doc.html", "994880908@qq.com"))
                .version("0.1")
                .build();
    }
}
