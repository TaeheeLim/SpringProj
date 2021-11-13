<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- /member/register01.jsp -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결과</title>
</head>
<body>
<!-- model.addAttribute("userId", userId); -->
회원ID는 ${userId} 입니다. <br />
비밀번호는 ${map.password}<br />
비트코인수는 ${map.coin}이고, 가격은 ${map.coin * 70000000}원 입니다.<br />
</body>
</html>