

angular.module('myApp').controller('RegisterController', ['$scope', 'RegisterService', function($scope, RegisterService) {
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
    }
   
}]);
