package kr.or.ddit.Controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.function.ServerRequest.Headers;

import kr.or.ddit.util.FileuploadUtil;
import kr.or.ddit.vo.AttachFileVO;
import net.coobird.thumbnailator.Thumbnailator;

@Controller
public class UploadController {
	private static final Logger logger = LoggerFactory.getLogger(UploadController.class);
	
	//파일업로드 폼
	@GetMapping("/uploadForm")
	public String uploadForm() {
		logger.info("uploadForm");
		
		return "uploadForm";
	}
	
	//파일업로드 처리
	@PostMapping("/uploadFormAction")
	public String uploadFormPost(MultipartFile[] uploadFile, Model model) {
		String uploadFolder = "C:\\workspace (3)\\workspace\\springProj\\src\\main\\webapp\\resources\\upload";
		
		List<String> list = new ArrayList<>();
		
		for(MultipartFile file : uploadFile) {
			logger.info("------------------------");
			logger.info("업로드 파일명 : " + file.getOriginalFilename());
			logger.info("업로드 파일 크기 : " + file.getSize());
			
			//파일 객체를 통해 이렇게 하겠다..
			File saveFile
				= new File(uploadFolder, file.getOriginalFilename());
			
			try {
				//transferTo() 메소드의 파라미터 : java.io.File의 객체
				file.transferTo(saveFile);
				list.add(file.getOriginalFilename());
			} catch (IOException e) {
				logger.error(e.getMessage());
			} //end catch
		} // end for
		//업로드 된 파일 목록을 리스트에 담음
		model.addAttribute("list", list);
		return "uploadFormPost";
	}
	
	//아작스 이미지 업로드 폼
	@GetMapping("/uploadAjax")
	public void uploadAjax() {
		logger.info("upload ajax");
		// /uploadAjax URI 주소와 uploadAjax.jsp의 위치 및 파일명이
		// 동일하여 return 생략 가능
		
	}
	
	//아작스 이미지 업로드 실행
	@ResponseBody
	@PostMapping("/uploadAjaxAction")
	public ResponseEntity<List<AttachFileVO>> uploadAjaxPost(MultipartFile[] uploadFile) {
		logger.info("update ajax post........");
		
		List<AttachFileVO> list = new ArrayList<AttachFileVO>();
		
		String uploadFolder 
			= "C:\\workspace (3)\\workspace\\springProj\\src\\main\\webapp\\resources\\upload";
		
		//날짜 계층형 폴더 생성 시작 ----------------------
		File uploadPath = new File(uploadFolder, getFolder());
		//webapp\resources_upload_2021_11_02
		logger.info("upload path : " + uploadPath);
		//생성될 폴더가 존재하지 않으면
		if(uploadPath.exists() == false) {
			//해당 폴더들을 생성 => 연도 > 월 > 일
			uploadPath.mkdirs();
		}
		//날짜 계층형 폴더 생성 끝 --------------------
		
		for(MultipartFile multipartFile : uploadFile) {
			AttachFileVO attachFileVO = new AttachFileVO();
			
			logger.info("----------------------------");
			//IE에서는 파일명 : C:\Users\임태희\Desktop\스프링숙제.png
			logger.info("파일명 : " + multipartFile.getOriginalFilename());
			logger.info("파일크기 : " + multipartFile.getSize());
			//실제 파일명
			String uploadFileName = multipartFile.getOriginalFilename();
			
			//IE에서의 파일명의 경로를 처리
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);
			logger.info("IE를 위해 처리한 파일명 : " + uploadFileName);
			//vo에 파일명 setting
			attachFileVO.setFileName(uploadFileName);
			
			//java.util.UUID 를 통해 파일 중복 방지
			//randomUUID() : 임의의 값을 생성
			UUID uuid = UUID.randomUUID();
			uploadFileName = uuid.toString() + "_" + uploadFileName;
			
			// 1) 목적지 ? 2) 파일명 ?
			//File saveFile = new File(uploadFolder, uploadFileName);
			File saveFile = new File(uploadPath, uploadFileName); 
			
			//파일을 목적지로 복사하려면 파라미터가 File객체여야 함
			try {
				//파일 복사 실행
				multipartFile.transferTo(saveFile);
				logger.info("uploadPath.getAbsolutePath() : "
						+ uploadPath.getAbsolutePath());
				//2) vo객체의 저장 경로 setting
				attachFileVO.setUploadPath(uploadPath.getAbsolutePath());
				//3) vo객체의 uuid에 저장
				attachFileVO.setUuid(uuid.toString());
				//썸네일 만들기 시작 ------------
				// /uploadAjax를 통해 이미지를 업로드 하면
				// 1) 원본 이미지 파일 저장
				// 2) 섬네일(s_원본이미지 파일) 파일 생성
				// but) 일반 파일은 그냥 업로드만 됨
				//이미지인지 체킹
				if(checkImageType(saveFile)) {	//이미지인 경우
					//4) vo객체의 이미지 여부 setting
					attachFileVO.setImage(true);
					//썸네일 => s_이미지파일명
					FileOutputStream thumbnail = 
							new FileOutputStream(
									new File(uploadPath, "s_" + uploadFileName)
									);
					//InputStream과 java.io.File 객체를 이용하여
					//썸네일 파일 생성. width:100px, height:100px;
					Thumbnailator.createThumbnail(
							multipartFile.getInputStream(), thumbnail,100,100
							);
					thumbnail.close();
				}
				//썸네일 만들기 끝 ------------
				
				list.add(attachFileVO);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}	//end for
		//JSON 데이터를 반환
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	//날짜 계층형 폴더
	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Date date = new Date();
		//str : 2021-11-02
		String str = sdf.format(date);
		//2021폴더 > 11폴더 > 02폴더
		return str.replace("-", File.separator);
	}
	
