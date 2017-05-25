
/**
 * 
 */
(function(){
	angular.module("activities").factory('activitiesResource',['env','$resource',function(env,$resource){
		var resource={
				user_login:$resource(env.activities_api+'/user/login'),
				user_logout:$resource(env.activities_api+'user/logout'),
				user_search:$resource(env.activities_api+'user/search'),
				student_register:$resource(env.activities_api+'/student/register'),
				activities_all:$resource(env.activities_api+'activity/getall'),
				activities_waiting:$resource(env.activities_api+'activity/checkall'),
				activities_getActByOid:$resource(env.activities_api+'activity/getActByOid'),
				activities_getActByStuid:$resource(env.activities_api+'activity/getActByStuid'),
            	activities_createAct:$resource(env.activities_api+'activity/createact'),
           		activities_checkAct:$resource(env.activities_api+'activity/checkact'),
				activities_alterActPhoto:$resource(env.activities_api+'activity/alterActPhoto'),
				activities_engage:$resource(env.activities_api+'activity/engage'),
		};
		return resource;
	}]);
}
)();