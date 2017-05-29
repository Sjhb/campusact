/**
 * Created by manlin on 2017/5/29.
 */
(function () {
    angular.module('activities').controller('checkOrgCtrl',['$uibModal','$scope','activitiesResource','messageService',checkOrgCtrl])
        .controller('rejectOrgCtrl',['messageService','activitiesResource','$rootScope','$scope','$uibModalInstance','orgId',rejectOrgCtrl])
        .controller('OrgDetailCtrl',['messageService','activitiesResource','$rootScope','$scope','$uibModalInstance','org',OrgDetailCtrl]);
    function checkOrgCtrl($uibModal,$scope,activitiesResource,messageService) {
        $scope.changeNum = function(num) {
            $scope.searchparam.pageNum = num;
            $scope.search();
        }

        $scope.search = function() {
            activitiesResource.organization_getAllOrg.save($scope.searchparam, function(res) {
                for (var i = 0; i <res.data.length; i++) {
                    // var a = res.data[i].document;
                    // while (a.indexOf('"') != -1) {
                    //     a = a.replace('"', '');
                    // }
                    // a = a.replace(']', '');
                    // a = a.replace('[', '');
                    // if (a.length == 0) {
                    //     res.data[i].document = [];
                    // } else {
                    //     a = a.split(',');
                    //     res.data[i].document = new Array();
                    //     _.each(a, function (item) {
                    //         item = item.trim();
                    //         res.data[i].document.push("/organization/getOrgDocu?photo=" + item);
                    //     })
                    // }
                    if(res.data[i].length!=null){
                        res.data[i].document='/organization/getOrgDocu?photo'+res.data[i].document;
                    }
                    res.data[i].icon = '/user/getIcon?role=organization&icon=' + res.data[i].icon;
                };
                $scope.orgList=res.data;
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
        $scope.$on('orgChanged',function (event, data) {
            messageService('操作成功');
            $scope.search();
        });
        //通过按钮
        $scope.permit=function (id) {
            $scope.checkedorg={id:id,state:1}
            activitiesResource.activities_checkOrg.save($scope.checkedorg,function (res) {
                if(res.status==200){
                    $scope.search();
                }else{
                    messageService(res.data.message);
                }
            });
        }
        //详情
        $scope.showDetail=function(org){
            $uibModal.open({
                templateUrl:'browser/views/org/checkOrg.html',
                controller:'OrgDetailCtrl',
                size:'lg',
                resolve:{
                    org:function () {
                        return org;
                    }
                }
            });
        }
        //拒绝
        $scope.reject=function(orgId) {
            $uibModal.open({
                templateUrl:'browser/views/checkAct/reject.html',
                controller:'rejectOrgCtrl',
                size:'sm',
                resolve:{
                    orgId:function () {
                        return orgId;
                    }
                }
            });
        }

    }
    function rejectOrgCtrl(messageService,activitiesResource,$rootScope,$scope,$uibModalInstance,orgId) {
        var org={
            id:'',
            state:3
        }
        org.id=orgId;
        var broad=function() {
            $rootScope.$broadcast('orgChanged');
        }
        $scope.reject=function(){
            activitiesResource.organization_checkOrg.save(org,function (res) {
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
    function OrgDetailCtrl(messageService,activitiesResource,$rootScope,$scope,$uibModalInstance,org) {
        $scope.org=org;

    }

})();