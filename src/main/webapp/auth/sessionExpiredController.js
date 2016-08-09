/*******************************************************************************
 ******************************************************************************/

(function (){

    'use strict';

    var app = angular.module("app-ui");


    app.controller("sessionExpiredController",

        function ($scope, ServiceFactory, $uibModalInstance) {

            var notif = ServiceFactory.NotificationService;

            //Close all existing modals
            notif.closeAllModals();

            $scope.ok = function () {
                notif.closeModal($uibModalInstance);
            };

        });



}) ();