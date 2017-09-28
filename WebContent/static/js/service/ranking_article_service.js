'use strict';
	
App.factory('RankingArticleSrv', ['$http', '$q', function($http, $q){

	return {
			    
		    filterAll: function(filter){
				return $http.put('ranking/articles/1', filter)
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
