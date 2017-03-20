var app = angular.module('myApp', [ 'ngRoute', 'ngMessages', 'oitozero.ngSweetAlert']);
app.config(function($routeProvider) {
	
	$routeProvider.when("/", {
		templateUrl : "/EInvoice/views/home.html",
		controller : 'mainController',
		isAuthenticated : false
	}).when("/signup", {
		templateUrl : "/EInvoice/views/signup.html",
		controller : 'usersController',
		isAuthenticated : false
	}).when("/login", {
		templateUrl : "/EInvoice/views/login.html",
		controller : 'usersController',
		isAuthenticated : false
	}).when("/forgotpw", {
		templateUrl : "/EInvoice/views/forgotpw.html",
		controller : "RegisterController as ctrl",
		isAuthenticated : false
	}).otherwise({
		redirectTo : '/'
	});
	
});

app.run(function($rootScope, $location, AuthenticationService) {
	$rootScope.$on("$routeChangeStart", function(event, next, current) {
		if ($location.path() == "/login") {
			if (AuthenticationService.getUserAuthenticated()) {
				$location.path("/");
			}
		}
		
		if (next.$$route.isAuthenticated
				&& !AuthenticationService.getUserAuthenticated()) {
			alert("You need to be authenticated to see this page!");
			event.preventDefault();
			$location.path("/login");
		}
	});
});
