var app = angular.module('dbApp');

app.controller('ReportController', [ '$scope', '$filter', 'ReportService', function($scope, $filter, ReportService) {
	var self = this;
    self.reports=[];
    
    function defaultValue() {
        $scope.currentPage = 0;
        $scope.pageSize = '5';    
        $scope.search = '';
        $scope.dateStart = new Date();
        $scope.dateEnd = new Date();
        $scope.size = 5;
        //$scope.page = 1;
        $scope.totalElements = 0;;
    }


    defaultValue();
    //fetchAllReport();
    
    
    $scope.onEventPaging = function(value) {
    	$scope.size = value;
    	console.log('value ' + $scope.size + "-" + $scope.currentPage);
    	fetchAllReport();
    }
    
    $scope.onEventPreCurrentPage = function() {
    	$scope.currentPage = $scope.currentPage - 1;
    	console.log('value ' + $scope.size + "-" + $scope.currentPage);
    	fetchAllReport();
    }
    
    $scope.onEventNextCurrentPage = function() {
    	$scope.currentPage = $scope.currentPage + 1;
    	console.log('value ' + $scope.size + "-" + $scope.currentPage);
    	fetchAllReport();
    }
    
    
    $scope.getData = function () {
        return $filter('filter')( self.reports, $scope.search)
    }
    
    $scope.numberOfPages=function(){
    	//console.log('$scope.totalElements ' + $scope.totalElements);
    	
    	// $scope.totalElements / pageSize
    	return Math.ceil($scope.totalElements/$scope.pageSize);
        //return Math.ceil($scope.getData().length/$scope.pageSize);                
    }

    function fetchAllReport(){
    	var dateStart = new Date($scope.dateStart).toISOString().slice(0, 10);
    	var dateEnd = new Date($scope.dateEnd).toISOString().slice(0, 10);
        ReportService.fetchAllReport(dateStart, dateEnd, $scope.currentPage, $scope.size)
            .then(
            function(d) {
            	self.reports = d.content;	
            	$scope.totalElements = d.totalElements;
            	//console.log("d.totalElements" + d.totalElements);
            },
            function(errResponse){
                console.error('Error while fetching Invoice');
            }
        );
    }
    
    $scope.btnReport = function() {
        $scope.currentPage = 0;
        $scope.pageSize = '5';    
        $scope.size = 5;
        $scope.totalElements = 0;
    	
    	fetchAllReport();
    }
    
}]);
