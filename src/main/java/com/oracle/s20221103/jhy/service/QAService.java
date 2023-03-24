package com.oracle.s20221103.jhy.service;

import java.util.List;

import com.oracle.s20221103.dto.QADTO;

public interface QAService {

	List<QADTO> getQaList(String memberId);

}
