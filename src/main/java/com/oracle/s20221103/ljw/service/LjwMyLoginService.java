package com.oracle.s20221103.ljw.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.oracle.s20221103.dto.MemberDTO;
import com.oracle.s20221103.ljw.dao.LjwLoginRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class LjwMyLoginService implements LjwLoginService {
	private final LjwLoginRepository lr;

	@Override
	public int insertMember(MemberDTO member) {
		return lr.insertMember(member);
	}

	@Override
	public int dupChkUsername(String username) {
		return lr.dupChkUsername(username);
	}

	@Override
	public List<MemberDTO> findJoinData(MemberDTO member) {
		log.info("findJoinData() start...");
		List<MemberDTO> result = lr.findJoinData(member);
		log.info("findJoinData() finish...");
		return result;
	}

	@Override
	public MemberDTO emailChk(MemberDTO member) {
		return lr.emailChk(member);
	}

	@Override
	public int mailChkSet(MemberDTO member) {
		return lr.mailChkSet(member);
	}

	@Override
	public MemberDTO mailCodeChk(MemberDTO member) {
		return lr.mailCodeChk(member);
	}

	@Override
	public int passReset(MemberDTO resetPass) {
		return lr.passReset(resetPass);
	}
}
