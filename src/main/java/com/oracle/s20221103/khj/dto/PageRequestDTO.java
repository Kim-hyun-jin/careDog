package com.oracle.s20221103.khj.dto;

import org.springframework.lang.Nullable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDTO {

	private int page = 1;
	private int size = 10;
	@Nullable
	private String category;
	@Nullable
	private String sorting;
	@Nullable
	private String keyword;
}
