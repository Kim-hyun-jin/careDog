package com.oracle.s20221103.ssh.service;

import java.util.List;

import com.oracle.s20221103.dto.QADTO;

public interface ShQAService {

	int totalQA();
	int totalQAAdmin();
	int totalMyQA(Long id);

	List<QADTO> qaQues(QADTO qadto);

	List<QADTO> qaAdmin(QADTO qadto);

	QADTO qaContent(Integer questionNo);

	void qaQuesInsert(QADTO qadto);

	List<QADTO> qaQuesMyList(QADTO qadto);

	void qaContentUpdate(QADTO qadto);
	void qaContentDelete(QADTO qadto);
	void qaContentAdminUpdate(QADTO qadto);



}
