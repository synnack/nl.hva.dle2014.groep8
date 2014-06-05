<%--
    Document   : manage
    Created on : 19-Apr-2014, 17:44:14
    Author     : wilco
--%>

<h2><a href="${context}/home" class="title">Teach 'n' Learn</a> | Groepen beheren</h2>

<div id="main_container">
     <div id="fade" class="black_overlay"></div>
    <div id="view_container">
        <div id="view_header">
            <h4>Groepen beheren</h4>
            <div id="left">   
                <a href="${context}/manage" class="title"><h6>Beheren</h6></a>
            </div>
            <div id="right">  
                <a href="${context}/home" class="title"></a>
            </div>

        </div>
        <div id="menu_container">
            <c:forEach var="group" items="${groups}">
                <a target="iframe" href="${context}/group/modify/${group.id}">
                    <div id="menu_item">
                        <h8>${group.name}</h8>
                    </div>
                </a>
            </c:forEach>
        </div>
        <a href = "javascript:void(0)" onclick = "document.getElementById('light').style.display = 'block';
                document.getElementById('fade').style.display = 'block'">
            <div id="menu_footer">
                <h8>Aanmaken </h8>
            </div>
        </a>
        <div id="content_container">
            <iframe id="iframe" name="iframe" src="${context}/landing"></iframe>
        </div>
    </div>
    <div id="light" class="white_content">
        <h1>Groep Aanmaken</h1>
        <div id="description">
            <h15>Maak een nieuwe groep aan door hieronder de naam aan de nieuwe groep mee te geven. Druk vervolgens op Aanmaken.</h15>
        </div>
        <form method="post" action="${context}/group/manage">
            <div id="description" >
                <input class="field_competency" type="text" placeholder="Naam" name="group">
                <input type="hidden" name="manager" value="Sander van Es">
<!--                <tr><td>
                <h18>Manager:</h18>
                </td><td>
                    <div id="dropdown" class="group_dropdown">
                        <select name="manager">
                            <c:forEach var="user" items="${users}">
                                <option value="${user.id}" ${user.id == group.manager.id ? "selected" : ""}>${user.givenName} ${user.surname}</option>
                            </c:forEach>
                        </select>
                    </div>
                </td></tr>-->
            </div>
            <a href = "javascript:void(0)" onclick = "document.getElementById('light').style.display = 'none';
                    document.getElementById('fade').style.display = 'none'">
                <h10>Sluiten</h10>
            </a>
            <input class="competency_submit" type="submit" name="group_add" value="Aanmaken"/>
        </form>
    </div>
</div>
