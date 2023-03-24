package com.oracle.s20221103.khj.controller;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.oracle.s20221103.asecurity.PrincipalDetails;
import com.oracle.s20221103.dto.CommonDTO;
import com.oracle.s20221103.dto.FreeBoardDTO;
import com.oracle.s20221103.dto.FreeReplyDTO;
import com.oracle.s20221103.dto.ImageDTO;
import com.oracle.s20221103.dto.MemberDTO;
import com.oracle.s20221103.dto.PageDTO;
import com.oracle.s20221103.khj.service.FreeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/customerService/freeboard")
@Slf4j
@RequiredArgsConstructor
public class FreeBoardController {
	private final FreeService freeService;

	@RequestMapping("/main")
	public String customerServiceFreeboardMain(@RequestParam(defaultValue = "1") int pageNum, Model model, @AuthenticationPrincipal PrincipalDetails principal) {
		log.info("customerServiceFreeboardMain() start...");
		PageDTO page = new PageDTO(freeService.selFreeTotal(), Integer.toString(pageNum));
		model.addAttribute("latestNotices",  freeService.getFreeLatestNotices());
		model.addAttribute("categoryList", freeService.getCategoryList());
		model.addAttribute("freeList",  freeService.selFreeListWithCri(page));
		model.addAttribute("pageMaker", page);
		model.addAttribute("pageNum",pageNum);
		log.info("customerServiceFreeboardMain() finish...");
		return "customerService/freeboard/main";
	}
	@GetMapping("/detail")
	public String detail(@RequestParam(name = "freeboardNo") Long freeboardNo, Model model, @AuthenticationPrincipal PrincipalDetails principal) {
		log.info("detail() start...");
		//자유글번호로 데이터 조회
		freeService.freeboardPostCntInc(freeboardNo);
		FreeBoardDTO freeboard = freeService.selFree(freeboardNo);
		List<FreeReplyDTO> replyList = freeService.getReplyList(freeboardNo);
		String otherPages[] = freeService.getOtherPages(freeboardNo);
		//조회 객체 모델에 담아서 화면에 전달
		model.addAttribute("otherPosts",otherPages);
		model.addAttribute("recommandLog",0);
		model.addAttribute("freeboard", freeboard);
		model.addAttribute("replyList", replyList);
		model.addAttribute("nextPage","");
		if(principal!=null) {
			int recommandLog = freeService.getRecommandLog(freeboardNo,principal.getId());
			model.addAttribute("recommandLog",recommandLog);
		}
		log.info("detail() finish...");
		return "customerService/freeboard/detail";
	}
	@GetMapping("/write")
	public String writeGet(Model model) {
		List<CommonDTO> result = freeService.getCategoryList();
		model.addAttribute("comm", result);
		return "customerService/freeboard/write";
	}
	@PostMapping("/write")
	public String writePost(FreeBoardDTO freeboard,String imgClass[], String imgPath[], @AuthenticationPrincipal PrincipalDetails principal,Model model,HttpServletRequest request) {
		List<ImageDTO> imgs = new ArrayList<ImageDTO>(); 
		if(imgPath!=null) {
			for(int i=0; i<imgPath.length; i++) {
				imgs.add(new ImageDTO(imgPath[i],imgClass[i]));
			}
		}
		MemberDTO myinfo = principal.getUser();
		freeboard.setUsername(myinfo.getMemberName());
		freeboard.setId(myinfo.getId());
		int result = freeService.insFree(freeboard);
		if(result==0){
			if(imgPath!=null) {
				String uploadPath = request.getSession().getServletContext().getRealPath("/images/freeboardImg/");
				for(String i : imgPath) upFileDelete(uploadPath+i);
			}
			return "redirect:/customerService/freeboard/main";
		} else if(imgs.size()!=0) {
			int insImgResult = freeService.insFreeImg(freeboard.getFreeboardNo(),imgs);
			if(insImgResult==0) for(int i=0; i<imgPath.length; i++) upFileDelete(imgPath[i]);
			return "redirect:/customerService/freeboard/detail?freeboardNo="+freeboard.getFreeboardNo();
		} else {
			return "redirect:/customerService/freeboard/detail?freeboardNo="+freeboard.getFreeboardNo();
		}
	}
	@PreAuthorize("principal.getId() == #freeboard.id")
	@PostMapping("/modify")
	public String modifyPost(FreeBoardDTO freeboard,
							String imgClass[], 
							String imgPath[], 
							String modImgPath[], 
							Model model,
							HttpServletRequest request) {
		log.info("modifyPost() start...");
		String uploadPath = request.getSession().getServletContext().getRealPath("/images/freeboardImg/");
		List<ImageDTO> imgs = new ArrayList<ImageDTO>(); 
		System.out.println(freeboard);
		int result = freeService.modFree(freeboard);
		
		if(result==0){
			if(imgPath!=null && imgPath.length!=0) {
				for(String i : imgPath) upFileDelete(uploadPath+i);
			}
			return "redirect:/customerService/freeboard/main";
		} else if(imgPath!=null && imgPath.length!=0) {
			for(int i=0; i<imgPath.length; i++) {
				imgs.add(new ImageDTO(imgPath[i],imgClass[i]));
			}
			int insImgResult = freeService.insFreeImg(freeboard.getFreeboardNo(),imgs);
			if(insImgResult==0) {
				for(int i=0; i<imgPath.length; i++) {
					upFileDelete(uploadPath+imgPath[i]);
				}
			}
			if(modImgPath!=null && modImgPath.length!=0) {
				freeService.delFreeImg(modImgPath); 
				for(String i : modImgPath) upFileDelete(uploadPath+i);
			}
			log.info("modifyPost() start...");
			return "redirect:/customerService/freeboard/detail?freeboardNo="+freeboard.getFreeboardNo();
		} else {
			if(modImgPath!=null && modImgPath.length!=0) {
				freeService.delFreeImg(modImgPath); 
				for(String i : modImgPath) upFileDelete(uploadPath+i);
			}
			log.info("modifyPost() start...");
			return "redirect:/customerService/freeboard/detail?freeboardNo="+freeboard.getFreeboardNo();
		}
	}
	@PreAuthorize("principal.getUsername() == #freeBoard.username")
	@GetMapping("/modify")
	public String modifyPost(Model model,FreeBoardDTO freeBoard) {
		System.out.println(freeBoard.getUsername());
		List<CommonDTO> result = freeService.getCategoryList();
		List<ImageDTO> imgResult = freeService.getImgList(freeBoard.getFreeboardNo());
		int maxClassSeqNum = 1000;
		if(imgResult!=null && imgResult.size()!=0) maxClassSeqNum = Integer.parseInt(imgResult.get(0).getImgClass().substring(6))+1;
		model.addAttribute("maxClassSeqNum", maxClassSeqNum);
		model.addAttribute("freeboard", freeService.selFree(freeBoard.getFreeboardNo()));
		model.addAttribute("freeboardImgs", imgResult);
		model.addAttribute("comm", result);
		return "/customerService/freeboard/modify";
	}
	@ResponseBody
	@PostMapping("/replyWrite")
	public void replyWrite(FreeReplyDTO freeReply,@AuthenticationPrincipal PrincipalDetails principal) {
		log.info("replyWrite() start...");
		freeService.insReply(freeReply);
		log.info("replyWrite() finish...");
	}
	@ResponseBody
	@GetMapping("/replyList")
	public List<FreeReplyDTO> replyList(Long freeboardNo) {
		log.info("replyList() start...");
		List<FreeReplyDTO> result = freeService.getReplyList(freeboardNo);
		log.info("replyList() finish...");
		return result;
	}
	@ResponseBody
	@PostMapping("/modReply")
	public String modReply(FreeReplyDTO freeReply) {
		log.info("modReply() start...");
		String result = Integer.toString(freeService.modReply(freeReply));
		log.info("modReply() finish...");
		return result;
	}
	@ResponseBody
	@PostMapping("/delReply")
	public String delReply(Long replyNo) {
		log.info("delReply() start...");
		String result = Integer.toString(freeService.delReply(replyNo));
		log.info("delReply() finish...");
		return result;
	}
	@ResponseBody
	@PostMapping("/ajaxFileUpload")
	public String[] uploadForm(HttpServletRequest request,@RequestParam("image") MultipartFile file1, Model model) throws IOException, Exception {
		log.info("upLoadFormStart() POST start...");
		
//		Servlet이 상속 받지 못 했을 때 realPath 불러 오는 방법.
		String uploadPath = request.getSession().getServletContext().getRealPath("/images/freeboardImg/");
		log.info("originalName : "+file1.getOriginalFilename());
		log.info("size : "+file1.getSize());
		log.info("contentType : "+file1.getContentType());
		log.info("uploadPath : "+uploadPath);
		String savedName = uploadFile(file1.getOriginalFilename().replaceAll(" ", "_"), file1.getBytes(), uploadPath);
		log.info("return savedName : "+savedName);
		log.info("upLoadFormStart() POST finish...");
		String[] result = {savedName, file1.getOriginalFilename().replaceAll(" ", "_")};
		return result;
	}
	@ResponseBody
	@PostMapping("/ajaxFileDelete")
	public String ajaxFileDelete(String filename, HttpServletRequest request) {
		String uploadPath = request.getSession().getServletContext().getRealPath("/images/freeboardImg/");
		String deleteFile = uploadPath+filename;
		int delResult = upFileDelete(deleteFile);
		return Integer.toString(delResult);
	}
	@ResponseBody
	@PostMapping("/ajaxUnloadFileDelete")
	public void ajaxUnloadFileDelete(String imgPath[],HttpServletRequest request) {
		String uploadPath = request.getSession().getServletContext().getRealPath("/images/freeboardImg/");
		int cnt = 0;
		for(String i : imgPath) {
			if(upFileDelete(uploadPath+i)>0) cnt++;
		}
		System.out.println(imgPath.length+"개 사진 중 "+cnt+"개 사진 삭제 완료.");
	}
	private String uploadFile(String originalName, byte[] fileData, String  uploadPath) throws Exception {
		File fileDirectory = new File(uploadPath); //업로드경로의 파일폴더 획득(없으면 생성)
		if (!fileDirectory.exists()) fileDirectory.mkdirs();
		String savedName =  UUID.randomUUID().toString()+"_"+originalName; // UUID로 중복없는 파일명 획득
		File target = new File(uploadPath,savedName); //폴더경로에 생성한 파일명으로 빈 파일 생성.
		FileCopyUtils.copy(fileData, target); //org.springframework.util.FileCopyUtils //저장한 파일데이터를 생성한 파일에 복사.(저장).
		return savedName;
	}
	private int upFileDelete(String deleteFileName) {
		int result = 0;
		File file = new File(deleteFileName);
		if(file.exists()) {
			if(file.delete()) result = 1;
			else log.info("파일 삭제 실패");
		} else {
			log.info("파일이 존재하지 않습니다.");
			result = -1;
		}
		return result;
	}
	@GetMapping("/delete")
	public String deleteFreeboard(Long freeboardNo, @AuthenticationPrincipal PrincipalDetails principal,Model model,HttpServletRequest request) {
		log.info("deleteFreeboard() start...");
		List<ImageDTO> imgPaths = freeService.getImgList(freeboardNo);
		int result = freeService.delContent(freeboardNo,principal.getUser());
		if(result==-1) return "redirect:/customerService/freeboard/main";
		if(imgPaths!=null) {
			String uploadPath = request.getSession().getServletContext().getRealPath("/images/freeboardImg/");
			for(ImageDTO i : imgPaths) upFileDelete(uploadPath+i.getImgPath());
		}
		model.addAttribute("DeletePostResult",result);
		log.info("deleteFreeboard() finish...");
		return "forward:/customerService/freeboard/main";
	}
	@ResponseBody
	@PostMapping("/recommandAct")
	public String recommandAct(int recommandLog, Long freeboardNo, @AuthenticationPrincipal PrincipalDetails principal) {
		String msg = "추천에 성공했습니다.";
		if(recommandLog!=0) msg = "추천을 취소했습니다.";
		freeService.actRecommand(recommandLog, freeboardNo, principal.getId());
		return msg;
	}
	@GetMapping("/search")
	public String customerServiceFreeboardSearch(String searchString, String searchOption,
												Model model, 
												HttpServletRequest request) {
		log.info("customerServiceFreeboardSearch() start...");
		HttpSession session = request.getSession();
		PageDTO searchs = new PageDTO(0, "0");
		searchs.setSearchOption(searchOption);
		searchs.setSearchString(searchString);
		session.setAttribute("freeboardSearchSet", searchs);
		int total = freeService.selSearchFreeTotal(searchs);
		PageDTO page = new PageDTO(total, Integer.toString(1));
		page.setSearchOption(searchs.getSearchOption());
		page.setSearchString(searchs.getSearchString());
		List<FreeBoardDTO> searchResult = freeService.getSearchFreeList(page);
		
		model.addAttribute("categoryList", freeService.getCategoryList());
		model.addAttribute("latestNotices",  freeService.getFreeLatestNotices());
		model.addAttribute("freeList",  searchResult);
		model.addAttribute("pageMaker", page);
		model.addAttribute("pageNum",1);
		log.info("customerServiceFreeboardSearch() finish...");
		return "customerService/freeboard/search";
	}
	@GetMapping("/searchPage")
	public String customerServiceFreeboardSearchMove(int pageNum, Model model, HttpServletRequest request) {
		log.info("customerServiceFreeboardSearch() start...");
		HttpSession session = request.getSession();
		PageDTO searchs = (PageDTO) session.getAttribute("freeboardSearchSet");
		int total = freeService.selSearchFreeTotal(searchs);
		PageDTO page = new PageDTO(total, Integer.toString(pageNum));
		page.setSearchOption(searchs.getSearchOption());
		page.setSearchString(searchs.getSearchString());
		List<FreeBoardDTO> searchResult = freeService.getSearchFreeList(page);
		
		model.addAttribute("latestNotices",  freeService.getFreeLatestNotices());
		model.addAttribute("categoryList", freeService.getCategoryList());
		model.addAttribute("freeList",  searchResult);
		model.addAttribute("pageMaker", page);
		model.addAttribute("pageNum",pageNum);
		log.info("customerServiceFreeboardSearch() finish...");
		return "customerService/freeboard/search";
	}
}

