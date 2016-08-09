/*******************************************************************************
******************************************************************************/

(function (){

    'use strict';

    //Get the main module
    angular
        .module("app-ui")
        .controller('navbarController', navbarController);

    navbarController.$inject = ['$scope', 'ServiceFactory'];

    //Define the controller function
    function navbarController($scope, ServiceFactory) {

        //Services
        var notif = ServiceFactory.NotificationService;
        var navigate = ServiceFactory.NavigatorService;

        $scope.doAboutQuery = function () {
            notif.openModal('../common/views/about.html', 'aboutController', {});
        };

        $scope.goto = function (tabName){
            navigate.goto(tabName);
        };

        $scope.adminLogin = function (){
            notif.openModal('../system/admin/adminLogin.html',
                'adminLoginController',
                {});
        };

    };
}) ();