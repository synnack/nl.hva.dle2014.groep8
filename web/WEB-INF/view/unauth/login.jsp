<%-- 
    Document   : index
    Created on : 03-Apr-2014, 21:23:40
    Author     : wilco
--%>
<h2>Teach 'n' Learn | Digitale leeromgeving</h2>
    <div class="centerwindow loginwindow">
        <div id="logo">
            <img src="${context}/images/login/cloud-icon-tl-120x75.png" alt="Teach 'n' Learn">
        </div>
        <h1>Log in op T'n'L</h1>
        <div class="error">${messages.error}<br/>
        </<div>
        <form method="post" action="${context}/login">
            <div class="inputs">
	        <input class="field" name="username" type="text" placeholder="Username" autofocus required>
	        <input class="field" name="password" type="password" placeholder="Password" required>
	       
                <input type="image" src="${context}/images/login/login-40x40.png" alt="Log in">
                
             </div>
	</form>
    </div>
