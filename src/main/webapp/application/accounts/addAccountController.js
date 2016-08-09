/*******************************************************************************
 ******************************************************************************/

(function (){

    'use strict';

    angular.module("app-ui").controller("addAccountController",

        function ($scope, $rootScope,
                 ServiceFactory, RestUtils, $uibModalInstance) {

            var rest = ServiceFactory.RestService;
            var notif = ServiceFactory.NotificationService;

            //Model
            $scope.accountInfo = {};


            $scope.addAccount = function () {

                console.log("Account creation submitted");

                var onSuccess = function (response) {
                    RestUtils.handleResponse({
                        event : "Account creation",
                        response : response
                    });
                    if(!RestUtils.hasError(response)) {
                        $rootScope.$broadcast('refresh-accounts');
                    }
                };

                var onError = function (reason) {
                    RestUtils.handleError({
                        event : "Account creation",
                        reason : reason
                    });
                };

                rest.post({
                    resource : 'accounts',
                    config : $scope.accountInfo
                }).then(onSuccess, onError);
            };


            $scope.resetForm = function () {
                $scope.accountInfo = {};
            };

            $scope.submitForm = function () {
                notif.closeModal($uibModalInstance);
                $scope.addAccount();
            };

            $scope.cancelForm = function () {
                notif.closeModal($uibModalInstance);
            };
        });
}) ();