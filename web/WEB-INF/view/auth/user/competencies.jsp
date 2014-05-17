<%-- 
    Document   : comptencies
    Created on : 20-Apr-2014, 15:06:23
    Author     : wilco
--%>

<h2><a href="${context}/home" class="title">Teach 'n' Learn</a> | Mijn competenties</h2>

<div id="main_container">
    <div id="fade" class="black_overlay"></div>
    <div id="view_container">
        <div id="view_header">
            <h4>Mijn Competenties</h4>
            <div id="left">   
                <a href="${context}/home" class="title"><h6>Home</h6></a>
            </div>
            <div id="right">  
                <a href="${context}/home" class="title"></a>
            </div>

        </div>
        <div id="menu_container">
            <c:forEach var="usercompetency" items="${competencies}">
                <a target="iframe" href="${context}/user/competencies/modify/${usercompetency.userCompetencyPK.userId}-${usercompetency.userCompetencyPK.competencyId}">
                    <div id="menu_item">
                        <h8>${usercompetency.competency.name}</h8>
                    </div>
                </a>
            </c:forEach>
            <div id="menu_footer">
                <a href = "javascript:void(0)" onclick = "document.getElementById('light').style.display = 'block';
                        document.getElementById('fade').style.display = 'block'"><h8>Toevoegen</h8>
                </a>
            </div>
        </div>
        <div id="content_container">
            <iframe id="iframe"></iframe>
        </div>
    </div>
    <div id="light" class="white_content">
        
        <a href = "javascript:void(0)" onclick = "document.getElementById('light').style.display = 'none';
            document.getElementById('fade').style.display = 'none'">Sluiten
        </a>
    </div>

</div>