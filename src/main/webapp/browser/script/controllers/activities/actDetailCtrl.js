/**
 * Created by manlin on 2016/12/28.
 */
(function () {
    angular.module('activities').controller('actDetailCtrl', function ($uibModal,permission, messageService, activitiesResource, $scope, $uibModalInstance, activity) {
        $scope.activity = activity;
        $scope.ok = function () {
            $uibModalInstance.dismiss('cancel');
        }
        var actId = $scope.activity.id;
        $scope.engage = function () {
            var user = permission.getUser();
            if (user == null) {
                messageService("未登录！");
            }
            else if (user.role.detail != 'student') {
                messageService("只有学生才能报名！");
            }
            else  activitiesResource.activities_engage.save({id: actId}, function (res) {
                    if (res.status == 200) {
                        messageService("报名成功！");
                    } else {
                        messageService(res.message)
                    }
                });
        }
        $scope.showOrg=function(org) {
            $uibModal.open({
                templateUrl:'browser/views/org/orgDetail.html',
                controller:'OrgDetailCtrl',
                size:'lg',
                resolve:{
                    org:function () {
                        return org;
                    }
                }
            });
        }
    });
})();