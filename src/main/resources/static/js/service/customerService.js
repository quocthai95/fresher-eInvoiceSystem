app.factory('CustomerService', ['$http', '$q', function($http, $q){
	 
	var REST_SERVICE_URI = 'http://localhost:8080/EInvoice/customer/';
	
	var factory = {
			getCustomer : getCustomer, 
	        updateCustomer:updateCustomer,
	    };
	return factory;
	
	  function getCustomer() {
	        var deferred = $q.defer();
	        $http.get(REST_SERVICE_URI + "get")
	            .then(
	            function (response) {
	                deferred.resolve(response.data);                
	            },
	            function(errResponse){
	                console.error('Error while fetching Customer');
	                deferred.reject(errResponse);
	            }
	        );
	        return deferred.promise;
	    }
	  
	  function updateCustomer(customer) {
	    	console.log(customer);
	        var deferred = $q.defer();
	        $http.post(REST_SERVICE_URI + "update", customer)
	            .then(
	            function (response) {
	                deferred.resolve(response.data);
	            },
	            function(errResponse){
	                console.error('Error while updating Customer');
	                deferred.reject(errResponse);
	            }
	        );
	        return deferred.promise;
	    }
}]);