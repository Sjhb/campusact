/**
 * 
 */
(function(){
    var app=angular.module('activities');
    app.run( function($scope, $location, permission) {
        $scope.$on('$routeChangeStart', function(scope, next, current) {
            var permissions = next.$$route.permission;
            if(_.isString(permissions) && !permission.hasPermission(permissions))
                $location.path('/404');
        });
    });
})();


