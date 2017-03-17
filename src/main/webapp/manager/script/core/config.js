/**
 * 
 */
(function(){
	angular.module('activities').config(function($routeProvider,$httpProvider,$locationProvider){
		$httpProvider.defaults.withCredentials = true;//跨域
		var views=['allAct/allAct','a','b','createAct/create,organization','checkAct/check,admin','registe/registe'];
		var setRoutes = function(route) {
		    var l = route.indexOf(",");
		    //var view = l != -1 ? route.substring(0, l) : route;
			var view,config, url;
			if(l==-1){
				view=route;
				url = '/' + route;
				config = {
					templateUrl: 'manager/views/' + view + '.html'
				};
				$routeProvider.when(url, config);
			}else{
				view=route.substring(0,l);
				var permission=route.substring(l+1);
				url = '/' + view;
				config = {
					templateUrl: 'manager/views/' + view + '.html'
				};
				var role={
					permission:permission
				}
				$routeProvider.when(url, config, permission);
			}
		    return $routeProvider;
		};
		views.forEach(function(route) {
		    return setRoutes(route);
		});

		$routeProvider.when('/', {
			templateUrl: 'manager/views/allAct/allAct.html'
		}).when('/404', {
		    templateUrl: 'manager/views/error.html'
		}).
		otherwise({
		    redirectTo: '/404'
		});
		
	});
	
})();


