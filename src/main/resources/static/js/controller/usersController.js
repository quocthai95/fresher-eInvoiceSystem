
var app = angular.module('myApp');

app.controller("usersController", function($scope, LoginService, AuthenticationService, $location) {
	var username;
	$scope.login = function() {
		username = $scope.username;
		var password = $scope.password;

		console.log('username:' + username);
		console.log('password:' + password);
		self.user = {
			'username' : username,
			'password' : password
		};
		LoginService.login(user).then(loginSuccess);
	}
	var loginSuccess = function(status) {
		if (status == 200) {
			console.log('status ' + status);
			AuthenticationService.setUserAuthenticated(username);
			$location.path('/dashboard');
		}		
	}
});
