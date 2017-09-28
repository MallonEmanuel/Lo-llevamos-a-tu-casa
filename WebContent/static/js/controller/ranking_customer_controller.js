'use strict';

App.controller('RankingCustomerCtrl', [
		'$scope',
		'$location',
		'RankingCustomerSrv',
		
		function($scope,$location, RankingCustomerService) {
			
			$scope.filter = {	
				beginDate : new Date(),
				endDate : new Date()
			};
			
			$scope.rankingCustomers = [];

			$scope.testFilter = function() {
				console.log($scope.filter);
			}
		
			
			$scope.filterAll = function() {
				console.log("filtering : "+$scope.filter);
				RankingCustomerService.filterAll($scope.filter).then(function(d) {
					$scope.rankingCustomers = d;
				}, function(errResponse) {
					console.error('Error while fetching Currencies');
				});
			}
			
			
			
		} ]);
