<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>업로드된 파일 정보 뷰</title>
<link rel="stylesheet" href="/resources/css/bootstrap.min.css">
</head>
<body>
<div class="container">
	<div class="row" align="center">
	<c:forEach var="rec" items="${list}">
		<div class="col-md-4">
			<h3>${rec}</h3>
			<p><img src="/resources/upload/${rec}" /> </p>
		</div>
	</c:forEach>
	</div>
</div>
</body>
</html>