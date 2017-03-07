var app = angular.module('myApp', [ 'ngRoute']);
app.config(function($routeProvider) {
    $routeProvider
    .when("/", {
    	templateUrl: "/EInvoice/views/home.html"
    })
    .when("/signup", {
        templateUrl : "/EInvoice/views/signup.html"
    }) .otherwise(
            { redirectTo: '/'}
    );
 
});