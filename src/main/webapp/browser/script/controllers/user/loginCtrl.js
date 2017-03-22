/**
 * Created by manlin on 2016/12/28.
 */
(function() {
    angular.module('activities').controller('loginCtrl',['permission','activitiesResource','$scope','$uibModalInstance',loginCtrl]);
    function loginCtrl(permission,activitiesResource,$scope,$uibModalInstance) {

        $scope.user={
            password:'',
            username:''
        }
        $scope.worning='';
        $scope.changed=function () {
            $scope.worning='';
        }
        $(document).ajaxSend(function(e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
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
                        permission.setPermission(res.data.role);
                        console.log(res.data.role);
                        $scope.cancel();
                    }
                });
            };

        }
        $scope.cancel=function () {
            $uibModalInstance.dismiss('cancel');
        }
    }
})();