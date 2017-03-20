'use strict';

var tmpContractNum;
var tmpInvoice;

app.controller('InvoiceController', ['$scope','$filter', 'InvoiceService', 'SweetAlert', 
                                     function($scope, $filter, InvoiceService, SweetAlert) {
    var self = this;
    
    self.invoice={
    	id:null,
    	date: new Date(),
//    	contractNumber: '',
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
    
    self.currencyUnit = 'VND';
    
    function defaultValue() {
        $scope.currentPage = 0;
        $scope.pageSize = 5;    
        $scope.search = '';
       
        $scope.size = 5;
        //$scope.page = 1;
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

    defaultValue();
    fetchAllInvoice();
    fetchAllTypeInvoice();
    
    
    function changeTotal(){
    	calculate();
    }
    
    function getService(){    	 	
    	getServiceByName(self.invoice.nameService, self.invoice.idType.id);  
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
    
    
    $scope.getData = function () {
        
        return $filter('filter')( self.invoices, $scope.search)
       
      }
    
    $scope.numberOfPages=function(){
    	
    	// $scope.totalElements / pageSize
    	return Math.ceil($scope.totalElements/$scope.pageSize);
        //return Math.ceil($scope.getData().length/$scope.pageSize);                
    }

    function fetchAllInvoice(){
        InvoiceService.fetchAllInvoice($scope.size, $scope.currentPage)
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
            function(rs) {
            	console.log(rs);
            	self.services = rs;
            	//compare to get unit
            	for (var index in rs) {
                	if (self.invoice.nameService === rs[index].nameService) {
                		self.service.unit = rs[index].unit;
                	}
            	}
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
     
    // Trigger create invoice
    function createInvoice(invoice){
    	console.log("create Invoice: " + invoice);
        InvoiceService.createInvoice(invoice)
            .then(function(success){
            	//call service
            	fetchAllInvoice();
            	//alert success
            	SweetAlert.swal("Updated!", "Your invoice " + invoice.contractNumber + " has been created.", "success");
            },
            function(errResponse){
                console.error('Error while creating Invoice');
                SweetAlert.swal("Error!", "Error while creating invoice", "error");
            }
        );
    }
    
    //trigger update invoice
    function updateInvoice(invoice, id){    	
    	console.log(invoice);            
        InvoiceService.updateInvoice(invoice, id)
            .then(function(success) {
                fetchAllInvoice();
                SweetAlert.swal("Updated!", "Your invoice " + invoice.contractNumber + " has been updated.", "success");
            }, 
            function(errResponse){
                console.error('Error while updating Invoice');
                SweetAlert.swal("Error!", "Error while updating invoice", "error");
            }
        );
    	
    }
    
    //trigger remove invoice
    function deleteInvoice(id){
    	SweetAlert.swal({
        	title: "Are you sure?",
        	text: "You will remove invoice.",
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
				  InvoiceService.deleteInvoice(id)
	                .then(
		                function(success){
		                	SweetAlert.swal("Remove!", "Invoice has been removed.", "success");
		                	//document.myForm.set.disabled = true;
		                	$scope.isUpdate = false;
		                	fetchAllInvoice();
		                }),
	                	// document.myForm.set.disabled = true;
		                function(errResponse){
		                    console.error('Error while removing invoice');
		                    SweetAlert.swal("Error!", "Error while removing invoice", "error");
		                }
			  } else {
				    //Do nothing.
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
    //Trigger when click show detail
    function showDetail(type, invoice){    
    	//set IdType
    	self.invoice.idType = type.id; 
    	//set name service
    	$scope.nameInvoice = type.nameInvoice;
    	
    	//call service to load object invoice
    	InvoiceService.getID(invoice.id).then(
			function(d) {
	        	self.invoice = d; 
	        	self.invoice.date = new Date(self.invoice.date);
	        	//call service to load list service
	        	fetchAllService(type.id);
        	},
	        function(errResponse) {        	
	            console.error('Error while updating Invoice');
	        }
        );
    	
    	//Trigger hidden input indexConsumed when serivce is Internet Bill
    	if (type.code == 'IB' || type.code == 'PB') {
    		document.myForm.hidden = false;
    		document.getElementById('ptef2').hidden = true;
    		document.getElementById('service2').hidden = false;
    		document.getElementById('index2').hidden = true;
    	} else {
    		document.myForm.hidden = false;
    		document.getElementById('ptef2').hidden = true;
    		document.getElementById('service2').hidden = false;
    		document.getElementById('index2').hidden = false;
    	}

    }
    
    function remove(id){
        console.log('id to be deleted', id);
        if(self.invoice.id === id) {//clean form if the user to be deleted is shown there.
            reset();
        }
        deleteInvoice(id);
    }

    function reset(){
    	self.invoice={
    	    	id:null,
    	    	date:new Date(),
//    	    	contractNumber:'',
    	    	nameService:'',
    	    	indexConsumed:'',
    	    	total:'',    	    	
    	    	grandTotal:'',
    	    	idType:'',
    	    	idCpn:'',
    	    	idCustomer:'',
    	    };
        $scope.myForm.$setPristine(); //reset Form
    }
    
    //trigger when input contract number changed
    $scope.contractChanged = function() {
    	//console.log('contractChanged= ' + self.invoice.contractNumber);
    	if (self.invoice.contractNumber == undefined) {
    		console.log('tmpContractNum= ' + tmpContractNum);
    		
    		self.invoice.date = tmpContractNumdate;
    		//fncshowForm(tmpInvoice.idType.id, tmpInvoice);
    	} 
    	
    }
  
    //Trigger when click show form
    $scope.showForm = function(code, id){
    	fncshowForm(code, id);
    	console.log('open create modal');
    };
    
    function fncshowForm(code, id) {
    	//set idType
    	self.invoice.idType = id; 
    	//set VAT
    	self.invoice.vat = id.vat;
 		//set nameType	
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
    		
    		self.invoice.indexConsumed = 1;
    	} 	
    }
   
   

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
    }])
.directive('invoiceExist', function($http, $q, SweetAlert) {
	  return {
	    require: 'ngModel',
	    link: function(scope, element, attrs, ngModel) {
	    		ngModel.$asyncValidators.invoiceExist = function(modelValue, viewValue) {
	    		  return $http.get(REST_SERVICE_URI + "getName/"+ viewValue).then(
	    	        		function(response) {
	    	        			if (response.data.id != null) {
	    	        				tmpContractNum = viewValue;
	    	        				tmpInvoice = response.data;
		    	        			return $q.reject('Invoice can use.');
	    	        			}
	    	        			return true;
	    			        });
	    		      };
	    		    }
	  };
} );
app.filter('startFrom', function() {
    return function(input, start) {
        start = +start; //parse to int
        return input.slice(start);
    }
});

