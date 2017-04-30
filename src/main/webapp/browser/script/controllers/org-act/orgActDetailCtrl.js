/**
 * Created by Manlin on 2017/4/30.
 */
(function () {
    angular.module('activities').controller('orgActDetailCtrl', function ( $scope, $uibModalInstance, activity) {
        $scope.activity = activity;
        $scope.ok = function () {
            $uibModalInstance.dismiss('cancel');
        }
    });
})();