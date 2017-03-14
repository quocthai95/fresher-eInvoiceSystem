var app = angular.module('dbApp');

app.controller('ReportController', [ '$scope', '$filter', 'ReportService', function($scope, $filter, InvoiceService) {
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
    fetchAllReport();
    
    
    $scope.onEventPaging = function(value) {
    	$scope.size = value;
    	console.log('value ' + $scope.size + "-" + $scope.currentPage);
    	fetchAllInvoice();
    }
    
    $scope.onEventPreCurrentPage = function() {
    	$scope.currentPage = $scope.currentPage - 1;
    	console.log('value ' + $scope.size + "-" + $scope.currentPage);
    	fetchAllInvoice();
    }
    
    $scope.onEventNextCurrentPage = function() {
    	$scope.currentPage = $scope.currentPage + 1;
    	console.log('value ' + $scope.size + "-" + $scope.currentPage);
    	fetchAllInvoice();
    }
    
    
    $scope.getData = function () {
        
        return $filter('filter')( self.invoices, $scope.search)
       
    }
    
    $scope.numberOfPages=function(){
    	//console.log('$scope.totalElements ' + $scope.totalElements);
    	
    	// $scope.totalElements / pageSize
    	return Math.ceil($scope.totalElements/$scope.pageSize);
        //return Math.ceil($scope.getData().length/$scope.pageSize);                
    }

    function fetchAllReport(){
    	$scope.dateStart = '2016-01-10';
    	$scope.dateEnd = '2016-04-10';
        ReportService.fetchAllReport('1',id, $scope.dateStart, $scope.dateEnd, $scope.size, $scope.currentPage)
            .then(
            function(d) {
            	self.invoices = d.content;
            	$scope.totalElements = d.totalElements;
            	//console.log("d.totalElements" + d.totalElements);
            },
            function(errResponse){
                console.error('Error while fetching Invoice');
            }
        );
    }
    
}]);
