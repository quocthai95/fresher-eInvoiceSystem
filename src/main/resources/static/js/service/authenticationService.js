var app = angular.module('myApp');
var URI_LOGIN = "http://localhost:8080/EInvoice/login";

app.factory('authInterceptor', function($location, $q) {
	return {
		'request' : function(config) {
			// alert(config.url);
			if (config.url.includes(URI_LOGIN)) {
				config.headers = {
					// 'x-session-token' : 'test',
					// '_csrf' : 'c5417bd7-fb1c-44a9-8f1f-de39cd051379',
					'Content-Type' : 'application/x-www-form-urlencoded',
				};
			} else {
				config.headers = config.headers || {};
			}
			return config;
		},
//		'response' : function(response) {
//			return response;
//		},
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
	var service = {};

	service.Authen = Authen;
	service.Logout = Logout;

	return service;

	function Authen(username) {
		console.log('come AuthenticationService');
		$window.localStorage.currentUser = {
			username : username
		}
//		alert('Authen');
	};

	function Logout() {
//		alert('Logout');
		// remove user from local storage and clear http auth header
		delete $window.localStorage.currentUser;
		// $http.defaults.headers.common.Authorization = '';
	}
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
