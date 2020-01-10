package com.mmm.weixin.config;

import java.util.ArrayList;
import java.util.List;

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

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket getDocket() { // 此类主要是整个的Swagger配置项，利用这个类需要来指派扫描包
    	ParameterBuilder ticketPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();  
    	ticketPar.name("authorization").description("用户token")
    	.modelRef(new ModelRef("string")).parameterType("header") 
    	.required(false).build(); //header中的ticket参数非必填，传空也可以
    	pars.add(ticketPar.build()); 
    	
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(this.getApiInfo()).select()
                .apis(RequestHandlerSelectors
                        .basePackage("com.mmm.weixin.controller"))
                .paths(PathSelectors.any()).build().globalOperationParameters(pars); // 设置文档的显示类型
    }
    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder().title("吃喝玩乐")
                .version("1.0").build();
    }
}
