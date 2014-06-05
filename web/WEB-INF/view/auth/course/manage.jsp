<%--
    Document   : manage
    Created on : 19-Apr-2014, 17:44:49
    Author     : wilco
--%>

<h2><a href="${context}/home" class="title">Teach 'n' Learn</a> | Cursussen beheren</h2>

<div id="main_container">
    <div id="fade" class="black_overlay"></div>
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
        </div>
        <a href = "javascript:void(0)" onclick = "document.getElementById('light').style.display = 'block';
                document.getElementById('fade').style.display = 'block'">
            <div id="menu_footer">
                <h8>Aanmaken </h8>
            </div>
        </a>
        <div id="content_container">
            <iframe id="iframe" name="iframe" src="${context}/landing"></iframe>
        </div>
    </div>
    <div id="light" class="white_content">
        <h1>Cursus Aanmaken</h1>
        <div id="description">
            <h15>Maak een nieuwe cursus aan door hieronder de naam aan de nieuwe cursus mee te geven. Druk vervolgens op Aanmaken.</h15>
        </div>
        <form method="post" action="${context}/course/manage">
            <input class="field_competency" type="text" placeholder="Naam" name="name">          
            <select name="managing_group" class="field_competency">
                <c:forEach var="group" items="${groups}">
                    <option value="${group.id}">${group.name}</option>
                </c:forEach>
            </select>
            
            <a href = "javascript:void(0)" onclick = "document.getElementById('light').style.display = 'none';
                    document.getElementById('fade').style.display = 'none'">
                <h10>Sluiten</h10>
            </a>
            <input class="competency_submit" type="submit" name="course_add" value="Aanmaken"/>
        </form>
    </div>
</div>
