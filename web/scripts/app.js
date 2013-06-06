/*
- veateated
- clipboard
- search
- sisse loginud kasutaja nimi


- klassidiagrammid
- diagrammid
-

 */

angular.module('App', []).
	config(function ($routeProvider, $locationProvider) {
		$locationProvider.html5Mode(false);

		$routeProvider
			.when("/", { controller: 'CustomerList', templateUrl: 'customer/list.html'})
			.when("/add/customer", { controller: 'CustomerAdd', templateUrl: 'customer/add.html'})
			.when("/add/address/:customerId", { controller: 'AddressAdd', templateUrl: 'address/add.html'})
			.when("/add/device/:customerId", { controller: 'DeviceAdd', templateUrl: 'device/form.html'})
			.when("/edit/address/:id", { controller: 'AddressEdit', templateUrl: 'address/add.html'})
			.when("/edit/device/:id", { controller: 'DeviceEdit', templateUrl: 'device/form.html'})
			.when("/customer", { controller: 'CustomerList', templateUrl: 'customer/list.html'})
			.when("/customer/:id", { controller: 'Customer', templateUrl: 'customer/view.html'})
			.when("/customer/:id/:tab", { controller: 'Customer', templateUrl: 'customer/view.html'})
			.when("/clipboard", { controller: 'Clipboard', templateUrl: 'clipboard.html'})
			.when("/auth", { controller: 'Auth', templateUrl: 'auth/login.html'})
			.when("/search/:term", { controller: 'Search', templateUrl: 'customer/search.html'})
			.otherwise({redirectTo: '/auth'});
	}).
	controller('Main',function ($scope, $location, UserData, CustomerData, DevicesData, AddressData, GroupData) {
		$scope.search = {
			term: ''
		};
		$scope.doSearch = function(search) {
			if (search.term.length == 0) {
				return;
			}
			$location.url("search/" + search.term);
		};
		$scope.requireLogin = function () {
			UserData.isLoggedIn(function (result) {
				if (!result || result == "false") {
					$location.url("auth");
				}
			});
		};
		$scope.logout = function () {
			UserData.logout(function () {
				$scope.User = null;
				$scope.requireLogin();
			});
		};
		$scope.navigate = function(type) {
			$location.url(type);
		}
		$scope.getFormFieldClass = function (fieldId, object, errors) {
			var css = ["control-group"];
			if (errors != null && errors[fieldId]) {
				css.push("error")
			}
			return css;
		};
		$scope.delete = function (type, id, callback) {
			if (!confirm("Kindel?")) {
				return;
			}

			if (type == 'customer') {
				CustomerData.delete(id, callback);
			} else if (type == 'device') {
				DevicesData.delete(id, callback);
			} else if (type == 'address') {
				AddressData.delete(id, callback);
			} else if (type == 'groupDef') {
				GroupData.deleteDefinition(id, callback);
			}
		};
		$scope.formatDate = function(date) {
			if (!date) {
				return "";
			}
			if (!(date instanceof Date)) {
				date = new Date(date);
			}

			return $scope.formatNumber(date.getUTCDate())+"."+$scope.formatNumber(date.getUTCMonth()+1) + "." + date.getUTCFullYear();
		};
		$scope.formatDateInput = function(date) {
			if (!date) {
				return "";
			}
			if (!(date instanceof Date)) {
				date = new Date(date);
			}
			return date.getUTCFullYear()+"-"+$scope.formatNumber(date.getUTCMonth()+1) + "-" + $scope.formatNumber(date.getUTCDate());
		};
		$scope.formatNumber = function(n) {
			if (n >= 0 && n <= 9) {
				return "0" + n.toString();
			}
			return n.toString();
		};
		$scope.formatAddress = function (a) {
			if (a) {
				return a.address + ", " + a.house;
			}
			return "";
		};
		$scope.clipboardAddress = null;
		$scope.copyClipboard = function(address) {
			$scope.clipboardAddress = address;
		};
		$scope.navigateView = function (customer) {
			$location.url("customer/" + customer.id);
		};

	}).
	controller('Search', function ($scope, $routeParams, $location, CustomerData) {
		$scope.search.term = $routeParams.term;
		$scope.loading = true;
		$scope.results = [];

		CustomerData.search($routeParams.term, function(results){
			$scope.results = results;
			$scope.loading = false;
		});

		console.log("search", $routeParams);
	}).
	controller('Auth',function ($scope, $rootScope, $location, UserData) {
		$scope.user = {
			username: 'andrei',
			password: 'suva'
		};

		$scope.authUser = function (user) {
			$scope.user = angular.copy(user);

			UserData.auth(user, function (result) {
				if (!result.isError) {
					$rootScope.User = result;
					$location.url("customer");
					return;
				}
			});
		};
	}).
	controller('Clipboard', function ($scope) {
		$scope.requireLogin();


	}).
	controller('CustomerList',function ($scope, $location, CustomerData) {
		$scope.requireLogin();

		$scope.navigateAddForm = function (customer) {
			$location.url("add/customer");
		};


		CustomerData.list(function (result) {
			$scope.Customers = result;
		});

	}).
	controller('CustomerAdd',function ($scope, $location, CustomerData) {
		$scope.requireLogin();

		$scope.customer = {
			firstName: '',
			lastName: '',
			identityCode: '',
			birthDate: ''
		};
		$scope.errorMessages = {};

		$scope.cancel = function () {
			$location.url("customer");
		};
		$scope.save = function (customer) {
			$scope.customer = angular.copy(customer);

			CustomerData.add(customer, function (result) {
				if (typeof result.valid == "undefined") {
					$location.url("customer/" + result.id);
					return;
				}
				$scope.errorMessages = result.messages;
			});
		};
	}).
	controller('AddressAdd',function ($scope, $routeParams, $location, AddressData) {
		$scope.requireLogin();

		$scope.address = {
			customerId: $routeParams.customerId
		};

		$scope.copyAddress = function(d, s) {
			d.address = s.address;
			d.house = s.house;
			d.townCounty = s.townCounty;
			d.zip =s.zip;
			d.county = s.county;

			$scope.address = angular.copy(d);
		};
		$scope.save = function (address) {
			$scope.address = angular.copy(address);
			AddressData.add(address, function (result) {
				if (typeof result.valid == "undefined") {
					$scope.navigateCancel();
					return;
				}
				$scope.errorMessages = result.messages;
			});
		};
		$scope.navigateCancel = function () {
			$location.url("customer/" + $routeParams.customerId + "/address");
		};
	}).
	controller('AddressEdit',function ($scope, $routeParams, $location, AddressData) {
		$scope.requireLogin();

		$scope.address = null;
		AddressData.getById($routeParams.id, function (result) {
			$scope.address = result;
		});

		$scope.save = function (address) {
			$scope.address = angular.copy(address);
			AddressData.update(address, function (result) {
				if (typeof result.valid == "undefined") {
					$scope.navigateCancel();
					return;
				}
				$scope.errorMessages = result.messages;
			});
		};
		$scope.navigateCancel = function () {
			$location.url("customer/" + $scope.address.customer.id + "/address");
		};
	}).
	controller('DeviceAdd',function ($scope, $routeParams, $location, DevicesData) {
		$scope.requireLogin();

		$scope.device = {
			customerId: $routeParams.customerId
		};

		$scope.deviceTypes = [];
		DevicesData.getTypes(function (result) {
			$scope.deviceTypes = result;
			$scope.device.type = result[0];
		});

		$scope.navigateCancel = function () {
			$location.url("customer/" + $routeParams.customerId + "/devices");
		};

		$scope.save = function (device) {
			$scope.device = angular.copy(device);
			device.typeId = device.type.id;
			DevicesData.add(device, function (result) {
				if (typeof result.valid == "undefined") {
					$scope.navigateCancel();
					return;
				}
				$scope.errorMessages = result.messages;
			});
		};
	}).
	controller('DeviceEdit',function ($scope, $routeParams, $location, DevicesData) {
		$scope.requireLogin();

		$scope.device = null;
		$scope.deviceTypes = [];
		DevicesData.getTypes(function (result) {
			$scope.deviceTypes = result;
			DevicesData.getById($routeParams.id, function (result) {
				$scope.device = result;
			});
		});

		$scope.save = function (device) {
			$scope.device = angular.copy(device);
			device.typeId = device.type.id;
			DevicesData.update(device, function (result) {
				if (typeof result.valid == "undefined") {
					$scope.navigateCancel();
					return;
				}
				$scope.errorMessages = result.messages;
			});
		};

		$scope.navigateCancel = function () {
			$location.url("customer/" + $scope.device.customer.id + "/devices");
		};
	}).
	controller('Customer',function ($scope, $routeParams, $location, CustomerData, GroupData, AddressData) {
		$scope.requireLogin();

		$scope.customer = {};
		$scope.tabItems = [
			{type: 'main', text: 'P&otilde;hiandmed', active: true, visible: true},
			{type: 'address', text: 'Aadressid', active: false, visible: true},
			{type: 'groups', text: 'Grupid', active: false, visible: true},
			{type: 'devices', text: 'Sidevahendid', active: false, visible: true}
		];
		$scope.newGroup = { name: '' };
		$scope.id = $routeParams.id;
		$scope.tab = $scope.tabItems[0].type;
		if ($routeParams.tab) {
			$scope.tab = $routeParams.tab;
		}
		for (var i = 0; i < $scope.tabItems.length; i++) {
			var item = $scope.tabItems[i];
			if (item.type == $scope.tab) {
				$scope.currentTab = item;
				$scope.tab = item.type;
				break;
			}
		}

		$scope.setActiveTab = function (tab) {
			$location.url("customer/" + $scope.id + "/" + tab.type);
		};
		$scope.getTabCssClass = function(item) {
			var css = [];
			if ($scope.currentTab.type == item.type) {
				css.push('active');
			}
			return css;
		};
		$scope.reloadData = function () {
			CustomerData.getById($scope.id, function (result) {
				$scope.customer = result;
				$scope.customer.birthDate = new Date(result.birthDate);
				$scope.customer.birthDateInput = $scope.formatDateInput($scope.customer.birthDate);

				GroupData.list(function (result) {
					$scope.groupList = [];
					for (var i = 0; i < result.length; i++) {
						var item = result[i];
						item.isSelected = false;
						item.groupId = -1;
						for (var j = 0; j < $scope.customer.groups.length; j++) {

							if ($scope.customer.groups[j].definition.id == item.id) {
								item.isSelected = true;
								item.groupId = $scope.customer.groups[j].id;
								break;
							}
						}
						$scope.groupList.push(item);
					}
				});
			});
		};
		$scope.reloadData();

		$scope.navigateAdd = function (type, id) {
			$location.url("add/" + type + "/" + id);
		};
		$scope.navigateEdit = function (type, id) {
			$location.url("edit/" + type + "/" + id);
		};
		$scope.navigateAddAddress = function() {
			$location.url("add/address/" + $scope.id);
		};

		$scope.save = function (customer) {
			CustomerData.update(customer, function (result) {
				$scope.errorMessages = {};
				if (!result.valid) {
					$scope.errorMessages = result.messages;
				}
			});
		};

		$scope.deleteCallback = function (success) {
			$scope.reloadData();
		};


		$scope.toggleGroup = function (group) {
			if (!group.isSelected) {
				GroupData.remove(group.groupId, function (result) {
					group.isSelected = false;
					group.groupId = -1;
				})
			} else {
				var params = {
					id: group.id,
					customerId: $scope.customer.id
				};
				GroupData.add(params, function (result) {
				});
			}
			return true;
		};
		$scope.addGroup = function (newGroup) {
			var params = {
				name: newGroup.name,
				customerId: $scope.customer.id
			};
			GroupData.insertNew(params, function (result) {
				$scope.reloadData();
				$scope.newGroup = { name: '' };
			});
		};


		$scope.setPrimaryAddress = function (address) {
			for (var i = 0; i < $scope.customer.addresses.length; i++) {
				var a = $scope.customer.addresses[i];
				a.addressType = (a.id == address.id) ? 'Primary' : 'Additional';
			}
			AddressData.setPrimaryAddress(address.id, function (result) {
			});
		};

		$scope.filterAddress = function(address) {
			return (address.addressType != 'Primary');
		};
		$scope.filterPrimary = function(address) {
			return !$scope.filterAddress(address);
		};
	}).
	factory('UserData',function ($http) {
		return {
			post: function (action, params, callback) {
				params.a = action
				return $http.post("api/user", params).success(function (result) {
					callback(result);
				});
			},
			isLoggedIn: function (callback) {
				return this.post('isLoggedIn', {}, callback);
			},
			auth: function (user, callback) {
				return this.post('auth', user, callback);
			},
			logout: function (callback) {
				return this.post('logout', {}, callback);
			}
		};
	}).
	factory('GroupData',function ($http) {
		return {
			post: function (action, params, callback) {
				params.a = action
				return $http.post("api/groups", params).success(function (result) {
					callback(result);
				});
			},
			list: function (callback) {
				return this.post('list', {}, callback);
			},
			remove: function (id, callback) {
				return this.post('remove', {id: id}, callback);
			},
			add: function (params, callback) {
				return this.post('add', params, callback);
			},
			insertNew: function(params, callback) {
				return this.post('insertNew', params, callback);
			},
			deleteDefinition: function(id, callback) {
				return this.post('deleteDefinition', {id: id}, callback);
			},
			getDefinitionById: function(id, callback) {
				return this.post('getDefinitionById', {id: id}, callback);
			}
		};
	}).
	factory('AddressData',function ($http) {
		return {
			post: function (action, params, callback) {
				params.a = action;
				return $http.post("api/address", params).success(function (result) {
					callback(result);
				});
			},
			setPrimaryAddress: function (id, callback) {
				return this.post('setPrimaryAddress', {id: id}, callback);
			},
			add: function (params, callback) {
				return this.post('add', params, callback);
			},
			update: function (params, callback) {
				return this.post('update', params, callback);
			},
			getById: function (id, callback) {
				return this.post('getById', {id: id}, callback);
			},
			list: function (callback) {
				return this.post("list", {}, callback);
			}
		};
	}).
	factory('CustomerData',function ($http) {
		return {
			post: function (action, params, callback) {
				params.a = action;
				return $http.post("api/customer", params).success(function (result) {
					callback(result);
				});
			},
			list: function (callback) {
				return this.post("list", {}, callback);
			},
			getById: function (id, callback) {
				return this.post('getById', {id: id}, callback);
			},
			add: function (params, callback) {
				return this.post("add", params, callback);
			},
			update: function (params, callback) {
				return this.post("update", params, callback);
			},
			search: function (term, callback) {
				return this.post("search", {term: term}, callback);
			}
		};
	}).
	factory('DevicesData', function ($http) {
		return {
			post: function (action, params, callback) {
				params.a = action;
				return $http.post("api/devices", params).success(function (data) {
					callback(data);
				});
			},
			add: function (device, callback) {
				return this.post('add', device, callback);
			},
			update: function (device, callback) {
				return this.post('update', device, callback);
			},
			getById: function (id, callback) {
				return this.post('getById', {id: id}, callback);
			},
			delete: function (id, callback) {
				return this.post('delete', {id: id}, callback);
			},
			getTypes: function (callback) {
				return this.post('getTypes', {}, callback);
			}
		};
	});