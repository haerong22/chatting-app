let ws;
let nickname;
let recieverName;
const connect = function(userName, friendName) {
	ws = new WebSocket('ws://localhost:8088/websocket/chat');
	nickname = userName;
	recieverName = friendName;
	ws.onopen = () => {
		console.log(nickname + '연결됨');
		ws.send(JSON.stringify({
			writer : nickname,
			reciever : friendName,
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
			addmsg += '<span class="message-time">'+ jsonmsg.time +'</span>'
			addmsg += '<span class="message-own">' + jsonmsg.message + '</span>';
			addmsg += '</div></div>';
		} else {
			addmsg += '<div class="message-row">'
			addmsg += '<img class="profile-img" alt="profile" src="static/images/profile.jpg">'
			addmsg += '<div class="message-contents">'
			addmsg += '<span class="message-writer">' + jsonmsg.writer + '</span>'
	 		addmsg += '<div class="message-info">'
	 		addmsg += '<span class="message">'+ jsonmsg.message + '</span>'
	 		addmsg += '<span class="message-time">'+ jsonmsg.time +'</span>'
	 		addmsg += '</div></div></div>'
		}
		console.log(msg.data);
		msgBox.innerHTML += addmsg; 
		document.getElementById('msg').value = '';
	}
	ws.onclose = () => {
		console.log('연결종료');
		ws.send(JSON.stringify({
			writer : nickname,
			reciever : friendName,
			type : 'LEAVE'
		}));
	}
}

const sendMessage = function() {
	let date = new Date();
	let minutes = date.getMinutes();
	let time = date.getHours() + ":" + (minutes < 10 ? "0" + minutes: minutes);
	let msg = document.getElementById('msg').value;
	ws.send(JSON.stringify({
		writer : nickname,
		message : msg,
		reciever : recieverName,
		time : time,
		type : 'CHAT'
	}));
	
}	

const ready = function() {
	document.querySelector('.fa-chevron-left')
		.addEventListener('click', () => {
			location.href='/websocket/friends.do';
		})
	document.getElementById('msg').addEventListener('keydown', e => {
		if(e.keyCode == 13) {
			document.getElementById('btn-send').click();
		}
	})
	document.getElementById('btn-send').addEventListener('click', sendMessage);
}
