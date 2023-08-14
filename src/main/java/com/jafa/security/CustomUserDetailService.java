package com.jafa.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.jafa.member.domain.MemberVO;
import com.jafa.member.repository.MemberRepository;

//사용자 정보 조회
@Component
public class CustomUserDetailService implements UserDetailsService{

	@Autowired
	private MemberRepository memberRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MemberVO vo = memberRepository.selectById(username);	//사용자 정보 DB에서 조회
		if(vo==null) {	//없다면 예외 발생
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new CustomUser(vo);	//CustomeUser클래스를 사용하여 커스텀 사용자 정보 객체로 변환
	}
}
