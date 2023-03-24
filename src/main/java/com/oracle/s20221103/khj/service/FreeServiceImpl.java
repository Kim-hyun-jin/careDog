package com.oracle.s20221103.khj.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.oracle.s20221103.dto.CommonDTO;
import com.oracle.s20221103.dto.FreeBoardDTO;
import com.oracle.s20221103.dto.FreeReplyDTO;
import com.oracle.s20221103.dto.ImageDTO;
import com.oracle.s20221103.dto.MemberDTO;
import com.oracle.s20221103.dto.PageDTO;
import com.oracle.s20221103.dto.RecommandLogDTO;
import com.oracle.s20221103.khj.dao.FreeDAO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FreeServiceImpl implements FreeService {

	private FreeDAO freeDAO;
	
	
	public FreeServiceImpl(FreeDAO freeDAO) {
		
		this.freeDAO = freeDAO;
	}
	@Override
	public int selFreeTotal() {
		return freeDAO.selFreeTotal();
	}
	@Override
	public List<FreeBoardDTO> selFreeList() {
		List<FreeBoardDTO> freeList = freeDAO.selFreeList();
		return freeList;
	}
	@Override
	public List<FreeBoardDTO> selFreeListWithCri(PageDTO page) {
		log.info("selFreeListWithCri() start...");
		List<FreeBoardDTO> freeList = freeDAO.selFreeListWithCri(page);
		for(FreeBoardDTO a : freeList) {
			String newRegdate = a.getRegdate().substring(0,16);
			a.setRegdate(newRegdate);
		}
		log.info("selFreeListWithCri() finish...");
		return freeList;
	}
	@Override
	public FreeBoardDTO selFree(Long freeboardNo) {
		FreeBoardDTO freeboard = freeDAO.selFree(freeboardNo);
		return freeboard;
	}
	@Override
	public int insFree(FreeBoardDTO freeboard) {
		return freeDAO.insFree(freeboard);
	}
	@Override
	public int updFree(FreeBoardDTO freeboard) {
		// TODO Auto-generated method stub
		int result = freeDAO.updFree(freeboard);
		return result;
	}
	@Override
	public List<CommonDTO> getCategoryList() {
		return freeDAO.getCategoryList();
	}
	@Override
	public List<FreeReplyDTO> getReplyList(Long freeboardNo) {
		log.info("getReplyList() start...");
		List<FreeReplyDTO> result = freeDAO.getReplyList(freeboardNo);
		log.info("getReplyList() finish...");
		return result;
	}
	@Override
	public FreeReplyDTO insReply(FreeReplyDTO freeReply) {
		freeReply.setReplyContent(freeReply.getReplyContent().replace("\r\n","<br>"));
		return freeDAO.insReply(freeReply);
	}
	@Override
	public int modReply(FreeReplyDTO freeReply) {
		return freeDAO.modReply(freeReply);
	}
	@Override
	public int delReply(Long replyNo) {
		return freeDAO.delReply(replyNo);
	}
	@Override
	public int insFreeImg(Long freeboardNo, List<ImageDTO> imgs) {
		return freeDAO.insFreeImg(freeboardNo,imgs);
	}
	@Override
	public int delContent(Long freeboardNo, MemberDTO user) {
		int result = 0;
		FreeBoardDTO boardchk = freeDAO.selFree(freeboardNo);
		if(boardchk==null) return -1;
		else if (user.getId()==boardchk.getId() || user.getRole().equals("ROLE_ADMIN")) result = freeDAO.delContent(freeboardNo);
		return result;
	}
	@Override
	public int freeboardPostCntInc(Long freeboardNo) {
		return freeDAO.freeboardPostCntInc(freeboardNo);
	}
	@Override
	public int getRecommandLog(Long freeboardNo, Long id) {
	 RecommandLogDTO recommandLogDTO = freeDAO.getRecommandLog(freeboardNo,id);
	 if(recommandLogDTO==null) {
		 freeDAO.createRecommandLog(freeboardNo,id);
		 recommandLogDTO = freeDAO.getRecommandLog(freeboardNo,id);
	 }
		return recommandLogDTO.getRecommandLog();
	}
	@Override
	public int actRecommand(int recommandLog, Long freeboardNo, Long id) {
		return freeDAO.actRecommand(recommandLog,freeboardNo,id);
	}
	@Override
	public List<ImageDTO> getImgList(Long freeboardNo) {
		return freeDAO.getImgList(freeboardNo);
	}
	@Override
	public int selSearchFreeTotal(PageDTO searchs) {
		return freeDAO.selSearchFreeTotal(searchs);
	}
	@Override
	public List<FreeBoardDTO> getSearchFreeList(PageDTO page) {
		return freeDAO.getSearchFreeList(page);
	}
	@Override
	public int delFreeImg(String[] modImgPath) {
		return freeDAO.delFreeImg(modImgPath);
	}
	@Override
	public int modFree(FreeBoardDTO freeboard) {
		return freeDAO.modFree(freeboard);
	}
	@Override
	public String[] getOtherPages(Long freeboardNo) {
		return freeDAO.getOtherPages(freeboardNo);
	}
	@Override
	public List<FreeBoardDTO> getFreeLatestNotices() {
		return freeDAO.getFreeLatestNotices();
	}
}
