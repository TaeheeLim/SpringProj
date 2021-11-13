package kr.or.ddit.sevice.impl;

import org.springframework.stereotype.Service;


import kr.or.ddit.sevice.BoardService;
import kr.or.ddit.vo.MemberVO;
//org.springframework.web.multipart.support.StandardServletMutlipartResolver

import org.springframework.web.multipart.support.StandardServletMultipartResolver;
@Service
public class BoardServiceImpl implements BoardService{
//org.springframework.web.multipart.support.StandardServletMutlipartResolver
	@Override
	public int insertMemberHobby(MemberVO memberVO) {

		//1) 회원테이블로 insert
		//	BoardDAO.insertMemberHobby(memberVO);
		
		//2) 취미테이블로 insert
		//	if(result>0){
		//		BoardDAO.insertHobby(memberVO);
		//	}
		
		return 0;
	}

}
