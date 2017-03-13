
angular.module('dbApp').controller('InvoiceController', ['$scope', 'UserService', function($scope, UserService) {
    
	$scope.showEBForm = function(){
		document.myForm.hidden = false;
		document.getElementById('ptef').hidden = true;
		document.getElementById('service').hidden = true;
		document.getElementById('index').hidden = false;
    };
    $scope.showWBForm = function(){
    	
		document.myForm.hidden = false;
		document.getElementById('ptef').hidden = false;
		document.getElementById('service').hidden = true;
		document.getElementById('index').hidden = false;
		
    };
    $scope.showIBForm = function(){
		document.myForm.hidden = false;
		document.getElementById('ptef').hidden = true;
		document.getElementById('service').hidden = false;
		document.getElementById('index').hidden = true;
    };
    $scope.showPBForm = function(){
		document.myForm.hidden = false;
		document.getElementById('ptef').hidden = true;
		document.getElementById('service').hidden = false;
		document.getElementById('index').hidden = true;
    };

}]);
