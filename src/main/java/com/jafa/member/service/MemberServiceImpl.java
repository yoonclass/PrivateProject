package com.jafa.member.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jafa.error.PasswordMisMatchException;
import com.jafa.member.domain.AuthVO;
import com.jafa.member.domain.MemberVO;
import com.jafa.member.repository.AuthRepository;
import com.jafa.member.repository.MemberRepository;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private AuthRepository authRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public MemberVO read(String memberId) {
		return memberRepository.selectById(memberId);
	}

	@Transactional
	@Override
	public void join(MemberVO vo) {
		log.info(vo);
		log.info(passwordEncoder);
		
		vo.setMemberPwd(passwordEncoder.encode(vo.getMemberPwd()));
		AuthVO authVO = new AuthVO(vo.getMemberId(),"ROLE_MEMBER");
		memberRepository.insert(vo);
		authRepository.insert(authVO);
	}

	@Transactional
	@Override
	public void changePwd(Map<String, String> memberMap) {
		String memberId = memberMap.get("memberId");
		String newPwd = memberMap.get("newPwd");
		String currentPwd = memberMap.get("currentPwd");
		MemberVO vo = memberRepository.selectById(memberId);
		if(!passwordEncoder.matches(currentPwd, vo.getMemberPwd())) {
			throw new PasswordMisMatchException();
		}
		memberRepository.updatePwd(memberId, passwordEncoder.encode(newPwd));
	}

	@Override
	public void delete(String memberId) {
		memberRepository.delete(memberId);
	}
}
