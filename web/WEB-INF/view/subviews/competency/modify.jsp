<%-- 
    Document   : modify
    Created on : 17-May-2014, 19:12:54
    Author     : wilco
--%>

<h1>${competency.name}</h1>


<div id="description">
    <h13>Wijzig de competentie door onderstaande gegevens aan te passen.<h13>  
</div>
<form method="post" action="${context}/competency/manage" target="_top">
    <input type="hidden" name="competency" value="${competency.id}">
    <div id="title_profile_manage">
        <h7>Competentie</h7>
    </div>
    <div id="inputs_profile_manage">
        <input class="field_profile_manage" name="name" type="text" placeholder="Naam" value='${competency.name}' autofocus required>
    </div>
    <div class="error">${messages.error}<br/>
    </div>
    <input type="submit" name="modify" value="Opslaan" />
</form>
<a href = "javascript:void(0)" onclick = "document.getElementById('light').style.display = 'block';
        document.getElementById('fade').style.display = 'block'">
    <div id="remove_competency"></div>
</a>
<div id="light" class="remove_competency">
    <h1>Verwijder "${competency.name}"</h1>
    <div id="description">
        <h12>Weet u zeker dat u de competentie "${competency.name}" wilt verwijderen?</h12>
    </div>
    <a href = "javascript:void(0)" onclick = "document.getElementById('light').style.display = 'none';
            document.getElementById('fade').style.display = 'none'">
        <h10>Annuleren</h10>
    </a>
    <form method="post" target="_top" action="${context}/competency/manage">
        <input type="hidden" name="competency" value="${competency.id}">
        <input class="competency_submit" type="submit" name="delete" value="Verwijderen" />
    </form>
    <div id="remove_competency_arrow">
    </div>
</div>