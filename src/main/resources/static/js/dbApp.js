var app = angular.module('dbApp', [ 'ngRoute','zingchart-angularjs' ]);
app.config(function($routeProvider) {

	$routeProvider.when("/", {
		templateUrl : "/EInvoice/views/welcome.html",
		controller : 'UserController',
	}).when("/user-manage", {
		templateUrl : "/EInvoice/views/admin/userManagement.html",
		controller : 'UserController',
	}).when("/config-email", {
		templateUrl : "/EInvoice/views/admin/config-email.html",
		controller : 'UserController',
	}).when("/customer-manage", {
		templateUrl : "/EInvoice/views/customer/customerManagement.html",
		controller: 'CustomerController',
	}).when("/invoice-manage", {
		templateUrl : "/EInvoice/views/customer/invoiceManagement.html",
		controller: 'InvoiceController',
	}).when("/report-manage", {
		templateUrl : "/EInvoice/views/customer/reportManagement.html",
		controller: 'ReportController as ctrl',
	}).when("/chart-manage", {
		templateUrl : "/EInvoice/views/customer/chartManagement.html",
		controller: 'ChartController',



	})
	.otherwise({
		redirectTo : '/'
	});

});
