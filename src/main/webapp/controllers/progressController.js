/*******************************************************************************
 ******************************************************************************/

(function (){

    'use strict';

    var app = angular.module("app-ui");

    app.controller('progressController', function ($scope, ServiceFactory, $uibModalInstance){

        var notif = ServiceFactory.NotificationService;

        $scope.$on('request-end', function () {
            notif.closeModal($uibModalInstance);
        });



    });


}) ();