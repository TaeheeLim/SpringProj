<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>책 등록</h1>
<form method="post" action="/create">
	<p>제목 : <input type="text" name="title" /></p>
	<p>카테고리 : <input type="text" name="category" /></p>
	<p>가격 : <input type="text" name="price" /></p>
	<p><input type="submit" value="저장" /></p>
</form>

</body>
</html>