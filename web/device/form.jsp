<%@ page import="idu0200.kliendid.form.CommunicationDeviceForm" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	CommunicationDeviceForm form = (CommunicationDeviceForm)request.getAttribute("form");
%>
<div class="container">
	<%=form.toString()%>
</div>