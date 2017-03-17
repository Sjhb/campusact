/**
 * 
 */
(function(){
	angular.module("user").factory('userService',['env','$resource',function(env,$resource){
		var resource={
				user_login:$resource(env.activities_api+'user/login'),
				user_search:$resource(env.activities_api+'user/search')
		};
		return resource;
	}]);
	
	
	
	
}
)();