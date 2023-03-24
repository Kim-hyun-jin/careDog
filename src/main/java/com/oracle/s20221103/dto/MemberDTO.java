package com.oracle.s20221103.dto;

import lombok.Data;

@Data
public class MemberDTO {
	private Long	id;				// 회원번호
	private String 	username;		// 회원아이디
	private String 	role;			// 권한
	private String 	password;		// 비밀번호
	private String	memberCall;		// 연락처
	private String	memberName;		// 이름
	private String	memberAddress;	// 주소
	private String	memberEmail;	// 이메일
	private String	profImg;		// 프로필사진
	private Long	teacher;		// 담당선생님
	private Long	memberEnabled;	// 탈퇴여부
	private String	mailKey;		// 이메일인증
	
//	읽기전용
	private int		premiumChk;
	private String tname;			//선생님 이름
}
