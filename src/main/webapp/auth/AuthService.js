/*******************************************************************************
 ******************************************************************************/

(function () {

    'use strict';

    var app = angular.module("app-ui");

    app.service('AuthService',

        function ($cookieStore, $http, $q, $rootScope, NotificationService) {

            var authService = {};

            var settings = {};

            settings.session = {

                timeout : 30
            };

            var notif = NotificationService;

            var getSessionSettings = function (){

                if($cookieStore.get('X-Capi-Session-Settings') === undefined) {

                    $cookieStore.put('X-Capi-Session-Settings',  settings.session);

                    return settings.session;

                } else {

                    return $cookieStore.get('X-Capi-Session-Settings');
                }
            };

            authService.allowSession = function (keys){



                if(keys === undefined || keys === null || keys.value === undefined || keys.value === null) {

                    return;
                }

                var sessionKey = keys.value;

                var sessionSettings = getSessionSettings();
                var idleTimeInMinutes = sessionSettings.timeout;

                console.log("Session Key is: " + sessionKey);

                authService.sessionKey = sessionKey;

                var expireDate = new Date();
                expireDate.setMinutes(expireDate.getMinutes() + idleTimeInMinutes);

                $cookieStore.put('X-Capi-Admin-Session',  {'X-Capi-Request' : authService.sessionKey, 'expires': expireDate});

            };

            authService.disallowSession = function (){

                authService.sessionKey = undefined;

                $cookieStore.remove('X-Capi-Admin-Session');

            };



            authService.validateSession = function (){

            var cookie = $cookieStore.get('X-Capi-Admin-Session');

                if(cookie !== undefined && cookie.expires !== undefined && cookie['X-Capi-Request'] !== undefined) {

                var currentTime = new Date().getTime();
                var cookieExpireTime = new Date(cookie.expires).getTime();

                if(currentTime < cookieExpireTime) {

                    console.log("Session is still valid");
                    authService.allowSession({value : cookie['X-Capi-Request']});

                    return true;

                } else {
                    console.log("Session has expired");
                    authService.disallowSession();

                    $rootScope.$broadcast('session-expired');

                    return false;

                }


            } else {

               return false;

            }

        };



            authService.loginUser = function (credentials) {

                if(credentials === null || credentials === undefined){

                    var deferred = $q.defer();
                    deferred.reject("Invalid credentials provided");
                    return deferred.promise;
                }


                var onSuccess = function(response){

                    authService.allowSession(response.data);
                    return response.data;

                };

                var onError = function (reason){

                    authService.disallowSession();
                    var deferred = $q.defer();
                    deferred.reject(reason);
                    return deferred.promise;

                };

                return $http.post('/appw/v1/login', credentials).then(onSuccess, onError);
            };


            return authService;
    });







}) ();