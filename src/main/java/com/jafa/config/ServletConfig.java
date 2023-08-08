package com.jafa.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration	//Application 시작 시점에 실행
@EnableWebMvc	//스프링 MVC 사용시 필요한 설정 자동으로 활성화
@ComponentScan({"com.jafa"})
public class ServletConfig implements  WebMvcConfigurer{

	@Override	//뷰 리졸버 설정하는 역할
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/views/",".jsp");
	}
	
	@Override	//정적 리소스 핸들러 추가하는 역활
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**")
				.addResourceLocations("/resources/");
	}
}
