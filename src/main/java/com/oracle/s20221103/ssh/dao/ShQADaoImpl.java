package com.oracle.s20221103.ssh.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.oracle.s20221103.dto.QADTO;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ShQADaoImpl implements ShQADao {
	private final SqlSession session;
	
	@Override
	public int totalQA() {
		int total = 0;
		
		try {
			total = session.selectOne("shQATotal");
		} catch (Exception e) {
			System.out.println("ShQADaoImpl totalQA e.getMessage() -> " + e.getMessage());
		}
		return total;
	}

	@Override
	public List<QADTO> qaQues(QADTO qadto) {
		List<QADTO> qaQuestion = null;
		
		try {
			qaQuestion = session.selectList("shQAAll", qadto);
		} catch (Exception e) {
			System.out.println("ShQADaoImpl qaQues e.getMessage() -> " + e.getMessage());
		}
		return qaQuestion;
	}

	@Override
	public List<QADTO> qaAdmin(QADTO qadto) {
		List<QADTO> qaAdminList = null;
		
		try {
			qaAdminList = session.selectList("shQAAdmin", qadto);
		} catch (Exception e) {
			System.out.println("ShQADaoImpl qaAdmin e.getMessage() -> " + e.getMessage());
		}
		return qaAdminList;
	}

	@Override
	public QADTO qaContent(Integer questionNo) {
		QADTO qadto = new QADTO();
		
		try {
			qadto = session.selectOne("shQAContent", questionNo);
		} catch (Exception e) {
			System.out.println("ShQADaoImpl qaContent e.getMessage() -> " + e.getMessage());
		}
		
		return qadto;
	}

	@Override
	public void qaInsert(QADTO qadto) {
		int result = 0;
		
		try {
			result = session.insert("shQAInsert", qadto);
		} catch (Exception e) {
			System.out.println("ShQADaoImpl qaInsert e.getMessage() -> " + e.getMessage());
		}
		
	}

	@Override
	public List<QADTO> qaQuesMyList(QADTO qadto) {
		List<QADTO> myQuesList = null;
		
		try {
			myQuesList = session.selectList("shQAMy", qadto);
		} catch (Exception e) {
			System.out.println("ShQADaoImpl qaQuesMyList e.getMessage() -> " + e.getMessage());
		}
		
		return myQuesList;
	}

	@Override
	public void qaUpdate(QADTO qadto) {
		try {
			session.update("shQAUpdate", qadto);
		} catch (Exception e) {
			System.out.println("ShQADaoImpl qaUpdate e.getMessage() -> " + e.getMessage());
		}
	}

	@Override
	public int totalQAAdmin() {
		int total = 0;
		
		try {
			total = session.selectOne("shQAAdminTotal");
		} catch (Exception e) {
			System.out.println("ShQADaoImpl totalQAAdmin e.getMessage() -> " + e.getMessage());
		}
		return total;
	}

	@Override
	public void qaContentDelete(QADTO qadto) {
		int result = 0;
		
		try {
			result = session.delete("shQADelete", qadto);
		} catch (Exception e) {
			System.out.println("ShQADaoImpl qaContentDelete e.getMessege() -> " + e.getMessage());
		}
		
	}

	@Override
	public int totalMyQA(Long id) {
		int total = 0;
		
		try {
			total = session.selectOne("shQAMyTotal", id);
		} catch (Exception e) {
			System.out.println("ShQADaoImpl totalMyQA e.getMessege() -> " + e.getMessage());
		}
		return total;
	}

	@Override
	public void qaAdminUpdate(QADTO qadto) {
		try {
			session.update("shQAAdminUpdate", qadto);
		} catch (Exception e) {
			System.out.println("ShQADaoImpl qaAdminUpdate e.getMessege() -> " + e.getMessage());
		}
		
	}

}
