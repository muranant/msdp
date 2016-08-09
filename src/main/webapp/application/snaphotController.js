/*******************************************************************************
 ******************************************************************************/

(function (){
    'use strict';

    var app = angular.module("app-ui");

    var snapshotController = function ($scope, $location, ServiceFactory, RestUtils) {

        var notif = ServiceFactory.NotificationService;
        var rest = ServiceFactory.RestService;

        $scope.yes = function () {
            console.log('In yes');
        };

        $scope.no = function () {
            console.log('In no');
        };

        $scope.getDetails = function (accountId) {
            console.log("In getDetails");

            var onSuccess = function (response) {
                console.log("on Success");
                console.log(response);
                if(!RestUtils.hasError(response)){
                    console.log("on Success1");
                    $scope.snapshots = response.snapshots;
                    $scope.accountId = response.accountId;
                } else {
                    console.log("on Success2");
                    RestUtils.handleResponse({
                        event : "Get snapshot Info",
                        response : response
                    });
                }
            };

            var onError = function (reason) {
                console.log("on Error");
                RestUtils.handleError({
                    event : "Get snapshot Info",
                    reason : reason
                });
            };

            rest.get({
                resource : 'snapshots',
                id: accountId,
                disableLoader : true
            }).then(onSuccess, onError);
        };
        $scope.getDetails("12345");

    };
    //Assign the controller to the module
    app.controller("snapshotController", snapshotController);
}) ();