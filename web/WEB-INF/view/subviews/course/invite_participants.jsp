<%-- 
    Document   : invite participants
    Created on : 19-Apr-2014, 20:04:53
    Author     : wilco
--%>

<h1>Uitnodigen voor ${course.name}</h1>
<div id="description">
    <h13>Hieronder kun je gebruikers uitnodigen deel te nemen aan deze cursus.</h13>
</div>
<div id="title_profile_manage">
    <h7>Beheersing</h7>
</div>
<form method="GET" action="${context}/course/invite/${course.id}">
    <select name="competency" onchange="document.forms[0].submit();">
        <option value="">Kies een competentie..</option>
        <c:forEach var="competency" items="${competencies}">
            <option value="${competency.id}" ${competency.id==current_competency.id ? " selected":""}>${competency.name}</option>
        </c:forEach>
    </select>
</form>
<form method="POST" action="${context}/course/invite/${course.id}">
    
<div id="table_container">
    <table cellspacing="0" cellpadding="2" class="competencies_table">
        <tr class="competency_table_header">
            <td></td>
            <td><h17>Gebruiker</h17></td>
            <td><h17>Niveau</h17></td>
        </tr>
        <c:forEach var="user" items="${users}">
            <tr id="document_item">
                <td><input type="checkbox" name="invite" value="${user[0].id}"></td>
                <td><h18>${user[0].givenName} ${user[0].surname}</h18></td>
                <td><h18>${user[1]}</h18></td>
            </tr>
        </c:forEach>
    </table>
</div>
<input type="submit" name="invite_submit" value="Uitnodigen">

</form>
