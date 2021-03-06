<%-- 
    Document   : modify
    Created on : 17-May-2014, 18:38:27
    Author     : wilco
--%>

<h1>${user.givenName} ${user.surname}</h1>

<div id="description">
    <h13>Wijzig de gebruiker door onderstaande gegevens aan te passen.<h13>  
            </div>
        <form method="post" action="${context}/user/manage" target="_top">
            <div id="title_profile_manage">
                <h7>Persoonlijke Gegevens</h7>
            </div>
            <div id="inputs_profile_manage">
                <input class="field_profile_manage" name="given_name" type="text" placeholder="Voornaam" value="${user.givenName}" autofocus required>
                <input class="field_profile_manage" name="surname" type="text" placeholder="Achternaam" value="${user.surname}" required>
                <input class="field_profile_manage" name="email" type="text" placeholder="E-mail" value="${user.email}" required>
                <input type="hidden" name="username" value="${user.username}">
            </div>
            <div id="title_profile_manage"><h7>Wachtwoord wijzigen</h7></div>
            <div id="inputs_profile_manage">
                <input class="field_profile_manage" name="password" type="password" placeholder="Wachtwoord">
                <input class="field_profile_manage" name="confirm_password" type="password" placeholder="Wachtwoord bevestigen">
            </div>
           
           <div id="description" class="">
                    <input class="submit" type="submit" name="modify" value="Opslaan">
                </div>
            <div class="error">${messages.error}<br/>
            </div>
        </form>
            <a href="javascript:void(0)" onclick="document.getElementById('light').style.display = 'block';
        document.getElementById('fade').style.display = 'block'">
                <div id="remove_competency"></div>
            </a>
            <div id="light" class="remove_competency">
                <h1>Verwijder "${user.givenName} ${user.surname}"</h1>
                <div id="description">
                    <h12>Weet u zeker dat u de gebruiker "${user.givenName} ${user.surname}" wilt verwijderen?</h12>
                </div>
                <a href = "javascript:void(0)" onclick = "document.getElementById('light').style.display = 'none';
            document.getElementById('fade').style.display = 'none'">
                    <h10>Annuleren</h10>
                </a>
                <form method="post" target="_top" action="${context}/user/manage">
                    <input type="hidden" name="beheer_user_remove" value="${user.id}">
                    <input class="competency_submit" type="submit" name="beheer_user_remove_submit" value="Verwijderen" />
                </form>
                <div id="remove_competency_arrow">
                </div>
            </div>