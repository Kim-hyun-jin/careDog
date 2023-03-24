package com.oracle.s20221103.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class DogDTO {
	private Long 	id;  		  	//회원아이디
	private Long 	dogNo;  	  	//반려견번호
	private String 	dogName; 	  	//반려견이름
	private Long 	dogGender;    	//반려견성별
	private Long 	dogAge; 	  	//반려견나이
	private Long 	dogWeight;    	//반려견몸무게
	private String 	dogMemo; 	  	//특이사항
	private Long 	deSexibng;    	//중성화여부
	private String 	dogKind; 	  	//견종명
	private Long 	joinCheck;    	//유치원가입상태
	private String 	scStartdate;  	//유치원시작일
	private String 	scEnddate;    	//유치원종료일
	private String  dogImgName;     //강아지사진이름
	private String  dogImgPath;     //강아지사진위치

}
