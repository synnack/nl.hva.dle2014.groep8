<%-- 
    Document   : chat
    Created on : May 21, 2014, 9:30:04 PM
    Author     : AlvinRotteveel
--%>

<video autoplay id="video_container">
  
</video>

<link href="${context}/resource/css/bootstrap.css" rel="stylesheet">
<style type="text/css">

#chatroom {
	font-size: 16px;
	height: 40px;
	line-height: 40px;
	width: 300px;
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
	var serviceLocation = "ws://" + document.location.host + "/Digital_Learning_Environment/chat/${lecture.id}";        
        var $message;
	var $chatWindow;

        function onMessageReceived(evt) {
		var msg = JSON.parse(evt.data); // native API
		var $messageLine = $('<tr><td class="user label label-info">' + msg.sender
				+ '</td><td class="message badge">' + msg.message
				+ '</td></tr>');
		$chatWindow.append($messageLine);
	}
	function sendMessage() {
		var msg = '{"message":"' + $message.val() + '", "sender":"${sessionScope.User.givenName} ${sessionScope.User.surname}", "received":""}';
		wsocket.send(msg);
		$message.val('').focus();
	}

	function connectToChatserver() {
		wsocket = new WebSocket(serviceLocation);
		wsocket.onmessage = onMessageReceived;
	}

	$(document).ready(function() {
		$message = $('#message');
		$chatWindow = $('#response');
		$('.chat-wrapper').hide();
		$message.focus();
                connectToChatserver();
		
		$('#do-chat').submit(function(evt) {
			evt.preventDefault();
			sendMessage()
		});
        });
</script>
<h2>${lecture.name}</h2>

<div id="chat_container">
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
        