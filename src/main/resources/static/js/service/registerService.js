'use strict';

angular.module('myApp').factory('RegisterService', ['$http', '$q', function($http, $q){

    var REST_SERVICE_URI = 'http://localhost:8080/EInvoice/user/';

    var factory = {        
    	register: register        
    };

    return factory;

   
    function register(res) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI + "register", res)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while creating User');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

}]);
