<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	    
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>채팅방</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/chat.css">
	<script src="https://kit.fontawesome.com/5b3cf06972.js" crossorigin="anonymous"></script>
	<script src="static/js/chat.js"></script>
</head>
<body>
<div class="container">
	<nav class="navbar">
		<div class="back">
			<a><i class="fas fa-chevron-left"></i></a>
		</div>
		<div class="reciever">Nickname</div>
		<div class="option">
			<a><i class="fas fa-cog"></i></a>
		</div>
	</nav>
	<div id="msg-box" class="msg-box">
	</div>
	<footer>
		<div class="msg-send">	
			<input id="msg" type="text" autofocus placeholder="메시지 입력"><button id="btn-send" type="button">전송</button>
		</div>
	</footer>
</div>
</body>
<script>
	connect("${userName}");
	ready();
</script>
</html>