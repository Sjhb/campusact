/**
 * Created by manlin on 2017/1/4.
 */
(function () {
    angular.module('activities').controller('messageCtrl',['$scope','$uibModalInstance','message',messageCtrl]);
    function messageCtrl($scope,$uibModalInstance,message) {
        $scope.message=message;
        $scope.ok=function () {
            $uibModalInstance.dismiss('cancel');
        }
    }
})();