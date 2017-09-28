'use strict';

App.controller('ShipmentProviderEditCtrl',[
						'$scope',
						'$location',
						'$routeParams',
						'ShipmentProviderSrv',
						'ProviderSrv',

						function($scope, $location, $routeParams,ShipmentProviderService, ProviderService) {
							
							$scope.shipmentProvider = {
								id : null,
								deliveryDate : new Date(),
								provider : ''
							};

							// Inicio
							if ($routeParams.id != 'new') {
								ShipmentProviderService.findById($routeParams.id).then(function(d) {
									$scope.shipmentProvider = d;
								});
							}

							$scope.providers = function(name) {
								return ProviderService.filterByName(name).then(
										function(response) {
											return response;
										});
							};

							$scope.provider;
							
							$scope.onSelectProvider = function($item, $model,$label) {
								console.log("select"+$item);
								$scope.shipmentProvider.provider = $item;
							}

							
							
							$scope.go = function(path) {
								$location.path(path);
							}

							$scope.goList = function() {
								$scope.go("/shipmentProviders");
							}


							$scope.goArticles = function(id) {
								$scope.go("/shipmentProvider/"+id+"/edit/articles");
								
							}
							$scope.id ;
							
							$scope.create = function(shipmentProvider) {
								ShipmentProviderService.create(shipmentProvider).then(
										function(response) {
											$scope.id = parseInt(response);
											console.log("save : "+$scope.id);
											$scope.goArticles($scope.id);
											return (response);
										});
							};

							$scope.reset = function() {
								$scope.shipmentProvider = {
									id : null,
									delivery_date : '',
									provider : ''
								};
								$scope.myForm.$setPristine(); // reset Form
							};

							$scope.save = function() {
								if ($scope.shipmentProvider.id == null) {
									console.log('Saving New ShipmentProvider', $scope.shipmentProvider);
									$scope.create($scope.shipmentProvider);
								} else {
									$scope.update($scope.shipmentProvider, $scope.shipmentProvider.id);
									console.log('shipmentProvider updated with id ', $scope.shipmentProvider.id);
								}
							};
							
							$scope.saveAndContinue = function() {
								$scope.save();
							};
							
							
						} ]);
