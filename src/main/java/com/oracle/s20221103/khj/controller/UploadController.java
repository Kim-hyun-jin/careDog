package com.oracle.s20221103.khj.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.ibatis.annotations.Param;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

import com.oracle.s20221103.domain.CusNotice;
import com.oracle.s20221103.khj.dto.AttachFileDTO;
import com.oracle.s20221103.khj.service.CusNoticeS;

import lombok.RequiredArgsConstructor;
import lombok.val;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UploadController {

	private final CusNoticeS cusNoticeService;
	
	
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
	
	@GetMapping("/uploadForm")
	public String uploadForm() {
		return "customerService/cusNotice/uploadForm";
	}
	
	@GetMapping("/uploadAjax")
	public String uploadAjax() {
		return "customerService/cusNotice/uploadAjax";
	}

	@PostMapping("/uploadFormAction")
	public void uploadFormPost(MultipartFile[] uploadFile, Model model) {
		
		System.out.println("uploadFormAction start");
		
		String uploadFolder = "C:\\upload";
		String uploadFolderPath = getFolder();
		
		//make folder--------------------------------------//
		File uploadPath = new File(uploadFolder, getFolder());
		log.info("upload path: "+ uploadPath);
		
		if(uploadPath.exists() == false) {
			uploadPath.mkdirs();
		}
		
		
		for (MultipartFile multipartFile: uploadFile) {
			CusNotice cusNotice = null;
			AttachFileDTO attachDTO = new AttachFileDTO();
			log.info("================================");
			log.info("Upload File Name: "+ multipartFile.getOriginalFilename());
			log.info("Upload File Size: "+ multipartFile.getSize());
			
			String uploadFileName = multipartFile.getOriginalFilename();
			
			attachDTO.setFileName(uploadFileName);
			
			UUID uuid = UUID.randomUUID();
			uploadFileName = uuid.toString()+ "_"+ uploadFileName;
			
			//File saveFile = new File(uploadFolder,multipartFile.getOriginalFilename());
			File saveFile = new File(uploadPath,uploadFileName);
			
			
			try {
				multipartFile.transferTo(saveFile);
				
				//attachDTO setting
				attachDTO.setUuid(uuid.toString());
				attachDTO.setUploadPath(uploadFolderPath);
				
				
				//check image type file && 썸네일
				if(checkImgType(saveFile)) {
					attachDTO.setImage(true);
				}

			} 
			catch (Exception e) {
				log.error(e.getMessage());
				e.printStackTrace();
			}
		}		
	}
	
	
	
	@PostMapping(value =  "/uploadAjaxAction", produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
	@ResponseBody
	public ResponseEntity<List<AttachFileDTO>> uploadAjaxAction(MultipartFile[] uploadFile) {
		
		System.out.println("uploadAjaxAction start");
		
		List<AttachFileDTO> list = new ArrayList<AttachFileDTO>();
		
		String uploadFolder = "C:\\upload";
		//String uploadFolderPath = getFolder(); , getFolder()
		
		//make folder-----------
		File uploadPath = new File(uploadFolder);
		log.info("upload path: "+ uploadPath);
		
		if(uploadPath.exists() == false) {
			uploadPath.mkdirs();
		}
			
		
		for (MultipartFile multipartFile: uploadFile) {
			
			AttachFileDTO attachDTO = new AttachFileDTO();
			log.info("================================");
			log.info("Upload File Name: "+ multipartFile.getOriginalFilename());
			log.info("Upload File Size: "+ multipartFile.getSize());
			
			String uploadFileName = multipartFile.getOriginalFilename();
			
			attachDTO.setFileName(uploadFileName);
			
			UUID uuid = UUID.randomUUID();
			uploadFileName = uuid.toString()+ "_"+ uploadFileName;
			
			//File saveFile = new File(uploadFolder,multipartFile.getOriginalFilename());
			File saveFile = new File(uploadPath,uploadFileName);
			
			try {
				multipartFile.transferTo(saveFile);
				
				//attachDTO setting
				attachDTO.setUuid(uuid.toString());
				//attachDTO.setUploadPath(uploadFolderPath);
				
				
				//check image type file && 썸네일
				if(checkImgType(saveFile)) {
					attachDTO.setImage(true);
				}
				
				
				list.add(attachDTO);
			} 
			catch (Exception e) {
				log.error(e.getMessage());
				e.printStackTrace();
			}
			
		}//end for
		
		return new ResponseEntity<List<AttachFileDTO>>(list, HttpStatus.OK);
		
	}
	
	
	@PostMapping("/deleteFile")
	@ResponseBody
	public ResponseEntity<String> deleteFile(String fileName, String type){
		log.info("deleteFile: "+ fileName);
		
		File file;
		
		try {
			file = new File("c:\\upload\\"+URLDecoder.decode(fileName, "UTF-8"));
			file.delete();
			if(type.equals("image")) {
				
			}
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		}
		return new ResponseEntity<String>("deleted", HttpStatus.OK);
	}
	
	@GetMapping(value = "/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	public ResponseEntity<Resource> downloadFile(@RequestParam(value = "fileName") String fileName) throws MalformedURLException{
		log.info("download file: "+ fileName);
		
		Resource resource = new FileSystemResource("C:\\upload\\"+ fileName);
		
		//UrlResource resource = new UrlResource("file:"+"C:\\upload\\"+ fileName);
		
		log.info("resource: "+ resource);
		String resourceName = resource.getFilename();
		//String encodedUploadFileName = UriUtils.encode(fileName, StandardCharsets.UTF_8);
		
		HttpHeaders headers = new HttpHeaders();
		
		try {
			headers.add("Content-Disposition", "attachment; filename="
												+ new String(resourceName.getBytes("UTF-8"), "UTF-8"));

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
		
	}
	
	
	
	@GetMapping("/display")
	@ResponseBody
	public ResponseEntity<byte[]> getFile(String fileName){
		log.info("fileName: "+ fileName);
		
		File file = new File("c:\\upload\\"+ fileName);
		
		log.info("file: "+ file);
		
		ResponseEntity<byte[]> result = null;
		
		HttpHeaders header = new HttpHeaders();
		try {
			header.add("Content-Type", Files.probeContentType(file.toPath()));
			result = new ResponseEntity<byte[]>(
					FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK );
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return result;
	}
}
