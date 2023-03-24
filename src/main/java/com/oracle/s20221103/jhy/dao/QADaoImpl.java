package com.oracle.s20221103.jhy.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.oracle.s20221103.dto.QADTO;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class QADaoImpl implements QADao {
	private final SqlSession session;

	@Override
	public List<QADTO> getQaList(String status) {
		List<QADTO> getQaList = session.selectList("qaList", status);
		return getQaList;
	}
}
