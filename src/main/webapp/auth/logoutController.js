/*******************************************************************************
 ******************************************************************************/

(function (){

    'use strict';

    var app = angular.module("app-ui");


    app.controller("logoutController",

        function ($scope, $location, $uibModalInstance, $rootScope, ServiceFactory) {

            var notif = ServiceFactory.NotificationService;

            $scope.yes = function () {
                $rootScope.$broadcast('logout-user');
                notif.closeModal($uibModalInstance);
                $location.path('/login');
            };

            $scope.no = function () {
                notif.closeModal($uibModalInstance);
            };



        });



}) ();