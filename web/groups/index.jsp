<%@ page import="idu0200.kliendid.model.GroupDefinition" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	List<GroupDefinition> groupDefinitionList = (List<GroupDefinition>)request.getAttribute("groups");
%>
<div class="container">
	<p>
		<a class="btn btn-primary" href="r?c=groups&a=new">Lisa uus</a>
	</p>
	<table class="table table-condensed">
		<thead>
		<tr>
			<th>Nimetus</th>
			<th></th>
		</tr>
		<%
			for (GroupDefinition definition : groupDefinitionList) {
		%>
		<tr>
			<td><%=definition.getName()%></td>
			<td>
				<a href="r?c=groups&a=view&id=<%=definition.getId()%>" class="btn btn-small">Vaata</a>
			</td>
		</tr>
		<%
			}
		%>
		</thead>
	</table>

</div>