/*******************************************************************************
 ******************************************************************************/

(function () {

    'use strict';

    var app = angular.module("app-ui");

    app.factory('ServiceFactory', function (AuthService,
                                           NotificationService,
                                           RestService,
                                           SharedDataFactory,
                                           NavigatorService
    ){
        var serviceFactory = {};
        serviceFactory.AuthService = AuthService;
        serviceFactory.NotificationService = NotificationService;
        serviceFactory.RestService = RestService;
        serviceFactory.SharedDataFactory = SharedDataFactory;
        serviceFactory.NavigatorService = NavigatorService;
        return serviceFactory;

    });


}) ();