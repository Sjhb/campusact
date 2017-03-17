/**
 * 自定义标签 directive
 */
(function () {
    var app = angular.module('directive', []);
    app.directive(
        'paging',
        function () {
            return {
                restrict: 'EA',
                template: '<div style="text-align:right;">'
                + '<ul class="pagination"  ng-show="conf.numberOfPages > 0"> <li><a style="border:0;background: #ffffff;" ><i class="ace-icon fa">共计{{conf.totalItems}}条</i></a></li>'
                + '<li ng-class="{disabled: conf.currentPage == 1}" ><a data-ng-click="prevPage()" ><i class="ace-icon fa fa-angle-double-left">上一页<<</i></a></li>'
                + '<li ng-repeat="item in pageList track by $index" ng-class="{active: item == conf.currentPage}">'
                + ' <a ng-click="changeCurrentPage(item)" >{{item}}</a></li>'
                + '<li ng-class="{disabled: conf.currentPage == conf.numberOfPages}" ><a data-ng-click="nextPage()"> <i class="ace-icon fa fa-angle-double-right">>>下一页</i></a></li>'
                + '</ul>' + '</div>',
                replace: true,
                scope: {
                    conf: '='
                },
                link: function (scope, element, attrs) {

                    // 变更当前页
                    scope.changeCurrentPage = function (item) {
                        if (item == '...') {
                            return;
                        } else {
                            scope.conf.currentPage = item;
                        }
                    };
                    // 定义分页的长度必须为奇数 (default:9)
                    scope.conf.pagesLength = parseInt(scope.conf.pagesLength) ? parseInt(scope.conf.pagesLength)
                        : 9;
                    if (scope.conf.pagesLength % 2 === 0) {
                        // 如果不是奇数的时候处理一下
                        scope.conf.pagesLength = scope.conf.pagesLength - 1;
                    }

                    // conf.erPageOptions
                    if (!scope.conf.perPageOptions) {
                        scope.conf.perPageOptions = [10, 15, 20,
                            30, 50];
                    }

                    // pageList数组
                    function getPagination(newValue, oldValue) {

                        scope.conf.currentPage = parseInt(scope.conf.currentPage) ? parseInt(scope.conf.currentPage)
                            : 1;

                        if (scope.conf.currentPage < 1) {
                            scope.conf.currentPage = 1;
                        }
                        if (scope.conf.numberOfPages > 0
                            && scope.conf.currentPage > scope.conf.numberOfPages) {
                            scope.conf.currentPage = scope.conf.numberOfPages;
                        }
                        scope.pageList = [];
                        if (scope.conf.numberOfPages <= scope.conf.pagesLength) {
                            // 判断总页数如果小于等于分页的长度，若小于则直接显示
                            for (var i = 1; i <= scope.conf.numberOfPages; i++) {
                                scope.pageList.push(i);
                            }
                        } else {
                            // 总页数大于分页长度（此时分为三种情况：1.左边没有...2.右边没有...3.左右都有...）
                            // 计算中心偏移量
                            var offset = (scope.conf.pagesLength - 1) / 2;
                            if (scope.conf.currentPage <= offset) {
                                // 左边没有...
                                for (var j = 1; j <= offset + 1; j++) {
                                    scope.pageList.push(j);
                                }
                                scope.pageList.push('...');
                                scope.pageList
                                    .push(scope.conf.numberOfPages);
                            } else if (scope.conf.currentPage > scope.conf.numberOfPages
                                - offset) {
                                scope.pageList.push(1);
                                scope.pageList.push('...');
                                for (var h = offset + 1; h >= 1; h--) {
                                    scope.pageList
                                        .push(scope.conf.numberOfPages
                                            - h);
                                }
                                scope.pageList
                                    .push(scope.conf.numberOfPages);
                            } else {
                                // 最后一种情况，两边都有...
                                scope.pageList.push(1);
                                scope.pageList.push('...');

                                for (var k = Math.ceil(offset / 2); k >= 1; k--) {
                                    scope.pageList
                                        .push(scope.conf.currentPage
                                            - k);
                                }
                                scope.pageList
                                    .push(scope.conf.currentPage);
                                for (var n = 1; n <= offset / 2; n++) {
                                    scope.pageList
                                        .push(scope.conf.currentPage
                                            + n);
                                }

                                scope.pageList.push('...');
                                scope.pageList
                                    .push(scope.conf.numberOfPages);
                            }
                        }

                        scope.conf.pageNumList = [];
                        for (var index = 0; index < scope.conf.numberOfPages; index++) {
                            scope.conf.pageNumList[index] = index + 1;
                        }
                        if (scope.conf.onChange) {

                            if (!(oldValue != newValue && oldValue[0] == 0)) {
                                scope.conf.onChange();
                            }

                        }
                        scope.$parent.conf = scope.conf;
                    }

                    scope.prevPage = function () {
                        if (scope.conf.currentPage > 1) {
                            scope.conf.currentPage -= 1;
                        }
                    };
                    scope.nextPage = function () {
                        if (scope.conf.currentPage < scope.conf.numberOfPages) {
                            scope.conf.currentPage += 1;
                        }
                    };
                    scope.$watch(function () {
                        if (!scope.conf.totalItems) {
                            scope.conf.totalItems = 0;
                        }
                        var newValue = scope.conf.numberOfPages
                            + ' ' + scope.conf.currentPage;
                        return newValue;
                    }, getPagination);

                }
            };

        }).filter("getValue", function () { // D1
        return function (input, obj, param) {
            if (!obj) {
                return "";
            }
            if (obj[input]) {
                if (param)
                    return obj[input][param];
                else
                    return obj[input];
            }

            return input

        }
    }).filter("getParam", function () { // D1
        return function (input, param) {
            if (input) {
                if (param)
                    return input[param];

            }

            return input

        }
    }).filter("getArrayOneParam", function () { // D1
        return function (input, param) {
            if (input.length > 0) {
                if (input[0][param])
                    return input[0][param];
            }

            return false

        }
    }).filter('setFun', function () {

        return function (input, fun) {

            if (input)
                return input.fun();
            return input;
        }
    }).filter('trustUrl', function ($sce) {
        return function (url) {
            return $sce.trustAsResourceUrl(url);
        };
    }).filter('toDate', function () {

        return function (input) {
            if (input) {
                return moment(input).toDate();

            }
            return null;

        }

    }).directive('hasPermission', function(permission) {
        return {
            link: function(scope, element, attrs) {
                if(!_.isString(attrs.hasPermission))
                    throw "hasPermission value must be a string";

                var value = attrs.hasPermission.trim();
                function toggleVisibilityBasedOnPermission() {
                    var hasPermission = permission.hasPermission(value);

                    if(hasPermission)
                        element.show();
                    else
                        element.hide();
                }
                toggleVisibilityBasedOnPermission();
                scope.$on('permissionChanged', toggleVisibilityBasedOnPermission);
            }
        };
    }).directive('hasLogin', function(permission) {
        return {
            link: function(scope, element, attrs) {
                if(!_.isString(attrs.hasLogin))
                    throw "hasLogin value must be a string";

                var value = attrs.hasLogin.trim();
                function logVisibilityBasedOnPermission() {
                    var hasLogined = permission.hasLogin();
                    if(value=='login'){
                    	if(!hasLogined)
                        element.show();
                    	else {
							element.hide();
						}
                    }else if(value=='logout'){
                    	if(hasLogined)
                            element.show();
                        	else {
    							element.hide();
                        }
                    };
                    
                }
                logVisibilityBasedOnPermission();
                scope.$on('permissionChanged',logVisibilityBasedOnPermission);
            }
        };
    });

})();