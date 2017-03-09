var app = angular.module('admin', [ 'ngRoute' ]);
app.config(function($routeProvider) {

    $routeProvider
    .when("/", {
    	templateUrl: "/EInvoice/views/admin/userManagement.html"
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