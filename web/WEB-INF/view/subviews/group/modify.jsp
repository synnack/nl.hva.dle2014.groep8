<%-- 
    Document   : newjsp
    Created on : 17-May-2014, 19:22:15
    Author     : wilco
--%>


<h1>${group.name}</h1>

<div id="description">
    <h13>Wijzig de groep door onderstaande gegevens aan te passen.<h13>  
</div>
<form method="post" action="${context}/group/manage" target="_top">
    <%-- default submit action --%>
    <input class="submit" type="submit" name="modify" value="Opslaan" style="display:none;">
    <input type="hidden" name="group_id" value="${group.id}">
    <div id="title_profile_manage">
        <h7>Group</h7>
    </div>
    
    <div id="inputs_profile_manage">
        <table>
            <tr><td>Naam:</td><td><input name="name" type="text" placeholder="Naam" value="${group.name}" autofocus required></td></tr>
            <tr><td>Manager:</td><td>
                <select name="manager">
                    <c:forEach var="user" items="${users}">
                        <option value="${user.id}" ${user.id == group.manager.id ? "selected" : ""}>${user.givenName} ${user.surname}</option>
                    </c:forEach>
                </select></td></tr>
        </table>
        <input class="submit" type="submit" name="modify" value="Opslaan">
        <br/><br/>
    </div>
</form>

<div id="document_header">
    <h17>Gebruikers</h17>
</div>
<c:forEach var="user" items="${group.userCollection}">
    <div id="document_item">
        ${user.givenName} ${user.surname}
        <a href="${context}/group/modify/${group.id}?remove_user=${user.id}">
            <div id="remove_icon">
            </div>
        </a>
    </div>
</c:forEach>
<div id="document_item">
    <form method="POST" action="${context}/group/modify/${group.id}">
        <select name="user_to_add">
        <c:forEach var="user" items="${users}">
            <option value="${user.id}">${user.givenName} ${user.surname}</option>
        </c:forEach>
        </select>
        <input type="submit" name="add_user" value="Toevoegen">
    </form>
</div>       

<div class="error">${messages.error}<br/></div>
<a href = "javascript:void(0)" onclick = "document.getElementById('light').style.display = 'block';
        document.getElementById('fade').style.display = 'block'">
    <div id="remove_competency"></div>
</a>
<div id="light" class="remove_competency">
    <h1>Verwijder "${group.name}"</h1>
    <div id="description">
        <h12>Weet u zeker dat u de groep "${group.name}" wilt verwijderen?</h12>
    </div>
    <a href = "javascript:void(0)" onclick = "document.getElementById('light').style.display = 'none';
            document.getElementById('fade').style.display = 'none'">
        <h10>Annuleren</h10>
    </a>
    <form method="post" target="_top" action="${context}/group/manage">
        <input type="hidden" name="group_id" value="${group.id}">
        <input class="competency_submit" type="submit" name="remove_group" value="Verwijderen" />
    </form>
    <div id="remove_competency_arrow">
    </div>
</div>
