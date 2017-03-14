angular.module('dbApp').factory('ReportService', ['$http', '$q', function($http, $q){

    var REST_SERVICE_URI = 'http://localhost:8080/EInvoice/user/getReport/';
    
    var factory = {
    	fetchAllReport : fetchAllReport,
    };

    return factory;
    
    function fetchAllReport(id, start, end, size, page) {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI +"id=" + id + "&start="+ start + "&end=" + end + "&size="+ size + "&page=" + page)
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
    