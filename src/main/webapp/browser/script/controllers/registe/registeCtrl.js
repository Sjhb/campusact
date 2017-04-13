/**
 *
 */
(function () {
    angular.module('activities').controller('registeCtrl', ['messageService','$scope','permission','modalService',registeCtrl]);
    function registeCtrl(messageService,$scope,permission,modalService) {
        $scope.stu = {
            // id:,
            // icon,name,password,sex,phone,major,class,college
        }
        $scope.worning=false;
        $scope.messge='';
        $scope.confirm=function () {
            if($scope.stu.password!=$scope.confirmPass){
             $scope.worning=true;
            }else{$scope.worning=false;}
        }
        var options= {
            success: function (data) {
                if(data.status==200){
                permission.setPermission('student');
                modalService( 'browser/views/registe/icon.html','iconCtrl','注册成功，你可以现在设定你的头像');
                }
                else {
                    var message=data.message;
                    messageService(message);
                }
            },
            beforeSubmit:function () {
                if($scope.stu.password!=$scope.confirmPass){
                    return false;
                }else {
                    return true;
                }
            },
        }
        $("#stu_form").ajaxForm(options);


    }
})();