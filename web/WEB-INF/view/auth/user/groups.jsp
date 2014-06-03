<%-- 
    Document   : comptencies
    Created on : 20-Apr-2014, 15:06:23
    Author     : wilco
--%>

<h2><a href="${context}/home" class="title">Teach 'n' Learn</a> | Mijn groepen</h2>

<div id="main_container">
    <div id="fade" class="black_overlay"></div>
    <div id="view_container">
        <div id="view_header">
            <h4>Mijn Groepen</h4>
            <div id="left">   
                <a href="${context}/home" class="title"><h6>Home</h6></a>
            </div>
            <div id="right">  
                <a href="${context}/home" class="title"></a>
            </div>

        </div>
        <div id="menu_container">
            <c:forEach var="group" items="${groups}">
                <a target="iframe" href="${context}/user/groups/modify/${group.id}">
                    <div id="menu_item">
                        <h8>${group.name}</h8>
                    </div>
                </a>
            </c:forEach>
        </div>
            <a href = "javascript:void(0)" onclick = "document.getElementById('light').style.display = 'block';
                    document.getElementById('fade').style.display = 'block'">
                <div id="menu_footer">
                    <h8>Toevoegen</h8>
                </div>
            </a>
        <div id="content_container">
            <iframe id="iframe" name="iframe" src="${context}/landing">
            </iframe>
        </div>
    </div>
    <div id="light" class="white_content">
        <h1>Groep Toevoegen</h1>
        <div id="description">
            <h15>Kies een groep uit de lijst hieronder.</h15>
        </div>
        <form method="post" action="${context}/user/groups">
            <div id="dropdown" class="dropdown">
                <select name="group">
                    <option>Kies een groep...</option>
                    <c:forEach var="group" items="${all_groups}">
                        <option value="${group.id}">${group.name}</option>
                    </c:forEach>
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
