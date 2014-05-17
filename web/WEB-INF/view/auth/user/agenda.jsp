<%-- 
    Document   : agenda
    Created on : 20-Apr-2014, 14:18:31
    Author     : wilco
--%>

<h2><a href="${context}/home" class="title">Teach 'n' Learn</a> | Agenda</h2>

<div id="main_container">
    <div id="view_container">
        <div id="view_header">
            <h4>Agenda</h4>
            <div id="left">
                <a href="${context}/home" class="title"><h6>Home</h6></a>
            </div>
            <div id="right">
                <a href="${context}/home" class="title"></a>
            </div>
            <table>
            <c:forEach var="lecture" items="${lectures}">
                <tr>
                <td>${lecture.name}</td><td>${lecture.startDate}</td><td>${lecture.endDate}</td>
                </tr>
            </c:forEach>
            </table>
        </div>
    </div>
</div>
