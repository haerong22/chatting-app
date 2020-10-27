<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/friends.css"><title>Insert title here</title>
	<script src="https://kit.fontawesome.com/5b3cf06972.js" crossorigin="anonymous"></script>
</head>
<body>
	<header class="friend-menu">
		<span class="friend-menu-title">친구</span>
		<div class="friend-menu-icon">
			<span><i class="fas fa-plus"></i></span>
			<span><i class="fas fa-search"></i></span>
			<span><i class="fas fa-music"></i></span>
			<span><i class="fas fa-cog"></i></span>
		</div>
	</header>
	<div class="user">
		<div class="user-info">
			<img class="user-img" alt="profile" src="static/images/profile.jpg">
			<h2 class="user-name">${userName}</h2>
		</div>
		<div class="friends-list">
			<div class="friend-info">
				<img class="friend-img" alt="profile" src="static/images/profile.jpg">
				<h2 class="friend-name">friend-name</h2>
			</div>
			<span><i class="fas fa-chevron-right"></i></span>
		</div>
	</div>
	<footer class="nav">
		<div class="nav-contents">
			<span><i class="fas fa-users"></i></span>
			<span><i class="far fa-comment-dots"></i></span>
			<span><i class="fas fa-hashtag"></i></span>
			<span><i class="fas fa-ellipsis-h"></i></span>
		</div>
	</footer>
</body>
<script>
document.querySelector('.friends-list')
	.addEventListener('click', () => {
		location.href='/websocket/chat.do?userName=' + "${userName}";
	});
</script>
</html>