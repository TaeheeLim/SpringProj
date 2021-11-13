<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>책 상세</title>
</head>
<body>
<!--
{"TITLE":"검은태양"},{"CATEGORY":"소설"},{"PRICE":"10000"},{"INSERT_DATE":"2021-10-28"} 

컨트롤러에서 mav 객체에 data라는 이름으로 DB 검색 결과를 넣었으므로..
			mav.addObject("data", detailMap);
달러{data.TITLE} 형식으로 사용하게 됨
-->
	<h1>책 상세</h1>
	<p>제목 : ${data.TITLE}</p>
	<p>카테고리 : ${data.CATEGORY}</p>
	<p>가격 : <fmt:formatNumber type="number" maxFractionDigits="3" value="${data.PRICE }" /></p>
	<p>입력일 : <fmt:formatDate value="${data.INSERT_DATE}" pattern="yyyy.MM.dd HH:mm:ss"/> </p>
	<p><a href="/update?bookId=${bookId}">수정</a></p>
	<form method="post" action="/delete">
		<input type="text" name="bookId" value="${bookId}" />
		<input type="submit" value="삭제" />
	</form>
	<p><a href="list">목록으로</a></p>
</body>
</html>







