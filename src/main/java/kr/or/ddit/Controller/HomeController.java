package kr.or.ddit.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.vo.BookVO;

@Controller
public class HomeController {
	private static final Logger logger =
			LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value="/home",method=RequestMethod.GET)
	public void home() {
		logger.info("home");
	}
	//요청경로(/goHome0101)와 동일한 뷰(/goHome0101.jsp)를 가리킴
	@RequestMapping(value="/goHome0101", method=RequestMethod.GET)
	public void home0101() {
		logger.info("home0101");
	}

	@RequestMapping(value="/goHome0102", method=RequestMethod.GET)
	public void home0102() {
		logger.info("home0102");
	}
	
	// <a href="/goHome0201">반환값이 뷰를 가리킴1</a>
	@RequestMapping(value="/goHome0201",method=RequestMethod.GET)
	public String home0201() {
		logger.info("home0201");
		
		return "home0201";
	}
	//<a href="/sub/goHome0202">반환값이 뷰를 가리킴2</a>
	@RequestMapping(value="/sub/goHome0202",method = RequestMethod.GET)
	public String home0202() {
		logger.info("home0202");
		//jsp 파일명과 동일. forwarding
		return "goHome0202";
	}
	@RequestMapping(value="/goHome0204", method=RequestMethod.GET)
	public String goHome0204() {
		logger.info("goHome0204");
		
		return "redirect:/sub/goHome0202";
	}
	
	@RequestMapping(value="/sub/goHome0205",method=RequestMethod.GET)
	public String goHome0205() {
		logger.info("home0205");
		
		return "sub/home0205";
	}
	
	//ResponseBody 어노테이션 : 반환값이 객체 타입이면 JSON 타입으로 자동 변환
	@ResponseBody 
	@RequestMapping(value="/goHome0301",method=RequestMethod.GET)
	public BookVO home0301() {
		logger.info("home0301");
		
		BookVO bookVO = new BookVO();
		bookVO.setBookId("20211029001");
		bookVO.setTitle("효주이모의 에세이");
		bookVO.setCategory("에세이");
		bookVO.setPrice("100000000");
		bookVO.setInsertDate("2021-10-29");
		
		return bookVO;
	}
	
	//컬렉션 List 타입 처리
	//요청 URI : /goHome04
	//반환값이 컬렉션 List 타입이면 JSON 객체 배열 타입으로 반환
	@ResponseBody
	@RequestMapping(value="/goHome04",method=RequestMethod.GET)
	public List<BookVO> home04(){
		logger.info("home04");
		
		List<BookVO> list = new ArrayList<BookVO>();
		
		BookVO bookVO = new BookVO();
		list.add(makeBookVO("20211029001", "효주이모의 에세이", "에세이"
				, "100000000", "2021-10-29", bookVO));

		list.add(makeBookVO("20211029002", "봉형님 제발요", "소설"
				, "200000000", "2021-10-29", bookVO));
		
		list.add(makeBookVO("20211029003", "보리야 보리밥 먹으러 가자", "수필"
				, "150000000", "2021-10-29", bookVO));
		
		return list;
	}
	
	public BookVO makeBookVO(String id, String title, String category,
				String price, String dt, BookVO bookVO) {
		bookVO.setBookId(id);
		bookVO.setTitle(title);
		bookVO.setCategory(category);
		bookVO.setPrice(price);
		bookVO.setInsertDate(dt);
		
		return bookVO;
	}
	
	//Map 타입
	//요청 URI : /goHome05
	//반환 값이 Map타입이면 JSON 객체 타입으로 자동 변환됨
	@ResponseBody
	@RequestMapping(value="/goHome05",method = RequestMethod.GET)
	public Map<String, BookVO> home05(){
		logger.info("goHome05");
		
		Map<String, BookVO> map = new HashMap<String, BookVO>();
		
		map.put("key1", new BookVO("20211029001", "효주이모의 에세이", "에세이", "100000000", "2021-10-29"));
		map.put("key2", new BookVO("20211029002", "봉형님 제발요", "소설", "200000000", "2021-10-29"));
		map.put("key3", new BookVO("20211029003", "보리야 보리밥 먹으러 가자", "수필", "150000000", "2021-10-29"));
		
		return map;
	}
	@RequestMapping(value="/member/list",method=RequestMethod.GET)
	public String memberList() {
		return "member/list";
	}
	/*
	 HTML 폼 필드명과 컨트롤러 매개변수명이 일치하면 요청 데이터를  받는다
	 HTML 폼 필드가 여러 개일 때 컨트롤러 매개변수의 위치와 상관없다.
	 HTML 폼 필드값이 숫자일 경우 컨트롤러 매개변수의 타입을 int 타입으로 받아도 되고,
	 	매개변수 타입을 String 형태로 해도 된다.
	 */
	//요청 URI : /register01
	//파라미터 목록 : {userId=세균맨,password=1234,coin=100}
	@RequestMapping(value="/register01",method=RequestMethod.POST)
	public String register01(String userId, int coin, String password, Model model) {
		logger.info("register01");
		//userId : 세균맨
		logger.info("userId : " + userId);
		logger.info("password : " + password);
		logger.info("coin : " + coin);
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("password", password);
		map.put("coin", coin);
		//model 객체의 name은 userId이고, value는 세균맨
		//request.setAttribute("userId",userId); 처럼..
		model.addAttribute("userId", userId);
		model.addAttribute("map", map);
		
		//forwarding
		return "member/register01";
	}
}
