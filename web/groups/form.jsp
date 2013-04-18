<%@ page import="idu0200.kliendid.model.GroupDefinition" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	GroupDefinition item = (GroupDefinition)request.getAttribute("item");
%>
<div class="container">
	<form class="form-horizontal" method="post" action="r">
		<input type="hidden" name="c" value="groups" />
		<input type="hidden" name="a" value="submit" />
		<input type="hidden" name="id" value="<%=item.getId()%>"/>

		<div class="control-group">
			<label class="control-label" for="name">Nimi</label>
			<div class="controls">
				<input type="text" id="name" name="name" value="<%=item.getName()%>">
			</div>
		</div>

		<div class="form-actions">
			<button type="submit" class="btn btn-primary">Salvesta</button>
			<a href="r?c=groups&a=index" class="btn">Katkesta</a>
		</div>
	</form>

	<script type="text/javascript">
		angular.module('App').value('CurrentObjectId', '<%=item.getId()%>');
	</script>

	<table ng-controller="CustomerList" class="table">
		<thead></thead>
		<tbody>
			<tr ng-repeat="customer in customers">
				<td ng-bind="customer.firstName"></td>
				<td ng-bind="customer.lastName"></td>
				<td>
					<a href="javascript:void(0);" ng-click="navigateCustomer(customer)">Vaata</a>
				</td>
			</tr>
		</tbody>
	</table>

</div>