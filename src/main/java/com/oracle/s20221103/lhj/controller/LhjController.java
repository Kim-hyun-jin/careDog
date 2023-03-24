package com.oracle.s20221103.lhj.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.oracle.s20221103.asecurity.PrincipalDetails;
import com.oracle.s20221103.domain.Member;
import com.oracle.s20221103.dto.DogDTO;
import com.oracle.s20221103.dto.DogTypeDTO;
import com.oracle.s20221103.dto.MemberDTO;
import com.oracle.s20221103.dto.ResPayDTO;
import com.oracle.s20221103.dto.ReservationDTO;
import com.oracle.s20221103.lhj.service.LhjService;
import com.oracle.s20221103.lhj.service.Paging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Controller
@RequiredArgsConstructor
@Slf4j
public class LhjController {

	private final LhjService lhjs;

	// 나의 반려견 리스트 조회
	@GetMapping(value = "/mypage/member/myDogList")
	public String myDogList(DogDTO dog, Model model, @AuthenticationPrincipal PrincipalDetails principal) {
		System.out.println("LhjController Start myDogList...");
		
		if(principal!=null) {
			MemberDTO myInfo = principal.getUser();
			System.out.println("principal myinfo : "+ myInfo.toString());
			dog.setId((long) myInfo.getId());
		}
		
		List<DogDTO> myDogList = lhjs.myDogList(dog);
		System.out.println("LhjController myDogList myDogList.size()=>" + myDogList.size());
		model.addAttribute("myDogList", myDogList);
		return "mypage/member/myDogList";
	}

	// 반려견번호 별 상세정보 페이지
	@GetMapping(value = "/mypage/member/myDogInfo" )
	public String mydogInfo(Long dogNo, Model model) {
		System.out.println("LhjController Start mydogInfo...");

		DogDTO dog = lhjs.mydogInfo(dogNo);
		String scStartDate = "";
		String scEndDate = "";

		if (dog.getScStartdate() != null) {
			scStartDate = dog.getScStartdate().substring(0, 10);
			dog.setScStartdate(scStartDate);
		}
		if (dog.getScEnddate() != null) {
			scEndDate = dog.getScEnddate().substring(0, 10);
			dog.setScEnddate(scEndDate);
		}

		System.out.println("scStartDate->" + scStartDate);
		System.out.println("scEndDate->" + scEndDate);

		model.addAttribute("dog", dog);

		return "mypage/member/myDogInfo";

	}

	// 반려견 정보 수정 페이지 이동
	@GetMapping(value = "/mypage/member/UpdMyDogInfoForm")
	public String UpdmydogInfoForm(Long dogNo, Model model) {
		System.out.println("LhjController Start UpdmydogInfoForm...");
		DogDTO dog = lhjs.mydogInfo(dogNo);
		String scStartDate = "";
		String scEndDate = "";

		if (dog.getScStartdate() != null) {
			scStartDate = dog.getScStartdate().substring(0, 10);
			dog.setScStartdate(scStartDate);
		}
		if (dog.getScEnddate() != null) {
			scEndDate = dog.getScEnddate().substring(0, 10);
			dog.setScEnddate(scEndDate);
		}

		model.addAttribute("dog", dog);

		System.out.println("scStartDate->" + scStartDate);
		System.out.println("scEndDate->" + scEndDate);

		return "mypage/member/UpdMyDogInfoForm";
	}

	// 나의 반려견 정보 수정
	@PostMapping(value = "/mypage/member/UpdMyDogInfo")
	public String UpdMyDogInfo(DogDTO dog, Model model
							 , HttpServletRequest request,
							   MultipartFile dogImgs) throws IOException {
		
		log.info("LhjController UpdMyDogInfo Start...");

		// 파일선택 없으면 Save Skip Logic 추가 
		if (dogImgs.getOriginalFilename().length() > 0) {
			System.out.println("LhjController UpdMyDogInfo Start....");
			System.out.println("LhjController UpdMyDogInfo dogImgs.getOriginalFilename()->"+dogImgs.getOriginalFilename());

			String uploadPath = request.getSession().getServletContext().getRealPath("/images/");
			String saveName = uploaFile(dogImgs.getOriginalFilename(), dogImgs.getBytes(), uploadPath);
			model.addAttribute("saveName", saveName);
			dog.setDogImgName(saveName);
			dog.setDogImgPath(uploadPath);
		} else {
			System.out.println("LhjController UpdMyDogInfo 파일선택 없으면 Start...."+dog.getDogImgName());
		
		}	
		
		int updMydogCount = lhjs.UpdMyDogInfo(dog);
		System.out.println("LhjController lhjs.UpdMyDogInfo updMydogCount-->" + updMydogCount);
		model.addAttribute("updMydogCount", updMydogCount); // Test Controller간 Data 전달
		model.addAttribute("kk3", "Message Test"); // Test Controller간 Data 전달

		return "redirect:/mypage/member/myDogList";
	}

