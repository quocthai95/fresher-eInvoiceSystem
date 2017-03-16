'use strict';

angular.module('dbApp').factory('InvoiceService', ['$http', '$q', 'TypeInvoiceService', function($http, $q, TypeInvoiceService){

    var REST_SERVICE_URI = 'http://localhost:8080/EInvoice/invoice/';
    
    var factory = {
    	fetchAllInvoice : fetchAllInvoice,
        createInvoice : createInvoice,
        updateInvoice : updateInvoice,
        fetchAllTypeInvoice: fetchAllTypeInvoice,        
        getID : getID,    
        deleteInvoice : deleteInvoice ,
        fetchAllService: fetchAllService,
        getService: getService,
        };

    return factory;
    


    function fetchAllInvoice(size, page) {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI + "getAll?size="+ size + "&page=" + page)
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
    
    function fetchAllTypeInvoice() {
        var deferred = $q.defer();
        TypeInvoiceService.fetchAll()
            .then(
            function (d) {
            	console.log(d);
                deferred.resolve(d);                
            },
            function(errResponse){
                console.error('Error while fetching Invoice');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function fetchAllService(id) {
        var deferred = $q.defer();
        TypeInvoiceService.fetchAllService(id)
            .then(
            function (d) {
            	console.log(d);
                deferred.resolve(d);                
            },
            function(errResponse){
                console.error('Error while fetching Invoice');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function getService(name, id) {
        var deferred = $q.defer();
        TypeInvoiceService.getService(name, id)
            .then(
            function (d) {
            	console.log(d);
                deferred.resolve(d);                
            },
            function(errResponse){
                console.error('Error while fetching Invoice');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
       

    function createInvoice(invoice) {
    	console.log("Service create: "+invoice);
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
function getID(id){
	var deferred = $q.defer();
	$http.get(REST_SERVICE_URI + "get/" + id)
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
}]);


