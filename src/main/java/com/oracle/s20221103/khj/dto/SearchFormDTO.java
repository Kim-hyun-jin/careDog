package com.oracle.s20221103.khj.dto;

import lombok.Data;

@Data
public class SearchFormDTO {
	
	private String keyword;
	private String sorting;
	private String category; //title , cotent, titleContent

}
