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
            <a href = "javascript:void(0)" onclick = "document.getElementById('light').style.display = 'block';
                    document.getElementById('fade').style.display = 'block'">
                <div id="menu_footer">
                    <h8>Toevoegen</h8>
                </div>
            </a>
        </div>
        <div id="content_container">
            <iframe id="iframe" name="iframe" src="${context}/landing">
            </iframe>
        </div>
    </div>
    <div id="light" class="white_content">
        <h1>Competentie Toevoegen</h1>
        <div id="description">
            <h15>Kies een competentie uit de lijst hieronder. Als u een competentie heeft gekozen kiest u vervolgens het niveau dat u beheerst en klikt u op toevoegen.</h15>
        </div>
        <form method="post" action="${context}/user/competencies">
            <div id="dropdown" class="dropdown">
                <select name="competency">
                    <option>Kies een competentie...</option>
                    <c:forEach var="competency" items="${all_competencies}">
                        <option value="${competency.id}">${competency.name}</option>
                    </c:forEach>
                </select>
                
            </div>
            
            <div id="dropdown2" class="dropdown2">
            <select name="skill_level">
                <option>Hoe goed beheers je dit?</option>
                <option>1</option>
                <option>2</option>
                <option>3</option>
                <option>4</option>
                <option>5</option>
                <option>6</option>
                <option>7</option>
                <option>8</option>
                <option>9</option>
                <option>10</option>
            </select>
            </div>
            <a href = "javascript:void(0)" onclick = "document.getElementById('light').style.display = 'none';
                    document.getElementById('fade').style.display = 'none'">
                <h10>Sluiten</h10>
            </a>
            <input class="competency_submit" type="submit" name="add" value="Toevoegen" />
        </form>
    </div>

</div>
