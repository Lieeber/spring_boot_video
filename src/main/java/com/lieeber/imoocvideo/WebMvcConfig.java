package com.lieeber.imoocvideo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**")
		.addResourceLocations("classpath:/META-INF/resources/")
				.addResourceLocations("file:/Users/lieeber/Downloads/imoocvideo_save/");
	}

//
//	@Bean
//	public MiniInterceptor miniInterceptor() {
//		return new MiniInterceptor();
//	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(miniInterceptor())
//				.addPathPatterns("/user/**")
//				       .addPathPatterns("/video/upload", "/video/uploadCover",
//				    		   			"/video/userLike", "/video/userUnLike",
//				    		   			"/video/saveComment")
//								  .addPathPatterns("/bgm/**")
//								  .excludePathPatterns("/user/queryPublisher")
//								  .excludePathPatterns("/wxLogin");
//
//		WebMvcConfigurer.super.addInterceptors(registry);
	}

}
