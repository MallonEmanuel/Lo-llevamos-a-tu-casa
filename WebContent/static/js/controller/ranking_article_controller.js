'use strict';

App.controller('RankingArticleCtrl', [
		'$scope',
		'$location',
		'RankingArticleSrv',
		
		function($scope,$location, RankingArticleService) {
			
			$scope.filter = {	
				beginDate : new Date(),
				endDate : new Date()
			};
			
			$scope.rankingArticles = [];

			$scope.testFilter = function() {
				console.log($scope.filter);
			}
		
			
			$scope.filterAll = function() {
				console.log("filtering : "+$scope.filter);
				RankingArticleService.filterAll($scope.filter).then(function(d) {
					$scope.rankingArticles = d;
				}, function(errResponse) {
					console.error('Error while fetching Currencies');
				});
			}
			
			
		} ]);
