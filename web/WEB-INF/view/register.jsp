<%-- 
    Document   : register
    Created on : 19-Apr-2014, 12:58:43
    Author     : wilco
--%>
<h2><a href="home" class="title">Teach 'n' Learn</a> | Register</h2>

    <div class="centerwindow">
        <div id="logo">
            <img src="images/login/cloud-icon-tl-120x75.png" alt="Teach 'n' Learn">
        </div>
        <h1>Register!</h1>
	
        <form method="post" action="register">
            <div class="inputs">
                <input class="field" name="given_name" type="text" placeholder="Given name" autofocus required>
                <input class="field" name="surname" type="text" placeholder="Surname" required>
                <input class="field" name="display_name" type="text" placeholder="Display name" required>
                <input class="field" name="email" type="text" placeholder="E-mail" required>
                <input class="field" name="username" type="text" placeholder="Username" required>
	        <input class="field" name="password" type="password" placeholder="Password" required>
                <input class="field" name="confirm_password" type="password" placeholder="Confirm password" required>
	       
                <input type="image" src="images/login/login-40x40.png" alt="Log in">
                
             </div>
	</form>
    </div><