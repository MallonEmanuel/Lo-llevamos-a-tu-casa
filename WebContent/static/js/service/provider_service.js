'use strict';

App.factory('ProviderSrv', [
		'$http',
		'$q',
		function($http, $q) {

			return {

				fetchAll : function() {
					return $http.get('providers/').then(function(response) {
						return response.data;
					}, function(errResponse) {
						console.error('Error while fetching providers');
						console.error(errResponse);
						return $q.reject(errResponse);
					});
				},

				findById : function(id) {
					return $http.get('provider/' + id).then(function(response) {
						return response.data;
					}, function(errResponse) {
						return $q.reject(errResponse);
					});
				},

				filterByName : function(name) {
					return $http.get('providers/filterByName/' + name)
							.then(function(response) {
								return response.data;
							}, function(errResponse) {
								return $q.reject(errResponse);
							});
				},
			};

		} ]);