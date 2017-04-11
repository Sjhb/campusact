/**
 * Created by manlin on 2016/12/28.
 */
/* navController*/
(function() {
    angular.module('activities').controller('navCtrl',['permission','$scope','$uibModal',navCtrl]).controller('logoutCtrl',['permission','$scope','$uibModalInstance',logoutCtrl]);
    function navCtrl(permission,$scope,$uibModal) {
       $scope.showModal=function () {//打开模态
           $uibModal.open({
                   templateUrl : 'browser/views/registe/icon.html',  //指向上面创建的视图
                   controller : 'iconCtrl',// 初始化模态范围
                   resolve : {
                      scope : function(){
                           return $scope;
                       },
                        message: function () {
                         return  "注册成功，你可以现在设定你的头像";
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
       function logoutCtrl(permission,$scope,$uibModalInstance){
            $scope.logOut=function () {
                permission.setPermission(null);
                $uibModalInstance.dismiss('cancel');
            }
            $scope.cancel=function () {
                $uibModalInstance.dismiss('cancel');
            }
       };

})();