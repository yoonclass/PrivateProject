package com.jafa.config;

import javax.servlet.Filter;
import javax.servlet.ServletRegistration;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

//웹 구성에 대한 지정하는 내용 담음
public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer {

	//설정클래스 지정
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {RootConfig.class};
	}

	//서블릿별 빈 정의?
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {ServletConfig.class};
	}
	
	//어떤 url패턴과 매핑할 지를 지정
	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}
	// 모든 요청과 응답에 대하여 문자 인코딩 처리
	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
		filter.setEncoding("utf-8");
		filter.setForceEncoding(true);
		return new Filter[] {filter};
	}
	
	//DispatcherServlet이 요청을 처리할 때 404 오류가 발생할 경우 예외
	@Override
	protected void customizeRegistration(ServletRegistration.Dynamic registration) {
		registration.setInitParameter("throwExceptionIfNoHandlerFound", "true");
	}
}
