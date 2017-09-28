'use strict';

App.factory('CustomerSrv', [ '$http', '$q', function($http, $q) {

	return {

		fetchAll : function() {
			return $http.get('customers/').then(function(response) {
				return response.data;
			}, function(errResponse) {
				console.error('Error while fetching customers');
				console.error(errResponse);
				return $q.reject(errResponse);
			});
		},

		findById: function(id) {
			
			return $http.get('customer/'+ id 
				).then(function(response) {
				return response.data;
			}, function(errResponse) {
				return $q.reject(errResponse);
			});
		},

		
		filterByName : function(filter) {
			
			return $http.get('customers/filterByName/'+ filter.name 
				).then(function(response) {
				return response.data;
			}, function(errResponse) {
				return $q.reject(errResponse);
			});
		},
		
//   Sin usar
		filterByName2 : function(name) {
			
			return $http.get('customers/filterByName/'+name 
				).then(function(response) {
				return response.data;
			}, function(errResponse) {
				return $q.reject(errResponse);
			});
		},
		

		filterAll: function(filter) {
			return $http.get('customers/filter/'+validate(filter.name)+'/'+validate(filter.surname)+'/'+validate(filter.cuit) 
				).then(function(response) {
				return response.data;
			}, function(errResponse) {
				return $q.reject(errResponse);
			});
		},

	    create: function(customer){
				return $http.post('customer/', customer)
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
	    
	    update: function(customer, id){
				return $http.put('customer/'+id, customer)
						.then(
								function(response){
									return response.data;
								}, 
								function(errResponse){
									console.error('Error while updating user');
									return $q.reject(errResponse);
								}
						);
		},
	    
		deletee: function(id){
				return $http['delete']('customer/'+id)
						.then(
								function(response){
									return response.data;
								}, 
								function(errResponse){
									console.error('Error while deleting user');
									return $q.reject(errResponse);
								}
						);
		}
		
	};
//	Funciones extras
	function validate(s) {
		if(s === "") {
			return "TODOS"
		}
	    return s;              
	}
} ]);
