package kr.or.ddit.Controller;

import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.vo.AddressVO;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.CardVO;
import kr.or.ddit.vo.MemberVO;

/*
 RequestMapping 어노테이션 정리
 	- value 속성 : 필수
 	- 속성이 하나일 때 속성명 생략 가능
 	- 클래스 레벨로 요청 경로를 지정하면 메서드 레벨에서 지정한 경로의 기본 경로로 취급됨
 */
//클래스 레벨
@Controller
@RequestMapping("/board")
public class BoardController {
	private static final Logger logger
		= LoggerFactory.getLogger(BoardController.class);
	@RequestMapping("/ajaxHome")
	public  String ajaxHome() {
		return "ajaxHome";
	}
	
	//requestBody => {"no" : "1", "tit" : "제목", "cont" : "내용", "wri" : "개똥이"}
	//PUT을 쓰면 requestbody에 JSON데이터가 담김
	//RequestBody 어노테이션을 통해 JSON데이터를 VO객체에 자동으로 할당
	@RequestMapping(value="/{boardNo}"
			, method=RequestMethod.PUT)
	public ResponseEntity<String> test1(@PathVariable int boardNo, 
											@RequestBody BoardVO boardVO) {
		logger.info("boardVO : " + boardVO.toString());
		
		return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	}
	
	//value 속성에 요청 경로를 설정
	//@RequestMapping(value="/register")
	//속성이 하나일 때, 속성명 생략 가능
	@RequestMapping("/register") 
	public void registerForm() {
		logger.info("registerForm");
	}
	//하위 요청 경로
	@RequestMapping("/modify")
	public void modifyForm() {
		logger.info("modifyForm");
	}
	//하위 요청 경로
	@RequestMapping("/list")
	public void list() {
		logger.info("list");
	}
	/*
	 경로 패턴 매핑
	  - 경로 변수에 해당하는 값을 파라미터 변수에 설정할 수 있음
	 */
	@RequestMapping("/read/{boardNo}")
	public String read(@PathVariable("boardNo") int boardNo) {
		logger.info("read boardNo : " + boardNo);
		
		//경로가 변하므로 뷰 이름 지정(board폴더에 read.jsp)
		return "board/read";
	}
	/*
	 Params 매핑
	  - 요청 파라미터를 매핑 조건으로 지정하는 경우에는 params 속성을 사용
	  - 버튼이나 링크에 따라 호출할 메소드를 바꿔야 할 때 사용
	 */
	//<a href="/board/get?register">Register</a>
	@RequestMapping(value="/get",method=RequestMethod.GET,
			params="register")
	public String getForm() {
		logger.info("getForm");
		return "board/register";
	}
	@RequestMapping(value="/post",method=RequestMethod.POST,params="register")
	public String register() {
		logger.info("register");
		return "board/list";
	}
	@RequestMapping(value="/post",method=RequestMethod.POST,params="modify")
	public String modify() {
		logger.info("modify");
		return "board/list";
	}
	@RequestMapping(value="/post",method=RequestMethod.POST,params="remove")
	public String remove() {
		logger.info("remove");
		return "board/list";
	}
	@RequestMapping(value="/get",method=RequestMethod.GET, params="modify")
	public String modifyGet() {
		logger.info("modifyGet");
		
		return "board/modify";
	}

	@RequestMapping(value="/get",method=RequestMethod.GET, params="remove")
	public String removeGet() {
		logger.info("removeGet");
		
		return "board/remove";
	}
	//Params(파라미터 이름) 매핑
	@RequestMapping(value="/get",method=RequestMethod.GET, params="list")
	public String listGet() {
		logger.info("listGet");
		
		return "board/list";
	}
	@GetMapping("/registerCheckbox02")
	public String registerCheckbox02() {
		logger.info("registerCheckbox02");
		
		return "sub/registerCheckbox02";
	}
	//폼 체크박스 요소값을 문자열 배열 타입 매개변수로 처리
	@ResponseBody
	@RequestMapping(value="/registerCheckbox", method = RequestMethod.POST)
	public String[] registerCheckbox(MemberVO memberVO, String[] hobbyList) {
		logger.info("registerCheckbox");
		
		//null => 체크된 것이 없을 때
		if(hobbyList != null) {	//하나라도 체크가 되어있는 경우
			logger.info("hobbyList.length : " + hobbyList.length);
			
			for(int i = 0; i < hobbyList.length; i++) {
				logger.info("hobbyList[" + i + "] = " + hobbyList[i]);
			}
		}
		//memberVO.getAddressVO() => private AddressVO addressVO;
		AddressVO addressVO = memberVO.getAddressVO();
		
		if (addressVO != null) {
			logger.info("addressVO.getPostCode() : " + addressVO.getPostCode());
			logger.info("addressVO.getLocation() : " + addressVO.getLocation());
		}
		//List<CardVO>
		List<CardVO> cardList = memberVO.getCardList();
		if(cardList != null) {
			logger.info("cardList.size() : " + cardList.size());
			
			for(CardVO card : cardList) {
				logger.info("card.getNo() : " + card.getNo());
				logger.info("card.getvalidMonth() : " + card.getValidMonth());
			}
		}
		
		logger.info("memberVO : " + memberVO);
		return hobbyList;
	}
	
	@RequestMapping("/registerCheckbox05")
	public String registerCheckbox05() {
		logger.info("registerCheckbox05");
		//forwarding
		return "sub/registerCheckbox05";
	}
	//폼 체크박스 요소 값을 boolean 타입으로 작성하면, boolean 타입 매개변수로 처리
	@RequestMapping(value="registerCheckbox05Post", method = RequestMethod.POST)
	public String registerCheckbox05Post(boolean foreigner) {
		logger.info("registerCheckbox05Post");
		logger.info("foreigner = " + foreigner);
		
		return "success";
	}
}
