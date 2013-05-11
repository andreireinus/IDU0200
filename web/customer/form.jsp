<%@ page import="idu0200.kliendid.form.CustomerForm" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	CustomerForm form = (CustomerForm)request.getAttribute("form");
%>
<div class="container">
	<%=form.toString()%>
</div>