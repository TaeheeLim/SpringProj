<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- /board/list.jsp -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BOARD</title>
</head>
<body>
<h3>LIST</h3>

<a href="/board/get?register">Register</a>
<a href="/board/get?update">update</a>
<a href="/board/get?delete">delete</a>

<form method="get" action="/board/get">
	<button type="submit" name="modify">수정</button>
	<button type="submit" name="remove">삭제</button>
	<button type="submit" name="list">목록</button>
</form>
</body>
</html>