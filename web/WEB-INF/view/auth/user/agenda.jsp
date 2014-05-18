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
            <div id="left_description_container">
                <div id="agenda_header">
                        <h16>Omschrijving</h16>
                    </div>
                <c:forEach var="lecture" items="${lectures}">
                    <div id="agenda_item">
                        <h15>${lecture.name}</h15>
                    </div>
                </c:forEach>
            </div>
            <div id="middle_description_container">
                <div id="agenda_header">
                        <h16>Van</h16>
                    </div>
                <c:forEach var="lecture" items="${lectures}">
                    <div id="agenda_item">
                    <h15>${lecture.startDate}</h15>
                    </div>
                </c:forEach>
            </div>
            <div id="right_description_container">
                <div id="agenda_header">
                        <h16>Tot</h16>
                    </div>
                <c:forEach var="lecture" items="${lectures}">
                    <div id="agenda_item">
                    <h15>${lecture.endDate}</h15>
                    </div>
                </c:forEach>
            </div>

        </div>
    </div>
</div>
