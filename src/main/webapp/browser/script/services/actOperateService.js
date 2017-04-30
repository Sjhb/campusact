/**
 * Created by Manlin on 2017/4/30.
 */
(function () {
    angular.module('activities').factory('actOperateService',[actOperateService]);
    function actOperateService() {
        function strToarr(str) {
            while (str.indexOf('"') != -1) {
                str= str.replace('"', '');
            }
            str= str.replace(']', '');
            str= str.replace('[', '');
            if (str.length == 0) {
                return [];
            } else {
                str= str.split(',');
                var result = new Array();
                _.each(str, function (item) {
                    item = item.trim();
                    result.push(item);
                })
                return result;
            }
        };
        return{
            operate:function (data) {
                for (var i = 0; i <data.length; i++) {
                    var a = data[i].photo;
                    while (a.indexOf('"') != -1) {
                        a = a.replace('"', '');
                    }
                    a = a.replace(']', '');
                    a = a.replace('[', '');
                    if (a.length == 0) {
                        data[i].photo = [];
                    } else {
                        a = a.split(',');
                        data[i].photo = new Array();
                        _.each(a, function (item) {
                            item = item.trim();
                            data[i].photo.push("/activity/getPhoto?photo=" + item);
                        })
                    }
                    data[i].organization.icon = '/user/getIcon?role=organization&icon=' + data[i].organization.icon;
                    data[i].engage=strToarr(data[i].engage);
                };
                return data;
            },
            strToarr:strToarr
        }
    }
})();