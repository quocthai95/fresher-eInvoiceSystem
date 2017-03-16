
app.controller('ChartController',[ '$scope', '$filter', 'ReportService', function($scope, $filter, ReportService){
	var self = this;
    var reports=[];
    var eb = [];
    var pb = [];
    var dt = [];
    
    
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
	            		//reports[temp].date = new Date(reports[temp].date);
	            		dt.push(reports[temp].date);
	            		console.log(dt);
	            		
	            		if(reports[temp].idType.code=="PB"){
	 
	            			pb.splice(temp,0,reports[temp].grandTotal);
	            			eb.push(1110);
		            		
	            		}
	            		if(reports[temp].idType.code=="EB"){
	            			eb.splice(temp,0,reports[temp].grandTotal);
	            			pb.push(1110);
		            		
	            		}
	            		
	            	}
	            	
	            	console.log(pb);
	            	console.log(eb);
            	
            	//reports.push(item);
            },
            function(errResponse){
                console.error('Error while fetching Invoice');
            }
        );
    }
    
    
  //  $scope.myData = [pb,eb];
	 $scope.myObj = {  
			
			                
			                    "type":"bar",
			                   
			                    "scale-x":{
			                        "min-value":dt,
			                        "step":2629743000,
			                       "transform":{
			                            "type":"date",
			                            "all":"%d.%m.%Y"
			                        }
			                    },
			                    "series":[
									{  'values': pb,
									    backgroundColor : "#FAEE00"  ,
									    	
									},  
									{  'values':eb,
									    backgroundColor : "#A0FFEE"  
									    	
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