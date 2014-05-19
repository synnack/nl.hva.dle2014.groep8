<%-- 
    Document   : modify
    Created on : 17-May-2014, 14:11:22
    Author     : wilco
--%>


<h1>${usercompetency.competency.name}</h1>
<h13>Hieronder ziet u het huidige niveau van de geselecteerde competentie. Het niveau is een getal tussen 1 en 10, waarvan 1 het laagst en 10 het hoogst.</h13>    
<h14>${usercompetency.skillLevel}</h14>
<div id="competency_description">
    <h13>In het veld hieronder kunt u het niveau aanpassen. U kunt kiezen uit één getal van 1 tot en met 10. Druk vervolgens op opslaan.</h13>
    <div id="inputs_competency">
        <div id="dropdown_edit_competency" class="dropdown_edit_competency">
            <select name="skill_level">
                <option>Niveau</option>
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
            </div><input class="competency_submit" type="submit" name="submit" value="Opslaan" />
    </div>
</div>
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