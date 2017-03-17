/**
 * 
 */
(function () {
    angular.module('activities').controller('createActCtrl',['$location','$uibModal','$scope','activitiesResource',createActCtrl])
        .controller('messageCtrl',['$uibModalInstance','$scope','message',createmessageCtrl] );
    function createActCtrl($location,$uibModal,$scope,activitiesResource) {
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

       $scope.reset=function () {
           $scope.newAct={
               name:'',
               stime:'',
               etime:'',
               sponsor:'',
               signtime:'',
               endsigntime:'',
               detail:'',
               address:''
           }
       }
        $scope.reset();
       $scope.clear=function(){
    	   $scope.worning='';
       }
       $scope.showMessage=function (message) {
           $uibModal.open({
               templateUrl:'manager/views/createAct/message.html',
               controller:'createmessageCtrl',
               size:'sm',
               resolve:{
                   message:function () {
                       return message;
                   }
               }
           });
       };
        //提交
       $scope.submit=function () {
    	   var check=_.find($scope.newAct,function(item){if(item=='')return true;});
    	   if(check==undefined){
    		   activitiesResource.activities_createAct.save($scope.newAct,function (res) {
                   $scope.showMessage(res.status);
                   if (res.status!='200'){
                       $location.path('/allAct/allAct');
                   }else{
                      $scope.reset();
                   }
               });
    	   }else{
    		   $scope.worning='请填写所有信息';
    	   }
       }
    }
    //提交结果
    function createmessageCtrl($uibModalInstance,$scope,message) {
        $scope.state=message;
        $scope.init=function (thismessage) {
            if(thismessage==200){
                $scope.message='成功创建活动，请等待审核结果!';
            }else{
                $scope.message='未能创建活动，无效用户或者是未登录！';
            }
        }
        $scope.init($scope.state);
        $scope.ok=function () {
            $uibModalInstance.dismiss('cancel');
        }
    }

})();