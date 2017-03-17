/**
 * Created by manlin on 2017/1/4.
 */
(function(){
        angular.module("activities").factory('messageService',['$uibModal',function($uibModal){
            return function (message) {
                $uibModal.open({
                        templateUrl: 'browser/views/message.html',
                        controller: 'messageCtrl',
                        size: 'lg',
                        resolve: {
                            message: function () {
                                return message;
                            }
                        }
                    }
                );
            };
        }]);




    }
)();