package com.jafa.member.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jafa.member.domain.MemberVO;
import com.jafa.member.repository.MemberRepository;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberRepository memberRepository;
	
	@Override
	public MemberVO read(String memberId) {
		return memberRepository.selectById(memberId);
	}

	@Override
	public void join(MemberVO vo) {
		memberRepository.insert(vo);
	}

	@Override
	public void changePwd(String memberId, String newPwd) {
		memberRepository.updatePwd(memberId, newPwd);
	}

	@Override
	public void delete(String memberId) {
		memberRepository.delete(memberId);
	}

}
