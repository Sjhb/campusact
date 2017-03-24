/**
 *
 */
// csrf跨域攻击拦截器
(function () {
    angular.module("activities").factory('csrfInterceptor', ['$rootScope', '$q', function ($rootScope, $q) {
            return { request: function (config) {
                    var token = $("meta[name='_csrf']").attr("content");
                    var header = $("meta[name='_csrf_header']").attr("content");
                    if (config.url.indexOf(header) > -1) {
                        config.headers[header] = token;
                    }
                    return config;
                }
                 };
        }]);
})();
