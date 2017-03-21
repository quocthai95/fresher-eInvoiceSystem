'use strict';
var REST_SERVICE_URI = 'http://localhost:8080/EInvoice/invoice/';

app.factory('InvoiceService', ['$http','$q','TypeInvoiceService',function($http, $q, TypeInvoiceService) {
			var factory = {
				fetchAllInvoice : fetchAllInvoice,
				createInvoice : createInvoice,
				updateInvoice : updateInvoice,
				fetchAllTypeInvoice : fetchAllTypeInvoice,
				getID : getID,
				deleteInvoice : deleteInvoice,
				fetchAllService : fetchAllService,
				getService : getService,
				checkContract : getContractNum,
		    	getCompany: getCpn,
			};

			return factory;
			// Service call api to get invoice 
			function fetchAllInvoice(search, size, page) {
				var deferred = $q.defer();
				$http.get(
						REST_SERVICE_URI + "getAll/search=" + search + "?size="
								+ size + "&page=" + page).then(
						function(response) {
							deferred.resolve(response.data);
						}, function(errResponse) {
							console.error('Error while fetching Invoice');
							deferred.reject(errResponse);
						});
				return deferred.promise;
			}
			// Service all api too get all type invoice
			function fetchAllTypeInvoice() {
				var deferred = $q.defer();
				TypeInvoiceService.fetchAll().then(function(d) {
					console.log(d);
					deferred.resolve(d);
				}, function(errResponse) {
					console.error('Error while fetching Invoice');
					deferred.reject(errResponse);
				});
				return deferred.promise;
			}
			// Service call api to get all service
			function fetchAllService(id) {
				var deferred = $q.defer();
				TypeInvoiceService.fetchAllService(id).then(function(d) {
					console.log(d);
					deferred.resolve(d);
				}, function(errResponse) {
					console.error('Error while fetching Invoice');
					deferred.reject(errResponse);
				});
				return deferred.promise;
			}
			// Service call api to get service
			function getService(name, id) {
				var deferred = $q.defer();
				TypeInvoiceService.getService(name, id).then(function(d) {
					console.log(d);
					deferred.resolve(d);
				}, function(errResponse) {
					console.error('Error while fetching Invoice');
					deferred.reject(errResponse);
				});
				return deferred.promise;
			}
			// Service call api to create invoice
			function createInvoice(invoice) {
				console.log("Service create: " + invoice);
				var deferred = $q.defer();
				$http.post(REST_SERVICE_URI + "create", invoice).then(
						function(response) {
							deferred.resolve(response.data);
						}, function(errResponse) {
							console.error('Error while creating Invoice');
							deferred.reject(errResponse);
						});
				return deferred.promise;
			}
			// Service call api to update invoice
			function updateInvoice(invoice, id) {
				console.log(invoice);
				var deferred = $q.defer();
				$http.post(REST_SERVICE_URI + "update/" + id, invoice).then(
						function(response) {
							deferred.resolve(response.data);
						}, function(errResponse) {
							console.error('Error while updating Invoice');
							deferred.reject(errResponse);
						});
				return deferred.promise;
			}
			// Service call api to remove invoice
			function deleteInvoice(id) {
				var deferred = $q.defer();
				$http.get(REST_SERVICE_URI + "delete/" + id).then(
						function(response) {
							deferred.resolve(response.data);
						}, function(errResponse) {
							console.error('Error while deleting Invoice');
							deferred.reject(errResponse);
						});
				return deferred.promise;
			}
			// Service call api to get id invoice
			function getID(id) {
				var deferred = $q.defer();
				$http.get(REST_SERVICE_URI + "get/" + id).then(
						function(response) {
							deferred.resolve(response.data);
						}, function(errResponse) {
							console.error('Error while fetching Invoice');
							deferred.reject(errResponse);
						});
				return deferred.promise;
			}
			// Service call api to check contract number is already exists in month.
			function getContractNum(num) {
				var deferred = $q.defer();
				$http.get(REST_SERVICE_URI + "getName/" + num).then(
						function(response) {
							deferred.resolve(response.data);
						}, function(errResponse) {
							console.error('Error while getContractNum Invoice');
							deferred.reject(errResponse);
						});
				return deferred.promise;
			}
		    function getCpn(id) {
		        var deferred = $q.defer();
		        $http.get(REST_SERVICE_URI + "getCpn/" + id)
		            .then(
		            function (response) {
		            	console.log("getCpn" + response.data);
		                deferred.resolve(response.data);                
		            },
		            function(errResponse){
		                console.error('Error while fetching getCpn');
		                deferred.reject(errResponse);
		            }
		        );
		        return deferred.promise;
		    }
		} ]);
