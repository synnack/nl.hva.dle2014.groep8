<%-- 
    Document   : modify
    Created on : 17-May-2014, 18:38:27
    Author     : wilco
--%>

<h1>${user.username} ${user.surname}</h1>

<div id="description">
    <h13>Wijzig de gebruiker door onderstaande gegevens aan te passen.<h13>  
</div>


            <div id="single_container">

                <div id="title_profile">
                    <h7>Persoonlijke Gegevens</h7>
                </div>
                <div id="inputs_profile">
                    <input class="field_profile" name="given_name" type="text" placeholder="Voornaam" value='<%=request.getAttribute("given_name")%>' autofocus required>
                    <input class="field_profile" name="surname" type="text" placeholder="Achternaam" value='<%=request.getAttribute("surname")%>' required>
                    <input class="field_profile" name="email" type="text" placeholder="E-mail" value='<%=request.getAttribute("email")%>' required>
                </div>
                <div id="title_profile"><h7>Wachtwoord wijzigen</h7></div>
                <div id="inputs_profile">
                    <input class="field_profile" name="password" type="password" placeholder="Wachtwoord">
                    <input class="field_profile" name="confirm_password" type="password" placeholder="Wachtwoord bevestigen">
                </div>
                <div class="error">${messages.error}<br/>
                </div>

            </div>