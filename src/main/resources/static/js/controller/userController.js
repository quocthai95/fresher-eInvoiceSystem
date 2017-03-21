'use strict';

//var app = angular.module('dbApp');

app.controller('UserController', ['$scope','$filter', 'UserService', function($scope, $filter, UserService) {
    var self = this;
    self.user={id:null,
    		username:'',
    		password:'',
    		active: ''
    };    
        
    self.users=[];
    
    
    function defaultValue() {
        $scope.currentPage = 0;
        $scope.pageSize = 5;    
        $scope.search = '';
        $scope.status = 'all';
       
        $scope.size = 1;       
        $scope.totalElements = 0;
    }

    
    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;    
    self.update = updateUser;
    self.filterActive = filterActive;
    self.searchUser = searchUser;
    

    defaultValue();
    fetchAllUsers();
    
    
    $scope.onEventPaging = function(value) {
    	$scope.currentPage = 0;
    	$scope.pageSize = value;
    	console.log('value ' + $scope.pageSize + "-" + $scope.currentPage);
    	fetchAllUsers();
    }
    
    $scope.onEventPreCurrentPage = function() {
    	$scope.currentPage = $scope.currentPage - 1;
    	console.log('value ' + $scope.pageSize + "-" + $scope.currentPage);
    	fetchAllUsers();
    }
    
    $scope.onEventNextCurrentPage = function() {
    	$scope.currentPage = $scope.currentPage + 1;
    	console.log('value ' + $scope.pageSize + "-" + $scope.currentPage);
    	fetchAllUsers();
    }
    
    function searchUser(){
    	$scope.currentPage = 0;
    	$scope.search = $scope.search;
    	fetchAllUsers();
    }
    
    function filterActive(status){
    	$scope.currentPage = 0;
    	$scope.status = status;    	
    	fetchAllUsers();    	
    }
    
    $scope.DoCtrlPagingAct = function(text, page, pageSize, total) {
    	
    	$scope.currentPage = page - 1;   	
    	fetchAllUsers();
        console.log({
            text: text,
            page: page,
            pageSize: pageSize,
            total: total
        });
    };
          
    
    $scope.numberOfPages=function(){    	
    	return Math.ceil($scope.totalElements/$scope.pageSize);                       
    }

    function fetchAllUsers(){
    	UserService.fetchAllUsers($scope.status, $scope.search, $scope.pageSize, $scope.currentPage)
        .then(
	        function(d) {
	        	self.users = d.content;
	        	for(var u in self.users){
	        		var bool = self.users[u].active === '1' ? true : false;
	        		self.users[u].active = bool;
	        	}
	        	$scope.totalElements = d.totalElements;	        	
	        },
	        function(errResponse){
	            console.error('Error while fetching Users');
	        }
	    );
        
    }
    
   
    function createUser(user){
        UserService.createUser(user)
            .then(
            fetchAllUsers,
            function(errResponse){
                console.error('Error while creating User');
            }
        );
    }

    function updateUser(user, id){    	
    	UserService.updateUser(user, id)
        .then(
	        fetchAllUsers,
	        function(errResponse){
	            console.error('Error while updating User');
	        }
        );
    }

    function deleteUser(id){
        UserService.deleteUser(id)
            .then(
            fetchAllUsers,
            function(errResponse){
                console.error('Error while deleting User');
            }
        );
    }

    function submit() {
        if(self.user.id===null){
            console.log('Saving New User', self.user);
            createUser(self.user);
        }else{
            updateUser(self.user, self.user.id);
            console.log('User updated with id ', self.user.id);
        }
        reset();
    }

    function edit(id){
        console.log('id to be edited', id);
        for(var i = 0; i < self.users.length; i++){
            if(self.users[i].id === id) {
                self.user = angular.copy(self.users[i]);
               
                break;
            }
        }
    }
    

    function remove(id){
        console.log('id to be deleted', id);
        if(self.user.id === id) {//clean form if the user to be deleted is shown there.
            reset();
        }
        deleteUser(id);
    }

    function reset(){
        self.user={id:null,username:'',password:'',active:''};
        $scope.myForm.$setPristine(); //reset Form
    }
    
   
}]);
app.directive('pwCheck', [function () {
    return {
      require: 'ngModel',
      link: function (scope, elem, attrs, ctrl) {
        var firstPassword = '#' + attrs.pwCheck;
        elem.add(firstPassword).on('keyup', function () {
          scope.$apply(function () {
            var v = elem.val()===$(firstPassword).val();
            ctrl.$setValidity('pwmatch', v);
          });
        });
      }
    }
}])

