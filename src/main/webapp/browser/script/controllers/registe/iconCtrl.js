/**
 * Created by Manlin on 2017/4/8.
 */
// 头像图片提交
(function () {
    angular.module('activities').controller('iconCtrl',['$scope','$uibModalInstance','message','FileUploader',iconCtrl]);
    function iconCtrl($scope,$uibModalInstance,message,FileUploader) {
        $scope.message=message;
        $scope.exit=function () {
            $uibModalInstance.dismiss('cancel');
        }
        $scope.isImage=false;
        $scope.action='选择图片'
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
                return '|jpg|png|jpeg|bmp|gif|'.indexOf(type) !== -1;
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
        }
        $scope.UploadFile = function(){
            uploader.uploadAll();
            if(uploadStatus){
                    alert('上传成功！');
                }else{
                    alert('证书成功！私钥失败0！');
                }
        }
    }
})();