(function () {

    'use strict';

    var app = angular.module("app-ui", [
                 'ngAnimate',
                 'ui.bootstrap',
                 'ngRoute',
                 '$strap.directives',
                 'ngCookies',
                 'ngResource',
                 'chart.js',
                 'angular-loading-bar',
                 'ngFileSaver',
                 'toastr'
            ]);



    app.config(function ($routeProvider){

        $routeProvider
            .when("/login", {
                templateUrl : "auth/login.html",
                controller : "loginController"
            })
            .when("/accountInfo", {
                templateUrl : "application/accounts/accountSummary.html",
                controller : "accountSummaryController"
            })
            .when("/dashboard", {
                templateUrl : "application/dashboard.html",
                controller : "dashboardController"
            })
            .when("/instances", {
                templateUrl : "application/InstanceDetails.html",
                controller : "instanceController"
            })
            .when("/volumes", {
                templateUrl : "application/VolumeDetails.html",
                controller : "volumeController"
            })
            .when("/snapshots", {
                templateUrl : "application/SnapshotDetails.html",
                controller : "snapshotController"
            })
            .otherwise({redirectTo : "/login"});


    });

    app.config(function (cfpLoadingBarProvider) {

        cfpLoadingBarProvider.includeSpinner = true;
        cfpLoadingBarProvider.latencyThreshold = 1;

    });

    app.factory('HTTPInterceptor', function (){
        return {
            request: function (request) {

                if (request.loader) {

                    //intercept request and return it
                }
                return request;
            },

            response: function (response) {

                if (response.config.loader) {

                    //intercept response and return it
                }

                return response;
            }
        };
    });

    app.config(function ($httpProvider) {
        $httpProvider.interceptors.push('HTTPInterceptor');
    });


}) ();
