'use strict';
app.factory('ParameterService', ['$http', '$q', function($http, $q){
    
    var factory = {    	
        updateParameter:updateParameter,        
        getParameterByKey:getParameterByKey,
    };
    return factory;
    
    //Service call api get parameter
    function getParameterByKey(key) {
        var deferred = $q.defer();
        $http.get(BASE_URL + "parameter/getByKey/key=" +key)
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
        $http.post(BASE_URL + "parameter/update/key=" +id, parameter)
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
