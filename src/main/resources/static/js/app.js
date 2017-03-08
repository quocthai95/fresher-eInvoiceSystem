var app = angular.module('myApp', [ 'ngRoute']);
app.config(function($routeProvider) {
    $routeProvider
    .when("/", {
    	templateUrl: "/EInvoice/views/home.html"
    })
    .when("/signup", {
        templateUrl : "/EInvoice/views/signup.html"
    }) 
    .when("/login", {
        templateUrl : "/EInvoice/views/login.html"
    }) 
    .when("/user-manage", {
        templateUrl : "/EInvoice/views/admin/userManagement.html"
    }) 
    .when("/config-email", {
        templateUrl : "/EInvoice/views/admin/config-email.html"
    }) 
    .otherwise(
            { redirectTo: '/'}
    );
 
});