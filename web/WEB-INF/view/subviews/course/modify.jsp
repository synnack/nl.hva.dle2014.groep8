<%-- 
    Document   : list_lectures_documents
    Created on : 19-Apr-2014, 20:04:53
    Author     : wilco
--%>

<h1>${course.name}</h1>
<div id="description">
    <h13>Hieronder vind je de benodigde informatie voor de geselecteerde cursus.</h13>
</div>
<div id="file_browser_container">
    <div id="file_browser">
        <div id="document_header">
            <h17>Colleges</h17>
            <a href = "javascript:void(0)" onclick = "document.getElementById('light1').style.display = 'block';
                    document.getElementById('fade').style.display = 'block'"><div id="text" class="right_align"><submit2>College aanmaken</submit2></div>
            </a>
        </div>
        <c:forEach var="lecture" items="${lectures}">
            <div id="document_item">
                <h18>${lecture.name}</h18>
                <a href="${context}/course/modify/${course.id}?remove_lecture=${lecture.id}">
                    <div id="remove_icon">
                    </div>
                </a>
                <div id="join_icon" onclick="window.open('${context}/course/lecture/${lecture.id}','TnL Chat', 'width=825,height=760,innerHeight=760,scrollbars=no,toolbar=no,location=no'); return false" >
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
                    document.getElementById('fade').style.display = 'block'"><div id="text" class="right_align"><submit2>Document uploaden</submit2></div>
            </a>
        </div>
        <c:forEach var="document" items="${documents}">
            <div id="document_item">
                <h18>${document.name}</a></h18>
                <a href="${context}/course/modify/${course.id}?remove_document=${document.id}">
                    <div id="remove_icon">
                    </div>
                </a>
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
    <input class="competency_submit" type="submit" name="delete" value="Verwijderen" />
    <div id="remove_competency_arrow">
    </div>
</div>
<div id="light1" class="create_lecture">
    <form method="POST" action="${context}/course/modify/${course.id}">
        <h1>Nieuwe college</h1>
        <div id="description">
            <h12>Vul de onderstaande velden in om een nieuwe college aan te maken.</h12>
            <input class="field_lecture" name="name" type="text" placeholder="Omschrijving" autofocus required>
            <input class="field_lecture" name="date" type="text" placeholder="Dag (dd-mm-jjjj)" required>
            <input class="field_lecture" name="start_time" type="text" placeholder="Van (uu:mm)" required>
            <input class="field_lecture" name="end_time" type="text" placeholder="Tot (uu:mm)" required>
        </div>
        <a href = "javascript:void(0)" onclick = "document.getElementById('light1').style.display = 'none';
                document.getElementById('fade').style.display = 'none'">
            <h10>Annuleren</h10>
        </a>
        <input class="competency_submit" type="submit" name="create_lecture" value="Aanmaken" />
        <div id="create_lecture_arrow">
        </div>
    </form>
</div>
<div id="light2" class="upload_document">
    <form method="post" enctype="multipart/form-data" action="${context}/course/modify/${course.id}">
        <h1>Upload document</h1>
        <div id="description">
            <h12>Selecteer de upload knop om een bestand te kiezen.</h12>
        </div>
        <div id="upload_file">
            <input id="upload_file_input" name="file" type="file" required>
        </div>
        <a href = "javascript:void(0)" onclick = "document.getElementById('light2').style.display = 'none';
                document.getElementById('fade').style.display = 'none'">
            <h10>Annuleren</h10>
        </a>
        <input class="competency_submit" type="submit" name="upload_document" value="Uploaden" />
        <div id="upload_document_arrow">
        </div>
    </form>
</div>