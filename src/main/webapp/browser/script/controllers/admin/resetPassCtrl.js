/**
 * Created by manlin on 2017/5/31.
 */
(function () {
    angular.module('activities').controller('resetPassCtrl', ['$scope', resetPassCtrl])
        .controller('resetStuPassCtrl', ['$uibModal', '$scope', 'activitiesResource', 'messageService', resetStuPassCtrl])
        .controller('resetOrgPassCtrl', ['$uibModal', '$scope', 'activitiesResource', 'messageService', resetOrgPassCtrl]);
    function resetPassCtrl($scope) {
        $scope.isActive = 'stu';
        $scope.shiftRole = function (role) {
            $scope.isActive = role;
        }

    }

    function resetStuPassCtrl($uibModal, $scope, activitiesResource, messageService) {
        $scope.changeNum = function (num) {
            $scope.searchparam.pageNum = num;
            $scope.search();
        }
        $scope.search = function () {
            activitiesResource.admin_getResetRequestStu.save($scope.searchparam, function (res) {
                for (var i = 0; i < res.data.length; i++) {
                    res.data[i].role = '学生';
                }
                ;
                $scope.userList = res.data;
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

        //通过按钮
        $scope.permit = function (user) {
            var stu = {
                id: user.id,
                resetpass:2
            }

            activitiesResource.admin_resetStuPass.save(stu, function (res) {
                if (res.status == 200) {
                    $scope.search();
                } else {
                    messageService(res.data.message);
                }
            });
        }
        //拒绝
        $scope.reject = function (orgId) {
            var stu = {
                id: user.id,
                resetpass:3
            }
            activitiesResource.admin_resetStuPass.save(stu, function (res) {
                if (res.status == 200) {
                    $scope.search();
                } else {
                    messageService(res.data.message);
                }
            });
        }


    }

    function resetOrgPassCtrl($uibModal, $scope, activitiesResource, messageService) {
        $scope.changeNum = function (num) {
            $scope.searchparam.pageNum = num;
            $scope.search();
        }
        $scope.search = function () {
            activitiesResource.admin_getResetRequestOrg.save($scope.searchparam, function (res) {
                for (var i = 0; i < res.data.length; i++) {
                    res.data[i].role = '组织者';
                }
                ;
                $scope.userList = res.data;
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

        //通过按钮
        $scope.permit = function (user) {
            var stu = {
                id: user.id,
                resetpass:2
            }

            activitiesResource.admin_resetOrgPass.save(stu, function (res) {
                if (res.status == 200) {
                    $scope.search();
                } else {
                    messageService(res.data.message);
                }
            });
        }
        //拒绝
        $scope.reject = function (orgId) {
            var stu = {
                id: user.id,
                resetpass:3
            }

            activitiesResource.admin_resetOrgPass.save(stu, function (res) {
                if (res.status == 200) {
                    $scope.search();
                } else {
                    messageService(res.data.message);
                }
            });
        }


    }


})()