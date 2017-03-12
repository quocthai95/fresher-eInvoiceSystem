
angular.module('dbApp').controller('CustomerController', ['$scope', 'UserService', function($scope, UserService) {
    
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

}]);
