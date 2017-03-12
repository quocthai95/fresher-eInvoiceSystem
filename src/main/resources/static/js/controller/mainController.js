var app = angular.module('myApp');

app.controller("mainController", function($scope,// AuthenticationService,
		$location, $window) {
	$scope.isUser = function() {
		if (!angular.isUndefined($window.localStorage.currentUser)) {
			$scope.user = $window.localStorage.currentUser.username;
			// console.log('not undefined ' + $scope.user);
			return false;
		} else { // True if value is undefined.
			return true;
		}
	}

	$scope.goOut = function() {
		// reset login status
		// alert('goOut');
		//AuthenticationService.Logout();
		// go home page
		$location.path('/');
		// console.log('get $localStorage after logout ' +
		// JSON.stringify($localStorage.currentUser));
	};
});