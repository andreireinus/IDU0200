<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
	angular.module('App').value('CurrentObjectId', '<%=request.getParameter("id")%>');
</script>

<div class="container" ng-controller="CustomerView">
	<ul class="nav nav-tabs">
		<li ng-repeat="item in tabItems" ng-class="getTabCssClass(item)">
			<a href="javascript:void(0);" ng-click="setActiveTab(item)" ng-bind="item.text"></a>
		</li>
		<li>
			<a href="r?c=customer&a=change&id=<%=request.getParameter("id")%>" class="button">Muuda</a>
		</li>
	</ul>
	<div ng-show="currentTab.type == 'customerMain'">
		CustomerMain
	</div>
	<div ng-show="currentTab.type == 'addressList'">
		<div style="width: 50%; margin: auto;">
			<table class="table table-condensed">
				<caption>P&otilde;hiaadress</caption>
				<tbody>
				<tr>
					<th>Maakond</th>
					<td ng-bind="primaryAddress.county"></td>
				</tr>
				<tr>
					<th>Linn</th>
					<td ng-bind="primaryAddress.townCounty"></td>
				</tr>
				<tr>
					<th>Address</th>
					<td>asdfasd</td>
				</tr>
				</tbody>
			</table>
		</div>

		<table class="table table-condensed">
			<thead>
			<tr>
				<th>Maakond</th>
				<th>Linn</th>
				<th>Address</th>
				<th></th>
			</tr>
			</thead>
			<tbody>
			<tr ng-repeat="address in addresses">
				<td ng-bind="address.county"></td>
				<td ng-bind="address.townCounty"></td>
				<td ng-bind="formatAddress(address)"></td>
				<td>
					<button class="btn" ng-click="setPrimaryAddress(address)" ng-show="address.addressType != 'Primary'">M&auml;&auml;ra primaarseks</button>
					<button class="btn" ng-click="openAddressChange(address)">Muuda</button>
				</td>
			</tr>
			</tbody>
		</table>
	</div>
	<div ng-show="currentTab.type == 'groupsList'">
		<table class="table table-condensed">
			<thead>
			<tr>
				<th></th>
				<th></th>
				<th></th>
			</tr>
			</thead>
			<tbody>
			<tr ng-repeat="group in groupDefinitionList">
				<td style="width:1%; padding-right: 10px;">
					<input type="checkbox" ng-model="group.isSelected" ng-change="changeGroup(group)">
				</td>
				<td ng-bind="group.name"></td>
				<td></td>
			</tr>
			<tr>
				<td></td>
				<td>
					<div class="control-group">
						<input type="text" ng-model="newGroup.name"/>
						<button class="btn" ng-click="addGroup(newGroup)">Lisa</button>
					</div>
				</td>
				<td>

				</td>
			</tr>
			</tbody>
		</table>
	</div>
	<div ng-show="currentTab.type == 'devicesList'">
		<table class="table table-condensed">
			<thead>
			<tr>
				<th>Liik</th>
				<th>Informatsioon</th>
				<th>Järjekord</th>
				<th>
					<button class="btn" ng-click="addNewDevice()">Lisa</button>
				</th>
			</tr>
			</thead>
			<tbody>
			<tr ng-repeat="device in devices">
				<td ng-bind="device.type.name" ng-hide="device.$inEditMode"></td>
				<td ng-show="device.$inEditMode">
					<select ng-model="device.type.id" ng-options="o.id as o.name for o in deviceTypes">
						<%--<option ng-repeat="type in deviceTypes" value="{{type.id}}">{{type.name}}</option>--%>
					</select>
				</td>
				<td ng-bind="device.valueText" ng-hide="device.$inEditMode"></td>
				<td ng-show="device.$inEditMode">
					<input type="text" ng-model="device.valueText"/>
				</td>
				<td ng-bind="device.orderBy" ng-hide="device.$inEditMode"></td>
				<td ng-show="device.$inEditMode">
					<input type="number" ng-model="device.orderBy"/>
				</td>
				<td>
					<button class="btn" ng-click="updateDevice(device)">Muuda</button>
				</td>
			</tr>
			</tbody>
		</table>
	</div>
</div>