<%--
    Document   : mycourses
    Created on : 06-Apr-2014, 02:44:24
    Author     : wilco
--%>
<h2><a href="${context}/home" class="title">Teach 'n' Learn</a> | Mijn lessen</h2>

<div id="main_container">
    <div id="fade" class="black_overlay"></div>
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
                <a target="iframe" href="${context}/user/courses/modify/${course.id}">
                    <div id="menu_item">
                        <h8>${course.name}</h8>
                    </div>
                </a>
            </c:forEach>
            
            </div>
            <a href = "javascript:void(0)" onclick = "document.getElementById('light').style.display = 'block';
                    document.getElementById('fade').style.display = 'block'">
                <div id="menu_footer">
                    <h8>Inschrijven</h8>
                </div>
            </a>
        
        <div id="content_container">
            <iframe id="iframe" name="iframe" src="${context}/landing">
            </iframe>
        </div>
        <div id="light" class="white_content">
            <h1>Inschrijven Cursus</h1>
            <div id="description">
                <h15>Kies een cursus uit de lijst om in te schrijven om de bijbehorende informatie en documenten te kunnen bekijken.</h15>
            </div>
            <form method="post" action="${context}/user/courses">
                <div id="dropdown" class="dropdown">
                    <select name="course">
                        <option>Kies een cursus...</option>
                        <c:forEach var="course" items="${all_courses}">
                            <option value="${course.id}">${course.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <a href = "javascript:void(0)" onclick = "document.getElementById('light').style.display = 'none';
                document.getElementById('fade').style.display = 'none'">
                    <h10>Sluiten</h10>
                </a>
                <input class="competency_submit" type="submit" name="addUserCourse" value="Inschrijven" />
            </form>
        </div>
    </div>
</div>
