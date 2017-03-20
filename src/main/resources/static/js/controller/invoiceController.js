'use strict';

var app = angular.module('dbApp');

app.controller('InvoiceController', ['$scope','$filter', 'InvoiceService', 'SweetAlert',
                                     function($scope, $filter, InvoiceService, SweetAlert) {
    var self = this;
    
    self.invoice={
    	id:null,
    	date: new Date(),
    	contractNumber:'',
    	nameService:'',
    	indexConsumed:'',
    	total:0,
    	vat:'',
    	ptef:10,
    	grandTotal:0,
    	idType:'',
    	idCpn:'',
    	idCustomer:'',
    	
    }; 
    
         
    self.typeInvoice={
        	id:null,
        	code:'',
        	nameInvoice:'',
        	description:'',        	
        	vat:'',        	
        }; 
    
    self.service={
        	id:null,
        	nameService:'',
        	unit:'',
        	idType:'',        	        	      	
        }; 
    
    self.invoices=[];
    
    self.typeInvoices=[];
    
    self.services=[];
    
    function defaultValue() {
        $scope.currentPage = 0;
        $scope.pageSize = 5;    
        $scope.search = '';
       
        $scope.size = 5;
        // $scope.page = 1;
        $scope.totalElements = 0;
                     
        self.invoice.ptef = 10;        
                
    }

    
    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;    
    self.update = updateInvoice;
    self.showDetail = showDetail;
    self.changeTotal = changeTotal;
    self.getService = getService;
	self.deleteInvoice = deleteInvoice;
	self.searchInvoice = searchInvoice;

    defaultValue();
    fetchAllInvoice();
    fetchAllTypeInvoice();
    
    
    function changeTotal(){
    	calculate();
    }
    
    function getService(){    	 	
    	getServiceByName(self.invoice.nameService, self.invoice.idType.id)  
    }
    
    function calculate(){
    	var temp;
		temp = self.invoice.indexConsumed;
    	self.invoice.total = temp * self.service.unit;
    	self.invoice.grandTotal = self.invoice.total + ((self.invoice.total * self.invoice.vat)/100);
    }
    
    
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
    
    function searchInvoice(){
    	$scope.currentPage = 0;
    	$scope.search = $scope.search;
    	fetchAllInvoice();
    }
           
    $scope.numberOfPages=function(){   	   	
    	return Math.ceil($scope.totalElements/$scope.pageSize);       
    }

    function fetchAllInvoice(){
        InvoiceService.fetchAllInvoice($scope.search, $scope.size, $scope.currentPage)
            .then(
            function(d) {

            	self.invoices = d.content; 
            	$scope.totalElements = d.totalElements;            	
            },
            function(errResponse){
                console.error('Error while fetching Invoice');
            }
        );
    }
    

    function fetchAllTypeInvoice(){
    	InvoiceService.fetchAllTypeInvoice()
            .then(
            function(d) {
            	console.log(d);
            	self.typeInvoices = d;            	
            },
            function(errResponse){
                console.error('Error while fetching Invoice');
            }
        );
    }
    
    function fetchAllService(id){
    	InvoiceService.fetchAllService(id)
            .then(
            function(d) {
            	console.log(d);
            	self.services = d;            	
            },
            function(errResponse){
                console.error('Error while fetching Invoice');
            }
        );
    }
    
    function getServiceByName(name, id){
    	InvoiceService.getService(name, id)
            .then(
            function(d) {
            	console.log(d);
            	self.service = d;
            	calculate();
            },
            function(errResponse){
                console.error('Error while fetching Invoice');
            }
        );
    }
     

    function createInvoice(invoice){
    	console.log("create Invoice: " + invoice);
        InvoiceService.createInvoice(invoice).then(function(success){
        	SweetAlert.swal("Create!", "Your invoice has been created.", "success");
        	fetchAllInvoice();
        },
        function(errResponse){
            SweetAlert.swal("Error!", "Error while creating Invoice", "error");
        	}
        );
    }

    function updateInvoice(invoice, id){    	
    	console.log(invoice);       
		InvoiceService.updateInvoice(invoice, id).then(function(succes) {
        	//alert
			SweetAlert.swal("Update!", "Your invoice has been updated.", "success");
			
			fetchAllInvoice();
			reset();

			}, function(errResponse){
				SweetAlert.swal("Error!", "Error while updating invoice", "error");
			});
	}

    function deleteInvoice(id){
        SweetAlert.swal({
        	title: "Are you sure?",
        	text: "You will remove your invoice.",
        	type: "warning",
        	showCancelButton: true,
        	confirmButtonColor: "#DD6B55",
        	confirmButtonText: "Yes, remove it!",
        	cancelButtonText: "No, cancel plx!",
        	closeOnConfirm: false,
        	closeOnCancel: true
        }, 
        function(isConfirm){ // Function that triggers on user action.
    		// var r = confirm("Are you sure!");
    		// console.log(self.cus);
			  if (isConfirm) {
                InvoiceService.deleteInvoice(id).then(function(succes){
                	SweetAlert.swal("Remove!", "Your invoice has been removed.", "success");
                	fetchAllInvoice();
                }),function(errResponse){
                	SweetAlert.swal("Error!", "Error while deleting invoice", "error");
                }
			  } else {
				    // Do nothing.
			  }
        }); 
    }

    function submit() {
        if(self.invoice.id===null){
            console.log('Saving New Invoice', self.invoice);
            createInvoice(self.invoice);
        }else{
            updateInvoice(self.invoice, self.invoice.id);
            console.log('User updated with id ', self.invoice.id);
        }
        reset();

    }

    function edit(id){
        console.log('id to be edited', id);
        for(var i = 0; i < self.invoices.length; i++){
            if(self.invoices[i].id === id) {
                self.invoice = angular.copy(self.invoices[i]);
               
                break;
            }
        }
    }
    function showDetail(type, id){    
    	self.invoice.idType = type;
    	fetchAllService(type)
    	if(type.code == 'EB')
    	{
    		document.myForm.hidden = false;
    		document.getElementById('ptef2').hidden = true;

    		document.getElementById('service2').hidden = false;
    		document.getElementById('index2').hidden = false;
    	}	

    	if(type.code == 'WB')
    	{
    		document.myForm.hidden = false;
    		document.getElementById('ptef2').hidden = false;

    		document.getElementById('service2').hidden = false;
    		document.getElementById('index2').hidden = false;
    	}   

    	if(type.code == 'IB')
    	{
    		document.myForm.hidden = false;
    		document.getElementById('ptef2').hidden = true;
    		document.getElementById('service2').hidden = false;
    		document.getElementById('index2').hidden = true;
    		self.invoice.indexConsumed = 1;
    	} 

    	if(type.code == 'PB')
    	{
    		document.myForm.hidden = false;
    		document.getElementById('ptef2').hidden = true;
    		document.getElementById('service2').hidden = false;
    		document.getElementById('index2').hidden = true;
    		self.invoice.indexConsumed = 1;
    	} 
    	InvoiceService.getID(id)
        .then(
        		function(d) {
        			
                	self.invoice = d; 
                	self.invoice.date = new Date(self.invoice.date);
                	
                	// console.log("d.totalElements" + d.totalElements);
                },
        function(errResponse){
        	
            console.error('Error while updating Invoice');
        }
    );
    }
    
    function remove(id){
        console.log('id to be deleted', id);
        if(self.invoice.id === id) {// clean form if the user to be deleted is
									// shown there.
            reset();
        }
        deleteInvoice(id);
    }

    function reset(){
        self.invoice={
            	id:null,
            	date: new Date(),
            	contractNumber:'',
            	nameService:'',
            	indexConsumed:'',
            	total:0,
            	vat:'',
            	ptef:10,
            	grandTotal:0,
            	idType:'',
            	idCpn:'',
            	idCustomer:'',
            }; 
        $scope.myForm.$setPristine(); // reset Form
    }
  
    
    $scope.showForm = function(code, id){
    	self.invoice.idType = id; 
    	self.invoice.vat = id.vat;
 			
    	$scope.name_type = id.nameInvoice;    
    	fetchAllService(id.id)
    	if(code == 'EB')
    	{
    		document.myForm.hidden = false;
    		document.getElementById('ptef').hidden = true;

    		document.getElementById('service').hidden = false;
    		document.getElementById('index').hidden = false;
    	}	
    	if(code == 'WB')
    	{
    		document.myForm.hidden = false;
    		document.getElementById('ptef').hidden = false;

    		document.getElementById('service').hidden = false;
    		document.getElementById('index').hidden = false;
    	}   
    	if(code == 'IB')
    	{
    		document.myForm.hidden = false;
    		document.getElementById('ptef').hidden = true;
    		document.getElementById('service').hidden = false;
    		document.getElementById('index').hidden = true;
    		
    		self.invoice.indexConsumed = 1;
    	} 
    	if(code == 'PB')
    	{
    		document.myForm.hidden = false;
    		document.getElementById('ptef').hidden = true;
    		document.getElementById('service').hidden = false;
    		document.getElementById('index').hidden = true;
    	} 		
    };
   
   

	$scope.clear = function(){
		reset();
		document.getElementById('btnset').disabled = true;
		document.getElementById('btn').value ='Edit';
		console.log('clear form');
		
		self.service.unit = 0;
	}
	$scope.deleteinvoice = function(id){
		deleteInvoice(id);
		console.log('delete success' + id);
		
	}
	
 $scope.myEnable = function(id){
    	    if(document.getElementById('btn').value == 'Edit'){
    		document.getElementById('btnset').disabled = false;
    		document.getElementById('btn').value ='Update';
    		console.log('enabled form');
    	    } else {
    	    	updateInvoice(self.invoice, id);
    	    	$('.modal-backdrop').remove();
        		console.log('update success!!');
    	    }
        };
    }]);


app.filter('startFrom', function() {
    return function(input, start) {
        start = +start; // parse to int
        return input.slice(start);
    }

});