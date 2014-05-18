<%--
    Document   : mycourses
    Created on : 06-Apr-2014, 02:44:24
    Author     : wilco
--%>
<h2><a href="${context}/home" class="title">Teach 'n' Learn</a> | Mijn lessen</h2>

<div id="main_container">
    <div id="view_container">
        <div id="view_header">
                <h4>Mijn cursussen</h4>
            <div id="left">
                <a href="${context}/home" class="title"><h6>Home</h6></a>
            </div>
            <div id="right">
                <a href="${context}/home" class="title"></a>
                
            </div>
            </div>
            <div id="menu_container">
            <c:forEach var="course" items="${courses}">
                <div id="menu_item">
                <h6>${course.name}</h6><br />
                </div>
            </c:forEach>
                  All<br />
            <c:forEach var="course" items="${all_courses}">
                  ${course.name}<br />
            </c:forEach>
        </div>
    </div>
</div>
