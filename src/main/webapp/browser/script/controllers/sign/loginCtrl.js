/**
 * 
 */
(function(){
	angular.module('user').controller('loginCtrl',['$scope','userService',loginCtrl]);
	
	function loginCtrl($scope,userService){
		$scope.loginParam={
				role:"",
				userid:"",
				password:""
		}
		$scope.userid='';
		$scope.password='';
		$scope.role='';
		$scope.submit=function(){
			$scope.loginParam.userid=$scope.userid;
			$scope.loginParam.password=$scope.password;
			$scope.loginParam.role=$scope.role;
			userService.user_login.save($scope.loginParam,function(res){
				console.log(res.data);console.log(res.message)
			},function(res){console.log(res.data);}
			
			);
		}
	}
})();