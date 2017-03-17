/**
 * Created by manlin on 2016/12/28.
 */
(function() {
    angular.module('activities').controller('actDetailCtrl',function($scope,$uibModalInstance,activity) {
        $scope.activity=activity;
        $scope.ok=function () {
            $uibModalInstance.dismiss('cancel');
        }
    });
})();