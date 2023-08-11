//package com.jafa.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//import lombok.extern.log4j.Log4j;
//
//@Configuration
//@EnableWebSecurity//웹 기반 보안 설정에서 사용하도록 활성화
//@Log4j
//public class SecurityConfig extends WebSecurityConfigurerAdapter{
//
//	@Override
//		protected void configure(HttpSecurity http) throws Exception {
//			//특정 URL에 대한 접근권한 설정
//			http.authorizeRequests()
//				.antMatchers("/guest/**").permitAll()
//				.antMatchers("/member/**").access("hasROLE('ROLE_MEMBER')")
//				.antMatchers("/admin/**").access("hasROLE('ROLE_ADMIN')");
//				
//			http.formLogin();
//	}
//}
