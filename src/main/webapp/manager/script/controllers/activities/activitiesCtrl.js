/**
 * 
 */
(function(){
	angular.module('activities').controller('activitiesCtrl',['$uibModal','activitiesScrollService','$scope','$location','activitiesResource',activitiesCtrl]);
	
	function activitiesCtrl($uibModal,activitiesScrollService,$scope,$location,activitiesResource){
	        $scope.activitiesScroll =activitiesScrollService;
			$scope.showDetail=function (activity) {
				$uibModal.open({ 
					templateUrl:'manager/views/allAct/actDetail.html',
					controller:'actDetailCtrl',
					size:'lg',
					resolve:{
						activity:function () {
							return activity;
                        }
					}
				});
        }
//--end Ctrl
	}
	
	
	
})();