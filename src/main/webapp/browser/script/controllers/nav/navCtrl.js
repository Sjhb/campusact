/**
 * Created by manlin on 2016/12/28.
 */
/* navController*/
(function() {
    angular.module('activities').controller('navCtrl',['permission','$scope','$uibModal',navCtrl])
        .controller('logoutCtrl',['$location','activitiesResource','permission','$scope','$uibModalInstance',logoutCtrl])
        .controller('resetPassCtrl',['messageService','$location','activitiesResource','permission','$scope','$uibModalInstance',resetPassCtrl]);
    function navCtrl(permission,$scope,$uibModal) {
       $scope.showModal=function () {//打开模态
           $uibModal.open({
                   templateUrl : 'browser/views/user/login.html',  //指向上面创建的视图
                   controller : 'loginCtrl',// 初始化模态范围
                   resolve : {
                      scope : function(){
                           return $scope;
                       }
                   }
               })
           }
           $scope.resetPass=function () {//打开模态
           $uibModal.open({
                   templateUrl : 'browser/views/user/requestResetPass.html',  //指向上面创建的视图
                   controller : 'resetPassCtrl',// 初始化模态范围
                   resolve : {
                      scope : function(){
                           return $scope;
                       }
                   }
               })
           }
       $scope.logOut=function () {
    	   $uibModal.open({
               templateUrl : 'logoutConfirm.html',  //指向上面创建的视图
               controller : 'logoutCtrl',// 初始化模态范围
               resolve : {
                  scope : function(){
                       return $scope;
                   }
               }
           })
        }
       };
       function logoutCtrl($location,activitiesResource,permission,$scope,$uibModalInstance){
           var user={};
            $scope.logOut=function () {
                permission.setPermission(null);
                activitiesResource.user_logout.save(user,function (data) {
                    if(data.status==200){
                        $uibModalInstance.dismiss('cancel');
                        $location.path('/allAct/allAct')
                    }
                });
            }
            $scope.cancel=function () {
                $uibModalInstance.dismiss('cancel');
            }
       }
       function resetPassCtrl(messageService,$location,activitiesResource,permission,$scope,$uibModalInstance){
           $scope.user={id:''};
           $scope.submit=function(){
                activitiesResource.user_requestResetPass.save($scope.user,function (res) {
                        $scope.cancel();
                        messageService(res.message);
                });
            }
            $scope.cancel=function () {
                $uibModalInstance.dismiss('cancel');
            }
       };

})();