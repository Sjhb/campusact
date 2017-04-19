/**
 * 
 */
(function() {
	// 登陆用户身份，可以用来判定显示内容
angular.module('activities').factory('permission',function($rootScope){
	var permissions;
	var user;
	return{
		setPermission:function(role){
			if(role==null){
				permissions=role;
			}else{permissions=role.trim();}
			$rootScope.$broadcast('permissionChanged');
		},
		setUser:function (sendUser) {
			user=sendUser;
        	$rootScope.$broadcast('userChanged');
		},
		hasPermission:function(role){
			role=role.trim();
			return role===permissions;
		},
		hasLogin:function () {
			if(permissions==null){
				return false;
			}else return true;
        },
		getUser:function () {
			if (user==null) return null;
			return user;
        }
	}
	/*perimisson function*/
	}
	/*factory*/
	);
	
})();