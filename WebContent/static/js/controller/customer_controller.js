'use strict';

App.controller('CustomerCtrl', [
		'$scope',
		'$location',
		'CustomerSrv',
		function($scope, $location, CustomerService) {

			$scope.customers = [];

			$scope.go = function(path) {
				$location.path(path);
			}

			$scope.goCreate = function() {
				$scope.go("/customer/new/edit");
			}

			$scope.goEdit = function(id) {
				$scope.go("/customer/" + id + "/edit");
			}

			$scope.goList = function() {
				$scope.go("/customers");
			}

			$scope.fetchAll = function() {
				CustomerService.fetchAll().then(function(d) {
					$scope.customers = d;
				}, function(errResponse) {
					console.error('Error while fetching Currencies');
				});
			};

			$scope.filterAll = function() {
				if (!($scope.filter == null)) {
					CustomerService.filterAll($scope.filter).then(function(d) {
						$scope.customers = d;
					}, function(errResponse) {
						console.error('Error while fetching Currencies');
					});
				}else{
					$scope.fetchAll();
				}
			};
						
			$scope.filterAll();
			
			$scope.filterByName = function() {
				CustomerService.filterByName($scope.filter).then(function(d) {
					$scope.customers = d;
				}, function(errResponse) {
					console.error('Error while fetching Currencies');
				});
			};
			// No usado
			$scope.filterByName2 = function(name) {
				CustomerService.filterByName2(name).then(function(d) {
					$scope.customers = d;
				}, function(errResponse) {
					console.error('Error while fetching Currencies');
				});
			};


			$scope.deletee = function(id) {
				CustomerService.deletee(id).then($scope.fetchAll,
						function(errResponse) {
							console.error('Error while deleting User.');
						});
			};

			$scope.remove = function(id) {
				console.log('id to be deleted', id);
				$scope.deletee(id);
			};

		
		} ]);
