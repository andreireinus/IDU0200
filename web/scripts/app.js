angular.module('App', []).
	config(function ($locationProvider) {
		$locationProvider.html5Mode(true);
	}).
	controller('CustomerList',function ($scope, CurrentObjectId, CustomersData) {
		console.log("customerList", CurrentObjectId);

		$scope.navigateCustomer = function (customer) {
			window.location.href = "r?c=customer&a=view&id=" + customer.id;
		};
		CustomersData.getByGroupDefinitionId(CurrentObjectId, function (data) {
			console.log(data);
			$scope.customers = data;
		});
	}).
	controller('CustomerView',function ($scope, CurrentObjectId, CustomersData, DevicesData) {
		$scope.tabItems = [
			{type: 'customerMain', text: 'Põhiandmed', active: false, visible: true},
			{type: 'addressList', text: 'Aadressid', active: true, visible: true},
			{type: 'groupsList', text: 'Grupid', active: false, visible: true},
			{type: 'devicesList', text: 'Sidevahendid', active: false, visible: true},
			{type: 'groupsAdd', active: true, visible: false}
		];

		// Set CSS
		$scope.getTabCssClass = function (item) {
			var css = [];
			if ($scope.currentTab.type == item.type) {
				css.push('active');
			}
			return css;
		};
		$scope.setActiveTab = function (item) {
			for (var i = 0; i < $scope.tabItems.length; i++) {
				$scope.tabItems[i].active = false;
			}

			$scope.currentTab = item;
			item.active = true;
		};
		$scope.setActiveTab($scope.tabItems[1]);

		/// Devices
		DevicesData.getDeviceTypeList(function (data) {
			$scope.deviceTypes = data;
		});

		$scope.loadDeviceList = function() {
			DevicesData.getCustomerDevices(CurrentObjectId, function (data) {
				for (var i = 0; i < data.length; i++) {
					data[i]['$inEditMode'] = false;
					data[i]['$isNew'] = false;
				}
				$scope.devices = data;
			});
		};
		$scope.addNewDevice = function () {
			var item;
			for (var index in $scope.devices) {
				item = $scope.devices[index];
				if (item.$isNew) {
					return;
				}
			}

			var device = {
				'$inEditMode': true,
				'$isNew': true,
				valueText: '',
				orderBy: 1,
				customer: {
					id: CurrentObjectId
				},
				type: {
					id: 0
				}
			};
			$scope.devices.unshift(device);
		};
		$scope.updateDevice = function (device) {
			if (device.$inEditMode) {
				DevicesData.update(device,function(result) {
					if (!result.isError) {
						$scope.loadDeviceList();
					}
				});
			} else {
				for (var index in $scope.devices) {
					$scope.devices[index].$inEditMode = false;
				}
				device.$inEditMode = true;
			}
		};
		$scope.loadDeviceList();

		// Groups
		$scope.newGroup = {
			name: ''
		};
		$scope.loadGroupsList = function() {
			CustomersData.getGroupDefinitionList(function(data) {
				for (var i = 0; i < data.length; i++) {
					data[i].isSelected = false;
				}
				$scope.groupDefinitionList = data;
				CustomersData.getGroupsList(CurrentObjectId, function (data) {
					console.log(data);
					for (var i = 0; i < $scope.groupDefinitionList.length; i++) {

						for (var j = 0; j < data.length; j++) {
							if (data[j].definition.id == $scope.groupDefinitionList[i].id) {
								$scope.groupDefinitionList[i].isSelected = true;
								break;
							}
						}
					}
				});
			});
		};
		$scope.changeGroup = function(group) {
			CustomersData.toggleCustomerGroup(CurrentObjectId, group.id, function(){
				$scope.loadGroupsList();
			});
		};
		$scope.addGroup = function(group) {
			CustomersData.addGroup(CurrentObjectId, group.name, function() {
				$scope.newGroup.name = ''
				$scope.loadGroupsList();
			});
		};
		$scope.loadGroupsList();

		// Addresses
		$scope.loadAddresses = function() {
			CustomersData.getAddressList(CurrentObjectId, function(data) {
				$scope.primaryAddress = data[0];
				$scope.addresses = data[1];
			});
		};
		$scope.formatAddress = function(address) {
			return address.address + " " + address.house + ", " + address.county + ", " + address.zip;
		};
		$scope.setPrimaryAddress = function(address) {
			CustomersData.setPrimaryAddress(CurrentObjectId, address.id, function(data){
				$scope.primaryAddress = data[0];
				$scope.addresses = data[1];
			});
		};
		$scope.loadAddresses();



	}).
	factory('CustomersData',function ($http) {
		return {
			post: function (params, callback) {
				return $http.post("ajax", params).success(function (data) {
					callback(data);
				});
			},
			getByType: function (type, value, callback) {
				var params = {
					'c': 'customers',
					'f': type,
					'v': value
				};
				return $http.post("ajax", params).success(function (data) {
					return typeof callback === "function" ? callback(data) : void 0;
				})
			},
			getByGroupDefinitionId: function (definitionId, callback) {
				return this.getByType('group', definitionId, callback);
			},
			getGroupDefinitionList: function (callback) {
				var params = {
					c: 'groups',
					f: 'groupsList'
				};
				return this.post(params, callback);
			},
			getGroupsList: function (id, callback) {
				var params = {
					c: 'groups',
					f: 'customerGroups',
					v: id
				};

				return this.post(params, callback);
			},
			toggleCustomerGroup: function (customerId, groupId, callback) {
				var params = {
					c: 'groups',
					f: 'toggleGroup',
					v: customerId,
					g: groupId
				};
				return this.post(params, callback);
			},
			addGroup: function(customerId, name, callback) {
				var params = {
					c: 'groups',
					f: 'addGroup',
					v: customerId,
					n: name
				};
				return this.post(params, callback);
			},
			getAddressList: function(customerId, callback) {
				var params = {
					c: 'address',
					f: 'addressList',
					v: customerId
				};
				return this.post(params, callback);
			},
			setPrimaryAddress: function(customerId, addressId, callback) {
				var params = {
					c: 'address',
					f: 'setPrimary',
					v: customerId,
					a: addressId
				};
				return this.post(params, callback);

			}

		};
	}).
	factory('DevicesData', function ($http) {
		return {
			post: function (params, callback) {
				return $http.post("ajax", params).success(function (data) {
					callback(data);
				});
			},
			update: function(device, callback) {
				var params = {
					c: 'devices',
					f: 'update',
					id: device.id,
					typeId: device.type.id,
					valueText: device.valueText,
					orderBy: device.orderBy
				};
				return this.post({c: 'devices', f: 'update', o: device}, callback);
			},
			getDeviceTypeList: function (callback) {
				return this.post({ c: 'devices', f: 'typeList' }, callback);
			},
			getCustomerDevices: function (id, callback) {
				return this.post({ c: 'devices', f: 'customerList', v: id }, callback);
			}
		};
	});