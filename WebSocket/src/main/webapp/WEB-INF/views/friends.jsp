<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/friends.css"><title>Insert title here</title>
	<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
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
	
	<div class="modal hidden">
		<div class="modal-overlay"></div>
		<div class="modal-content">
			<i class="fas fa-times"></i>
			<h4>친구 추가</h4>
			<input id="search" placeholder="아이디 검색">
			<div class="search-user"></div>
		</div>
	</div>
</body>
<script>
const addFriendsBtn = document.querySelector('.fa-plus');
const addFriendsModal = document.querySelector('.modal');
const cancleModal = document.querySelector('.fa-times');
const findUser = document.getElementById('search');
addFriendsBtn.addEventListener('click', () => {
	addFriendsModal.classList.remove('hidden');
})
cancleModal.addEventListener('click', () => {
	addFriendsModal.classList.add('hidden');
})

document.querySelector('.friends-list')
	.addEventListener('click', () => {
		location.href='/websocket/chat.do?userName=' + "${userName}";
	});
findUser.addEventListener('keyup', () => {
	$.get("/websocket/findUser.do?search=" + findUser.value, function(data){
		let users = '';
		let list = document.querySelector('.search-user')
		list.innerHTML = '';
		data.map(value => {
			users += '<div class="users">' + value.userId 
					+ '<i class="fas fa-user-plus"></i></div>';	
		})
		list.innerHTML = users;
	});	
})
</script>
</html>