'use strict';

angular.module('dbApp').factory('TypeInvoiceService', ['$http', '$q', function($http, $q){

    var REST_SERVICE_URI = 'http://localhost:8080/EInvoice/invoice/';
    
    var factory = {
    	fetchAll : fetchAll,              
    };

    return factory;
    


    function fetchAll() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI + "getTypeAll)
            .then(
            function (response) {
                deferred.resolve(response.data);                
            },
            function(errResponse){
                console.error('Error while fetching Type Invoice');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }          

}]);


