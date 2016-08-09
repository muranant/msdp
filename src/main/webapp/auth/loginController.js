/*******************************************************************************
 ******************************************************************************/

(function (){

    'use strict';

    angular
        .module("app-ui")
        .controller('loginController', loginController);

    loginController.$inject = ['$scope', '$rootScope', '$location', 'ServiceFactory', 'RestUtils'];

    function loginController($scope,
                  $rootScope,
                  $location,
                  ServiceFactory,
                  RestUtils) {

        var auth = ServiceFactory.AuthService;
        var notif = ServiceFactory.NotificationService;


            $scope.enableAdminConsole = function () {
                console.log("Show panel");
                $scope.isAdminLoggedIn = true;
            };

            $scope.disableAdminConsole = function () {
                console.log("Hide panel");
                $scope.isAdminLoggedIn = false;
            };

            $scope.$on('menu-toggled', function (event, isExpanded) {
                $scope.isExpanded = isExpanded;
            });

            $scope.$on('logout-user', function (event) {
                console.log("User has selected to log out");
                auth.disallowSession();
                $scope.disableAdminConsole();
            });
            
            $scope.$on('session-expired', function (event) {
                auth.disallowSession();
                $scope.disableAdminConsole();
                if($location.$$path !== '' && $location.$$path != '/login'){
                    notif.openModal('../auth/sessionExpired.html', 'sessionExpiredController', {});
                }
            });

            $scope.logOutSession = function() {
                notif.openModal('../auth/logout.html', 'logoutController', {});
            };


            $scope.redirectToLogin = function () {
                if($location.$$path != '/login'){
                    $location.path('/login');
                }
            };

            $scope.redirectToDefaultPage = function () {
                if($location.$$path != '/dashboard'){
                    $location.path('/dashboard');
                }
            };



            $scope.checkSessionValid = function () {
                /*
                if(auth.validateSession() !== true) {
                    console.log("Session invalid. Disable console");
                    $scope.disableAdminConsole();
                    $scope.redirectToLogin();
                } else {
                    console.log("Session is still valid.");
                    if($location.$$path == '/login'){
                        $scope.redirectToDefaultPage();
                    }
                }
                */
                console.log("Session is still valid.");
                if($location.$$path == '/login'){
                    $scope.redirectToDefaultPage();
                }
            };

            $scope.checkSessionValid();

            $scope.initSession = function () {
                if(auth.validateSession()) {
                    $scope.enableAdminConsole();
                    console.log("madebug: initSession.");
                    $scope.redirectToDefaultPage();
                } else {
                    $scope.disableAdminConsole();
                    $scope.redirectToLogin();
                }
            };


            $scope.credentials = {};
            $scope.show_error = false;


            $scope.login = function (credentials) {

                var onSuccess = function (response){
                    var isValid = !RestUtils.hasError(response);
                    $scope.show_error = !isValid;
                    if(isValid) {
                        $scope.enableAdminConsole();
                        console.log("madebug: login onSuccess");
                        $scope.redirectToDefaultPage();
                    } else {

                        $scope.disableAdminConsole();
                        $scope.show_error = true;
                    }
                };

                var onError = function (reason){
                    console.log("Login failed");
                    $scope.disableAdminConsole();
                    $scope.show_error = true;
                };
                credentials.expiration = "-1";
                //MADEBUG: auth.loginUser(credentials).then(onSuccess, onError);
                console.log("madebug: login main");
                $scope.isAdminLoggedIn = true;
                $scope.redirectToDefaultPage();
            };
        };

}) ();