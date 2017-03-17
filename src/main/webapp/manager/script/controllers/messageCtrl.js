/**
 * Created by manlin on 2017/1/4.
 */
(function () {
    angular.module('activities').controller('messageCtrl',['$uibModalInstance','message',messageCtrl]);
    function messageCtrl($uibModalInstance,message) {
        $scope.message=message;
        $scope.ok=function () {
            $uibModalInstance.dismiss('cancel');
        }
    }
})();