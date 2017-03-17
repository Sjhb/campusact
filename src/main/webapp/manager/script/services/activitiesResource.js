
/**
 * 
 */
(function(){
	angular.module("activities").factory('activitiesResource',['env','$resource',function(env,$resource){
		var resource={
				user_login:$resource(env.activities_api+'user/login'),
				user_search:$resource(env.activities_api+'user/search'),
				activities_all:$resource(env.activities_api+'activities/getall'),
				activities_waiting:$resource(env.activities_api+'activities/checkall'),
            	activities_createAct:$resource(env.activities_api+'activities/createact'),
           		activities_checkAct:$resource(env.activities_api+'activities/checkact')

		};
		return resource;
	}]);
	
	
	
	
}
)();