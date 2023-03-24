package com.oracle.s20221103.dto;

import lombok.Data;

@Data
public class MemberDogDTO {
	private Long 	id;				// 회원번호
	private String 	username;		// 회원아이디
	private String 	role;			// 권한
	private String 	password;		// 비밀번호
	private String	memberCall;		// 연락처
	private String	memberName;		// 이름
	private String	memberAddress;	// 주소
	private String	memberEmail;	// 이메일
	private String	profImg;		// 프로필사진
	private Long	teacher;		// 담당선생님
	private Long	memberDrop;		// 탈퇴여부
	private Long 	dogNo;  	 //반려견번호
	private String 	dogName; 	 //반려견이름
	private Long 	dogGender; 	 //반려견성별
	private Long 	dogAge; 	 //반려견나이
	private Long 	dogWeight; 	 //반려견몸무게
	private String 	dogMemo; 	 //특이사항
	private Long 	deSexibng; 	 //중성화여부
	private String 	dogKind; 	 //견종명
	private Long 	joinCheck; 	 //유치원가입상태
	private String 	scStartdate; //유치원시작일
	private String 	scEnddate; 	 //유치원종료일
	
	//읽기전용
	private String teacherName;
	
	private int bcd;	//테이블
	private int mcd;	//컬럼
	private String commDetail; //상세분류

}
