'use strict';

App.controller('CustomerEditCtrl', [ 
        '$scope',
        '$location',
        '$routeParams',
        'CustomerSrv',
		function($scope, $location,$routeParams ,CustomerService) {
			$scope.customer = {
				id : null,
				name : '',
				surname : '',
				cuit : '',
				active : true,
				// address : [ {id : ''}, {name : ''}, {active : true}, {city :
				// null} ]
				address : null
			};

			// Inicio
			if ($routeParams.id != 'new') {
				CustomerService.findById($routeParams.id).then(function(d) {
					$scope.customer = d;
				});
			}

			$scope.go = function(path) {
				$location.path(path);
			}

			$scope.goList = function() {
				$scope.go("/customers");
			}

			$scope.create = function(customer) {
				CustomerService.create(customer);
			};

			$scope.update = function(customer, id) {
				CustomerService.update(customer, id);
			};

			$scope.saveAndContinue = function() {
				if ($scope.customer.id == null) {
					console.log('Saving New User', $scope.customer);
					$scope.create($scope.customer);
				} else {
					$scope.update($scope.customer, $scope.customer.id);
					console.log('User updated with id ', $scope.customer.id);
				}
				$scope.reset();
				// $scope.goCreate();
				document.getElementById("name").focus();
			};

			$scope.save = function() {
				if ($scope.customer.id == null) {
					console.log('Saving New User', $scope.customer);
					$scope.create($scope.customer);
				} else {
					$scope.update($scope.customer, $scope.customer.id);
					console.log('User updated with id ', $scope.customer.id);
				}
				$scope.goList();
			};

			$scope.reset = function() {
				$scope.customer = {
					id : null,
					name : '',
					surname : '',
					active : true,
					cuit : '',
					address : null
				};
				$scope.myForm.$setPristine(); // reset Form
			};

		} ]);
