var app = angular.module('myApp');
var URI_LOGIN = "http://localhost:8080/EInvoice/login";

app.factory('authInterceptor', function($location, $q) {
	return {
		'request' : function(config) {
			// alert(config.url);
			if (config.url.includes(URI_LOGIN)) {
				config.headers = {
					'Content-Type' : 'application/x-www-form-urlencoded',
				};
			} else {
				config.headers = config.headers || {};
			}
			return config;
		},
		// 'response' : function(response) {
		// return response;
		// },
		'responseError' : function(response) {
			if (response && response.status === 404) {
			}
			if (response && response.status >= 500) {
			}
			// return $q.reject(response);
			return response;
		}
	};
})
app.config(function($httpProvider) {
	$httpProvider.interceptors.push('authInterceptor');
})

app.factory('AuthenticationService', Service);

function Service($http, $window) {
	var userIsAuthenticated = false;
	var service = {};
	service.setUserAuthenticated = setUserAuthenticated;
	service.getUserAuthenticated = getUserAuthenticated;
	service.Logout = Logout;
	return service;

	function Logout() {
		// remove user from local storage and clear http auth header
		delete $window.localStorage.currentUser;
		// $http.defaults.headers.common.Authorization = '';
	}
	function getUserAuthenticated() {
		return userIsAuthenticated;
	}
	function setUserAuthenticated(username) {
		console.log('go on setUserAuthenticated');
		$window.localStorage.currentUser = {
			username : username
		}
		userIsAuthenticated = true;
	}
	;
}

// app.factory('httpInterceptor', function($q) {
// return function(promise) {
// return promise.then(function(res) {
// // do something on success
//
// return res;
// }, function(res) {
// // everytime redirecting to on condition
// if (res.status == 404 || res.status == 301) {
// $window.location.hash = '/error';
// return;
// }
// return $q.reject(res);
// });
// };
// });
// app.config(function($httpProvider) {
// $httpProvider.interceptors.push('httpInterceptor');
// });
