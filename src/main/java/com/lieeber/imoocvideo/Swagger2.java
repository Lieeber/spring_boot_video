package com.lieeber.imoocvideo;

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
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2 {
	@Bean
	public Docket createRestApi() {
		// 为swagger添加header参数可供输入  
        ParameterBuilder userTokenHeader = new ParameterBuilder();
        ParameterBuilder userIdHeader = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();  
        userTokenHeader.name("headerUserToken").description("userToken")
        	.modelRef(new ModelRef("string")).parameterType("header")
        	.required(false).build();  
        userIdHeader.name("headerUserId").description("userId")
	    	.modelRef(new ModelRef("string")).parameterType("header")
	    	.required(false).build(); 
        pars.add(userTokenHeader.build());
        pars.add(userIdHeader.build());
		
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("com.lieeber.imoocvideo.controller"))
				.paths(PathSelectors.any()).build();
//				.globalOperationParameters(pars);
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				// 设置页面标题
				.title("使用swagger2构建短视频后端api接口文档")
				// 设置联系人
				.contact(new Contact("imooc-风间影月", "http://www.imooc.com", "imooc@163.com"))
				// 描述
				.description("欢迎访问短视频接口文档，这里是描述信息")
				// 定义版本号
				.version("1.0").build();
	}

}
