'use strict';
	
App.factory('ShipmentProviderSrv', ['$http', '$q', function($http, $q){

	return {
			fetchAll: function() {
					return $http.get('shipmentProviders/')
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while fetching users');
										return $q.reject(errResponse);
									}
							);
			},
		
			findById: function(id) {
				
				return $http.get('shipmentProvider/'+ id 
					).then(function(response) {
					return response.data;
				}, function(errResponse) {
					return $q.reject(errResponse);
				});
			},
			
		    create: function(shipmentProvider){
					return $http.post('shipmentProvider/', shipmentProvider)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while creating user');
										return $q.reject(errResponse);
									}
							);
		    },
		    
		    update: function(shipmentProvider, id){
				return $http.put('shipmentProvider/'+id, shipmentProvider)
						.then(function(response){
									return response.data;
								}, 
								function(errResponse){
									console.error('Error while updating user');
									return $q.reject(errResponse);
								}
						);
		    },
		    
		    filterAll: function(filter){
				return $http.put('shipmentProviders/filterAll/1', filter)
						.then(function(response){
									return response.data;
								}, 
								function(errResponse){
									console.error('Error while filter');
									return $q.reject(errResponse);
								}
						);
		    },
	    
	    
		    
		    
	};

}]);
