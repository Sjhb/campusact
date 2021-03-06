/**
 * Created by manlin on 2016/12/28.
 */
(function() {
    angular.module('activities').controller('loginCtrl',['$location','permission','activitiesResource','$scope','$uibModalInstance',loginCtrl]);
    function loginCtrl($location,permission,activitiesResource,$scope,$uibModalInstance) {

        $scope.user={
            password:'',
           id:''
        }
        $scope.worning='';
        $scope.changed=function () {
            $scope.worning='';
        }

        $scope.check={isEmpty:false,
                    check:function () {
                            this.isEmpty=false;
                            _.each($scope.user,function(item) {
                              if(item=='') {$scope.worning='请填写完整信息'; $scope.check.isEmpty=true;}
                         });
                     }}
        $scope.login=function () {
            $scope.check.check();
            if(!$scope.check.isEmpty){
                activitiesResource.user_login.save($scope.user,function (res) {
                    $scope.message=res.message;
                    if(res.status==401){
                        $scope.worning='用户名或者密码不对';
                    }else if (res.status==200){
                        permission.setPermission(res.data.role.detail);
                        permission.setUser(res.data);
                        $scope.cancel();
                        $location.path('/allAct/allAct');

                    }
                });
            };

        }
        $scope.cancel=function () {
            $uibModalInstance.dismiss('cancel');
        }
    }
})();