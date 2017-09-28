'use strict';

App.controller('UserCtrl', [
		'$scope',
		'UserSrv',
		function($scope, UserService) {			
			$scope.user = {
				id : null,
				username : '',
				address : '',
				email : ''
			};
			$scope.users = [];
			
			$scope.fetchAllUsers = function() {
				UserService.fetchAllUsers().then(function(d) {
					$scope.users = d;
				}, function(errResponse) {
					console.error('Error while fetching Currencies');
				});
			};

			$scope.createUser = function(user) {
				UserService.createUser(user).then($scope.fetchAllUsers,
						function(errResponse) {
							console.error('Error while creating User.');
						});
			};

			$scope.updateUser = function(user, id) {
				UserService.updateUser(user, id).then($scope.fetchAllUsers,
						function(errResponse) {
							console.error('Error while updating User.');
						});
			};

			$scope.deleteUser = function(id) {
				UserService.deleteUser(id).then($scope.fetchAllUsers,
						function(errResponse) {
							console.error('Error while deleting User.');
						});
			};

			$scope.fetchAllUsers();

			$scope.save = function() {
				if ($scope.user.id == null) {
					console.log('Saving New User', $scope.user);
					$scope.createUser($scope.user);
				} else {
					$scope.updateUser($scope.user, $scope.user.id);
					console.log('User updated with id ', $scope.user.id);
				}
				$scope.reset();
			};

			$scope.edit = function(id) {
				console.log('id to be edited', id);
				for (var i = 0; i < $scope.users.length; i++) {
					if ($scope.users[i].id = id) {
						$scope.user = angular.copy($scope.users[i]);
						break;
					}
				}
			};

			$scope.remove = function(id) {
				console.log('id to be deleted', id);
				if ($scope.user.id === id) {// clean form if the user to be
											// deleted is shown there.
					$scope.reset();
				}
				$scope.deleteUser(id);
			};

			$scope.reset = function() {
				$scope.user = {
					id : null,
					username : '',
					address : '',
					email : ''
				};
				$scope.myForm.$setPristine(); // reset Form
			};

		} ]);