	// 나의 반려견 등록 페이지 이동
	@RequestMapping(value = "/mypage/member/creMyDogForm")
	public String creMyDogForm(DogDTO dog,@AuthenticationPrincipal PrincipalDetails principal,
								HttpServletRequest request, MultipartFile file, Model model) {
		System.out.println("LhjController creMyDogForm Start..");
		if(principal!=null) {
			MemberDTO myInfo = principal.getUser();
			System.out.println("principal creMyDog myinfo : "+ myInfo.toString());
			dog.setId((long) myInfo.getId());
		}
		List<DogTypeDTO> dogType = lhjs.dogTypeSelect();
		model.addAttribute("dogType", dogType);
		System.out.println("LhjController creMyDogForm dogType.size->" + dogType.size());
		return "mypage/member/creMyDogForm";
	}

	// 나의 반려견 등록
	@RequestMapping(value = "/mypage/member/creMyDog", method = RequestMethod.POST)
	public String creMyDog(DogDTO dog, @AuthenticationPrincipal PrincipalDetails principal,
						   HttpServletRequest request, 
						   MultipartFile dogImgs, Model model  )  throws IOException {
		
		if(principal!=null) {
			MemberDTO myInfo = principal.getUser();
			System.out.println("principal creMyDog myinfo : "+ myInfo.toString());
			dog.setId((long) myInfo.getId());
		}

		System.out.println("LhjController creMyDog Start....");
		System.out.println("LhjController dog.getId()--->"+dog.getId());
		
		// 파일선택 없으면 Save Skip Logic 추가 
		if (dogImgs.getOriginalFilename().length() > 0) {
			System.out.println("LhjController 이미지 등록 Start....");
			System.out.println("LhjController dogImgs.getOriginalFilename()->"+dogImgs.getOriginalFilename());
			//이미지 등록
			String uploadPath = request.getSession().getServletContext().getRealPath("/images/");
			String saveName = uploaFile(dogImgs.getOriginalFilename(), dogImgs.getBytes(), uploadPath);
			model.addAttribute("saveName", saveName);
			dog.setDogImgName(saveName);
			dog.setDogImgPath(uploadPath);
		} else {
			System.out.println("LhjController 파일선택 없으면 Start...."+dog.getDogImgName());
		}
		
		int insertResult = lhjs.insertDog(dog);
		if (insertResult > 0)
			return "redirect:/mypage/member/myDogList";
		else {
			model.addAttribute("msg", "입력 실패! 확인해보세요");
			return "forward:/mypage/member/creMyDogForm";
		}
	}

	private String uploaFile(String originalFilename, byte[] fileData, String uploadPath) throws IOException {
		UUID uid = UUID.randomUUID();

		System.out.println("uploadPath-->" + uploadPath);

		File fileDirectory = new File(uploadPath);
		if (!fileDirectory.exists()) {
			fileDirectory.mkdirs();
			System.out.println("업로드용 폴더 생성 : " + uploadPath);
		}

		String savedName = uid.toString() + "_" + originalFilename;
		log.info("saveName-->" + savedName);
		File target = new File(uploadPath, savedName);
		FileCopyUtils.copy(fileData, target);

		return savedName;
	}
	//사용자_나의 반려견 정보 삭제 
	@RequestMapping(value = "/mypage/member/delMyDogInfo")
	public String delMyDogInfo(Long dogNo, Model model) {
		DogDTO dog = lhjs.delMyDogInfo(dogNo);
		System.out.println("LhjController delMyDogInfo Start...");
		model.addAttribute("dogno", dogNo);
		return "forward:/mypage/member/myDogList";
	}

	// 관리자 페이지_호텔예약관리 전체 리스트
	@GetMapping(value = "/mypage/admin/hotelRseList")
	public String hotelRseList(ReservationDTO reservation, Model model) {
		System.out.println("LhjController Start hotelRseList...");
		List<ReservationDTO> memberResList = lhjs.memberResList(reservation);
		System.out.println("LhjController memberResList.size()=>" + memberResList.size());
		model.addAttribute("memberResList",memberResList);

		return "mypage/admin/hotelRseList";

	}
	
