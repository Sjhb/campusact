/**
 *
 */
(function () {
    angular.module('activities').controller('registeCtrl', ['activitiesResource','FileUploader','messageService','$scope','permission','modalService',registeCtrl]);
    function registeCtrl(activitiesResource,FileUploader,messageService,$scope,permission,modalService) {
        $scope.isActive='stu';
        $scope.shiftRole=function(role) {
            $scope.isActive=role;
        }
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

        $scope.org={
            //name,mail,phone,password,address,detail
        }
        $scope.isSubmit=true;
        // 组织
        $scope.org_confirmPass='';
        $scope.confirmOrgPass=function () {
            if($scope.org.password!=$scope.org_confirmPass){
                $scope.worning=true;
            }else{$scope.worning=false;}
        }

        //组织头像提交
        var up_icon = $scope.up_icon= new FileUploader({
            url: '/organization/alterIcon',
            queueLimit: 1,
            removeAfterUpload: true
        })
        up_icon.filters.push({
            name: 'imageFilter',
            fn: function (item /*{File|FileLikeObject}*/, options) {
                var type = '|' + item.type.slice(item.type.lastIndexOf('/') + 1) + '|';
                return '|jpg|png|jpeg|bmp|'.indexOf(type) !== -1;
            }
        });
        up_icon.filters.push({
            name: 'imgSizeFilter',
            fn: function (item) {
                return item.size / 1024 / 1024 < 1;
            }
        });
        $scope.clearIcon= function () {
            up_icon.clearQueue();
            $scope.remove_Icon= false;
            $scope.limit_icon = false;
        }
        // 是否禁用添加按钮
        $scope.limit_icon=false;
        // 是否显示移除按钮
        $scope.remove_Icon = false;
        // 从末尾移除图片
        $scope.removeIcon= function () {
            up_icon.queue.pop();
            if (up_icon.queue.length == 0) {
                $scope.remove_Icon= false;
            }
            if (up_icon.queue.length < 1.
            )
                $scope.limit_icon = false;
        }
        up_icon.onAfterAddingFile = function (fileItem) {
            $scope.remove_Icon = true;
            if (up_icon.queue.length == 1) {
                $scope.limit_icon = true;
            }
        }

        up_icon.onCompleteAll = function () {
            // $scope.reset();
            // $scope.uploadStatus = true;
            // $scope.state = '';
            // if (uploadResult.length > 0) {
            //     messageService('活动申请信息提交成功，图片' + uploadResult.toString()+"上传失败");
            // }else messageService('活动申请信息提交成功');
        }

        //文件

        var up_docu = $scope.up_docu= new FileUploader({
            url: '/organization/alterDocu',
            queueLimit: 3,
            removeAfterUpload: true
        })
        up_docu.filters.push({
            name: 'imageFilter',
            fn: function (item /*{File|FileLikeObject}*/, options) {
                var type = '|' + item.type.slice(item.type.lastIndexOf('/') + 1) + '|';
                return '|jpg|png|jpeg|bmp|'.indexOf(type) !== -1;
            }
        });
        up_docu.filters.push({
            name: 'imgSizeFilter',
            fn: function (item) {
                return item.size / 1024 / 1024 < 2;
            }
        });
        $scope.clearDocu= function () {
            up_docu.clearQueue();
            $scope.remove_Docu= false;
            $scope.limit_docu = false;
        }
        // 是否禁用添加按钮
        $scope.limit_docu=false;
        // 是否显示移除按钮
        $scope.remove_Docu = false;
        // 从末尾移除图片
        $scope.removeDocu= function () {
            up_docu.queue.pop();
            if (up_docu.queue.length == 0) {
                $scope.remove_Docu= false;
            }
            if (up_docu.queue.length < 3.
            )
                $scope.limit_docu = false;
        }
        up_docu.onAfterAddingFile = function (fileItem) {
            $scope.remove_Docu = true;
            if (up_docu.queue.length == 3) {
                $scope.limit_docu = true;
            }
        }
 // 重置
        $scope.clearItems = function () {
            up_icon.clearQueue();
            up_docu.clearQueue();
            $scope.remove_Docu = false;
            $scope.remove_Icon = false;
            $scope.limit_docu = false;
            $scope.limit_icon = false;
        }
        $scope.reset=function () {
            $scope.org= {
                name:'',
                mail:'',
                phone:'',
                password:'',
                address:'',
                detail:''
            }
            $scope.message='';
            $scope.org_confirmPass='';
            $scope.clear();
            $scope.clearItems();
            $scope.isSubmit = true;
        };

        up_docu.onCompleteAll = function () {
            $scope.reset();
            $scope.uploadStatus = true;
        }
        $scope.clear=function () {
            $scope.clearIcon();
            $scope.clearDocu();
        }

// 提交
        $scope.uploadFile=function () {
            up_icon.uploadAll();
            up_docu.uploadAll();
            $scope.state = '上传图片...';
        }
// 整体提交组织注册信息
        $scope.submit_organization=function() {
            var check = _.find($scope.org, function (item) {
                if (item == '')return true;
            });
            if (check == undefined) {
                $scope.isSubmit = false;
                $scope.message = '提交活动信息中';
                activitiesResource.organization_register.save($scope.org, function (res) {
                    if (res.status != 200) {
                        messageService(res.message);
                        $scope.reset();
                    } else {
                        $scope.uploadFile();
                    }
                });
            } else {
                $scope.state = '请填写所有信息';
            }
        }
    }
})();