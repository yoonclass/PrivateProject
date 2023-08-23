package com.jafa.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.jafa.security.CustomUserDetailService;

import lombok.extern.log4j.Log4j;

@Configuration
@EnableWebSecurity//웹 기반 보안 설정에서 사용하도록 활성화
@ComponentScan("com.jafa.security")
@EnableGlobalMethodSecurity(prePostEnabled = true)//메소드 수준의 보안 어노테이션을 활성화
@Log4j
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private AuthenticationSuccessHandler authenticationSuccessHandler;
	
	@Autowired
	private AuthenticationFailureHandler authenticationFailureHandler;
	
	@Autowired
	CustomUserDetailService customUserDetailService;

	@Autowired
	DataSource dataSource;
	
	 
	//특정 URL에 대한 접근권한 설정
	@Override
		protected void configure(HttpSecurity http) throws Exception {
	        http
	            // 다른 권한 설정
	        .formLogin()
	            .loginPage("/login")
	            .loginProcessingUrl("/member/login")
	            .usernameParameter("memberId")
	            .passwordParameter("memberPwd")
	            .successHandler(authenticationSuccessHandler)
	            .failureHandler(authenticationFailureHandler)
	            .and()
	        .rememberMe()
	        	.key("jafa")
	        	.tokenRepository(persistentTokenRepository())
	        	.tokenValiditySeconds(604800)
	        	.and()
	        .logout()
	            .logoutUrl("/member/logout")
	            .invalidateHttpSession(true)
	            .deleteCookies("remember-me","JSESSION_ID")
	            .and()
	        .exceptionHandling()
	            .accessDeniedPage("/accessDenied");
	        
	    	CharacterEncodingFilter filter = new CharacterEncodingFilter(); 
	    	filter.setEncoding("utf-8");
	    	filter.setForceEncoding(true);
	    	http.addFilterBefore(filter, CsrfFilter.class);

	}
	
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl jdbcTokenRepositoryImpl = new JdbcTokenRepositoryImpl();
		jdbcTokenRepositoryImpl.setDataSource(dataSource);
		return jdbcTokenRepositoryImpl;
	}


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailService)//사용자 정보 조회
			.passwordEncoder(passwordEncoder());//비밀번호 해시화 설정
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();//비밀번호를 안전하게 해시화(암호화)하는 역할을 수행
	}
	
	//사용자 정보 추가
		/*@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.inMemoryAuthentication()
				.withUser("admin").password("{noop}1234").roles("ADMIN","MEMBER").and()
				.withUser("YOON").password("{noop}1234").roles("MEMBER");
		}*/
}
