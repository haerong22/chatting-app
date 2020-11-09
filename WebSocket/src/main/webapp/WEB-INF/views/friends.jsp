<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
		<div class="friends-container">
			<div class="friends-list"></div>
			<c:forEach var="friend" items="${userInfo.friends}">
				<div class="friends-list">
					<div class="friend-info">
						<img class="friend-img" alt="profile" src="static/images/profile.jpg">
						<h2 class="friend-name">${friend}</h2>
					</div>
					<span><i class="fas fa-chevron-right" onclick="location.href='/websocket/chat.do?friendName=${friend}'"></i></span>
				</div>
			</c:forEach>
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
const friendscontainer = document.querySelector('.friends-container');
let friends = document.querySelectorAll('.friend-info > h2');
const chatting = (value) => {
	location.href='/websocket/chat.do?friendName=' + value;
}
const addFriends = val => {
	$.get("/websocket/addFriends.do?searchId=" + val, (data) => {
		let friends = '';
		friendscontainer.innerHTML = '';
		data.friends.map(value => {
			friends += '<div class="friends-list"><div class="friend-info">'
					+ '<img class="friend-img" alt="profile" src="static/images/profile.jpg">'
					+ '<h2 class="friend-name">'+ value + '</h2></div>'
					+ '<span><i class="fas fa-chevron-right" onclick="chatting('+ value +')"></i></span></div>';
		})
		friendscontainer.innerHTML = friends;
		findUser.value = '';
		cancleModal.click();
	})
}
addFriendsBtn.addEventListener('click', () => {
	addFriendsModal.classList.remove('hidden');
})
cancleModal.addEventListener('click', () => {
	addFriendsModal.classList.add('hidden');
})

findUser.addEventListener('keyup', () => {
	$.get("/websocket/findUser.do?search=" + findUser.value, function(data){
		let users = '';
		let list = document.querySelector('.search-user')
		list.innerHTML = '';
		data.map(value => {
			let b = true;
			for(let i=0; i<friends.length; i++) {
				if(friends[i].innerText == value.userId) b = false;  
			}
			b ? 
			users += '<div class="users" onclick="addFriends(this.innerText)">' + value.userId 
					+ '<i class="fas fa-user-plus"></i></div>':
			users += '<div class="users">' + value.userId 
			+ '<i class="fas fa-user-friends"></i></div>';
		})
		list.innerHTML = users;
	});	
})
</script>
</html>