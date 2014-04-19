<%@page import="entity.User"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true" %>

<%-- 
    Document   : home
    Created on : 06-Apr-2014, 00:36:45
    Author     : wilco
--%>
<h2>Teach 'n' Learn | Home</h2>
<table id="tile_table">
    <tr>
        <td><div class="tile"><a href="myprofile"><img alt="" src="images/home/profile.png"><h3>Profiel</h3></a></div></td>
        <td><div class="tile"><a href="mycompetencies"><img alt="" src="images/home/competencies.png"><h3>Competencies</h3></a></div></td>
        <td><div class="tile"><a href="mycourses"><img class="tile" alt="" src="images/home/courses.png"><h3>Cursussen</h3></a></div></td>
        <td><div class="tile"><a href="schedules"><img class="tile" alt="" src="images/home/schedules.png"><h3>Planning</h3></a></div></td>
        <td><div class="tile"><a href="manage"><img class="tile" alt="" src="images/home/manage.png"><h3>Beheren</h3></a></div></td>

    </tr>
    <tr>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
    </tr>
</table>