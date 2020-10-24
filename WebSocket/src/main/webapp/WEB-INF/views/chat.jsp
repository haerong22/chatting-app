<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>채팅방</title>
</head>
<body>
	<div>
		<input id="user-name" type="text" placeholder="아이디 입력"><button id="btn-name" type="button">확인</button> 	
	</div>
	<div id="chatroom" style="width: 400px; height: 400px; border: 1px solid;"></div>
	<div>	
		<input id="msg" type="text" placeholder="메시지 입력"><button id="btn-send" type="button">전송</button>
	</div>
</body>
<script>
	let ws;
	let nickname;
	const enterChattingRoom = function () {
		nickname = document.getElementById('user-name').value;
		document.getElementById('user-name').style.display='none';
		document.getElementById('btn-name').style.display='none';
		connect();
	}
	const connect = function() {
		ws = new WebSocket('ws://localhost:8089/websocket/chat');
		ws.onopen = () => {
			console.log(nickname + '연결됨');
		}
		ws.onmessage = msg => {
			let chatroom = document.getElementById('chatroom');
			console.log(msg);
			console.log(msg.data);
			chatroom.innerHTML = chatroom.innerHTML + "<br>" + msg.data; 
			document.getElementById('msg').value = '';
		}
		ws.close = () => {
			console.log('연결종료');
		}
	}
	
	const sendMessage = function() {
		let msg = document.getElementById('msg').value;
		ws.send(nickname + " : " + msg);
		
	}
	document.getElementById('btn-name').addEventListener('click', enterChattingRoom);
	document.getElementById('btn-send').addEventListener('click', sendMessage);
</script>
</html>