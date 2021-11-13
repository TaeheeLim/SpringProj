package kr.or.ddit.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/*
DAO클래스 : mapper XML을 실행
*/
//Repository 어노테이션이 붙은 클래스는 스프링이 데이터를 관리하는 클래스라고 인지
//자바빈으로 등록하여 관리함
@Repository
public class BookDAO {
	//DI(Dependency Injection)
	//new 키워드를 통해 직접 생성하지 않고
	//스프링이 미리 만들어 놓은(서버 실행 시 스프링이 미리 XML을 읽어 객체를 인스턴스화 해놓음)
	//sqlSessionTemplate 타입 객체를 BookDao 객체에 주입함
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	public String insert(Map<String,Object> map) {
		//book_SQL.xml 파일의 namespace가 book이고, id가 insert인 구문을 찾아 실행
		//map을 파라미터로 보냄
		int result = this.sqlSessionTemplate.insert("book.insert", map);
		
		return String.valueOf(map.get("bookId"));
	}
	
	//책 상세보기
	public Map<String, Object> select(Map<String, Object> map){
		//selectOne 메소드 : 1행을 가져올 때 사용.
		//결과 행 수가 0일때 null 반환
		//결과 행 수가 2 이상일 때 TooManyResultsException 예외 발생
		//(namespace.id, 파라미터)
		return this.sqlSessionTemplate.selectOne("book.select", map);
		
	}
	
	//책 수정하기
	public boolean update(Map<String,Object> map){
		//update 후에 영향받은 행의 수를 리턴
		int result = this.sqlSessionTemplate.update("book.update", map);
		//0보다 크다는 것은 update가 되었다는 것
		return result > 0;
	}
	
	//책 삭제하기
	public boolean delete(Map<String,Object> map) {
		//RDBMS 에서 delete 구문은 update 구문처럼 조건에 일치하는
		//모든 행을 삭제하므로 영향을 받은 행의 수는 0 혹은 1 이상이 됨
		int result = this.sqlSessionTemplate.delete("book.delete", map);
		return result > 0;
	}
	
	//책 목록
	public List<Map<String,Object>> list(Map<String, Object> map){
		//selectList() 메소드는 결과 집합 목록 반환
		//List 타입으로 읽어들일 수 있음

		List<Map<String,Object>> list = this.sqlSessionTemplate.selectList("book.list", map);
		return list;
	}
	
	
}
