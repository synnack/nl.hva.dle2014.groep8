<%-- 
    Document   : chat
    Created on : May 21, 2014, 9:30:04 PM
    Author     : AlvinRotteveel
--%>

<div id="video_container">
    video
</div>

<link href="${context}/resource/css/bootstrap.css" rel="stylesheet">
<style type="text/css">

.form-signin {
	max-width: 300px;
	padding: 19px 29px 29px;
	margin: 0 auto 20px;
	background-color: #fff;
	border: 1px solid #e5e5e5;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	border-radius: 5px;
	-webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
	-moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
	box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
}

.form-signin .form-signin-heading,.form-signin .checkbox {
	margin-bottom: 10px;
}

.form-signin input[type="text"],.form-signin input[type="password"] {
	font-size: 16px;
	height: auto;
	margin-bottom: 15px;
	padding: 7px 9px;
}

#chatroom {
	font-size: 16px;
	height: 40px;
	line-height: 40px;
	width: 300px;
}

#chat-signin
{
    background-color: grey;
}

.received {
	width: 160px;
	font-size: 10px;
}
</style>
<link href="${context}/resource/css/bootstrap-responsive.css" rel="stylesheet">

<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
      <script src="./resource/js/html5shiv.js"></script>
    <![endif]-->


<script>
	var wsocket;
	var serviceLocation = "ws://" + document.location.host + "/Digital_Learning_Environment/chat/";        
	var $nickName;
        var $message;
	var $chatWindow;
	var room = '';

        function onMessageReceived(evt) {
		var msg = JSON.parse(evt.data); // native API
		var $messageLine = $('<tr><td class="user label label-info">' + msg.sender
				+ '</td><td class="message badge">' + msg.message
				+ '</td></tr>');
		$chatWindow.append($messageLine);
	}
	function sendMessage() {
		var msg = '{"message":"' + $message.val() + '", "sender":"'
				+ $nickName.val() + '", "received":""}';
		wsocket.send(msg);
		$message.val('').focus();
	}

	function connectToChatserver() {
                room = $('#lecture').val();
		wsocket = new WebSocket(serviceLocation + room);
		wsocket.onmessage = onMessageReceived;
	}

	$(document).ready(function() {
		$nickName = $('#nickname');
		$message = $('#message');
		$chatWindow = $('#response');
		$('.chat-wrapper').hide();
		$nickName.focus();
                connectToChatserver();
		
		$('#do-chat').submit(function(evt) {
			evt.preventDefault();
			sendMessage()
		});
        });
</script>

<!-- /container -->
<input type="hidden" class="input-block-level" placeholder="Nickname" id="nickname" value="${sessionScope.User.getGivenName()} ${sessionScope.User.getSurname()}">
<input type="hidden" class="input-block-level" placeholder="Lecture" id="lecture" value=${lecture.id}>

<div id="chat_container">
    <h2>${lecture.name}</h2>
    <form id="do-chat">
        <h2 class="alert-success"></h2>
        <table id="response"></table>
        <fieldset>
            <div>
                <input type="text" class="input-block-level" placeholder="Je bericht..." id="message" style="height:20px" autocomplete="off"/>
            </div>
        </fieldset>
    </form>
</div>
        