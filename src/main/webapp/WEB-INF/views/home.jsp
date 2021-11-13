<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home.jsp</title>
</head>
<body>
<h1>Home</h1>

<h1>컨트롤러 응답 연습</h1><br />
1.return이 void일때.. <br />
<a href="/goHome0101">요청 경로와 동일한 뷰</a> <br />
<a href="/goHome0102">요청 경로와 동일한 뷰2</a><br /> <br />
2.리턴 타입이 스트링일 때 ..<br />
<a href="/goHome0201 ">반환값이 뷰를 가리킴1</a> <br />
<a href="/sub/goHome0202">반환값이 goHome0202이고 요청이 /sub/goHome0202일때</a>
3. 반환값이 redirect:로 시작하면 리다이렉트 방식으로 처리<br />
<a href="/goHome0204">리다이렉트 방식1</a> <br />
<a href="/sub/goHome0205">Return을 폴더 경로로 처리</a>
</body>
</html>





