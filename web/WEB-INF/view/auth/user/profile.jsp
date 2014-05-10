<%-- 
    Document   : profile
    Created on : 19-Apr-2014, 20:41:10
    Author     : wilco
--%>

<h2><a href="${context}/home" class="title">Teach 'n' Learn</a> | Profiel</h2>

<div id="main_container">
    <div id="submenu">
    </div>
    <div id="window">
        <div id="window_header">
                <h4>Profiel</h4>
            <div id="left">   
                <a href="${context}/home" class="title"><h6>Home</h6></a>
            </div>
            <div id="right">  
                <a href="${context}/home" class="title"><h5>Opslaan</h5></a>
            </div>
        </div>
        <form method="post" action="${context}/register">
            <div id="title_profile"><h7>Persoonlijke Gegevens</h7></div>
            <div id="inputs_profile">
                <input class="field_profile" name="given_name" type="text" placeholder="Voornaam" autofocus required>
                <input class="field_profile" name="surname" type="text" placeholder="Achternaam" required>
                <input class="field_profile" name="email" type="text" placeholder="E-mail" required>
                <input class="field_profile" name="email" type="text" placeholder="E-mail" required>
             </div>
            <div id="title_profile"><h7>Wachtwoord wijzigen</h7></div>
            <div id="inputs_profile">
                <input class="field_profile" name="password" type="password" placeholder="Wachtwoord" required>
                <input class="field_profile" name="confirm_password" type="password" placeholder="Wachtwoord bevestigen" required>
            </div>
        </form>
    </div>
</div>


