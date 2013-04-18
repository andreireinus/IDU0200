<%@ page import="idu0200.kliendid.model.Customer" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	List<Customer> customerList = (List<Customer>)request.getAttribute("customerList");
%>
<div class="container">
	<p>
		<a class="btn btn-primary" href="r?c=customer&a=new">Lisa uus</a>
	</p>
	<table class="table table-condensed">
		<thead>
			<tr>
				<th>Eesnimi</th>
				<th>Perenimi</th>
				<th>S&uuml;nniaeg</th>
				<th>Viimati muudetud</th>
				<th></th>
			</tr>
		    <%
				for (Customer customer : customerList) {
					%>
			<tr>
				<td><%=customer.getFirstName()%></td>
				<td><%=customer.getLastName()%></td>
				<td><%=customer.getBirthDate()%></td>
				<td><%=customer.getUpdated()%></td>
				<td>
					<a href="r?c=customer&a=view&id=<%=customer.getId()%>" class="btn btn-small">Vaata</a>
				</td>
			</tr>
			        <%
				}
		    %>
		</thead>
	</table>

</div>