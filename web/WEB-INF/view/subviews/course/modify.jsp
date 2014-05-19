<%-- 
    Document   : list_lectures_documents
    Created on : 19-Apr-2014, 20:04:53
    Author     : wilco
--%>

<h1><c:out value="${course.name}"/></h1>
${course.id}
<form method="post">
    
    <label for="name">Name</label>
    <input type="text" name="name" value="${course.name}" />
    
    <select name="group_id">
        <c:forEach var="group" items="${groups}">
            <option value="${group.id}" ${group.id==course.managingGroup.id ? 'selected="selected"': ''}><c:out value="${group.name}" /></option>
        </c:forEach>
    </select>
    
    <input type="submit" value="Verzend" />
</form>