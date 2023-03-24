package com.oracle.s20221103.khj.repository;

import org.springframework.stereotype.Component;

@Component
public class FileStore {

	private String fileDir;

	public String getFullPath(String filename) {
		return fileDir + filename;
	}

	// AttachDTO
	// public List<> storeFiles(List<MultipartFile> multipartFiles){
	//
	// }

}
