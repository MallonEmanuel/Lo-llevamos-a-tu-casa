'use strict';
//'ui.bootstrap'
angular.module('app.Routes', ['ui.bootstrap']).config(
		[ '$routeProvider', '$locationProvider',
				function($routeProvider, $locationProvider) {
					var env = 'static/partials/';
					$routeProvider
					.when('/', {templateUrl : env + 'welcome.html'})
					.when('/users', {templateUrl : env + 'users/admin.html',controller : 'UserCtrl'})
					
					.when('/customers', {templateUrl : env + 'customers/list.html',controller : 'CustomerCtrl'})
//					.when('/customer/create', {templateUrl : env + 'customers/create_edit.html',controller : 'CustomerCtrl'})
					.when('/customer/:id/edit', {templateUrl : env + 'customers/create_edit.html',controller : 'CustomerEditCtrl'})
					
					.when('/shipmentProviders', {templateUrl : env + 'shipment_providers/list.html',controller : 'ShipmentProviderCtrl'})
					.when('/shipmentProvider/:id/edit', {templateUrl : env + 'shipment_providers/create_edit.html',controller : 'ShipmentProviderEditCtrl'})					
					.when('/shipmentProvider/:id/edit/articles', {templateUrl : env + 'shipment_providers/select_article.html',controller : 'SelectArticleCtrl'})

					.when('/ranking/customers', {templateUrl : env + 'ranking_customer/list.html',controller : 'RankingCustomerCtrl'})
					
					.when('/ranking/articles', {templateUrl : env + 'ranking_article/list.html',controller : 'RankingArticleCtrl'})
					/* Default */
					.otherwise({templateUrl : env + 'home/404.html'});
				} 
		]);