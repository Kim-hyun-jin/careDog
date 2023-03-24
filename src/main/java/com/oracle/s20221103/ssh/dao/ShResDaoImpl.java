package com.oracle.s20221103.ssh.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.oracle.s20221103.dto.MapperVO;
import com.oracle.s20221103.dto.ResVO;
import com.oracle.s20221103.dto.ReservationDTO;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ShResDaoImpl implements ShResDao {
	private final SqlSession session;

	@Override
	public List<ReservationDTO> resList(ReservationDTO reservationDTO) {
		List<ReservationDTO> listRes = null;
		System.out.println("ShResDaoImpl Start..." );
		
		try {
			listRes = session.selectList("shResList", reservationDTO);
			System.out.println("ShResDaoImpl resList listRes.size() -> " + listRes.size());
			System.out.println("ShResDaoImpl resList listRes.get(0).getRes_no() -> " + listRes.get(0).getResNo());
		
		} catch (Exception e) {
			System.out.println("ShResDaoImpl resList Exception->"+e.getMessage());
		}
		return listRes;
	}

	@Override
	public void resInsert(ResVO resVO) {
		System.out.println("ShResDaoImpl resInsert...");
		int result = 0;
		
		try {
			result = session.insert("shResInsert", resVO);
			result = session.insert("shResPayInsert", resVO);
		} catch (Exception e) {
			System.out.println("ShResDaoImpl resInsert Exception->"+e.getMessage());
		}
	}

	@Override
	public List<ReservationDTO> resList(MapperVO mapperVO) {
		List<ReservationDTO> listRes = null;
		System.out.println("ShResDaoImpl Start..." );
		
		try {
			listRes = session.selectList("shResMemberList", mapperVO);
			System.out.println("ShResDaoImpl resList(Member) listRes.size() -> " + listRes.size());
			System.out.println("ShResDaoImpl resList(Member) listRes.get(0).getRes_no() -> " + listRes.get(0).getResNo());
			
		} catch (Exception e) {
			System.out.println("ShResDaoImpl resList(Member) Exception->"+e.getMessage());
		}
		return listRes;
	}

	@Override
	public long resNoSelect() {
		long resNum = 0;
		System.out.println("ShResDaoImpl resNoSelect");
		
		try {
			resNum = session.selectOne("shResNumSel");
		} catch (Exception e) {
			System.out.println("ShResDaoImpl resNum Exception->"+e.getMessage());
		}
		
		return resNum;
	}

	@Override
	public void resUpdate(Long resNo) {
		int result = 0;
		System.out.println("ShResDaoImpl resUpdate");
		
		try {
			result = session.update("shResPayUpdate", resNo);
		} catch (Exception e) {
			System.out.println("ShResDaoImpl resUpdate Exception->"+e.getMessage());
		}
		
	}

	@Override
	public int totalRes() {
		int total = 0;
		
		try {
			total = session.selectOne("shResTotal");
		} catch (Exception e) {
			System.out.println("ShResDaoImpl totalRes Exception -> " + e.getMessage());
		}
		return total;
	}

	@Override
	public int totalRes(Long id) {
		int total = 0;
		
		try {
			total = session.selectOne("shResMemberTotal", id);
		} catch (Exception e) {
			System.out.println("ShResDaoImpl totalRes Exception -> " + e.getMessage());
		}
		return total;
	}

	@Override
	public List<ReservationDTO> resListSearch(MapperVO mapperVO) {
		List<ReservationDTO> listRes = null;
		System.out.println("ShResDaoImpl Start..." );
		
		try {
			listRes = session.selectList("shResMemberListSearch", mapperVO);
			System.out.println("ShResDaoImpl resList(Member) listRes.size() -> " + listRes.size());
			
		} catch (Exception e) {
			System.out.println("ShResDaoImpl resList(Member) Exception->"+e.getMessage());
		}
		return listRes;
	}

	@Override
	public List<ReservationDTO> resListSearchAdmin(MapperVO mapperVO) {
		List<ReservationDTO> listRes = null;
		System.out.println("resListSearchAdmin Start..." );
		
		try {
			listRes = session.selectList("shResListSearch", mapperVO);
			System.out.println("mapperVO -> " + mapperVO.getName());
			System.out.println("resListSearchAdmin listRes.size() -> " + listRes.size());
			
		} catch (Exception e) {
			System.out.println("ShResDaoImpl resListSearchAdmin Exception->"+e.getMessage());
		}
		return listRes;
	}

	@Override
	public int totalRes(String name) {
		int total = 0;
		
		try {
			total = session.selectOne("shResSearchTotal", name);
		} catch (Exception e) {
			System.out.println("ShResDaoImpl totalRes Exception->"+e.getMessage());
		}
		return total;
	}

}
