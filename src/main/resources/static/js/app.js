var app = angular.module('myApp', [ 'ngRoute', 'ngMessages', 'oitozero.ngSweetAlert']);
app.config(function($routeProvider) {
	
	$routeProvider.when("/", {
		templateUrl : "/EInvoice/views/home.html",
		controller : 'mainController',
	}).when("/signup", {
		templateUrl : "/EInvoice/views/signup.html",
		controller : 'usersController',
	}).when("/login", {
		templateUrl : "/EInvoice/views/login.html",
		controller : 'usersController',
	}).when("/forgotpw", {
		templateUrl : "/EInvoice/views/forgotpw.html",
		controller : "RegisterController as ctrl",
	}).otherwise({
		redirectTo : '/'
	});
	
});
