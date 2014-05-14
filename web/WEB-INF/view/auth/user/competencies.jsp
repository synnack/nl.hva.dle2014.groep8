<%-- 
    Document   : comptencies
    Created on : 20-Apr-2014, 15:06:23
    Author     : wilco
--%>

<h2><a href="${context}/home" class="title">Teach 'n' Learn</a> | Mijn competenties</h2>

<div id="main_container">
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
            <div id="menu_item">
                x
            </div>
            <div id="menu_item">
                x
            </div>
            <div id="menu_item">
                x
            </div>
            <div id="menu_item">
                x
            </div>
            <div id="menu_item">
                x
            </div>
            <div id="menu_item">
                x
            </div>
            <div id="menu_item">
                x
            </div>
            <div id="menu_item">
                x
            </div>
            <div id="menu_item">
                x
            </div>
            <div id="menu_item">
                x
            </div>
            <div id="menu_item">
                x
            </div>
            <div id="menu_item">
                x
            </div>            
            <div id="menu_item">
                x
            </div>
            <div id="menu_item">
                x
            </div>
            <div id="menu_item">
                x
            </div>
            <div id="menu_item">
                x
            </div>
            <div id="menu_item">
                x
            </div>
            <div id="menu_item">
                x
            </div>
            <div id="menu_item">
                x
            </div>
            <div id="menu_item">
                x
            </div>
            <div id="menu_item">
                x
            </div>
            <div id="menu_item">
                x
            </div>
            <div id="menu_item">
                x
            </div>
            <div id="menu_item">
                x
            </div>
        </div>
        <div id="content_container">
            <form method="post" action="${context}/user/competencies">
                <input class="field_profile" name="given_name" type="text" placeholder="Voornaam" value='<%=request.getAttribute("given_name")%>' autofocus required>
                <input class="field_profile" name="surname" type="text" placeholder="Achternaam" value='<%=request.getAttribute("surname")%>' required>
                <input class="field_profile" name="email" type="text" placeholder="E-mail" value='<%=request.getAttribute("email")%>' required>
            </form>
        </div>
    </div>
</div>
