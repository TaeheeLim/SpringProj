package kr.or.ddit.sevice;

import java.util.List;
import java.util.Map;

public interface BookService {
	//메소드 시그니처
	String insert(Map<String, Object> map);
	//책 상세보기
	Map<String, Object> select(Map<String, Object> map);
	//책 수정
	boolean update(Map<String, Object> map);
	//책 삭제
	boolean delete(Map<String, Object> map);
	//책 목록
	List<Map<String, Object>> list(Map<String, Object> map);
	
}
