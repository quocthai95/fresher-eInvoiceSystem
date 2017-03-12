var app = angular.module('dbApp', [ 'ngRoute' ]);
app.config(function($routeProvider) {

	$routeProvider.when("/", {
		templateUrl : "/EInvoice/views/admin/userManagement.html",
		controller : 'UserController',
	}).when("/user-manage", {
		templateUrl : "/EInvoice/views/admin/userManagement.html",
		controller : 'UserController',
	}).when("/config-email", {
		templateUrl : "/EInvoice/views/admin/config-email.html",
		controller : 'UserController',
	}).otherwise({
		redirectTo : '/'
	});

});