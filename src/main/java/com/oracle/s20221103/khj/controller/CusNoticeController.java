package com.oracle.s20221103.khj.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.lang.Nullable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.oracle.s20221103.asecurity.PrincipalDetails;
import com.oracle.s20221103.domain.CusNotice;
import com.oracle.s20221103.dto.CusNoticeDTO;
import com.oracle.s20221103.khj.dto.AttachFileDTO;
import com.oracle.s20221103.khj.dto.PageRequestDTO;
import com.oracle.s20221103.khj.dto.PageResponseDTO;
import com.oracle.s20221103.khj.dto.SearchFormDTO;
import com.oracle.s20221103.khj.service.CusNoticeS;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/customerService/cusNotice")
@RequiredArgsConstructor
public class CusNoticeController {

	private final CusNoticeS cusNoticeS;

	@RequestMapping("/main")
	public String selectAll(PageRequestDTO pageRequestDTO, Model model) {
		System.out.println(pageRequestDTO.getPage());
		System.out.println(pageRequestDTO.getSize());
		System.out.println(pageRequestDTO.getSorting());

		// Page<CusNotice> noticeList1 =
		// cusNoticeS.selNoticeAll(PageRequest.of(pageable.getPageNumber(), 10,
		// pageable.getSort()));
		PageResponseDTO<CusNotice> responseDTO = cusNoticeS.findAll(pageRequestDTO);

		model.addAttribute("pageNum", pageRequestDTO.getPage());
		model.addAttribute("sorting", pageRequestDTO.getSorting());
		model.addAttribute("totalPage", responseDTO.getEnd());
		model.addAttribute("noticeList", responseDTO.getNoticeList());
		model.addAttribute("responseDTO", responseDTO);
		model.addAttribute("keyword", pageRequestDTO.getKeyword());
		return "/customerService/cusNotice/main";
	}

	// @PreAuthorize("isAuthenticated()")
	@GetMapping("/detail")
	public String detail(@RequestParam(defaultValue = "1") Long cusNo, Model model) {

		// 공지글번호로 데이터 조회
		CusNotice notice = cusNoticeS.selNotice(cusNo);
		CusNoticeDTO noticeDto = new CusNoticeDTO(notice);
		// 조회객체 모델에 담아서 화면에 전달
		model.addAttribute("notice", noticeDto);
		return "customerService/cusNotice/detail";
	}

	// 글작성
	// @PreAuthorize("hasRole('ADMIN')")
	// @PreAuthorize("hasRole('USER')")
	@GetMapping("/write")
	public String writeGet() {

		return "customerService/cusNotice/write";
	}

	@PostMapping("/write")
	public String writePost(CusNotice notice) {

		System.out.println("write notice: " + notice);

		try {
			cusNoticeS.insNotice(notice);
		} catch (Exception e) {

			e.printStackTrace();
		}

		return "redirect:/customerService/cusNotice/main?page=1&size=10&sorting=desc";
	}

	// 수정페이지
	@GetMapping("/modify")
	public String modifyGET(@RequestParam(defaultValue = "1") Long cusNo, Model model) {
		CusNotice notice = cusNoticeS.selNotice(cusNo);
		model.addAttribute("notice", notice);
		return "customerService/cusNotice/modify";
	}

	// 수정동작
	@PostMapping("/modify")
	public String modifyPOST(Long cusNo, CusNotice notice) {

		System.out.println("cusNo: " + cusNo);
		int result = cusNoticeS.updNotice(cusNo, notice);

		System.out.println("update result: " + result);
		return "redirect:/customerService/cusNotice/main?page=1&size=10&sorting=desc";
	}

	/// 삭제
	@PostMapping("/delete")
	public String delete(Long cusNo) {
		System.out.println("delete cusNo: " + cusNo);
		cusNoticeS.delNotice(cusNo);
		return "redirect:/customerService/cusNotice/main?page=1&size=10&sorting=desc";
	}

	// 검색

	@PostMapping("/search")
	public String searchPost(PageRequestDTO requestDTO, String sorting, String category, String keyword, Model model) {

		sorting = requestDTO.getSorting();
		if (keyword.isEmpty() && sorting.equals("desc")) {
			sorting = "desc";
			return "redirect:/customerService/cusNotice/main?page=1&size=10&sorting=desc";
		}
		if (keyword.isEmpty() && sorting.equals("asc")) {
			sorting = "asc";
			return "redirect:/customerService/cusNotice/main?page=1&size=10&sorting=asc";
		}

		PageRequestDTO pageRequestDTO = new PageRequestDTO(requestDTO.getPage(), requestDTO.getSize(), category,
				sorting, keyword);

		PageResponseDTO<CusNotice> dto = cusNoticeS.search(pageRequestDTO, model);

		Long total = dto.getTotal();
		System.out.println("total: " + total);
		// PageResponseDTO response = new PageResponseDTO(total, pageRequestDTO, list);

		model.addAttribute("responseDTO", dto);
		model.addAttribute("noticeList", dto.getNoticeList());
		model.addAttribute("pageNum", dto.getPage());
		model.addAttribute("sorting", sorting);
		model.addAttribute("keyword", keyword);
		model.addAttribute("category", category);
		model.addAttribute("total", total);

		return "/customerService/cusNotice/searchResult";
	}

	public void uploadFormPost(MultipartFile[] uploadFile) {

		System.out.println("uploadFormAction start");

		String uploadFolder = "C:\\upload";
		String uploadFolderPath = getFolder();

		// make folder-----------
		File uploadPath = new File(uploadFolder, getFolder());
		log.info("upload path: " + uploadPath);

		if (uploadPath.exists() == false) {
			uploadPath.mkdirs();
		}

		for (MultipartFile multipartFile : uploadFile) {

			AttachFileDTO attachDTO = new AttachFileDTO();
			log.info("================================");
			log.info("Upload File Name: " + multipartFile.getOriginalFilename());
			log.info("Upload File Size: " + multipartFile.getSize());

			String uploadFileName = multipartFile.getOriginalFilename();

			attachDTO.setFileName(uploadFileName);

			UUID uuid = UUID.randomUUID();
			uploadFileName = uuid.toString() + "_" + uploadFileName;

			// File saveFile = new File(uploadFolder,multipartFile.getOriginalFilename());
			File saveFile = new File(uploadPath, uploadFileName);

			try {
				multipartFile.transferTo(saveFile);

				// attachDTO setting
				attachDTO.setUuid(uuid.toString());
				attachDTO.setUploadPath(uploadFolderPath);

				// check image type file && 썸네일
				if (checkImgType(saveFile)) {
					attachDTO.setImage(true);
				}

			} catch (Exception e) {
				log.error(e.getMessage());
				e.printStackTrace();
			}

		}
	}

	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();

		String str = sdf.format(date);
		return str.replace("-", File.separator);
	}

	private boolean checkImgType(File file) {
		try {
			String contentType = Files.probeContentType(file.toPath());
			return contentType.startsWith("image");
		} catch (IOException e) {

			e.printStackTrace();
		}
		return false;
	}

}
