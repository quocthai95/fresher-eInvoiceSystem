
app.controller('ChartController',[ '$scope', '$filter', 'ReportService', function($scope, $filter, ReportService){
	var self = this;
    var reports=[];
    var eb = [];
    var pb = [];
    
    
   $scope.size='0';
   $scope.currentpage = '10';
    
    fetchAllReport();
//	 $scope.myData = [[1,4,5,5,10], [9,3,4,5,6],[11,8,5,5,10],[10,4,5,5,9]] ;
    function fetchAllReport(){
    	$scope.dateStart = '2016-01-10';
    	$scope.dateEnd = '2016-04-10';
        ReportService.fetchAllReport($scope.dateStart, $scope.dateEnd,$scope.size,  $scope.currentpage)
            .then(
            function(d) {
            	reports = d.content;	
            	//$scope.totalElements = d.totalElements;
            	//console.log("d.totalElements" + d.totalElements);
            	console.log('reports=' + reports);
            	
	            	for(var temp in reports){
	            		
	            		if(reports[temp].idType.code=="PB"){
	            			pb.push(reports[temp].grandTotal);
	            			eb.push(0);
		            		console.log(pb);
	            		}
	            		if(reports[temp].idType.code=="EB"){
	            			eb.push(reports[temp].grandTotal);
	            			pb.push(0);
		            		console.log(eb);
	            		}
	            		
	            	}
            	
            	
            	//reports.push(item);
            },
            function(errResponse){
                console.error('Error while fetching Invoice');
            }
        );
    }
    
    
    $scope.myData = [pb,eb];
	 $scope.myObj = {  
		      type : 'bar',  
		      "scale-x":{
//	                "min-value":1372982401000,
//	                "step":2629743000,
	                "transform":{
	                    "type":"date",
	                    "all":"%m.%d.%Y",
	                    
	                    
	                }
	            },
		      series:[  
		          {  
		              backgroundColor : "#FAEE00"  
		          } ,
		          {  
		              backgroundColor : "#A0FFEE"  
		          },  
		          {  
		              backgroundColor : "#ff0000"  
		          },  
		          {  
		              backgroundColor : "#00995c"  
		          }
		         
		        ]  
		    };  
	 $scope.myObj1 = {
			    type: "pie",
			    title: {
			      textAlign: 'center',
			      text: "Pie Chart"
			    },
			    plot: {
			      slice: 50 //to make a donut
			    },
			    series: [{
			      values: [3],
			      text: "Total Commits"

			    },
			    {
			      values: [4],
			      text: "Issues Solved"

			    }, {
			      values: [8],
			      text: "Issues Submitted"
			    }, {
			      values: [7],
			      text: "Number of Clones"

			    }]
			  };
}]);