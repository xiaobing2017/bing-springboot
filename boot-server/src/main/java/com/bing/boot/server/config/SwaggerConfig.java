package com.bing.boot.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Description:
 * Author: zhangfusheng
 * Date: 2017/11/2 上午10:02
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket userApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .paths(PathSelectors.regex("/api.*"))
                .build();
    }

    private static ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("样本服务接口")
                .description("样本服务接口文档")
                .termsOfServiceUrl("http://www.xiaobing.com")
                .contact(new Contact("xiaobing", null, ""))
                .license("xiao bing license")
                .licenseUrl("http://www.xiaobing.com")
                .version("1.0")
                .build();
    }

//    @Bean
//    public Docket restApi() {
//        return new Docket(DocumentationType.SWAGGER_2).groupName("api")
//                .apiInfo(restApiInfo())
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.mycompany.financial.nirvana.admin.controller"))
//                .paths(PathSelectors.any())
//                .build();
//    }
//
//    private ApiInfo restApiInfo() {
//        return new ApiInfoBuilder()
//                .title("有鱼小贷联盟商户端接口")//大标题
//                .description("有鱼小贷联盟商户端接口")//详细描述
//                .termsOfServiceUrl("http://blog.csdn.net/eacter")
//                .contact("有鱼团队")//作者
//                .version("1.0")//版本
//                .build();
//    }
//
//
//    @Bean
//    public Docket demoApi() {
//        return new Docket(DocumentationType.SWAGGER_2).groupName("demo")//创建多个分组
//                .apiInfo(demoApiInfo())
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.mycompany.financial.nirvana.demo"))
//                .paths(PathSelectors.any())
//                .build();
//    }
//
//    private ApiInfo demoApiInfo() {
//        return new ApiInfoBuilder()
//                .title("Spring Boot中使用Swagger2构建RESTful APIs")//大标题
//                .description("Swagger2 demo")//详细描述
//                .termsOfServiceUrl("http://blog.csdn.net/eacter")
//                .contact("有鱼团队")//作者
//                .version("1.0")//版本
//                .license("The Apache License, Version 2.0")
//                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
//                .build();
//    }
}
