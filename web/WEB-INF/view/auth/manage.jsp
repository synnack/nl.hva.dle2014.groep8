<%-- 
    Document   : home
    Created on : 06-Apr-2014, 00:36:45
    Author     : wilco
--%>
<h2><a href="${context}/home" class="title">Teach 'n' Learn</a> | Beheren</h2>
<%@ page session="true" %>

<table id="tile_table">
    <tr>
        <td><div class="tile"><a href="${context}/user/manage"><img alt="" src="${context}/images/home/users.png"><h3>Gebruikers</h3></a></div></td>
        <td><div class="tile"><a href="${context}/course/manage"><img class="tile" alt="" src="${context}/images/home/courses_manage.png"><h3>Cursussen</h3></a></div></td>
        <td><div class="tile"><a href="${context}/competency/manage"><img class="tile" alt="" src="${context}/images/home/competencies_manage.png"><h3>Competenties</h3></a></div></td>
        <td><div class="tile"><a href="${context}/group/manage"><img alt="" src="${context}/images/home/groups.png"><h3>Groepen</h3></a></div></td>
        <td><div class="tile"><a href="${context}/home"><img class="tile" alt="" src="${context}/images/home/home.png"><h3>Home</h3></a></div></td>
    </tr>
    <tr>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
    </tr>
</table>
