var app = angular.module('dbApp');

app.controller('ReportController', [ '$scope', '$filter', 'ReportService', function($scope, $filter, ReportService) {
	var self = this;
    self.reports=[];
    
    function defaultValue() {
        $scope.currentPage = 0;
        $scope.pageSize = 5;    
        $scope.search = '';
       
        $scope.size = 5;
        //$scope.page = 1;
        $scope.totalElements = 0;
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
//    	$scope.dateStart = new Date('2016-01-10');
//    	$scope.dateEnd = new Date('2016-04-10');
//    	$scope.dateStart = (new Date($scope.dateStart)).toISOString();
//    	$scope.dateEnd = (new Date($scope.dateEnd)).toISOString();
        ReportService.fetchAllReport($scope.dateStart.toJSON().substring(0, 10), 
        		$scope.dateEnd.toJSON().substring(0, 10), 
        		$scope.currentPage, 
        		$scope.size)
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
//    	console.log(new Date().toJSON().substring(0, 10));
//    	console.log($scope.dateStart.toJSON().substring(0, 10))
    	fetchAllReport();
    }
    
}]);
