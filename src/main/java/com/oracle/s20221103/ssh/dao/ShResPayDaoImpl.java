package com.oracle.s20221103.ssh.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.oracle.s20221103.dto.ResPayDTO;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ShResPayDaoImpl implements ShResPayDao {
	private final SqlSession session;

	@Override
	public List<ResPayDTO> resPayList(ResPayDTO resPayDTO) {
		List<ResPayDTO> listResPay = null;
		System.out.println("ShResPayDaoImpl Start..." );
		
		try {
			listResPay = session.selectList("shResPayList", resPayDTO);
			System.out.println("ShResPayDaoImpl resPayList listResPay.size() -> " + listResPay.size());
			System.out.println("ShResPayDaoImpl resPayList listResPay.get(0).getOrderDate() -> " + listResPay.get(0).getOrderDate());
		
		} catch (Exception e) {
			System.out.println("ShResPayDaoImpl resPayList Exception->"+e.getMessage());
		}
		return listResPay;
	}

	@Override
	public int totalResPay() {
		int total = 0;
		
		try {
			total = session.selectOne("shResPayTotal");
		} catch (Exception e) {
			System.out.println("ShResPayDaoImpl totalResPay Exception -> " + e.getMessage());
		}
		
		return total;
	}

}
