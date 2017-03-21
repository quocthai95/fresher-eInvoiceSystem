'use strict';


app.factory('ParameterService', ['$http', '$q', function($http, $q){
	//Base URI
    var REST_SERVICE_URI = 'http://localhost:8080/EInvoice/parameter/';
    
    var factory = {    	
        updateParameter:updateParameter,        
        getParameterByKey:getParameterByKey,
    };
    return factory;
    
    //Service call api get parameter
    function getParameterByKey(key) {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI + "getByKey/key=" +key)
            .then(
            function (response) {            	
                deferred.resolve(response.data);                
            },
            function(errResponse){
                console.error('Error while fetching parameter');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    //Service call api update parameter
    function updateParameter(id, parameter) {
    	console.log(parameter);
    	var deferred = $q.defer();
        $http.post(REST_SERVICE_URI + "update/key=" +id, parameter)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while updating parameter');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

}]);
