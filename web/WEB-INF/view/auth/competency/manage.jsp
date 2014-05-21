<%--
    Document   : manage
    Created on : 19-Apr-2014, 17:45:05
    Author     : wilco
--%>

<h2><a href="${context}/home" class="title">Teach 'n' Learn</a> | Competenties beheren</h2>

<div id="main_container">
    <div id="view_container">
        <div id="view_header">
            <h4>Competenties beheren</h4>
            <div id="left">   
                <a href="${context}/manage" class="title"><h6>Beheren</h6></a>
            </div>
            <div id="right">  
                <a href="${context}/home" class="title"></a>
            </div>

        </div>
        <div id="menu_container">
            <c:forEach var="competency" items="${competencies}">
                <a target="iframe" href="${context}/competency/modify/${competency.id}">
                <div id="menu_item">
                    <h8>${competency.name}</h8>
                </div>
                </a>
            </c:forEach>
        </div>
        <div id="content_container">
            <iframe id="iframe" name="iframe" src="${context}/landing"></iframe>
        </div>
    </div>
</div>
