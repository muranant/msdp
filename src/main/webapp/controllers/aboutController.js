/*******************************************************************************
 ******************************************************************************/

(function (){

    'use strict';

    var app = angular.module("app-ui");


    app.controller("aboutController",

        function ($scope, ServiceFactory, RestUtils, $uibModalInstance) {

            var notif = ServiceFactory.NotificationService;
            var rest = ServiceFactory.RestService;


            $scope.getManifestAboutInfo = function (){


                var onSuccess = function (response) {
                    if(!RestUtils.hasError(response)){
                        $scope.manifestAbout = response.manifestFiles;
                    } else {
                        RestUtils.handleResponse({
                            event : "Get manifest about",
                            response : response
                        });
                    }
                };

                var onError = function (reason) {
                    RestUtils.handleError({
                        event : "Get manifest about",
                        reason : reason
                    });
                };

                rest.get({
                    resource : 'manifest/about'
                }).then(onSuccess, onError);
            };

            $scope.getManifestAboutInfo();


            $scope.aboutOK = function () {
                console.log("Close about");
                notif.closeModal($uibModalInstance);
            };

        });



}) ();