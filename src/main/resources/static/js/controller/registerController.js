app.controller('RegisterController', function($scope, RegisterService, $location, SweetAlert) {
    var self = this;
    self.res={id:null,username:'',password:'',name:'', email:'', phone:''};
    self.users=[];

    self.submit = submit;
    
    function register(res){
    	RegisterService.register(res)
            .then(
            function(succes){
            	$scope.register_succes = "Register success!"
            	console.log('register success!');
            	
            	//alert
                SweetAlert.swal({
                    title: "Register successful!!", //Bold text
                    type: "success", //type -- adds appropiriate icon
                    confirmButtonColor: "blue",
                    confirmButtonText: "Click to login",
                }, 
                function(confirmButtonText){ //Function that triggers on user action.
                	$location.path('/login');
                });
            },
            function(errResponse){
                console.error('Error while creating User');
            }
        );
    }
    
    function submit() {
        if(self.res.id===null){
            console.log('Saving New User', self.res);
            register(self.res);
        }else{          
            console.log('User updated with id ', self.res.id);
        }   
        //alert('Đăng ký thành công! Vui lòng login để hoạt động');
    }
})
.directive('pwCheck', [function () {
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

.directive('emailNotUsed', function($http, $q) {
	  return {
	    require: 'ngModel',
	    link: function(scope, element, attrs, ngModel) {
	      ngModel.$asyncValidators.emailNotUsed = function(modelValue, viewValue) {
	        return $http.post('http://localhost:8080/EInvoice/user/getEmail/', viewValue).then(function(response) {
	        	if (response.data) {
	        		return $q.reject('Email is already used.');
	        	} else {
	        		return true;
	        	}
	          //return response.data == true ? $q.reject('Email is already used.') : true;
	        });
	      };
	    }
	  };
});
