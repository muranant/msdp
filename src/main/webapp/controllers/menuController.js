/*******************************************************************************
 ******************************************************************************/

(function (){

    'use strict';

    var app = angular.module("app-ui");


    app.controller("menuController",

        function ($scope, $rootScope, $location,
                GlobalSettings) {


            $scope.onDashboardHover = false;
            $scope.onAccountsInfoHover = false;
            $scope.expandHelpMessage = "Expand menu";
            $scope.expandMessage = ">>";
            $scope.activeTabs = [];


            var menuSettings = GlobalSettings.getMenuSettings();

            if(menuSettings.startup_state == 'Collapsed') {

                $scope.isExpanded = false;
            } else {

                $scope.isExpanded = true;
            }

            $rootScope.$broadcast('menu-toggled', $scope.isExpanded);

            $scope.toggleExpand = function () {
                $scope.isExpanded = !$scope.isExpanded;

                if($scope.isExpanded) {
                    $scope.expandHelpMessage = "Collapse menu";
                    $scope.expandMessage = "<<";
                } else {
                    $scope.expandHelpMessage = "Expand menu";
                    $scope.expandMessage = ">>";
                }

                $rootScope.$broadcast('menu-toggled', $scope.isExpanded );
            };


            $scope.dashboardOver = function (){
                $scope.onDashboardHover = true;
            };
            $scope.accountsOver = function (){
                $scope.onAccountsInfoHover = true;
            };

            $scope.updateActiveTab = function(tabName) {
                $scope.activeTabs = [];
                $scope.activeTabs[tabName] = true;
            };

            $scope.notifyTabChange = function (tabName) {
                $location.path('/'+tabName);
                $scope.updateActiveTab(tabName);
                console.dir($location);
            };

            $scope.$on('goto', function (event, args) {
                $scope.notifyTabChange(args.tabName);

            });

        });

}) ();