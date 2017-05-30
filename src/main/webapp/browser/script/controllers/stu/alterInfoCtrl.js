/**
 * Created by manlin on 2017/5/30.
 */
(function () {
    angular.module('activities').controller('alterInfoCtrl', ['activitiesResource','FileUploader','messageService','$scope','permission','modalService',alterInfoCtrl]);
    function alterInfoCtrl(activitiesResource,FileUploader,messageService,$scope,permission,modalService) {
        $scope.stu = {
            id:'',
            name:'',
            password:'',
            sex:'',
            phone:'',
            major:'',
            class:'',
            college:''
        }
        $scope.worning=false;
        $scope.messge='';
        $scope.confirm=function () {
            if($scope.stu.password!=$scope.confirmPass){
                $scope.worning=true;
            }else{$scope.worning=false;}
        }
        function init() {
            activitiesResource.user_getStu.save($scope.stu,function(res){
                if(res.status!=200){
                    messageService(res.message);
                }else if(res.status==200){
                $scope.stu=res.data;
                $scope.confirmPass=$scope.stu.password;
                }
            });
        }
        init();
        var options= {
            success: function (data) {
                if(data.status==200){
                    permission.setPermission('student');
                    messageService("修改成功");
                }
                else {
                    messageService(data.message);
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
        $scope.isSubmit=true;
        // 组织
        $scope.org_confirmPass='';
        $scope.confirmOrgPass=function () {
            if($scope.org.password!=$scope.org_confirmPass){
                $scope.worning=true;
            }else{$scope.worning=false;}
        }


    }
})();