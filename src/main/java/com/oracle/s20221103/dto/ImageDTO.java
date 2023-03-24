package com.oracle.s20221103.dto;

import lombok.Data;

@Data
public class ImageDTO {
	public ImageDTO() {}
	public ImageDTO(String imgPath, String imgClass) {
		this.imgPath=imgPath;
		this.imgClass=imgClass;
	}
	private String tableType;
	private Long cusNo;//(자유게시판?)게시글 번호
	private Long imageNo;
	private String imgPath;
	private String imgClass;
	
//	readOnly
	private String orgImg;
}
