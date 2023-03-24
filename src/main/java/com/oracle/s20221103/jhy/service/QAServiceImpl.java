package com.oracle.s20221103.jhy.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.oracle.s20221103.dto.QADTO;
import com.oracle.s20221103.jhy.dao.QADao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QAServiceImpl implements QAService {
	private final QADao qaDao;

	@Override
	public List<QADTO> getQaList(String status) {
		List<QADTO> qaDto = qaDao.getQaList(status);
		return qaDto;
	}
}
