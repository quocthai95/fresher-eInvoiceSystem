
app.controller('ChartController', function($scope){
    
	 $scope.myData = [[1,4,5,5,10], [9,3,4,5,6]] ;
	 $scope.myObj = {  
		      type : 'bar',  
		      series:[  
		          {  
		              backgroundColor : "#FAEE00"  
		          },  
		          {  
		              backgroundColor : "#A0FFEE"  
		          }  
		        ]  
		    };  
});