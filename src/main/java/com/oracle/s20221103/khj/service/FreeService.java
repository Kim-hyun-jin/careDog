package com.oracle.s20221103.khj.service;

import java.util.List;

import com.oracle.s20221103.dto.CommonDTO;
import com.oracle.s20221103.dto.FreeBoardDTO;
import com.oracle.s20221103.dto.FreeReplyDTO;
import com.oracle.s20221103.dto.ImageDTO;
import com.oracle.s20221103.dto.MemberDTO;
import com.oracle.s20221103.dto.PageDTO;

public interface FreeService {

	int selFreeTotal();
	List<FreeBoardDTO> selFreeList();
	List<FreeBoardDTO> selFreeListWithCri(PageDTO page);
	FreeBoardDTO selFree(Long freeboardNo);
	int insFree(FreeBoardDTO freeboard);
	int updFree(FreeBoardDTO freeboard);
	List<CommonDTO> getCategoryList();
	List<FreeReplyDTO> getReplyList(Long freeboardNo);
	FreeReplyDTO insReply(FreeReplyDTO freeReply);
	int modReply(FreeReplyDTO freeReply);
	int delReply(Long replyNo);
	int insFreeImg(Long freeboardNo, List<ImageDTO> imgs);
	int delContent(Long freeboardNo, MemberDTO user);
	int freeboardPostCntInc(Long freeboardNo);
	int getRecommandLog(Long freeboardNo, Long id);
	int actRecommand(int recommandLog, Long freeboardNo, Long id);
	List<ImageDTO> getImgList(Long freeboardNo);
	int selSearchFreeTotal(PageDTO searchs);
	List<FreeBoardDTO> getSearchFreeList(PageDTO page);
	int delFreeImg(String[] modImgPath);
	int modFree(FreeBoardDTO freeboard);
	String[] getOtherPages(Long freeboardNo);
	List<FreeBoardDTO> getFreeLatestNotices();
}
