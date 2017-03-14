'use strict';

angular.module('dbApp').factory('InvoiceService', ['$http', '$q', function($http, $q){

    var REST_SERVICE_URI = 'http://localhost:8080/EInvoice/invoice/';
    
    var factory = {
    	fetchAllInvoice : fetchAllInvoice,
        createInvoice : createInvoice,
        updateInvoice : updateInvoice,
        deleteInvoice : deleteInvoice,      
    };

    return factory;
    


    function fetchAllInvoice(id, size, page) {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI + "getAll/"+ id + "?size="+ size + "&page=" + page)
            .then(
            function (response) {
                deferred.resolve(response.data);                
            },
            function(errResponse){
                console.error('Error while fetching Invoice');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
       

    function createInvoice(invoice) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI + "create", invoice)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while creating Invoice');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }


    function updateInvoice(invoice, id) {
    	console.log(invoice);
        var deferred = $q.defer();        
        $http.post(REST_SERVICE_URI + "update/" +id, invoice)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while updating Invoice');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

    function deleteInvoice(id) {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI + "delete/" +id)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while deleting Invoice');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

}]);


