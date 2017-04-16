/**
 *
 */
(function () {
    angular.module('activities').controller('createActCtrl', ['messageService', 'FileUploader', '$location', '$uibModal', '$scope', 'activitiesResource', createActCtrl]);
    function createActCtrl(messageService, FileUploader, $location, $uibModal, $scope, activitiesResource) {
        $('#sdate').datetimepicker({
            format: 'yyyy-mm-dd hh:mm',
            locale: moment.locale('zh-cn')
        });
        $('#edate').datetimepicker({
            format: 'yyyy-mm-dd hh:mm',
            locale: moment.locale('zh-cn')
        });
        $('#signdate').datetimepicker({
            format: 'yyyy-mm-dd hh:mm',
            locale: moment.locale('zh-cn')
        });
        $('#endsigndate').datetimepicker({
            format: 'yyyy-mm-dd hh:mm',
            locale: moment.locale('zh-cn')
        });


        $scope.clear = function () {
            $scope.state = '';
        }
        $scope.showMessage = function (message) {
            $uibModal.open({
                templateUrl: 'browser/views/createAct/message.html',
                controller: 'createmessageCtrl',
                size: 'sm',
                resolve: {
                    message: function () {
                        return message;
                    }
                }
            });
        };
        //活动图片提交
        var uploader = $scope.uploader = new FileUploader({
            url: '/activity/alterActPhoto',
            queueLimit: 3,
            removeAfterUpload: true
        })
        // FILTERS
        uploader.filters.push({
            name: 'imageFilter',
            fn: function (item /*{File|FileLikeObject}*/, options) {
                var type = '|' + item.type.slice(item.type.lastIndexOf('/') + 1) + '|';
                return '|jpg|png|jpeg|bmp|'.indexOf(type) !== -1;
            }
        });
        uploader.filters.push({
            name: 'imgSizeFilter',
            fn: function (item) {
                return item.size / 1024 / 1024 < 2;
            }
        });

        $scope.clearItems = function () {
            uploader.clearQueue();
            $scope.removeButton = false;
            $scope.limit = false;
        }
        // 添加图片
        $scope.addFile = function () {

        }
        // 是否隐藏提交按钮
        $scope.isSubmit = true;
        // 是否禁用添加按钮
        $scope.limit = false;
        // 是否显示移除按钮
        $scope.removeButton = false;
        // 存储图片提交信息
        var uploadResult = new Array();
        // 从末尾移除图片
        $scope.removePhoto = function () {
            uploader.queue.pop();
            if (uploader.queue.length == 0) {
                $scope.removeButton = false;
            }
            if (uploader.queue.length < 3)
                $scope.limit = false;
        }
        uploader.onAfterAddingFile = function (fileItem) {
            $scope.removeButton = true;
            if (uploader.queue.length == 3) {
                $scope.limit = true;
            }
        }
        //自定义form
        var form = $scope.form = '------WebKitFormBoundaryJEVk9qpk4GSMa5fu\r\nContent-Disposition: form-data; name="over"\r\n\r\nover\r\n------WebKitFormBoundaryJEVk9qpk4GSMa5fu--';

        function svae() {
            activitiesResource.activities_alterActPhoto.save(form, function (res) {
            });
        }

        uploader.onCompleteAll = function () {
            svae();
            $scope.reset();
            $scope.uploadStatus = true;
            $scope.state = '';
            if (uploadResult.length > 0) {
                messageService('活动申请信息提交成功，图片' + uploadResult.toString()+"上传失败");
            }else messageService('活动申请信息提交成功');
        }
        uploader.onSuccessItem = function (fileItem, response, status, headers) {

        }
        uploader.onErrorItem = function (fileItem, response, status, headers) {
            uploadResult.push(fileItem._file.name);
        }
        $scope.uploadFile = function () {
            uploader.uploadAll();
            $scope.state = '上传图片...';
        }
        $scope.reset = function () {
            $scope.newAct = {
                name: '',
                startTime: '',
                endTime: '',
                sponsor: '',
                signTime: '',
                endSignTime: '',
                detail: '',
                address: ''
            }
            $scope.clear();
            $scope.clearItems();
            $scope.isSubmit = true;
        }
        $scope.reset();
        //提交
        $scope.submit = function () {
            var check = _.find($scope.newAct, function (item) {
                if (item == '')return true;
            });
            if (check == undefined) {
                $scope.isSubmit = false;
                $scope.state = '提交活动信息中';
                activitiesResource.activities_createAct.save($scope.newAct, function (res) {
                    if (res.status != 200) {
                        messageService(res.message);
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