
angular.module('myApp', ['ngTable', 'restangular'])
	.controller('userCtrl', function(NgTableParams, $scope, Restangular) {
	    
	    var data = Restangular.one('user').get().then(function(result) {
	    	$scope.tableParams = new NgTableParams({
		              page: 1, // show first page
		              count: 10 // count per page
		    }, { dataset: result});
	    }, function(response) {
			console.log("Error with status code", response.status);
		});
	    
});

