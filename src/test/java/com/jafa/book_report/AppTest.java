package com.jafa.book_report;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.jafa.config.RootConfig;
import com.jafa.config.ServletConfig;

/*RunWith : JUnit 4와 Spring 프레임워크를 함께 사용할 때 사용되며, 
테스트 시 Spring 컨테이너를 생성하여 스프링 빈들을 로드하고 의존성 주입(Dependency Injection)을 할 수 있게 해줍니다.*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class, ServletConfig.class})//테스트 컨텍스트의 설정을 지정
@WebAppConfiguration	//	설정을 로드하기 위해 사용
public class AppTest {

}
