/**
 *
 */
(function () {
    angular.module('activities').controller('registeCtrl', ['$scope','permission','$location', registeCtrl]);
    function registeCtrl($scope,permission,$location) {
        $scope.stu = {
            // id:,
            // icon,name,password,sex,phone,major,class,college
        }
        $scope.worning=false;

        $scope.confirm=function () {
            if($scope.stu.password!=$scope.confirmPass){
             $scope.worning=true;
            }else{$scope.worning=false;}
        }
        var options= {
            success: function (data) {
                if(data.status==200){
                permission.setPermission('student');
               window.location="#/allAct/allAct"
                }
                else {

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