package com.oracle.s20221103.ssh.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.oracle.s20221103.domain.QA;
import com.oracle.s20221103.dto.QADTO;
import com.oracle.s20221103.ssh.dao.ShQADao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShQAServiceImpl implements ShQAService {
	private final ShQADao shQADao;

	@Override
	public int totalQA() {
		int total = shQADao.totalQA();
		
		return total;
	}

	@Override
	public List<QADTO> qaQues(QADTO qadto) {
		List<QADTO> qaQuestion = shQADao.qaQues(qadto);
		
		return qaQuestion;
	}

	@Override
	public List<QADTO> qaAdmin(QADTO qadto) {
		List<QADTO> qaAdminList = shQADao.qaAdmin(qadto);
		
		return qaAdminList;
	}

	@Override
	public QADTO qaContent(Integer questionNo) {
		QADTO qadto = shQADao.qaContent(questionNo);
		
		return qadto;
	}

	@Override
	public void qaQuesInsert(QADTO qadto) {
		shQADao.qaInsert(qadto);
	}

	@Override
	public List<QADTO> qaQuesMyList(QADTO qadto) {
		List<QADTO> myQuesList = shQADao.qaQuesMyList(qadto);
		
		return myQuesList;
	}

	@Override
	public void qaContentUpdate(QADTO qadto) {
		shQADao.qaUpdate(qadto);
	}

	@Override
	public int totalQAAdmin() {
		int total = shQADao.totalQAAdmin();
		
		return total;
	}

	@Override
	public void qaContentDelete(QADTO qadto) {
		shQADao.qaContentDelete(qadto);
	}

	@Override
	public int totalMyQA(Long id) {
		int total = shQADao.totalMyQA(id);
		return total;
	}

	@Override
	public void qaContentAdminUpdate(QADTO qadto) {
		shQADao.qaAdminUpdate(qadto);
	}

}
