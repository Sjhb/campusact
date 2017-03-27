/**
 * Created by manlin on 2016/12/28.
 */
(function() {
    angular.module('activities').controller('actDetailCtrl',function($scope,$uibModalInstance,activity) {
        $scope.activity=activity;
        function inti(){
            $scope.organization_icon='/user/getIcon?role=organization&icon='+$scope.activity.organization.icon;
        }
        inti();
        $scope.ok=function () {
            $uibModalInstance.dismiss('cancel');
        }
    });
})();