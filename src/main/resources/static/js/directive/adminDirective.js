var app = angular.module('dbApp');

app.directive('dbAdmin', function() {
	return {
		templateUrl : "/EInvoice/views/admin/dashboardAdmin.html"
	}
});
app.directive('dbMainAdmin', function() {
	return {
		templateUrl : "/EInvoice/views/admin/templatedbAdmin.html"
	}
});

app.directive('dbCustomer', function() {
	return {
		templateUrl : "/EInvoice/views/customer/dashboardCustomer.html"
	}
});

