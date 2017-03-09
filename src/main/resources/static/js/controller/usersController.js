var app = angular.module('myApp');

app.controller("usersController", function($scope, UserService) {
	var username = $scope.username;
	var password = $scope.password;
	$scope.login = function() {
		self.user = {
			'username' : username,
			'password' : password
		};
		UserService.login(user).then(loginSuccess, loginError);
	}
	var loginSuccess = function(data) {
		console.log('loginSuccess');
	}
	var loginError = function(error) {
		console.log(error);
	}
});
