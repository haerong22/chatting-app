<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="static/css/join.css">
<title>회원가입</title>
</head>
<body>

<div class="join-box">
		<h2>회원가입</h2>
		<p>회원가입 후 서비스 이용가능 합니다.</p>		
</div>

<form id="join-form" action="/websocket/joinPro.do">
	<input name="userId" type="text" autofocus required placeholder="아이디 입력">
	<input name="userPassword" type="password" required placeholder="비밀번호 입력">
	<input type="password" required placeholder="비밀번호  재입력">
	<div class="join-form-btn">
		<button class="join-btn">가입</button>
		<button type="button" onclick="location.href='/websocket/login.do'">취소</button>
	</div>
</form>

</body>
</html>