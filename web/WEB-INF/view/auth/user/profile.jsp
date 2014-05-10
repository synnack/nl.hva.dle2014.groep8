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
        <form method="post" action="${context}/profile">
            <div id="window_header">
                <h4>Profiel</h4>
                <div id="left">   
                    <a href="${context}/home" class="title"><h6>Home</h6></a>
                </div>
                <div id="right">
                    <input class="form_submit" type="submit" name="submit" value="Opslaan" />
                </div>
            </div>
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
                <input class="field_profile" name="password" type="password" placeholder="Wachtwoord" required>
                <input class="field_profile" name="confirm_password" type="password" placeholder="Wachtwoord bevestigen" required>
            </div>
        </form>
    </div>
</div>


