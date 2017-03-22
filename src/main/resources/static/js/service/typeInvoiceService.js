'use strict';

angular.module('dbApp').factory('TypeInvoiceService', ['$http', '$q', function($http, $q){

    var REST_SERVICE_URI = 'http://localhost:8080/EInvoice/invoice/';
    
    var factory = {
    	fetchAll : fetchAllType,   
    	fetchAllService: fetchAllService,
    	getService: getService,
    };

    return factory;
    


    function fetchAllType() {
    	return $http.get(REST_SERVICE_URI + "getTypeAll").then(function (response) {
                return response.data;                
        })
    } 
    
    function fetchAllService(id) {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI + "getAllService/" + id)
            .then(
            function (response) {
            	console.log("fetchAllService" + response.data);
                deferred.resolve(response.data);                
            },
            function(errResponse){
                console.error('Error while fetching fetchAllService');
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
            	console.log("getService" + response.data);
                deferred.resolve(response.data);                
            },
            function(errResponse){
                console.error('Error while fetching getService');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }          
}]);


