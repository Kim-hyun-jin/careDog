package com.oracle.s20221103.jhy.dao;

import java.util.List;

import com.oracle.s20221103.dto.QADTO;

public interface QADao {

	List<QADTO> getQaList(String status);

}
