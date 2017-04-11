/**
 * Created by Manlin on 2017/4/8.
 */
/**
 *通用模态框
 */
(function() {
    angular.module('activities').factory('modalService',['$uibModal',function($uibModal){
            return function (url,controller,message) {
                        $uibModal.open({
                            templateUrl :url,  //指向上面创建的视图
                            controller :controller,// 初始化模态范围
                            resolve : {
                                message : function(){
                                    return message;
                                }
                            }
                        })
                }
            /*return*/
        }]
        /*factory*/
    );

})();