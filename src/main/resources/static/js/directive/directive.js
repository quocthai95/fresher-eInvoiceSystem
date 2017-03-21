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

app.directive('listUser', function() {
	return {
		templateUrl : "/EInvoice/views/admin/list.html"
	}
});

app.directive('userForm', function() {
	return {
		templateUrl : "/EInvoice/views/admin/user-form.html"
	}
});
//User directive
app.directive('listInvoice', function() {
	return {
		templateUrl : "/EInvoice/views/customer/listInvoice.html"
	}
});

app.directive('listReport', function() {
	return {
		templateUrl : "/EInvoice/views/customer/templatedListReport.html"
	}
});

app.directive('cusM', function() {
	return {
		templateUrl : "/EInvoice/views/customer/templatedCustomer.html"
	}
});

app.directive('chartTemplate', function() {
	return {
		templateUrl : "/EInvoice/views/customer/chartManagement.html"
	}
});

