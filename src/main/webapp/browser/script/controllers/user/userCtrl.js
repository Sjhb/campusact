/**
 * 用户
 */
(function() {
	angular.module('user').controller('userCtrl',
			[ 'userService', '$scope', userCtrl ]);

	function userCtrl(userService, $scope) {
		
		$scope.changeNum = function(num) {
			$scope.searchparam.pageNum = num;
			$scope.search();
		}
		$scope.search = function() {
			userService.user_search.save($scope.searchparam, function(res) {
				$scope.userinfo = res.data;console.log(res.data);
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

	}

})();