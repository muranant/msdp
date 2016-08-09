/*******************************************************************************
 ******************************************************************************/

(function (){
    'use strict';

    var app = angular.module("app-ui");

    var dashboardController = function ($scope, $location, ServiceFactory) {

        var notif = ServiceFactory.NotificationService;
        var shared = ServiceFactory.SharedDataFactory;

        $scope.accountInfo = shared.accountInfo;


        $scope.yes = function () {
            console.log('In yes');
        };

        $scope.no = function () {
            console.log('In no');
        };

        $scope.getDashboardInfo = function () {
            $scope.showAccountVMs();
            $scope.showAccountSnapshots();
            $scope.showAccountVolumes();
        };

        $scope.showAccountVMs = function(account) {
            console.log("showAccountVMs");
            $location.path('/instances');
        };

        $scope.showAccountSnapshots = function(account) {
            console.log("showSnapshots");
            $location.path('/snapshots');
        };

        $scope.showAccountVolumes = function(account) {
            console.log("showVolumes");
            $location.path('/volumes');
        };

    };

    //Assign the controller to the module
    app.controller("dashboardController", dashboardController);


}) ();