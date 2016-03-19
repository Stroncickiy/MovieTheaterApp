angular.module('register', ['ngRoute', 'auth']).controller(
		'register',
		function($scope, $route, auth) {

			$scope.user = {id:null,firstName:'',lastName:'',birthDate:null,email:'',password:'',enabled:true};
			$scope.registered = false;

			$scope.register = function() {
				auth.register($scope.user, function(registered) {
					if (registered) {
						console.log("register succeeded")
						$scope.registered = true;
						$scope.error = false;
						
					} else {
						console.log("Register failed")
						$scope.error = true;
					}
				})
			};
});
