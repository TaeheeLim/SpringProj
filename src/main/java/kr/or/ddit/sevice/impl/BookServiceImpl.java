package kr.or.ddit.sevice.impl;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import kr.or.ddit.dao.BookDAO;
import kr.or.ddit.sevice.BookService;



//스프링한테 이 클래스는 서비스 클래스라고 알려줌
//스프링 MVC 구조에서 Controller와 DAO를 연결하는 역할
/*
스프링 프레임워크는 직접 클래스를 생성하는것을 지양하고
인터페이스를 통해 접근하는 것을 권장하고 있기 때문.
그래서 서비스 레이어는 인터페이스와 클래스를 함께 사용함 
 */
//@Service("bookService") 이렇게도 사용가능 다만 @Autowired로 객체를 가져다 쓰면 변수이름을 bookService그대로 써야함
@Service
public class BookServiceImpl implements BookService {
	//데이터베이스 접근을 위해 BookDAO 인스턴스를 주입받음
	@Autowired
	BookDAO bookDAO;
	
	@Override
	public String insert(Map<String,Object> map) {
		return this.bookDAO.insert(map);
	}
	//책 상세보기
	@Override
	public Map<String, Object> select(Map<String, Object> map){
		return this.bookDAO.select(map);
	}
	//책 수정하기
	@Override
	public boolean update(Map<String,Object> map) {
		return this.bookDAO.update(map);
	}
	//책 삭제하기
	@Override
	public boolean delete(Map<String,Object> map) {
		return this.bookDAO.delete(map);
	}
	//책 목록
	@Override
	public List<Map<String,Object>> list(Map<String,Object> map){
		return this.bookDAO.list(map);
	}
}
