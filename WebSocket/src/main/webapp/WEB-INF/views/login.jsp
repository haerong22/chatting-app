<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/chat.css">
<title>로그인</title>
</head>
<body>
	<div class="welcome-box">
		<h2>환영합니다!</h2>
		<p>아이디를 입력하고 채팅에 참여하세요.</p>		
	</div>
	<form id="login-form" method="post" action="/websocket/loginPro.do">
		<input id="user-name" autofocus name="userId" type="text" required placeholder="아이디">
		<input id="user-passwd" name="userPassword" type="password" required placeholder="비밀번호">
		<button id="btn-name">로그인</button>
		<a class="find" href="/websocket/joinForm.do">회원가입</a> 	
	</form>
</body>
<script>
</script>
</html>