<%-- 
    Document   : profile
    Created on : 19-Apr-2014, 20:41:10
    Author     : wilco
--%>

<h2><a href="${context}/home" class="title">Teach 'n' Learn</a> | Profiel</h2>

<div id="main_container">
    <div id="view_container">
        <form method="post" action="${context}/user/profile">
            <div id="view_header">
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
            <div class="error">${messages.error}<br/>
            </div>
            <div id="inputs_profile">
                <input class="field_profile" name="password" type="password" placeholder="Wachtwoord">
                <input class="field_profile" name="confirm_password" type="password" placeholder="Wachtwoord bevestigen">
            </div>
        </form>
    </div>
</div>


