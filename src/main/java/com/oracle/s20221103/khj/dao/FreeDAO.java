package com.oracle.s20221103.khj.dao;

import java.util.List;

import com.oracle.s20221103.dto.CommonDTO;
import com.oracle.s20221103.dto.FreeBoardDTO;
import com.oracle.s20221103.dto.FreeReplyDTO;
import com.oracle.s20221103.dto.ImageDTO;
import com.oracle.s20221103.dto.PageDTO;
import com.oracle.s20221103.dto.RecommandLogDTO;

public interface FreeDAO {

	int selFreeTotal();
	
	List<FreeBoardDTO> selFreeList();
	
	List<FreeBoardDTO> selFreeListWithCri(PageDTO page);
	
	FreeBoardDTO selFree(Long freeboardNo);

	int insFree(FreeBoardDTO freeboard);

	int updFree(FreeBoardDTO freeboard);

	List<CommonDTO> getCategoryList();

	List<FreeReplyDTO> getReplyList(Long freeboardNo);

	FreeReplyDTO insReply(FreeReplyDTO freeReply);
	
	FreeReplyDTO getReply(Long replyNo);

	int modReply(FreeReplyDTO freeReply);

	int delReply(Long replyNo);

	int insFreeImg(Long freeboardNo, List<ImageDTO> imgs);

	int delContent(Long freeboardNo);

	int freeboardPostCntInc(Long freeboardNo);

	RecommandLogDTO getRecommandLog(Long freeboardNo, Long id);

	int createRecommandLog(Long freeboardNo, Long id);

	int actRecommand(int recommandLog, Long freeboardNo, Long id);

	List<ImageDTO> getImgList(Long freeboardNo);

	int selSearchFreeTotal(PageDTO searchs);

	List<FreeBoardDTO> getSearchFreeList(PageDTO page);

	int delFreeImg(String[] modImgPath);

	int modFree(FreeBoardDTO freeboard);

	String[] getOtherPages(Long freeboardNo);

	List<FreeBoardDTO> getFreeLatestNotices();
}
