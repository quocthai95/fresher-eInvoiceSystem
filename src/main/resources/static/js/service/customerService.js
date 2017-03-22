app.factory('CustomerService', ['$http', '$q', function($http, $q){
	var factory = {
			getCustomer : getCustomer, 
	        updateCustomer:updateCustomer,
	        changePwd : changePwd,
	        getPwd : getPwd,
	    };
	return factory;
	
	  function getCustomer() {
	        var deferred = $q.defer();
	        $http.get(BASE_URL + "customer/get")
	            .then(
	            function (response) {
	                deferred.resolve(response.data);                
	            },
	            function(errResponse){
	                console.error('Error while fetching Customer');
	                deferred.reject(errResponse);
	            }
	        );
	        return deferred.promise;
	    }
	  
	  function updateCustomer(customer) {
	    	console.log(customer);
	        var deferred = $q.defer();
	        $http.post(BASE_URL + "customer/update", customer)
	            .then(
	            function (response) {
	                deferred.resolve(response.data);
	            },
	            function(errResponse){
	                console.error('Error while updating Customer');
	                deferred.reject(errResponse);
	            }
	        );
	        return deferred.promise;
	    }
	  function changePwd(user){
		  console.log(user);
		  var deferred = $q.defer();
		  $http.post(BASE_URL + "customer/updatepwd", user)
          .then(
          function (response) {
              deferred.resolve(response.data);
          },
          function(errResponse){
              console.error('Error while updating User');
              deferred.reject(errResponse);
          }
      );
      return deferred.promise;
	  }
	  
	  function getPwd(pwd){
		  console.log(pwd);
		  var deferred = $q.defer();
		  $http.post(BASE_URL + "customer/getPwd", pwd)
          .then(
          function (response) {
              deferred.resolve(response.data);
          },
          function(errResponse){
              console.error('Error while updating Pwd');
              deferred.reject(errResponse);
          }
      );
      return deferred.promise;
	  }
}]);