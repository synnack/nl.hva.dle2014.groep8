<%--
    Document   : manage
    Created on : 19-Apr-2014, 17:45:05
    Author     : wilco
--%>

<h2><a href="${context}/home" class="title">Teach 'n' Learn</a> | Competenties beheren</h2>

<div id="main_container">
    <div id="view_container">
        <div id="view_header">
            <h4>Competenties beheren</h4>
            <div id="left">   
                <a href="${context}/manage" class="title"><h6>Beheren</h6></a>
            </div>
            <div id="right">  
                <a href="${context}/home" class="title"></a>
            </div>

        </div>
        <div id="menu_container">
            <c:forEach var="competency" items="${competencies}">
                <a target="iframe" href="${context}/competency/modify/${competency.id}">
                    <div id="menu_item">
                        <h8>${competency.name}</h8>
                    </div>
                </a>
            </c:forEach>
        </div>
        <a href = "javascript:void(0)" onclick = "document.getElementById('light').style.display = 'block';
                document.getElementById('fade').style.display = 'block'">
            <div id="menu_footer">
                <h8>Aanmaken</h8>
            </div>
        </a>
        <div id="content_container">
            <iframe id="iframe" name="iframe" src="${context}/landing"></iframe>
        </div>
    </div>
    <div id="light" class="white_content">
        <h1>Competentie Aanmaken</h1>
        <div id="description">
            <h15>Maak een nieuwe competentie aan door hieronder de naam aan de nieuwe competentie mee te geven. Druk vervolgens op Aanmaken.</h15>
        </div>
        <form method="post" action="${context}/competency/manage">
            <div id="description" >
                <br><br>
                <input type="text" name="beheren_competency">

            </div>
            <a href = "javascript:void(0)" onclick = "document.getElementById('light').style.display = 'none';
                    document.getElementById('fade').style.display = 'none'">
                <h10>Sluiten</h10>
            </a>
            <input class="competency_submit" type="submit" name="beheren_cometency_add" value="Aanmaken" />
        </form>
    </div>
</div>
