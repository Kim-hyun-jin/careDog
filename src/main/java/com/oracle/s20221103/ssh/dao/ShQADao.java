package com.oracle.s20221103.ssh.dao;

import java.util.List;

import com.oracle.s20221103.dto.QADTO;

public interface ShQADao {

	int totalQA();
	int totalQAAdmin();
	int totalMyQA(Long id);

	List<QADTO> qaQues(QADTO qadto);

	List<QADTO> qaAdmin(QADTO qadto);

	QADTO qaContent(Integer questionNo);

	void qaInsert(QADTO qadto);

	List<QADTO> qaQuesMyList(QADTO qadto);

	void qaUpdate(QADTO qadto);

	void qaContentDelete(QADTO qadto);
	void qaAdminUpdate(QADTO qadto);


}
