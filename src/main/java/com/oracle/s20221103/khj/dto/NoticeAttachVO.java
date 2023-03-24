package com.oracle.s20221103.khj.dto;

import lombok.Data;

@Data
public class NoticeAttachVO {
	
	private String uuid;
	private String uploadPath;
	private String fileName;
	private boolean fileType;

}
