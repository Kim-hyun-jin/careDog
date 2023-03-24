package com.oracle.s20221103.ljw.service;

import java.util.List;

import com.oracle.s20221103.dto.MemberDTO;

public interface LjwLoginService {

	int insertMember(MemberDTO member);

	int dupChkUsername(String username);

	List<MemberDTO> findJoinData(MemberDTO member);

	MemberDTO emailChk(MemberDTO member);

	int mailChkSet(MemberDTO member);

	MemberDTO mailCodeChk(MemberDTO member);

	int passReset(MemberDTO resetPass);

}
