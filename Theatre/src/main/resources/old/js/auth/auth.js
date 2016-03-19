angular.module('auth', []).factory(
		'auth',

		function($rootScope, $http, $location) {

			enter = function() {
				if (($location.path() != auth.loginPath) && ($location.path() != auth.registerPath))  {
					auth.path = $location.path();
					if (!auth.authenticated) {
						$location.path(auth.loginPath);
					}
				}					
			}
			var auth = {
				authenticated : false,
				loginPath : '/login',
				logoutPath : '/logout',
				registerPath: '/register',
				homePath : '/',
				path : $location.path(),

                register : function(user,callback){
                var registered = false;
                $http.post('signup', user).success(function(user) {
                						if (user.id) {
                							registered = true;
                						} else {
                							registered = false;
                						}
                						callback && callback(registered);
                					}).error(function() {
                						registered = false;
                						callback && callback(false);
                					});
                },
				authenticate : function(credentials, callback) {
                $http({
                    method: "post",
                    url: "login",
                    data    : $.param({username:credentials.username,password:credentials.password}),
                    headers : { 'Content-Type': 'application/x-www-form-urlencoded' }
                    })
                    .success(function(data) {
                        console.log(data);
						if (data) {
							auth.authenticated = data.authenticated;
						} else {
							auth.authenticated = false;
						}
						callback && callback(auth.authenticated);
						$location.path(auth.path==auth.loginPath ? auth.homePath : auth.path);
					}).error(function() {
						auth.authenticated = false;
						callback && callback(false);
					});

				},
				checkAuthentication : function(callback) {
                                $http({
                                    method: "get",
                                    url: "user"})
                                    .success(function(data) {
                						if (data) {
                							auth.authenticated = data.authenticated;
                						} else {
                							auth.authenticated = false;
                						}
                						callback && callback(auth.authenticated);
                						$location.path(auth.path==auth.loginPath ? auth.homePath : auth.path);
                					}).error(function() {
                						auth.authenticated = false;
                						callback && callback(false);
                });
                },
				clear : function() {
					$location.path(auth.loginPath);
					auth.authenticated = false;
					$http.post(auth.logoutPath, {}).success(function() {
						console.log("Logout succeeded");
					}).error(function(data) {
						console.log("Logout failed");
					});
				},

				init : function(homePath, loginPath, logoutPath,registerPath) {

					auth.homePath = homePath;
					auth.loginPath = loginPath;
					auth.logoutPath = logoutPath;
					auth.registerPath = registerPath;

					auth.checkAuthentication(function(authenticated) {
						if (authenticated) {
							$location.path(auth.path);
						}
					})
					// Guard route changes and switch to login page if unauthenticated
					$rootScope.$on('$routeChangeStart', function() {
						enter();
					});

				}
			};

			return auth;

		});
