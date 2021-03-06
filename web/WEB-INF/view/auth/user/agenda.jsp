<%-- 
    Document   : agenda
    Created on : 20-Apr-2014, 14:18:31
    Author     : wilco
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
            <div id="main_description_container">
                <div id="agenda_header">
                    <a href="${context}/user/courses"><h16>Cursus</h16></a>
                </div>
                <c:forEach var="lecture" items="${lectures}">
                    <div id="agenda_item">
                        
                        <h15v2>${lecture.course.name}</h15v2>
                    </div>
                </c:forEach>
            </div>         
            <div id="left_description_container">
                <div id="agenda_header">
                    <h16>College</h16>
                </div>
                <c:forEach var="lecture" items="${lectures}">
                    <div id="agenda_item">
                        <h15v2>${lecture.name}</h15v2>
                        <div id="join_icon" onclick="window.open('${context}/course/lecture/${lecture.id}','TnL Chat', 'width=825,height=760,innerHeight=760,scrollbars=no,toolbar=no,location=no'); return false" >
                        </div>                        
                    </div>
                </c:forEach>
            </div>
            <div id="middle_description_container">
                <div id="agenda_header">
                    <h16>Van</h16>
                </div>
                <c:forEach var="lecture" items="${lectures}">
                    <div id="agenda_item">
                        <h15v2><fmt:formatDate type="both" 
            dateStyle="medium" timeStyle="medium" 
            value="${lecture.startDate}" /></h15v2>
                    </div>
                </c:forEach>
            </div>
            <div id="right_description_container">
                <div id="agenda_header">
                    <h16>Tot</h16>
                </div>
                <c:forEach var="lecture" items="${lectures}">
                    <div id="agenda_item">
                        <h15v2><fmt:formatDate type="both" 
            dateStyle="medium" timeStyle="medium" 
            value="${lecture.endDate}" /></h15v2>
                    </div>
                </c:forEach>
            </div>

        </div>
    </div>
</div>
