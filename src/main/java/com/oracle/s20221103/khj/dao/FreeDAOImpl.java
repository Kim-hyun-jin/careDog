package com.oracle.s20221103.khj.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.oracle.s20221103.dto.CommonDTO;
import com.oracle.s20221103.dto.FreeBoardDTO;
import com.oracle.s20221103.dto.FreeReplyDTO;
import com.oracle.s20221103.dto.ImageDTO;
import com.oracle.s20221103.dto.PageDTO;
import com.oracle.s20221103.dto.RecommandLogDTO;

import lombok.extern.slf4j.Slf4j;


@Repository
@Slf4j
public class FreeDAOImpl implements FreeDAO {
	private final SqlSession session;

	public FreeDAOImpl(SqlSession session) {
		
		this.session = session;
	}
	@Override
	public int selFreeTotal() {
		int total = 0;
		try {
			total = session.selectOne("selFreeTotal");
		} catch (Exception e) {
			log.info("selFreeTotal() e.getMessage() ==>"+e.getMessage());
		}
		return total;
	}
	@Override
	public List<FreeBoardDTO> selFreeList() {
		
		List<FreeBoardDTO> freeList = session.selectList("selFreeList");
		
		return freeList;
	}
	@Override
	public List<FreeBoardDTO> selFreeListWithCri(PageDTO page) {
		log.info("selFreeListWithCri() start...");
		List<FreeBoardDTO> freeList = null;
		try {
			freeList  = session.selectList("selFreeList",page);
		} catch (Exception e) {
			log.info("selFreeListWithCri() e.getMessage() : "+e.getMessage());
		}
		log.info("selFreeListWithCri() finish...");
		return freeList;
	}
	@Override
	public FreeBoardDTO selFree(Long freeboardNo) {
		log.info("selFree() start...");
		FreeBoardDTO result = null;
		try {
			result = session.selectOne("selFree", freeboardNo);
		} catch (Exception e) {
			log.info("selFree() e.getMessage :"+e.getMessage());
		}
		log.info("selFree() finish...");
		return result;
	}
	@Override
	public int insFree(FreeBoardDTO freeboard) {
		int result = 0;
		System.out.println("freeboard : "+freeboard.toString());
		try {
			result = session.insert("insFree", freeboard);
		} catch (Exception e) {
			log.info("insFree() e.getMessage : "+e.getMessage());
		}
		return result;	

	}
	@Override
	public int updFree(FreeBoardDTO freeboard) {
		// TODO Auto-generated method stub
		
		return 0;
	}
	@Override
	public List<CommonDTO> getCategoryList() {
		log.info("getCategoryList() start...");
		List<CommonDTO> result = null;
		try {
			result = session.selectList("getFreeCategoryList");
		} catch (Exception e) {
			log.info("getCategoryList() e.getMessage() : "+e.getMessage());
		}
		log.info("getCategoryList() finish...");
		return result;
	}
	@Override
	public List<FreeReplyDTO> getReplyList(Long freeboardNo) {
		log.info("getReplyList() start...");
		List<FreeReplyDTO> result = null;
		try {
			result = session.selectList("findReplyForBoard",freeboardNo);
		} catch (Exception e) {
			log.info("getReplyList() e.getMessage : "+e.getMessage());
		}
		log.info("getReplyList() finish...");
		return result;
	}
	@Override
	public FreeReplyDTO insReply(FreeReplyDTO freeReply) {
		log.info("insReply() start...");
		FreeReplyDTO result =null;
		try {
			session.insert("insertReply",freeReply);
			result = getReply(freeReply.getReplyNo());
		} catch (Exception e) {
			log.info("insReply() e.getMessage :"+e.getMessage());
		}
		log.info("insReply() finish...");
		return result;
	}
	@Override
	public FreeReplyDTO getReply(Long replyNo) {
		log.info("getReply() start...");
		FreeReplyDTO result =null;
		try {
			result = session.selectOne("getReply",replyNo);
		} catch (Exception e) {
			log.info("getReply() e.getMessage :"+e.getMessage());
		}
		log.info("getReply() finish...");
		return result;
	}
	@Override
	public int modReply(FreeReplyDTO freeReply) {
		log.info("modReply() start...");
		int result = 0;
		try {
			result = session.update("modifyReply",freeReply);
		} catch (Exception e) {
			log.info("modReply() e.getMessage() :"+e.getMessage());
		}
		log.info("modReply() finish...");
		return result;
	}
	@Override
	public int delReply(Long replyNo) {
		log.info("delReply() start...");
		int result = 0;
		try {
			result = session.delete("deleteReply",replyNo);
		} catch (Exception e) {
			log.info("delReply() e.getMessage() :"+e.getMessage());
		}
		log.info("delReply() finish...");
		return result;
	}
	@Override
	public int insFreeImg(Long freeboardNo, List<ImageDTO> imgs) {
		log.info("insFreeImg() start...");
		int result = 0;
		try {
			for(ImageDTO image : imgs) {
				image.setTableType("FREEBOARD");
				image.setCusNo(freeboardNo);
				result = session.insert("insertFreeboardImg",image);
			}
		} catch (Exception e) {
			log.info("insFreeImg() e.getMessage() :"+e.getMessage());
		}
		log.info("insFreeImg() finish...");
		return result;
	}
	@Override
	public int delContent(Long freeboardNo) {
		log.info("delContent() start...");
		int result = 0;
		try {
			session.delete("deleteImgs",freeboardNo);
			session.delete("deleteReplyByBoardNo",freeboardNo);
			session.delete("deleteRecommandLog",freeboardNo);
			result = session.delete("deleteFreeboardContent",freeboardNo);
		} catch (Exception e) {
			log.info("delContent() e.getMessage : "+e.getMessage());
		}
		log.info("delContent() finish...");
		return result;
	}
	@Override
	public int freeboardPostCntInc(Long freeboardNo) {
		log.info("freeboardPostCntInc() start...");
		int result = 0;
		try {
			result = session.update("freeboardPostCntInc",freeboardNo);
		} catch (Exception e) {
			log.info("freeboardPostCntInc() e.getMessage : "+e.getMessage());
		}
		log.info("freeboardPostCntInc() start...");
		return result;
	}
	@Override
	public RecommandLogDTO getRecommandLog(Long freeboardNo, Long id) {
		log.info("getRecommandLog() start...");
		RecommandLogDTO result = null;
		try {
			RecommandLogDTO parameter = new RecommandLogDTO();
			parameter.setFreeboardNo(freeboardNo);
			parameter.setId(id);
			result = session.selectOne("getRecommandLog",parameter);
		} catch (Exception e) {
			log.info("getRecommandLog() e.getMessage() : "+e.getMessage());
		}
		log.info("getRecommandLog() finish...");
		return result;
	}
	@Override
	public int createRecommandLog(Long freeboardNo, Long id) {
		log.info("createRecommandLog() start...");
		int result = 0;
		try {
			RecommandLogDTO parameter = new RecommandLogDTO();
			parameter.setFreeboardNo(freeboardNo);
			parameter.setId(id);
			System.out.println(parameter);
			result = session.insert("createRecommandLog",parameter);
		} catch (Exception e) {
			log.info("createRecommandLog() e.getMessage() : "+e.getMessage());
		}
		log.info("createRecommandLog() finish...");
		return result;
	}
	@Override
	public int actRecommand(int recommandLog, Long freeboardNo, Long id) {
		log.info("actRecommand() start...");
		int result = 0;
		RecommandLogDTO parameter = new RecommandLogDTO();
		parameter.setFreeboardNo(freeboardNo);
		parameter.setRecommandLog(1);
		parameter.setId(id);
		try {
			if(recommandLog==0) {
				session.update("postRecommand",parameter);
				result = session.update("actRecommand",parameter);
			} else {
				parameter.setRecommandLog(-1);
				session.update("postRecommand",parameter);
				result = session.update("actRecommand",parameter);
			}
		} catch (Exception e) {
			log.info("actRecommand() e.getMessage() : "+e.getMessage());
		}
		log.info("actRecommand() finish...");
		return result;
	}
	@Override
	public List<ImageDTO> getImgList(Long freeboardNo) {
		log.info("getImgList() start...");
		List<ImageDTO> result = null;
		try {
			result = session.selectList("selectImgs",freeboardNo);
			for(ImageDTO i : result) {
				i.setOrgImg(i.getImgPath().substring(i.getImgPath().indexOf("_")+1));
			}
		} catch (Exception e) {
			log.info("getImgList() e.getMessage() : "+e.getMessage());
		}
		log.info("getImgList() finish...");
		return result;
	}
	@Override
	public int selSearchFreeTotal(PageDTO searchs) {
		log.info("selSearchFreeTotal() start...");
		int result = 0;
		try {
			result = session.selectOne("selSearchFreeTotal",searchs);
		} catch (Exception e) {
			log.info("selSearchFreeTotal() e.getMessage() : "+e.getMessage());
		}
		log.info("selSearchFreeTotal() finish...");
		return result;
	}
	@Override
	public List<FreeBoardDTO> getSearchFreeList(PageDTO page) {
		log.info("getSearchFreeList() start...");
		List<FreeBoardDTO> result = null;
		try {
			result = session.selectList("getSearchFreeList",page);
		} catch (Exception e) {
			log.info("getSearchFreeList() e.getMessage() : "+e.getMessage());
		}
		log.info("getSearchFreeList() finish...");
		return result;
	}
	@Override
	public int delFreeImg(String[] modImgPath) {
		log.info("delFreeImg() start...");
		int result = 0;
		try {
			for(String imgPath : modImgPath) result += session.delete("deleteFreeboardImages",imgPath);
		} catch (Exception e) {
			log.info("delFreeImg() e.getMessage() : "+e.getMessage());
		}
		log.info("delFreeImg() finish...");
		return result;
	}
	@Override
	public int modFree(FreeBoardDTO freeboard) {
		log.info("modFree() start...");
		int result = 0;	
		try {
			result = session.update("updateFreeboard",freeboard);
			System.out.println("result : "+result);
		} catch (Exception e) {
			log.info("modFree() e.getMessage() : "+e.getMessage());
		}
		log.info("modFree() finish...");
		return result;
	}
	@Override
	public String[] getOtherPages(Long freeboardNo) {
		log.info("getOtherPages() start...");
		String[] result = new String[2];
		try {
			result[0] = session.selectOne("prevPost",freeboardNo);
			result[1] = session.selectOne("nextPost",freeboardNo);
		} catch (Exception e) {
			log.info("getOtherPages() e.getMessage() : "+e.getMessage());
		}
		log.info("getOtherPages() finish...");
		return result;
	}
	@Override
	public List<FreeBoardDTO> getFreeLatestNotices() {
		log.info("getFreeLatestNotices() start...");
		List<FreeBoardDTO> result = null;
		try {
			result = session.selectList("freeBoardLatestNotices");
			System.out.println(result.size());
		} catch (Exception e) {
			log.info("getFreeLatestNotices() e.getMessage() : "+e.getMessage());
		}
		log.info("getFreeLatestNotices() finish...");
		return result;
	}
}
