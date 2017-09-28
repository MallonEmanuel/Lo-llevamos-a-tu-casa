'use strict';

App.controller('SelectArticleCtrl',[
						'$scope',
						'$location',
						'$routeParams',
						'ShipmentProviderSrv',
						'ArticleSrv',
						'ArticleOrderProviderSrv',

						function($scope, $location, $routeParams,ShipmentProviderService,ArticleService,ArticleOrderProviderService) {
							
							$scope.shipmentProvider = {
								id : null,
								deliveryDate : '',
								provider : ''
							};
							
							$scope.articleOrderProvider = {
									id : null,
									price : '',
									quantity : '',
									price : '',
									active: true,
									article:''
							}
							
							$scope.articleOrderProviders = [];
							$scope.myDate='';
							// Inicio
							if ($routeParams.id != 'new') {
								ShipmentProviderService.findById($routeParams.id).then(function(d) {
									$scope.shipmentProvider = d;
									$scope.myDate=new Date($scope.shipmentProvider.deliveryDate) ;
								});
								console.log("routeParam : "+$routeParams.id);
//								ArticleOrderProviderService.findByIdShipmentProvider($routeParams.id).then(function(d) {
//									$scope.articleOrderProviders = d;
//								});
																
							}else{
						
							}
							$scope.articles = function(name) {
								return ArticleService.filterByName(name).then(
										function(response) {
											return response;
										});
							};

//							$scope.article;
							
							$scope.onSelectArticle= function($item, $model,$label) {
								console.log("select"+$item);
								$scope.articleOrderProvider.article = $item;
								$scope.articleOrderProvider.price = $item.price;
								$scope.article = $item;
							}

							

							$scope.go = function(path) {
								$location.path(path);
							}

							$scope.goList = function() {
								$scope.go("/shipmentProviders");
							}

							$scope.addAndContinue= function() {
//								$scope.articleOrderProvider.provider = $.extend( {}, $scope.articleOrderProvider.provider);
								console.log($scope.articleOrderProvider);
								$scope.articleOrderProviders.push($.extend( {}, $scope.articleOrderProvider));
								$scope.reset();
							}

							$scope.reset = function() {
								$scope.articleOrderProvider = {
										id : null,
										price : '',
										quantity : '',
										price : '',
										active: true,
										article:''
								}
								
//								$scope.myForm.$setPristine(); // reset Form
							};

							$scope.saveArticles = function(){
//								if ($routeParams.id == 'new') {
									console.log("create ");
									ArticleOrderProviderService.createAll($scope.articleOrderProviders,$scope.shipmentProvider.id);	
//								}
								$scope.goList();
							};
						
			} ]);
