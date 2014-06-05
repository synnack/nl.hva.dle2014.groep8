<%-- 
    Document   : modify
    Created on : 17-May-2014, 14:11:22
    Author     : wilco
--%>


<h1>${group.name}</h1>
<h13>Rechtsbovenin kunt u uzelf uitschrijven uit deze groep.</h13>    
<a href = "javascript:void(0)" onclick = "document.getElementById('light').style.display = 'block';
        document.getElementById('fade').style.display = 'block'"><div id="remove_competency">       
    </div>
</a>
<div id="light" class="remove_competency">
    <h1>Uitschrijven uit "${group.name}"</h1>
    <div id="description">
        <h12>Weet u zeker dat u uzelf uit "${group.name}" wilt verwijderen?</h12>
    </div>
    <a href = "javascript:void(0)" onclick = "document.getElementById('light').style.display = 'none';
            document.getElementById('fade').style.display = 'none'">
        <h10>Annuleren</h10>
    </a>
    <form method="post" target="_top" action="${context}/user/groups">
        <input type="hidden" name="group" value="${group.id}">
        <input class="competency_submit" type="submit" name="delete" value="Verwijderen" />
    </form>
    <div id="remove_competency_arrow">
    </div>
</div>