	// 관리자페이지_예약번호별 예약상세 조회
	@GetMapping(value = "/mypage/admin/memberResDetail")
	public String memberResDetail(ReservationDTO resNo, Model model) {
		System.out.println("LhjController Start memberResDetail...");
		ReservationDTO reservation =lhjs.memberResDetail(resNo);
		
		String resStartdate = "";
		String resEnddate = "";
		
		if (reservation.getResStartdate() != null) {
			resStartdate = reservation.getResStartdate().substring(0, 10);
			reservation.setResStartdate(resStartdate);
		}
		if (reservation.getResEnddate() != null) {
			resEnddate = reservation.getResEnddate().substring(0, 10);
			reservation.setResEnddate(resEnddate);
		}

		System.out.println("resStartdate->" + resStartdate);
		System.out.println("resEnddate->" + resEnddate);

		model.addAttribute("reservation", reservation);
		
		return "mypage/admin/memberResDetail";
	}
	
	//관리자_결제내역 조회
	@RequestMapping(value = "/mypage/admin/hotelPayList")
	public String hotelPayList(ResPayDTO resPay, Model model) {
		System.out.println("LhjController Start hotelPayList...");
		List<ResPayDTO> resPayList = lhjs.hotelPayList(resPay);
		System.out.println("LhjController myDogList resPayList.size()=>" + resPayList.size());
		model.addAttribute("resPayList", resPayList);
		return "mypage/admin/hotelPayList";
	}
	
	//관리자_반려견 품종관리
	@RequestMapping(value = "/mypage/admin/dogTypeList")
	public String dogTypeList(DogTypeDTO dogType, String currentPage, Model model) {
		System.out.println("LhjController Start dogTypeList...");
		// dogType 전체 Count
		int totalDogType =  lhjs.totalDogType();
		System.out.println("LhjController totalDogType=>" + totalDogType);
		// Paging 작업
		Paging   page = new Paging(totalDogType, currentPage);
		// Parameter dogType --> Page만 추가 Setting
		dogType.setStart(page.getStart());   // 시작시 1
		dogType.setEnd(page.getEnd());       // 시작시 10   

		List<DogTypeDTO> dogTypeList = lhjs.dogTypeList(dogType);
		System.out.println("LhjController myDogList dogTypeList.size()=>" + dogTypeList.size());

		model.addAttribute("totalDogType", totalDogType);
		model.addAttribute("dogTypeList",dogTypeList);
		model.addAttribute("page"    , page);
		
		return "mypage/admin/dogTypeList";
	}
	
	//관리자_반려견 품종등록 페이지 
	@RequestMapping(value = "/mypage/admin/creDogTypeForm")
	public String creDogTypeForm(Model model) {
		System.out.println("LhjController Start creDogTypeForm...");
		return "mypage/admin/creDogTypeForm";
	}
	
	//관리자_반려견 품종등록
	@RequestMapping(value = "/mypage/admin/creDogType")
	public String creDogType(DogTypeDTO dogType, Model model) {
		System.out.println("LhjController Start dogTypeList...");
		int insertResult = lhjs.insertDogType(dogType);
		if (insertResult > 0)
			return "redirect:/mypage/admin/dogTypeList";
		else {
			model.addAttribute("msg", "입력 실패! 확인해보세요");
			return "forward:mypage/admin/creDogTypeForm";
		}
		
	}
	
	//관리자_반려견 품종 삭제
	@RequestMapping(value = "/mypage/admin/delDogType")
	public String delDogType(String dogKind,Model model) {
		DogTypeDTO dogType = lhjs.delDogType(dogKind);
		System.out.println("LhjController delMyDogInfo Start...");
		model.addAttribute("dogKind", dogKind);
		return "forward:/mypage/admin/dogTypeList";
	}
	
	@RequestMapping(value = "/mypage/admin/classTeacheForm")
	public String classTeacheForm(Long id,Model model) {
		System.out.println("LhjController classTeacheForm Start...");
		MemberDTO member = lhjs.memberTeacherup(id);
		List<MemberDTO> teacher = lhjs.teacherSelect();	
		model.addAttribute("teacher",teacher);
		model.addAttribute("member",member);
		return "mypage/admin/classTeacheForm";
		
	}
	
	@RequestMapping(value = "/mypage/admin/updateTeacher")
	public String updateTeacher(MemberDTO member,Model model) {
		System.out.println("LhjController Start updateTeacher...");
		int updateTeacher = lhjs.updateTeacher(member);
		model.addAttribute("updateTeacher",updateTeacher);
		return  "redirect:/mypage/admin/memberList";
		
	}
}