	//이미지의 경우에만 섬네일 제작
	private boolean checkImageType(File file) {
		//MIME 타입을 통해 이미지 여부 확인
		//file.toPath() : 파일 객체를 path객체로 변환
		String contentType;
		try {
			contentType = Files.probeContentType(file.toPath());
			logger.info("contentType : " + contentType);
			//MIME 타입 정보고 image로 시작하는지 여부를 return
			return contentType.startsWith("image");
		} catch (IOException e) {
			e.printStackTrace();
		}
		//이 파일은 이미지가 아니다.
		return false;
	}
	
	
	@RequestMapping(value = "/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<Resource> downloadFile(@RequestHeader("User-Agent") String userAgent ,String fileName) {
       
       logger.info("download file : " + fileName);
       
       //파일이 있는 절대 경로
       String uploadFolder = FileuploadUtil.uploadFolder;
       
       // ...resources\\upload\\2021\\11\\05\\개똥이.jpg
       Resource resource = new FileSystemResource(uploadFolder  + "\\"+ fileName);
       
       //해당 파일이 없을때...
       if(!resource.exists()) {
          return new ResponseEntity<Resource>(HttpStatus.NOT_FOUND);
       }
       
       logger.info("resource => " + resource);
       
       //파일명 가져오기
       String resourceName = resource.getFilename();
       
       //파일명이 한글이면 httpHeaders 객체를 통해 처리
       HttpHeaders headers = new HttpHeaders();
       
       try {
          // 헤더의 파일이름 처리하기 전에 해줘야함
          String downloadName = null;
          // Trident => IE 11버전의 엔진이름, 즉 IE를 나타냄
          if(userAgent.contains("Trident")) {
             logger.info("IE browser");
             
             downloadName = URLEncoder.encode(resourceName, "UTF-8").replaceAll("\\+", " ");
          } else if(userAgent.contains("Edg")) {
             logger.info("Edge browser");
             
             downloadName = URLEncoder.encode(resourceName, "UTF-8");
          }else{
             logger.info("chrome browser");
             
             downloadName = new String(resourceName.getBytes("UTF-8"), "ISO-8859-1");
          }
          
          //Content-disposition : 다운로드시 저장되는 이름을 처리하라
          headers.add("Content-disposition", "attachment;filename="+ downloadName);
       } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
       }
       
       // resource : 첨부파일 객체
       // headers : 파일명 처리 정보
       // HttpStatus.OK : 200(성공)
       return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
    }
}














