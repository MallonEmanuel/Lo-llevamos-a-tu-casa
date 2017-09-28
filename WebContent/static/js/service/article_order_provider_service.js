'use strict';

App.factory('ArticleOrderProviderSrv', [
		'$http',
		'$q',
		function($http, $q) {

			return {
				fetchAll : function() {
					return $http.get('articleOrderProviders/').then(function(response) {
						return response.data;
					}, function(errResponse) {
						console.error('Error while fetching providers');
						console.error(errResponse);
						return $q.reject(errResponse);
					});
				},

				findById : function(id) {
					return $http.get('/articleOrderProvider/' + id).then(function(response) {
						return response.data;
					}, function(errResponse) {
						return $q.reject(errResponse);
					});
				},

				findByIdShipmentProvider: function(id) {
					return $http.get('articleOrderProviders/shipmentProvider/' + id).then(function(response) {
						return response.data;
					}, function(errResponse) {
						return $q.reject(errResponse);
					});
				},

				
				createAll: function(articleOrderProviders,id){
					return $http.post('articleOrderProviders/'+id, articleOrderProviders)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while creating user');
										return $q.reject(errResponse);
									});
				},//end createAll
				
			};//end declaration
			
		} ]);// end articleSrv