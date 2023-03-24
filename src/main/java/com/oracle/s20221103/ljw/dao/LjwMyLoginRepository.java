package com.oracle.s20221103.ljw.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.oracle.s20221103.dto.MemberDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class LjwMyLoginRepository implements LjwLoginRepository {
	private final SqlSession session;

	@Override
	public int insertMember(MemberDTO member) {
		int result = 0;
		try {
			result = session.update("insertMember",member);
		} catch (Exception e) {
			log.info("insertMember() e.getMessage ==>"+e.getMessage());
		}
		System.out.println("result : "+result);
		return result;
	}

	@Override
	public int dupChkUsername(String username) {
		int result = 0;
		try {
			List<MemberDTO> members = session.selectList("selectMemberByUsername",username);
			result = members.size();
		} catch (Exception e) {
			log.info("dupChkUsername() e.getMessage ==>"+e.getMessage());
		}
		return result;
	}

	@Override
	public MemberDTO findUser(String username) {
		MemberDTO result = null;
		try {
			result = session.selectOne("selectMemberByUsername",username);
		} catch (Exception e) {
			log.info("findUser() e.getMessage ==>"+e.getMessage());
		}
		return result;
	}

	@Override
	public int checkPremium(Long id) {
		log.info("checkPremium() start...");
		int result = 0;
		try {
			int count = session.selectOne("checkPremium", id);
			if(count>=1) result = 1;
		} catch (Exception e) {
			log.info("checkPremium() e.getMessage() : "+e.getMessage());
		}
		log.info("checkPremium() finish...");
		return result;
	}

	@Override
	public List<MemberDTO> findJoinData(MemberDTO member) {
		log.info("findJoinData() start...");
		List<MemberDTO> result = null;
		try {
			result = session.selectList("findJoinData",member);
		} catch (Exception e) {
			log.info("findJoinData() e.getMessage() : "+e.getMessage());
		}
		log.info("findJoinData() finish...");
		return result;
	}

	@Override
	public MemberDTO emailChk(MemberDTO member) {
		log.info("emailChk() start...");
		MemberDTO result = null;
		try {
			result = session.selectOne("checkEmail",member);
		} catch (Exception e) {
			log.info("emailChk() e.getMessage() : "+e.getMessage());
		}
		log.info("emailChk() finish...");
		return result;
	}

	@Override
	public int mailChkSet(MemberDTO member) {
		log.info("mailChkSet() start...");
		int result = 0;
		try {
			result = session.update("mailKeySet",member);
		} catch (Exception e) {
			log.info("mailChkSet() e.getMessage() : "+e.getMessage());
		}
		log.info("mailChkSet() finish...");
		return result;
	}

	@Override
	public MemberDTO mailCodeChk(MemberDTO member) {
		log.info("mailCodeChk() start...");
		MemberDTO result = null;
		try {
			result = session.selectOne("mailKeyChk",member);
		} catch (Exception e) {
			log.info("mailCodeChk() e.getMessage() : "+e.getMessage());
		}
		log.info("mailCodeChk() finish...");
		return result;
	}

	@Override
	public int passReset(MemberDTO resetPass) {
		log.info("passReset() start...");
		int result = 0;
		try {
			result = session.update("passwordReset",resetPass);
		} catch (Exception e) {
			log.info("passReset() e.getMessage() : "+e.getMessage());
		}
		log.info("passReset() finish...");
		return result;
	}
	
}
