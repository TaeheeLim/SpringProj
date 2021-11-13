package kr.or.ddit.Controller;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.xml.ws.Response;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.sevice.BookService;
/*
Controller 어노테이션
개발자가 스프링 프레임워크에게 이 클래스는 웹 브라우저의 요청(request)을 받아
컨트롤러야 라고 알려주는 것임
스프링 프레임워크(디자인패턴 + 라이브러리) 이 클래스가 컨트롤러구나 라고
인지를 해서 자바빈을(java bean)으로 등록하여 관리
ex) /notice/list
 */

@Controller
public class BookController {
	@Autowired
	//org.slf4j.Logger
	BookService bookSerivce;
	//책을 등록할 때 요청
	//value속성 : 웹브라우저에서 요청한 주소(URI)
	//파라미터 목록 : title=제목&category=카테고리&price=10000
	//{{"title","제목"},{"category","카테고리"},{"price","10000"}}
	
	@RequestMapping(value="/create",method=RequestMethod.GET)
	public ModelAndView create() {
		/*
		 ModelAndView
		 1) Model: Controller가 반환할 데이터를 담당
		 2) View : 화면을 담당(뷰의 경로)
		 */
		ModelAndView mav = new ModelAndView();
		//this.bookSerivce.insert(map);
		
		
		//웹 브라우저에서 /create 주소를 GET방식으로 요청하면
		//book/create 경로의 뷰를 응답
		mav.setViewName("book/create");
		return mav;
		
//		return new ModelAndView("book/create");
	}
	
	//파라미터 목록 : title=제목&category=카테고리&price=10000
	//{{"title","제목"},{"category","카테고리"},{"price","10000"}}
	@RequestMapping(value="/create",method=RequestMethod.POST)
	public ModelAndView createPost(@RequestParam Map<String,Object> map) {
		ModelAndView mav = new ModelAndView();
		String bookId = this.bookSerivce.insert(map);
		
		if(bookId == null) {	//insert가 안됨 - >책 입력 화면으로 되돌아감
			mav.setViewName("redirect:/create");
		} else {	//insert 성공 -> 상세 페이지를 요청
			mav.setViewName("redirect:/detail?bookId="+bookId);
		}
		
		return mav;
	}
	//bookId = 1
	//{"bookId","1"}
	@RequestMapping(value="/detail",method = RequestMethod.GET)
	public ModelAndView select(@RequestParam Map<String,Object> map) {
		/*
		 {"TITLE":"검은태양"},{"CATEGORY":"소설"},
		 {"PRICE":"10000"},{"INSERT_DATE":"2021-10-28"}
		 */
		Map<String, Object> detailMap = this.bookSerivce.select(map);
		
		ModelAndView mav = new ModelAndView();
		//request.setAttribute("data", detailMap); 랑 유사
		mav.addObject("data", detailMap);
		String bookId = String.valueOf(map.get("bookId"));
		//request.setAttribute("bookId", bookId); 랑 유사
		mav.addObject("bookId", bookId);
		mav.setViewName("/book/detail");
		
		return mav;
	}
	//책 수정 화면 메소드/update?bookId=1
	//map {bookId=1}
	//책 수정 화면 = 책 입력 화면(jsp) + 책 상세 화면(서비스)
	@RequestMapping(value="/update",method = RequestMethod.GET)
	public ModelAndView update(@RequestParam Map<String,Object> map) {
		//책 상세 화면(서비스) 데이터 가져오기
		Map<String,Object> detailMap = this.bookSerivce.select(map);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("data", detailMap);
		mav.setViewName("book/update");
		
		return mav;
		
	}
	@RequestMapping(value="/update",method = RequestMethod.POST)
	public ModelAndView updatePost(@RequestParam Map<String,Object> map,
			ModelAndView mav) {
		/*
		 책 수정 화면에서 책 수정 기능으로 보내주는 파라미터는총 4개
		 1) /update?bookId = 1
		 2) form 태그를 통해 전달되는 title, category, price
		 */
		//true : update 성공, false : update 실패
		boolean isUpdateSuccess = this.bookSerivce.update(map);
		if(isUpdateSuccess) {	//성공
			//상세페이지로 이동
			String bookId = map.get("bookId").toString();
			mav.setViewName("redirect:/detail?bookId=" + bookId);
		} else {	//실패
			//BookController의 update 메소드를 호출
			//책 수정 화면 = 책 입력 화면(jsp) + 책 상세 화면(서비스)
			//방법1)
			mav = this.update(map);
			//방법2) /update?bookId=1
//			mav.setViewName("redirect:/update?bookId" + bookId);
		}
		
		return mav;
	}
	
	//map : ${bookdId=1}
	@RequestMapping(value="/delete",method = RequestMethod.POST)
	public ModelAndView deletePost(@RequestParam Map<String,Object> map,
			ModelAndView mav) {
		//true : 삭제 성공, false : 삭제 실패
		boolean isDeleteSuccess = this.bookSerivce.delete(map);
		if(isDeleteSuccess) { // 성공
			//목록 화면으로 redirect
			mav.setViewName("redirect:/list");
		} else { //실패
			//상세 화면으로 redirect
			String bookId = map.get("bookId").toString();
			mav.setViewName("redirect:/detail?bookId=" + bookId);
		}
		return mav;
	}
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public ModelAndView list(@RequestParam Map<String,Object> map, ModelAndView mav) {
		List<Map<String,Object>> list = this.bookSerivce.list(map);
		
		//데이터를 VIEW에 전달할 수 있도록 mav 객체에 넣음
		mav.addObject("data",list);
		//forwarding
		mav.setViewName("book/list");
		
		return mav;
	}
}





