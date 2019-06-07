package com.igoosd.config;

import com.igoosd.util.Constants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket createRestApi() {

        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("PGIS 项目接口文档")
                .description("智能交通诱导系统 RESTful API")
                .version("1.0.0")
                .build();

        ParameterBuilder tokenParam = new ParameterBuilder();
        tokenParam.name(Constants.AUTH_KEY).description("令牌").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        ParameterBuilder sessionParam = new ParameterBuilder();
        sessionParam.name(Constants.SESSION_ID).description("会话ID").modelRef(new ModelRef("string")).parameterType("header").required(false).build();


        List<Parameter> headers = new ArrayList<>();
        headers.add(tokenParam.build());
        headers.add(sessionParam.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.igoosd.api")) // 以扫描包的方式
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(headers);
    }

}
