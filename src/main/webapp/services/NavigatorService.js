/*******************************************************************************
 ******************************************************************************/

(function () {

    'use strict';

    var app = angular.module("app-ui");


    app.service('NavigatorService', function ($rootScope) {


        var navigatorService = {};

        navigatorService.goto  = function (tabName) {

            $rootScope.$broadcast('goto', {tabName: tabName});
        };


        //Only expose the services. Do not expose data
        return {

            "goto" : navigatorService.goto

        };

    });






}) ();