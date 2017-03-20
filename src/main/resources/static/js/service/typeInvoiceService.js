'use strict';

angular.module('dbApp').factory('TypeInvoiceService', ['$http', '$q', function($http, $q){

    var REST_SERVICE_URI = 'http://localhost:8080/EInvoice/invoice/';
    
    var factory = {
    	fetchAll : fetchAll,   
    	fetchAllService: fetchAllService,
    	getService: getService,
    };

    return factory;
    


    function fetchAll() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI + "getTypeAll")
            .then(
            function (response) {
            	console.log("Type servcie" + response.data);
                deferred.resolve(response.data);                
            },
            function(errResponse){
                console.error('Error while fetching Type Invoice');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    } 
    
    function fetchAllService(id) {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI + "getAllService/" + id)
            .then(
            function (response) {
            	console.log("Service" + response.data);
                deferred.resolve(response.data);                
            },
            function(errResponse){
                console.error('Error while fetching Type Invoice');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function getService(name, id) {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI + "getService/name=" + name + "&id=" + id)
            .then(
            function (response) {
            	console.log("Servcie" + response.data);
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


