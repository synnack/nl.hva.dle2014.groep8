<%-- 
    Document   : list_lectures_documents
    Created on : 19-Apr-2014, 20:04:53
    Author     : wilco
--%>

<h1>${course.name}</h1>

<div id="description">
    <h13>Hieronder ziet u de informatie die benodigd is bij de cursus ${course.name}. 
        De documenten zijn te vinden in de filebrowser hieronder. 
        Er kunnen documenten gedownload en geupload worden door middel van de knoppen "Upload" en "Download".
    </h13>  
</div>
<div id="chat_start_container">
    <br>
    <br>
    <h15>hier komt de knop om de chat in een nieuw window te openen en deel te nemen</h15>
</div>
<div id="file_browser_container">
    <div id="file_browser">
        <div id="document_header">
            <h17>Documentnaam</h17>
        </div>
        <div id="document_item">
            <h18>javascript_bla_bla.docx</h18>
        </div>
        <div id="document_item">
            <h18>objective_c_bla.docx</h18>
        </div>
        <div id="document_item">
            <h18>kale_poes.jpg</h18>
        </div>
    </div>
</div>
<a href = "javascript:void(0)" onclick = "document.getElementById('light').style.display = 'block';
                document.getElementById('fade').style.display = 'block'"><div id="remove_competency">       
    </div>
</a>
<div id="light" class="remove_competency">
    <h1>Verwijder ${course.name}</h1>
    <div id="description">
        <h12>Weet u zeker dat u uzelf uit wilt scrhijven van ${course.name}?</h12>
    </div>
    <a href = "javascript:void(0)" onclick = "document.getElementById('light').style.display = 'none';
            document.getElementById('fade').style.display = 'none'">
        <h10>Annuleren</h10>
    </a>
    <input class="competency_submit" type="submit" name="submit" value="Verwijderen" />
    <div id="remove_competency_arrow">
    </div>
</div>