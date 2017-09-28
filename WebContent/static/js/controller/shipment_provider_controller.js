'use strict';

App.controller('ShipmentProviderCtrl', [
		'$scope',
		'$location',
		'ShipmentProviderSrv',
		function($scope,$location, ShipmentProviderService) {
			
			$scope.filter = {
				shipmentProviderId : null,
				beginDate : new Date(),
				endDate : new Date(),
				providerName : ''
			};
			
			$scope.shipmentProviders = [];

			$scope.fetchAll = function() {
				ShipmentProviderService.fetchAll().then(function(d) {
					$scope.shipmentProviders = d;
				}, function(errResponse) {
					console.error('Error while fetching Currencies');
				});
			};
			$scope.fetchAll();


			$scope.go = function(path) {
				$location.path(path);
			}

			$scope.goCreate = function() {
				$scope.go("/shipmentProvider/new/edit");
			}

			$scope.goEdit = function(id) {
				console.log("id : "+id);
				$scope.go("/shipmentProvider/" + id + "/edit/articles");
			}

			
			
			$scope.testFilter = function() {
				console.log($scope.filter);
			}
			
			$scope.filterAll = function() {
				console.log("filtering : "+$scope.filter);
				ShipmentProviderService.filterAll($scope.filter).then(function(d) {
					$scope.shipmentProviders = d;
				}, function(errResponse) {
					console.error('Error while fetching Currencies');
				});
			}
			
			
		} ]);
