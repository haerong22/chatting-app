let ws;
let nickname;
const connect = function(userName) {
	ws = new WebSocket('ws://localhost:8088/websocket/chat');
	nickname = userName;
	ws.onopen = () => {
		console.log(nickname + '연결됨');
		ws.send(JSON.stringify({
			writer : nickname,
			type : 'ENTER'
		}));
	}
	ws.onmessage = msg => {
		let msgBox = document.getElementById('msg-box');
		let addmsg = '';
		let jsonmsg = JSON.parse(msg.data);
		if(jsonmsg.writer === nickname) {
			addmsg += '<div class="message-row-own">';
			addmsg += '<div class="message-info-own">';
			addmsg += '<span class="message-time">22:07</span>'
			addmsg += '<span class="message-own">' + jsonmsg.message + '</span>';
			addmsg += '</div></div>';
		} else {
			addmsg += '<div class="message-row">'
			addmsg += '<img class="profile-img" alt="profile" src="static/images/profile.jpg">'
			addmsg += '<div class="message-contents">'
			addmsg += '<span class="message-writer">' + jsonmsg.writer + '</span>'
	 		addmsg += '<div class="message-info">'
	 		addmsg += '<span class="message">'+ jsonmsg.message + '</span>'
	 		addmsg += '<span class="message-time">22:07</span>'
	 		addmsg += '</div></div></div>'
		}
		console.log(msg.data);
		msgBox.innerHTML += addmsg; 
		document.getElementById('msg').value = '';
	}
	ws.close = () => {
		console.log('연결종료');
		ws.send(JSON.stringify({
			writer : nickname,
			type : 'LEAVE'
		}));
	}
}

const sendMessage = function() {
	let msg = document.getElementById('msg').value;
	ws.send(JSON.stringify({
		writer : nickname,
		message : msg,
		type : 'CHAT'
	}));
	
}	

const ready = function() {
	document.querySelector('.fa-chevron-left')
		.addEventListener('click', () => {
			location.href='/websocket/login.do';
		})
	document.getElementById('msg').addEventListener('keydown', e => {
		if(e.keyCode == 13) {
			document.getElementById('btn-send').click();
		}
	})
	document.getElementById('btn-send').addEventListener('click', sendMessage);
}
