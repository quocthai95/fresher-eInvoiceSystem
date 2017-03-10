angular.module('myApp').factory('LoginService', function($http, $q){
    var REST_SERVICE_URI = 'http://localhost:8080/EInvoice/user/';
    
    var factory = {
		login : loginUser,
    }
    return factory;
	function loginUser(user) {
        var deferred = $q.defer();
        $http({
            method: 'POST',
            url: "http://localhost:8080/EInvoice/login",
            data: $.param({
            	'username' : user.username,
            	'password' : user.password,
//            	'_csrf' : 'c5417bd7-fb1c-44a9-8f1f-de39cd051379',
            	}),
        }).then(function (response) {
                deferred.resolve(response.status);

            },function(errResponse){
                console.error('Error while login Users');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
	
});