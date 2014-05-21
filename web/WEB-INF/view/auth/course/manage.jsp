<%--
    Document   : manage
    Created on : 19-Apr-2014, 17:44:49
    Author     : wilco
--%>

<h2><a href="${context}/home" class="title">Teach 'n' Learn</a> | Cursussen beheren</h2>

<div id="main_container">
    <div id="view_container">
        <div id="view_header">
            <h4>Cursussen beheren</h4>
            <div id="left">   
                <a href="${context}/manage" class="title"><h6>Beheren</h6></a>
            </div>
            <div id="right">  
                <a href="${context}/home" class="title"></a>
            </div>

        </div>
        <div id="menu_container">
            <c:forEach var="course" items="${courses}">
                <a target="iframe" href="${context}/course/modify/${course.id}">
                <div id="menu_item">
                    <h8>${course.name}</h8>
                </div>
                </a>
            </c:forEach>
            <a target="iframe" href="${context}/course/create">
            <div id="menu_item">
                <h8><strong>Nieuwe cursus</strong></h8>
            </div>
            </a>
        </div>
        <div id="content_container">
            <iframe id="iframe" name="iframe" src="${context}/landing"></iframe>
        </div>
    </div>
</div>

