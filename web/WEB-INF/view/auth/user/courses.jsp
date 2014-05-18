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
                <a target="iframe" href="${context}/user/courses/modify">
                    <div id="menu_item">
                        <h8>${course.name}</h8>
                    </div>
                </a>
            </c:forEach>
            <a href = "javascript:void(0)" onclick = "document.getElementById('light').style.display = 'block';
                                document.getElementById('fade').style.display = 'block'">
                <div id="menu_footer">
                    <h8>Toevoegen</h8>
                </div>
            </a>
        </div>
        <div id="content_container">
            <iframe id="iframe"></iframe>
        </div>

    </div>
</div>
