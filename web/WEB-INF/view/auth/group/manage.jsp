<%--
    Document   : manage
    Created on : 19-Apr-2014, 17:44:14
    Author     : wilco
--%>

<h2><a href="${context}/home" class="title">Teach 'n' Learn</a> | Groepen beheren</h2>

<div id="main_container">
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
        <div id="content_container">
            <iframe id="iframe" src="${context}/landing"></iframe>
        </div>
    </div>
</div>
