<%-- 
    Document   : modify
    Created on : 17-May-2014, 14:11:22
    Author     : wilco
--%>


<h1>${usercompetency.competency.name}</h1>
${usercompetency.skillLevel}
<a href = "javascript:void(0)" onclick = "document.getElementById('light').style.display = 'block';
        document.getElementById('fade').style.display = 'block'"><div id="remove_competency">       
    </div>
</a>

<div id="light" class="remove_competency">
    <h1>Verwijder "${usercompetency.competency.name}"</h1>
    <div id="description">
        <h12>Weet u zeker dat u de competentie "${usercompetency.competency.name}" wilt verwijderen?</h12>
    </div>
    <a href = "javascript:void(0)" onclick = "document.getElementById('light').style.display = 'none';
            document.getElementById('fade').style.display = 'none'">
        <h10>Annuleren</h10>
    </a>
    <input class="competency_submit" type="submit" name="submit" value="Verwijderen" />
    <div id="remove_competency_arrow">
    </div>
</div>