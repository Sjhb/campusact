/**
 * Created by Manlin on 2017/4/30.
 */
(function () {
    angular.module('activities').controller('orgActCtrl', ['$location','activitiesResource', '$scope', '$uibModal', 'actOperateService', orgActCtrl]);
    function orgActCtrl($location,activitiesResource, $scope, $uibModal, actOperateService) {
        $scope.showDetail = function (activity) {
            $uibModal.open({
                templateUrl: 'browser/views/org-act/orgActDetail.html',
                controller: 'orgActDetailCtrl',
                size: 'lg',
                resolve: {
                    activity: function () {
                        return activity;
                    }
                }
            });
        }
        $scope.changeNum = function (num) {
            $scope.searchparam.pageNum = num;
            $scope.search();
        }

        $scope.search = function () {
            activitiesResource.activities_getActByOid.save($scope.searchparam, function (res) {
                if (res.data != null) {
                    $scope.activities = actOperateService.operate(res.data);
                }
                $scope.paginationConf.totalItems = res.page.totalNum;
                $scope.paginationConf.currentPage = res.page.pageNum;
                $scope.paginationConf.numberOfPages = res.page.pages;
            }, function (res) {
                console.log(res.data);
            });
        }

        $scope.reSet = function () {
            $scope.searchparam = {
                pageNum: 1
            }
            $scope.search();
        }

        $scope.reSet();

        $scope.paginationConf = {
            currentPage: 1,
            totalItems: 0,
            numberOfPages: 0,
            itemsPerPage: 10,
            pagesLength: 9,
            // rememberPerPage: 'perPageItems',
            onChange: function () {
                if ($scope.searchparam.pageNum != $scope.paginationConf.currentPage) {
                    $scope.searchparam.pageNum = $scope.paginationConf.currentPage;
                    $scope.search();
                }

            }
        };
        $scope.reApply=function () {
            $location.path('/createAct/create');
        }
    }
})();