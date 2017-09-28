'use strict';

angular.module('app.Environment',[]).config([ '$provide', function($provide) {
	$provide.factory('env', [ function() {
		return {
			partials : 'static/partials/'
		}
	} ])
} ]);