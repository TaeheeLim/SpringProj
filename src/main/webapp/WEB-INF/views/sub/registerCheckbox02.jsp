<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>체크박스 요소</title>
<script src="/resources/js/jquery-3.6.0.min.js"></script>
<script src="/resources/js/checkbox.js"></script>
</head>
<body>

<form action="/board/registerCheckbox" method="post">
	<p>회원 ID : <input type="text" name="memId" value="a001"> </p>
	<p>회원이름 : <input type="text" name="memName" value="김은대"> </p>
	<!-- MemberVO의 AddressVO 타입의 addressVO 필드 
		중첩된(nested) 자바빈즈
	-->
	<p>우편번호 : <input type="text" name="addressVO.postCode"> </p>
	<p>주소 : <input type="text" name="addressVO.location"> </p>
	hobbyList : <br />
	<input type="checkbox" id="hobbyList1" name="hobbyList" value="Sports" />
	<label for="hobbyList1">Sports</label> <br />
	<input type="checkbox" id="hobbyList2" name="hobbyList" value="Music" />
	<label for="hobbyList2">Music</label> <br />
	<input type="checkbox" id="hobbyList3" name="hobbyList" value="Movie" />
	<label for="hobbyList3">Movie</label> <br />
	<input type="button" id="btnAdd" value="+" onclick="createInput()">&nbsp;
	<input type="button" id="btnDel" value="-" onclick="deleteInput()">
	<div id="divCard">
	</div>
	<input type="submit" value="registerCheckbox02" />
</form>
</body>
</html>