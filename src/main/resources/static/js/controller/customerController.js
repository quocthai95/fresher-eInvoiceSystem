
angular.module('dbApp').controller('CustomerController',function($scope, CustomerService) {
    
	 var self = this;
	    self.customer={id:null,idCustomer:'',nameCustomer:'',address:'',email:'',phone:'',taxCode:'',limitConsume:''};    
	     
	    fetchCustomer();

	    function fetchCustomer(){
	    	
	    		CustomerService.fetchCustomer()
		            .then(
		            function(d) {
		            	self.customer = d;
		            	$scope.totalElements = d.totalElements;
		            	//console.log("d.totalElements" + d.totalElements);
		            },
		            function(errResponse){
		                console.error('Error while fetching Users');
		            }
		        );	    			 	    	
	        
	    };
	    	    
});
