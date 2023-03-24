package com.oracle.s20221103.ljw.dao;

import java.util.List;

import com.oracle.s20221103.dto.MemberDTO;

public interface LjwLoginRepository {

	int insertMember(MemberDTO member);

	int dupChkUsername(String username);

	MemberDTO findUser(String username);

	int checkPremium(Long id);

	List<MemberDTO> findJoinData(MemberDTO member);

	MemberDTO emailChk(MemberDTO member);

	int mailChkSet(MemberDTO member);

	MemberDTO mailCodeChk(MemberDTO member);

	int passReset(MemberDTO resetPass);

}
