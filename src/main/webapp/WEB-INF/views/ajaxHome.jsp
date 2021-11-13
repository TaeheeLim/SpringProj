<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="/resources/js/jquery-3.6.0.min.js"></script>
<title>Headers 매핑</title>
<script type="text/javascript">
$(function(){
	$('#putBtn').on('click',function(){
		var boardNo = $("#boardNo").val();
		var title = $("#title").val();
		var content = $("#content").val();
		var writer = $("#writer").val();
		
		var boardObject = {
				no : boardNo,
				tit : title,
				cont : content,
				wri : writer
		};
		//JSON.stringify => 배열을 JSON데이터로 바꿔줌
		//type => get, post, put(post방식, 데이터가 RequestBody에 들어감)
		//data => 보낼 데이터
		//contentType => 보내는 데이터의 타입
		
		//dataType => 받는 데이터의 타입
		
		$.ajax({
			type: "put",
			url: "/board/" + boardNo,
			data : JSON.stringify(boardObject),
			contentType : "application/json;charset=utf-8",
			success : function(result){
				console.log("result : " + result);
				if(result == "SUCCESS"){
					$("#div1").css("font-color:red;");
					$("#div1").text("성공입니다.");
				}
			}
		});
	});
});
</script>
</head>
<body>
<div id="div1"></div>
<p><input type="text" name="boardNo" id="boardNo" value="1" /></p>
<p><input type="text" name="title" id="title" value="제목" /></p>
<p><textarea cols="10" rows="10" name="content" id="content"></textarea></p>
<p><input type="text" name="writer" id="writer" value="개똥이" /></p>
<p><input type="button" id="putBtn" value="전송" /></p>

</body>
</html>