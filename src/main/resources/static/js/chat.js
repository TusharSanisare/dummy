const nameForm = document.querySelector("#name-form");
const chatRoom = document.querySelector("#chat-room");
const name = document.querySelector("#name-value");
const loginbtn = document.querySelector("#login-btn");
const sendbtn = document.querySelector("#send-btn");
const leavebtn = document.querySelector("#leave-btn");
const msgContainer = document.querySelector("#msg-container");
const messageValue = document.querySelector("#message-value");
const name_value = document.querySelector("#name-value");

let stompClient = null;
const connectedUser = JSON.parse(localStorage.getItem('connectedUser'));
name_value.value = connectedUser.username;


loginbtn.addEventListener("click",()=>{
	
	localStorage.setItem("name",name.value);
	connect();
})

sendbtn.addEventListener("click",()=>{
	sendMessage();
	
})

leavebtn.addEventListener("click",()=>{
	sendMessage();
	
	stompClient.disconnect();
	chatRoom.classList.add("d-none");
	nameForm.classList.remove("d-none");
	console.log(stompClient);
	
	
})


function sendMessage(){
	let jsonObj = {
		name:localStorage.getItem("name"),
		content:messageValue.value,
	}
	
	stompClient.send("/app/message",{},JSON.stringify(jsonObj));
}

function connect(){
	let socket = new SockJS("/server1");
	stompClient = Stomp.over(socket);
	stompClient.connect({},(frame)=>{
		nameForm.classList.add("d-none");
		chatRoom.classList.remove("d-none");
		
		stompClient.subscribe("/topic/return-to",(response)=>{
			showMessage(JSON.parse(response.body));
		});
	})
}

function showMessage(message){
	var newItem = document.createElement("td");
	newItem.className = "mt-1";
	newItem.innerHTML = `<b>${message.name}</b> : ${message.content}`
	msgContainer.appendChild(newItem);
}


