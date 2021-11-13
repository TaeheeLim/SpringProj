<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 업로드</title>
</head>
<body>
<form action="/uploadFormAction" method="post"
	enctype="multipart/form-data">
	<input type="file" name="uploadFile" multiple/>
	<button>Submit</button>
</form>
</body>
</html>