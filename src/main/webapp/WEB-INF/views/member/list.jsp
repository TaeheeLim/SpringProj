<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>요청 처리 연습</title>
</head>
<body>
	<a href="/member/register?userId=hongkd&password=1234">
		register?userId=hongkd&password=1234
	</a><br />
	<form action="/register01" method="post">
		userId : <input type="text" name="userId" value="세균맨" /> <br />
		password : <input type="text" name="password" value="1234" /> <br />
		coin : <input type="text" name="coin" value="100" /><br />
		<input type="submit" value="register01" />
	</form>
</body>
</html>