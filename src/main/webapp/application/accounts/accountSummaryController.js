(function (){

    'use strict';

    //Get the main module
    var app = angular.module("app-ui");


    var accountSummaryController = function ($scope, $location, ServiceFactory, RestUtils, $q) {

    //accountSummaryController.$inject = ['$scope', '$location', 'ServiceFactory', 'RestUtils', '$q'];

    //Define the controller function

        //Models

        //Services
        var rest = ServiceFactory.RestService;
        var shared = ServiceFactory.SharedDataFactory;
        var notif = ServiceFactory.NotificationService;

        $scope.orderByField = 'accountName';
        $scope.reverseSort = false;

        //Behaviors
        $scope.getAccountInfo = function () {

            var onSuccess = function (response) {
                if(!RestUtils.hasError(response)){
                    $scope.accountInfo = response.accounts;
                    shared.accountInfo = $scope.accountInfo;
                    console.log($scope.accountInfo);
                } else {
                    RestUtils.handleResponse({
                        event : "Get account Info",
                        response : response
                    });
                }
            };

            var onError = function (reason) {
                RestUtils.handleError({
                            event : "Get Account Info",
                            reason : reason
                });
            };

            rest.get({
                resource : 'accounts',
                disableLoader : true
            }).then(onSuccess, onError);

        };
        $scope.getAccountInfo();

        $scope.$on('refresh-account', function (event) {
            $scope.getAccountInfo();
        });


        $scope.addAccount = function (){
            notif.openModal('../application/accounts/addAccount.html', 'addAccountController', {});
        };


        $scope.deleteAccount = function (accountName){
            if(accountName === null || accountName === undefined){
                var deferred = $q.defer();
                deferred.reject("Invalid input provided");
                return deferred.promise;
            }

            var onSuccess = function (response) {
                RestUtils.handleResponse({
                    event : "Account delete",
                    response : response
                });
                if(!RestUtils.hasError(response)) {
                    $scope.getAccountInfo();
                }
            };

            var onError = function (reason) {
                RestUtils.handleError({
                    event : "Account delete",
                    reason : reason
                });
            };

            rest.del({
                resource : 'accounts',
                id : accountInfo.accountName
            }).then(onSuccess, onError);
        };


        $scope.updateAccount = function (accountName){
            shared.accountInfo.updateAccount = angular.copy(accountInfo);
            notif.openModal('../accounts/updateAccount.html', 'updateAccountController', {});
        };

    };
    //Assign the controller to the module
    app.controller("accountSummaryController", accountSummaryController);
}) ();