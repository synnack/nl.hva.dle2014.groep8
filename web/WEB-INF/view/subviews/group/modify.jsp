<%-- 
    Document   : newjsp
    Created on : 17-May-2014, 19:22:15
    Author     : wilco
--%>


<h1>${group.name}</h1>

<div id="description">
    <h13>Wijzig de groep door onderstaande gegevens aan te passen.<h13>  
</div>
<form method="post" action="${context}/competency/manage" target="_top">
    <input type="hidden" name="competency" value="${group.id}">
    <div id="title_profile_manage">
        <h7>Group</h7>
    </div>
    <div id="inputs_profile_manage">
        <input class="field_profile_manage" name="name" type="text" placeholder="Naam" value='${group.name}' autofocus required>
        <select name="">
            <c:forEach var="user" items="${users}">
                <option value="${user.id}" ${user.id == $group.manager.id ? "selected" : ""}>${user.givenName} ${user.surname}</option>
            </c:forEach>
        </select>
        

    </div>
    <div class="error">${messages.error}<br/>
    <input class="submit" type="submit" name="modify" value="Opslaan">
    </div>
</form>
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
    <form method="post" target="_top" action="${context}/competency/manage">
        <input type="hidden" name="remove" value="${group.id}">
        <input class="competency_submit" type="submit" name="remove_group" value="Verwijderen" />
    </form>
    <div id="remove_competency_arrow">
    </div>
</div>
