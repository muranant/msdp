/*******************************************************************************
 ******************************************************************************/

(function () {

    'use strict';

    var module = angular.module("app-ui");

    module.factory('SharedDataFactory', function () {
        var sharedData = {};
        sharedData.logout = {};
        return sharedData;
    });


}) ();