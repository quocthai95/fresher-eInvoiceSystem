
'use strict';
angular.module('dbApp').controller('CustomerController', ['$scope', 'CustomerService', function($scope, CustomerService) {
	 var self = this;
	 
	$scope.myEnable = function(){
	/*	document.myForm.name.disabled = false;
		document.myForm.email.disabled = false;
		document.myForm.address.disabled = false;
		document.myForm.phone.disabled = false;
		document.myForm.taxcode.disabled = false;
		document.myForm.limit.disabled = false;
		document.myForm.btn.disabled = false;*/
		document.myForm.set.disabled = false;
		console.log('enabled form');
    };
    
   
    self.cus={
    		id:null,
    		idCustomer:'',
    		nameCustomer:'',
    		address:'',
    		email:'',
    		phone:'',
    		taxCode:'',
    		limitConsume:''
    		}; 
    
   
    self.users=[];
       
    self.edit = edit;
    self.updateCustomer = updateCustomer;
    //seft.getCustomer = getCustomer;
    
    
    getCustomer();
   
    function getCustomer(){
    	CustomerService.getCustomer()
            .then(
            function(d) {

            	self.cus = d; 
            	
            	//console.log("d.totalElements" + d.totalElements);
            },
            function(errResponse){
                console.error('Error while fetching Customer');
            }
        );
    }
    
    function updateCustomer(id){    	
    	var r = confirm("Are you sure!");
    	if (r == true) {
    		console.log(self.cus);
            CustomerService.updateCustomer(self.cus, id)
                .then(
                function(succes){
                	console.log("Update success")
                	document.myForm.set.disabled = true;
                },
                function(errResponse){
                    console.error('Error while updating Customer');
                }
            );
    	} else {
    		//fetchCustomer();
    	}
    	
    }
    
    function edit(id){
        console.log('id to be edited', id);
        for(var i = 0; i < self.users.length; i++){
            if(self.users[i].id === id) {
                self.cus = angular.copy(self.users[i]);             
                break;
            }
        }
    }
	    	    
});
