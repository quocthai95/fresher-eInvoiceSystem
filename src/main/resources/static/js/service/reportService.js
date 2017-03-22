angular.module('dbApp').factory('ReportService', ['$http', '$q', function($http, $q){   
    var factory = {
    	fetchAllReport : fetchAllReport,
    };

    return factory;
    
    function fetchAllReport(start, end, size, page) {
        return $http.get(BASE_URL + "user/getReport/start="+ start + "&end=" + end)
            .then(function (response) {
                return response.data;              
            }
        );
    }
       
}]);
    