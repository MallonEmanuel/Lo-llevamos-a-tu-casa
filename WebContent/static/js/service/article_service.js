'use strict';

App.factory('ArticleSrv', [
		'$http',
		'$q',
		function($http, $q) {

			return {
				fetchAll : function() {
					return $http.get('articles/').then(function(response) {
						return response.data;
					}, function(errResponse) {
						console.error('Error while fetching providers');
						console.error(errResponse);
						return $q.reject(errResponse);
					});
				},

				findById : function(id) {
					return $http.get('article/' + id).then(function(response) {
						return response.data;
					}, function(errResponse) {
						return $q.reject(errResponse);
					});
				},

				filterByName : function(name) {
					return $http.get('articles/filterByName/' + name)
							.then(function(response) {
								return response.data;
							}, function(errResponse) {
								return $q.reject(errResponse);
							});
				},
				
			};//end declaration
			
		} ]);// end articleSrv