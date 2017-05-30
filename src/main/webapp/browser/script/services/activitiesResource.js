
/**
 * 
 */
(function(){
	angular.module("activities").factory('activitiesResource',['env','$resource',function(env,$resource){
		var resource={
				user_login:$resource(env.activities_api+'/user/login'),
				user_logout:$resource(env.activities_api+'user/logout'),
				user_search:$resource(env.activities_api+'user/search'),
				user_getStu:$resource(env.activities_api+'user/getStu'),
				student_register:$resource(env.activities_api+'/student/register'),
				organization_register:$resource(env.activities_api+'/organization/register'),
				organization_getAllOrg:$resource(env.activities_api+'/organization/getAllOrg'),
				organization_getOrgDocu:$resource(env.activities_api+'/organization/getOrgDocu'),
				organization_getOrgIcon:$resource(env.activities_api+'/organization/getOrgIcon'),
				organization_checkOrg:$resource(env.activities_api+'/organization/checkOrg'),
				organization_deleteOrg:$resource(env.activities_api+'/organization/deleteOrg'),
				activities_all:$resource(env.activities_api+'activity/getall'),
				activities_waiting:$resource(env.activities_api+'activity/checkall'),
				activities_getActByOid:$resource(env.activities_api+'activity/getActByOid'),
				activities_getActByStuid:$resource(env.activities_api+'activity/getActByStuid'),
            	activities_createAct:$resource(env.activities_api+'activity/createact'),
           		activities_checkAct:$resource(env.activities_api+'activity/checkact'),
				activities_alterActPhoto:$resource(env.activities_api+'activity/alterActPhoto'),
				activities_engage:$resource(env.activities_api+'activity/engage'),
				activities_checkAuth:$resource(env.activities_api+'activity/checkAuth'),
		};
		return resource;
	}]);
}
)();