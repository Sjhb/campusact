/**
 * 全局变量
 */
(function(){
	angular.module("activities").constant('env',{
		activities_api:'/campusact/',
		status_404:'404',
		status_500:'500',
		status_300:'300'
	});
	
})();