package com.oracle.s20221103.kdj.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.oracle.s20221103.asecurity.PrincipalDetails;
import com.oracle.s20221103.dto.DogDTO;
import com.oracle.s20221103.dto.DogNoticeDTO;
import com.oracle.s20221103.dto.MemberDTO;
import com.oracle.s20221103.kdj.service.DogService;
import com.oracle.s20221103.kdj.service.Paging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class DogNoticeController {
	private final DogService ds;

	@PreAuthorize("principal.getPremiumChk() || hasAnyRole('ROLE_TEACHER','ROLE_ADMIN')")
	@RequestMapping(value = "school/dogNotice/main")
	public String DogList(Model model, DogNoticeDTO dog, Long noticeNo, String currentPage,
			@AuthenticationPrincipal PrincipalDetails principal) {
		Long id = 0L;
		System.out.println("DogNoticeController DogList start ");
		MemberDTO myInfo = principal.getUser();
		id = myInfo.getId();
		dog.setId(id);

		if (myInfo.getRole().equals("ROLE_TEACHER")) {
			dog.setTeacherId(id);
			int teacherCount = ds.TtotalCount(id);
			Paging page = new Paging(teacherCount, currentPage); // Parameter
			// Page만 추가 Setting
			dog.setStart(page.getStart()); // 시작시 1
			dog.setEnd(page.getEnd()); // 시작시 10
			List<DogNoticeDTO> TList = ds.teacherList(dog);

			model.addAttribute("id", id);
			model.addAttribute("dogList", TList);
			model.addAttribute("page", page);

		}
		if (myInfo.getRole().equals("ROLE_MEMBER")) {
			dog.setId(id);
			int totalCount = ds.totalCount(id);
			Paging page1 = new Paging(totalCount, currentPage); // Parameter
			// Page만 추가 Setting
			dog.setStart(page1.getStart()); // 시작시 1
			dog.setEnd(page1.getEnd()); // 시작시 10
			List<DogNoticeDTO> dogList = ds.listDog(dog);

			model.addAttribute("id", id);
			model.addAttribute("noticeNo", noticeNo);
			model.addAttribute("dogList", dogList);
			model.addAttribute("page", page1);
			System.out.println("KinderController DogList->" + dogList.size());
		}
		return "school/dogNotice/main";
	}

	@PreAuthorize("principal.getPremiumChk() || hasAnyRole('ROLE_TEACHER','ROLE_ADMIN')")
	@GetMapping("/school/dogNotice/detailContent2")
	public String selContent(Long noticeNo, Model model, DogNoticeDTO dog,
			@AuthenticationPrincipal PrincipalDetails principal) {
		Long id = 0L;
		if (principal != null) {
			MemberDTO myInfo = principal.getUser();
			id = myInfo.getId();
			dog.setId(id);
		}
		System.out.println("DogNoticeController selContent start...");
		DogNoticeDTO dog1 = ds.selContent(noticeNo);
		model.addAttribute("dog", dog1);

		return "school/dogNotice/detailContent2";
	}

	@PreAuthorize("principal.getPremiumChk() || hasAnyRole('ROLE_TEACHER','ROLE_ADMIN')")
	@GetMapping(value = "/school/dogNotice/modFormContent")
	public String modFormContent(Long noticeNo, Model model, @AuthenticationPrincipal PrincipalDetails principal) {
		System.out.println("DogNoticeController modFormContent start...");
		Long id = 0L;
		if (principal != null) {
			MemberDTO myInfo = principal.getUser();
			id = myInfo.getId();
		}

		DogNoticeDTO dog = ds.modForm(noticeNo);
		model.addAttribute("dog", dog);

		return "school/dogNotice/modFormContent";
	}

	@PreAuthorize("principal.getPremiumChk() || hasAnyRole('ROLE_TEACHER','ROLE_ADMIN')")
	@RequestMapping(value = "/school/dogNotice/mod")
	public String modContent(DogNoticeDTO dog, Model model, @RequestParam int noticeNo, MultipartFile dogImgs,
			HttpServletRequest request) throws IOException {

		log.info("DogNoticeController modContent start...");
		// 파일선택 없으면 Save Skip Logic 추가
		if (dogImgs.getOriginalFilename().length() > 0) {
			System.out.println("DogNoticeController modContent start...");
			System.out.println(
					"DogNoticeController modContent dogImgs.getOriginalFilename()->" + dogImgs.getOriginalFilename());

			String uploadPath = request.getSession().getServletContext().getRealPath("/DJimages/");
			String saveName = uploadFile(dogImgs.getOriginalFilename(), dogImgs.getBytes(), uploadPath);
			model.addAttribute("saveName", saveName);
			dog.setDogImgName(saveName);
			dog.setDogImgPath(uploadPath);
		} else {
			System.out.println("LhjController UpdMyDogInfo 파일선택 없으면 Start...." + dog.getDogImgName());

		}

		int count = ds.modContent(dog);
		System.out.println("Count-->" + count);
		model.addAttribute("count", count);
		return "redirect:/school/dogNotice/main";

	}

	@PreAuthorize("principal.getPremiumChk() || hasAnyRole('ROLE_TEACHER','ROLE_ADMIN')")
	@RequestMapping(value = "/school/dogNotice/writeForm")
	public String insDogNotice(Model model, DogNoticeDTO dog, @AuthenticationPrincipal PrincipalDetails principal,
			HttpServletRequest request, MultipartFile file) {
		System.out.println("DogNoticeController insDogNotice start");
		Long id = 0L;
		MemberDTO myInfo = principal.getUser();
		id = myInfo.getId();
		List<DogDTO> dogList = null;
		MemberDTO member = null;
		System.out.println(myInfo);
		if (myInfo.getRole().equals("ROLE_TEACHER")) {
		    dog.setTeacherId(id);
			System.out.println("teacher log start...");
			dogList = ds.TinsWrite(id);
			member = ds.TNameinsWrite(id);
			
		} else if (myInfo.getRole().equals("ROLE_MEMBER")) {
			System.out.println("MEMBER log start...");
			member = ds.MeminsWrite(id); // 선생님 id 끌고오는 거
			dogList = ds.insWrite(id);
			System.out.println("insDogNotice member => " + member);

		} else {
			System.out.println("" + toString());
		}

		model.addAttribute("dogList", dogList);
		System.out.println("dogList => " + dogList);
		model.addAttribute("member", member);
		model.addAttribute("writerId", id);
		return "school/dogNotice/writeForm";
	}

	// 나의 알림장 등록
	@PreAuthorize("principal.getPremiumChk() || hasAnyRole('ROLE_TEACHER','ROLE_ADMIN')")
	@RequestMapping(value = "school/dogNotice/write", method = { RequestMethod.POST })
	public String writeNotice(Model model, DogNoticeDTO dog, @AuthenticationPrincipal PrincipalDetails principal,
			HttpServletRequest request, MultipartFile dogImgs) throws IOException {
		System.out.println("DogNoticeController WriteNotice start");
		Long id = 0L;
		MemberDTO myInfo = principal.getUser();
		id = myInfo.getId();

		// 파일선택 없으면, save skip Logic 추가
		if (dogImgs.getOriginalFilename().length() > 0) {
			System.out.println("DogNoticeController 이미지등록 Start");
			System.out.println(" DogNoticeController dogImgs.get" + dogImgs.getOriginalFilename());
			// 이미지 등록
			String uploadPath = request.getSession().getServletContext().getRealPath("/DJimages/");
			String saveName = uploadFile(dogImgs.getOriginalFilename(), dogImgs.getBytes(), uploadPath);
			model.addAttribute("saveName", saveName);
			dog.setDogImgName(saveName);
			dog.setDogImgPath(uploadPath);
		} else {
			System.out.println("DogNoticeController 파일 선택 없으면 Start.." + dog.getDogImgName());
		}
		if (myInfo.getRole().equals("ROLE_TEACHER")) {
			System.out.println("teacher log start...");
			// 로그인한 선생님 본인의 id가 아니고, 선택한 강아지 주인 id를 셋 해야함
			// dog테이블에서 select where dogNo= dog.getDogNo()
			DogDTO dog2 = ds.getDogInfo(dog.getDogNo());
			dog.setTeacherId(id);
			dog.setId(dog2.getId());
			int result = ds.TinsertNotice(dog);
		} else if (myInfo.getRole().equals("ROLE_MEMBER")) {
			System.out.println("MEMBER log start...");
			ds.insertNotice(dog);
		} else {
			System.out.println("" + toString());
		}

		return "redirect:/school/dogNotice/main";

	}

	private String uploadFile(String originalFilename, byte[] fileData, String uploadPath) throws IOException {
		UUID uid = UUID.randomUUID();

		System.out.println("uploadPath-->" + uploadPath);

		File fileDirectory = new File(uploadPath);
		if (!fileDirectory.exists()) {
			fileDirectory.mkdirs();
			System.out.println("업로드용 폴더생성:" + uploadPath);
		}
		String savedName = uid.toString() + "_" + originalFilename;
		log.info("saveName==>" + savedName);
		File target = new File(uploadPath, savedName);
		FileCopyUtils.copy(fileData, target);
		return savedName;
	}

	@RequestMapping(value = "/school/dogNotice/delete")
	public String DeldogNotice(Long noticeNo, Model model) {
		System.out.println("DogNoticeController DeldogNotice start ...");
		Long result = ds.delDogNotice(noticeNo);
		return "redirect:/school/dogNotice/main";

	}

}
