angular.module('message', []).controller('message', function($scope, $http) {

    $scope.bookTicket = function(){
    $http.get('/event/book/1').success(function(data) {
    		$scope.booked = data;
            updateData();
    	});
    };

     var updateData  = function(){
     $http.get('/discounts/total').success(function(data) {
            		$scope.allDiscounts = data;
     });
    $http.get('/discounts/users').success(function(data) {
                		$scope.usersSpecificDiscounts = data;
     });
     }
    updateData() ;
	$http.get('/event/STARWARS').success(function(data) {
		$scope.event = data;
	});
	$http.get('/event/calls').success(function(data) {
    		$scope.calls = data;
    });



});
