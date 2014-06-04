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
        </div>
        <c:forEach var="lecture" items="${lectures}">
            <div id="document_item">
                <h18>${lecture.name}</h18>
                <div id="join_icon" onclick="window.open('${context}/course/chat','TnL Chat', 'width=825,height=935,innerHeight=935,scrollbars=no,toolbar=no,location=no'); return false" >
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<div id="file_browser_container">
    <div id="file_browser">
        <div id="document_header">
            <h17>Documenten</h17>
        </div>
        <c:forEach var="document" items="${documents}">
            <div id="document_item">
                <h18>${document.name}</a></h18>
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
        <h12>Weet u zeker dat u zichzelf wilt uitschrijven van ${course.name}?</h12>
    </div>
    <a href = "javascript:void(0)" onclick = "document.getElementById('light').style.display = 'none';
            document.getElementById('fade').style.display = 'none'">
        <h10>Annuleren</h10>
    </a>
    <form method="post" action="${context}/user/courses" target="_top">
    <input type="hidden" name="course" value="${course.id}">
    <input class="competency_submit" type="submit" name="delete" value="Verwijderen" />
    <div id="remove_competency_arrow">
    </div>
    </form>
</div>