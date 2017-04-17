/**
 * 
 */
(function () {
    angular.module('activities').controller('checkActCtrl',['$uibModal','$scope','activitiesResource','messageService',checkActCtrl])
        .controller('rejectCtrl',['messageService','activitiesResource','$rootScope','$scope','$uibModalInstance','activityid',rejectCtrl]);
    function checkActCtrl($uibModal,$scope,activitiesResource,messageService) {
        $scope.changeNum = function(num) {
            $scope.searchparam.pageNum = num;
            $scope.search();
        }

        $scope.search = function() {
            activitiesResource.activities_waiting.save($scope.searchparam, function(res) {
                for (var i = 0; i <res.data.length; i++) {
                    var a = res.data[i].photo;
                    while (a.indexOf('"') != -1) {
                        a = a.replace('"', '');
                    }
                    a = a.replace(']', '');
                    a = a.replace('[', '');
                    if (a.length == 0) {
                        res.data[i].photo = [];
                    } else {
                        a = a.split(',');
                        res.data[i].photo = new Array();
                        _.each(a, function (item) {
                            item = item.trim();
                            res.data[i].photo.push("/activity/getPhoto?photo=" + item);
                        })
                    }
                    res.data[i].organization.icon = '/user/getIcon?role=organization&icon=' + res.data[i].organization.icon;
                };
                $scope.activities=res.data;
                $scope.paginationConf.totalItems = res.page.totalNum;
                $scope.paginationConf.currentPage = res.page.pageNum;
                $scope.paginationConf.numberOfPages = res.page.pages;
            }, function(res) {
                console.log(res.data);
            });
        }

        $scope.reSet=function(){
            $scope.searchparam = {
                pageNum : 1
            }
            $scope.search();
        }

        $scope.reSet();

        $scope.paginationConf = {
            currentPage : 1,
            totalItems : 0,
            numberOfPages : 0,
            itemsPerPage : 10,
            pagesLength : 9,
            // rememberPerPage: 'perPageItems',
            onChange : function() {
                if ($scope.searchparam.pageNum != $scope.paginationConf.currentPage) {
                    $scope.searchparam.pageNum = $scope.paginationConf.currentPage;
                    $scope.search();
                }

            }
        };
        // 活动不通过回调
        $scope.$on('actChanged',function (event, data) {
            messageService('操作成功');
            $scope.search();
        });
        //通过按钮
        $scope.permit=function (id) {
            $scope.checkedact={id:id,stateId:2000}
            activitiesResource.activities_checkAct.save($scope.checkedact,function (res) {
                if(res.status==200){
                    $scope.search();
                }else{
                   messageService(res.data.message);
                }
            });
        }
        //详情
        $scope.showDetail=function(activity){
            $uibModal.open({
                templateUrl:'browser/views/checkAct/checkDetail.html',
                controller:'actDetailCtrl',
                size:'lg',
                resolve:{
                    activity:function () {
                        return activity;
                    }
                }
            });
        }
        //拒绝
        $scope.reject=function(activityid) {
        	$uibModal.open({
                templateUrl:'browser/views/checkAct/reject.html',
                controller:'rejectCtrl',
                size:'sm',
                resolve:{
                    activityid:function () {
                        return activityid;
                    }
                }
            });
        }

    }
    function rejectCtrl(messageService,activitiesResource,$rootScope,$scope,$uibModalInstance,activityid) {
        $scope.activity=activityid;
        var broad=function() {
            $rootScope.$broadcast('actChanged');
        }
        $scope.reject=function(){
            var checkedact={id:activityid,stateId:3000}
            activitiesResource.activities_checkAct.save(checkedact,function (res) {
                if(res.status==200){
                    broad();
                    $scope.cancel();
                }else{
                    messageService(res.message);
                    $scope.cancel();
                }
            });
        };
        $scope.cancel=function () {
            $uibModalInstance.dismiss('cancel');
        }
    }

})();