package com.jafa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import lombok.extern.log4j.Log4j;

@Configuration
@EnableWebSecurity//웹 기반 보안 설정에서 사용하도록 활성화
@ComponentScan("com.jafa.security")
@Log4j
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private AuthenticationSuccessHandler authenticationSuccessHandler;
	
	//특정 URL에 대한 접근권한 설정
	@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests()
				.antMatchers("/guest/**").permitAll()
				.antMatchers("/member/**").access("hasRole('ROLE_MEMBER')")
				.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')"); 
			
			http.formLogin()
				.loginPage("/login")
				.loginProcessingUrl("/member/login")
				.usernameParameter("memberId")
				.passwordParameter("memberPwd")
				.successHandler(authenticationSuccessHandler);
				
			http.logout().logoutUrl("/member/logout").invalidateHttpSession(true);
			http.exceptionHandling().accessDeniedPage("/accessDenied");
	}

	//사용자 정보 추가
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("admin").password("{noop}1234").roles("ADMIN","MEMBER").and()
			.withUser("YOON").password("{noop}1234").roles("MEMBER");
	}
}
