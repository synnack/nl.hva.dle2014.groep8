<%--
    Document   : manage
    Created on : 19-Apr-2014, 17:44:14
    Author     : wilco
--%>

<h2><a href="${context}/home" class="title">Teach 'n' Learn</a> | Gebruikers beheren</h2>

<div id="main_container">
    <div id="view_container">
        <div id="view_header">
            <h4>Gebruikers beheren</h4>
            <div id="left">   
                <a href="${context}/home" class="title"><h6>Home</h6></a>
            </div>
            <div id="right">  
                <a href="${context}/home" class="title"></a>
            </div>

        </div>
        <div id="menu_container">
            <c:forEach var="user" items="${users}">
                <a target="iframe" href="${context}/user/modify/${user.id}">
                <div id="menu_item">
                    <h8>${user.givenName} ${user.surname}</h8>
                </div>
                </a>
            </c:forEach>
        </div>
        <div id="content_container">
            <iframe id="iframe"></iframe>
        </div>
    </div>
</div>
