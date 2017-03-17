/**
 * 
 */
(function () {
    angular.module('activities').controller('checkActCtrl',['$uibModal','$scope','activitiesResource','messageService',checkActCtrl])
        .controller('rejectCtrl',['$scope','$uibModalInstance','activityid',rejectCtrl]);
    function checkActCtrl($uibModal,$scope,activitiesResource,messageService) {
        $scope.changeNum = function(num) {
            $scope.searchparam.pageNum = num;
            $scope.search();
        }
        
        $scope.search = function() {
            activitiesResource.activities_waiting.save($scope.searchparam, function(res) {
                for (var i = 0; i <res.data.length; i++) {
                    res.data[i].photo="manager/images/actphoto/"+res.data[i].photo+".jpg";
                    res.data[i].organization.icon="manager/images/icon/"+ res.data[i].organization.icon+".jpg";
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
        //通过按钮
        $scope.permit=function (id) {
            $scope.checkedact={id:id,state:10}
            activitiesResource.activities_checkAct.save($scope.checkedact,function (res) {
                if(res.status==200){
                    $scope.search();
                }else{
                   messageService('操作失败');
                }
            });
        }
        //详情
        $scope.showDetail=function(activity){
            $uibModal.open({
                templateUrl:'manager/views/checkAct/checkDetail.html',
                controller:'actDetailCtrl',
                size:'lg',
                resolve:{
                    activity:function () {
                        return activity;
                    }
                }
            });
        }
        //拒绝提醒
        $scope.reject=function(activityid) {
        	$uibModal.open({
                templateUrl:'manager/views/checkAct/reject.html',
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
    function rejectCtrl($scope,$uibModalInstance,activityid) {
        $scope.activity=activityid;
        $scope.cancel=function () {
            $uibModalInstance.dismiss('cancel');
        }
    }

})();