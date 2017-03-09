var app = angular.module('myApp', [ 'ngRoute' ]);
app.config(function($routeProvider) {
	$routeProvider.when("/", {
		templateUrl : "/EInvoice/views/home.html",
			controller : 'mainController'
	}).when("/signup", {
		templateUrl : "/EInvoice/views/signup.html",
		controller : 'usersController'
	}).when("/login", {
		templateUrl : "/EInvoice/views/login.html",
		controller : 'usersController'
//	}).when("/logout", {
//		templateUrl : "/EInvoice/views/home.html",
//		controller : 'usersController'
	}).when("/admin/manage", {
		templateUrl : "/EInvoice/views/userManagement.html"
	}).otherwise({
		redirectTo : '/'
	});

});