<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/chat.css">
<title>채팅방 리스트</title>
</head>
<body>
	<form method="post" action="/websocket/chat.do">
		<div>
			<input id="user-name" name="userName" type="text" required placeholder="아이디 입력"><button id="btn-name">확인</button> 	
		</div>
		<div id="chatroom"></div>
	</form>
</body>
<script>
</script>
</html>