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

<script type="text/javascript">
    var serviceLocation = "ws://" + document.location.host + "/Digital_Learning_Environment/chat/${lecture.id}";
    var nickName = "${sessionScope.User.givenName} ${sessionScope.User.surname}";
</script>
<script type="text/javascript" src="${context}/js/lecture.js"></script>

<h2>${lecture.name}</h2>

<div id="chat_container">
        <table id="response"></table>
</div>
    <form id="do-chat">
        <h2 class="alert-success"></h2>
        <fieldset>
            <div>
                <input type="text" class="input-block-level" placeholder="Je bericht..." id="message" style="height:20px" autocomplete="off"/>
            </div>
        </fieldset>
    </form>

        