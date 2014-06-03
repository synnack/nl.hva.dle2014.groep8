<%-- 
    Document   : list_lectures_documents
    Created on : 19-Apr-2014, 20:04:53
    Author     : wilco
--%>

<h1>${course.name}</h1>

</div>
<div id="file_browser_container">

    <div id="file_browser">
        <div id="document_header">
            <h17>Colleges</h17>
            <a href = "javascript:void(0)" onclick = "document.getElementById('light1').style.display = 'block';
                    document.getElementById('fade').style.display = 'block'"><div id="text" class="right_align"><h17>College aanmaken</h17></div>
            </a>
        </div>
        <c:forEach var="lecture" items="${lectures}">
            <div id="document_item">
                <h18>${lecture.name}</h18>
                <div id="remove_icon">
                </div>
                <div id="join_icon">
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<div id="file_browser_container">
    <div id="file_browser">
        <div id="document_header">
            <h17>Documenten</h17>
            <a href = "javascript:void(0)" onclick = "document.getElementById('light2').style.display = 'block';
                    document.getElementById('fade').style.display = 'block'"><div id="text" class="right_align"><h17>Document uploaden</h17></div>
            </a>
        </div>
        <c:forEach var="document" items="${documents}">
            <div id="document_item">
                <h18>${document.name}</a></h18>
                <div id="remove_icon">
                </div>
                <a href="${context}/document/${document.id}">
                    <div id="download_icon">
                    </div>
                </a>
            </div>

        </c:forEach>
    </div>
</div>
<a href = "javascript:void(0)" onclick = "document.getElementById('light').style.display = 'block';
        document.getElementById('fade').style.display = 'block'"><div id="remove_competency">       
    </div>
</a>
<div id="light" class="remove_competency">
    <h1>Verwijder ${course.name}</h1>
    <div id="description">
        <h12>Weet u zeker dat u de cursus &quot;${course.name}&quot; wilt verwijderen?</h12>
    </div>
    <a href = "javascript:void(0)" onclick = "document.getElementById('light').style.display = 'none';
            document.getElementById('fade').style.display = 'none'">
        <h10>Annuleren</h10>
    </a>
    <input class="competency_submit" type="submit" name="submit" value="Verwijderen" />
    <div id="remove_competency_arrow">
    </div>
</div>
<div id="light1" class="create_lecture">
    <h1>Nieuwe college</h1>
    <div id="description">
        <h12>Vul de onderstaande velden in om een nieuwe college aan te maken.</h12>
        <input class="field_lecture" name="lecture_name" type="text" placeholder="Omschrijving" autofocus required>
        <input class="field_lecture" name="lecture_date" type="text" placeholder="Dag (dd-mm-jj)" autofocus required>
        <input class="field_lecture" name="lecture_from" type="text" placeholder="Van (uu:mm)" autofocus required>
        <input class="field_lecture" name="lecture_to" type="text" placeholder="Tot (uu:mm)" autofocus required>
    </div>
    <a href = "javascript:void(0)" onclick = "document.getElementById('light1').style.display = 'none';
            document.getElementById('fade').style.display = 'none'">
        <h10>Annuleren</h10>
    </a>
    <input class="competency_submit" type="submit" name="submit" value="Aanmaken" />
    <div id="create_lecture_arrow">
    </div>
</div>
<div id="light2" class="upload_document">
    <h1>Upload document</h1>
    <div id="description">
        <h12>Selecteer de upload knop om een bestand te kiezen.</h12>
        <input class="field_lecture" name="lecture_name" type="file" autofocus required>
    </div>
    <a href = "javascript:void(0)" onclick = "document.getElementById('light2').style.display = 'none';
            document.getElementById('fade').style.display = 'none'">
        <h10>Annuleren</h10>
    </a>
    <input class="competency_submit" type="submit" name="submit" value="Uploaden" />
    <div id="upload_document_arrow">
    </div>
</div>