/**
 * Created by Manlin on 2017/4/8.
 */
// 头像图片提交
(function () {
    angular.module('activities').controller('iconCtrl',['messageService','$location','$scope','$uibModalInstance','message','FileUploader',iconCtrl]);
    function iconCtrl(messageService,$location,$scope,$uibModalInstance,message,FileUploader) {
        $scope.message=message;
        $scope.exit=function () {
            $uibModalInstance.dismiss('cancel');
        }
        $scope.isImage=false;
        $scope.action='选择图片'
        $scope.state='';
        // 文件上传组建
        var uploader=$scope.uploader=new FileUploader({
             url:'/student/setIcon',
            queueLimit:1,
            removeAfterUpload:true
        })
        // FILTERS

        uploader.filters.push({
            name: 'imageFilter',
            fn: function(item /*{File|FileLikeObject}*/, options) {
                var type = '|' + item.type.slice(item.type.lastIndexOf('/') + 1) + '|';
                return '|jpg|png|jpeg|bmp|'.indexOf(type) !== -1;
            }
        });
        uploader.filters.push({
            name: 'imgSizeFilter',
            fn: function(item) {
                return item.size/1024/1024<2;
            }
        });

        $scope.clearItems=function () {
            uploader.clearQueue();
        }
        uploader.onAfterAddingFile=function (fileItem) {
            $scope.fileItem=fileItem._file;
            $scope.isImage=true;
            $scope.action='更换图片';
                    }
        uploader.onSuccessItem = function(fileItem,response, status, headers){
            $scope.uploadStatus = true;
            $scope.state='';
            messageService('设置成功');
            $scope.exit();
            $location.path('/allAct/allAct');
        }
        uploader.onErrorItem=function(fileItem, response, status, headers){
            $scope.state='设置失败';
        }
        $scope.uploadFile = function(){
            uploader.uploadAll();
            $scope.state='waiting...';
        }
    }
})();