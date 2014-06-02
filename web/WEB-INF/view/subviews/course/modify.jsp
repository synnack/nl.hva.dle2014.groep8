<%-- 
    Document   : list_lectures_documents
    Created on : 19-Apr-2014, 20:04:53
    Author     : wilco
--%>

<h1>${course.name}</h1>

<div id="description">
    <h13>Hieronder ziet u de informatie die benodigd is bij de cursus ${course.name}. 
        De documenten zijn te vinden in de lijst hieronder. 
        Er kunnen documenten gedownload worden door middel van de download knop naast het document.
    </h13>  
</div>
<div id="file_browser_container">
    <div id="file_browser">
        <div id="document_header">
            <h17>Colleges</h17>
        </div>
        <c:forEach var="lecture" items="${lectures}">
            <div id="document_item">
                <h18>${lecture.name}</h18>
                <div id="remove_icon">
                </div>
                <div id="download_icon">
                </div>
            </div>
        </c:forEach>
    </div>
</div><div id="file_browser_container">
    <div id="file_browser">
        <div id="document_header">
            <h17>Documenten</h17>
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
