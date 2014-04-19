<%-- 
    Document   : register
    Created on : 19-Apr-2014, 12:58:43
    Author     : wilco
--%>
<h2><a href="home" class="title">Teach 'n' Learn</a> | Wachtwoord vergeten</h2>

    <div class="centerwindow registerwindow">
        <div id="logo">
            <img src="images/login/cloud-icon-tl-120x75.png" alt="Teach 'n' Learn">
        </div>
        <h1>Registreer bij T'n'L</h1>
	
        <form method="post" action="register">
            <div class="inputs">
                <input class="field" name="given_name" type="text" placeholder="Voornaam" autofocus required>
                <input class="field" name="surname" type="text" placeholder="Achternaam" required>
                <input class="field" name="email" type="text" placeholder="E-mail" required>
                <input class="field" name="username" type="text" placeholder="Gebruikersnaam" required>
	        <input class="field" name="password" type="password" placeholder="Wachtwoord" required>
                <input class="field" name="confirm_password" type="password" placeholder="Wachtwoord bevestigen" required>
	       
                <input type="image" src="images/login/login-40x40.png" alt="Log in">
                
             </div>
	</form>
    </div>
