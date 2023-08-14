package com.jafa.security;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Component
@Log4j
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler{@Override
	
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		if(exception instanceof BadCredentialsException) {	//인증 실패했는지 확인
			String memberId = request.getParameter("memberId");	//아이디 값 가져와서
			request.setAttribute("memberId", memberId);			//아이디 값 유지
			request.setAttribute("LoginFail", "아이디 또는 비밀번호가 일치하지 않습니다.");
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/login");//로그인 페이지로 요청을 전달하기 위한 RequestDispatcher 객체를 생성
		dispatcher.forward(request, response);	//로그인 페이지로 요청 전달
	}
}
