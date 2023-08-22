package com.jafa.config;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;


@Configuration
@MapperScan("com.jafa.*.repository")	//myBatis의 mapper 인터페이스들이 위치하는 패키지 지정
@PropertySource(value="classpath:database/db.properties")	//DB연결 정보, classpath 내에 위치하는 properties 파일 지정
@EnableTransactionManagement	//트랜잭션 관리 활성화 @Transactional 사용 가능
@Import({SecurityConfig.class})
@EnableScheduling
public class RootConfig {

	@Value("${db.driver}")
	private String driverClassName;
	
	@Value("${db.url}")
	private String jdbcUrl; 
	
	@Value("${db.username}")
	private String username;
	
	@Value("${db.password}")
	private String password;
	
	@Bean(destroyMethod = "close")
	//HikariCP 데이터베이스 커넥션 풀을 사용하여 Oracle 데이터베이스와의 연결을 설정
	public DataSource dataSource() {
		HikariConfig config = new HikariConfig();
		config.setDriverClassName(driverClassName);
		config.setJdbcUrl(jdbcUrl);
		config.setUsername(username);
		config.setPassword(password);
		HikariDataSource dataSource = new HikariDataSource(config);
	    return dataSource;
	}
	
	@Bean//MyBatis의 SqlSessionFactory를 생성하는 데 사용됩니다. 
		//SqlSessionFactory는 MyBatis와 데이터베이스 간의 세션을 관리하고 SQL 쿼리를 실행하기 위한 핵심적인 객체
	public SqlSessionFactoryBean sessionFactoryBean() throws IOException {
		SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
		factory.setDataSource(dataSource());
		factory.setMapperLocations(new PathMatchingResourcePatternResolver()
				.getResources("classpath:mappers/**/*Mapper.xml"));
		factory.setTypeAliasesPackage("com.jafa.*.domain");
		return factory;
	}

	@Bean//SqlSessionFactory를 기반으로 SQL 세션을 생성하고 사용할 수 있게 해주는 클래스
	public SqlSessionTemplate sessionTemplate() throws Exception {
		return new SqlSessionTemplate(sessionFactoryBean().getObject());
	}
	
	@Bean//다국어 지원을 구현
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("message.label");
		messageSource.setDefaultEncoding("utf-8");
		return messageSource;
	}
	
	@Bean	//프로퍼티 파일에 정의된 변수를 읽어서 Spring 빈 설정 등에서 사용할 수 있게 해주는 클래스
	public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	@Bean// 데이터베이스 트랜잭션을 관리하는 데 사용되며, Spring의 트랜잭션 기능을 활성화하기 위해 필요
	public DataSourceTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}
	
	@Bean
	public JavaMailSenderImpl mailSender() {
	    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	    mailSender.setHost("smtp.naver.com");
	    mailSender.setPort(465);
	    mailSender.setUsername("sjsdjfcn@naver.com");
	    mailSender.setPassword("rkehtps@00");

	    Properties properties = new Properties();
	    properties.put("mail.transport.protocol", "smtp");
	    properties.put("mail.smtp.auth", "true");
	    properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	    properties.put("mail.smtp.starttls.enable", "true");
	    properties.put("mail.debug", "true");
	    properties.put("mail.smtp.ssl.trust", "smtp.naver.com");
	    properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
	    mailSender.setJavaMailProperties(properties);
	    return mailSender;
	}
}
