/*******************************************************************************
 ******************************************************************************/

(function () {

    'use strict';

    var module = angular.module("app-ui");

    module.service('RestUtils', function (ServiceFactory, $rootScope) {


        var notif = ServiceFactory.NotificationService;

        var hasError = function (response) {

            return ((response.errorMessage !== undefined) && (response.errorMessage !== null));
        };


        var handleResponse = function (context) {

            var response = context.response,
                event = context.event,
                alert;

            if(hasError(response)){

                alert = {
                    message : event + " failed",
                    errorCode : response.httpErrorCode,
                    reason : response.errorMessage
                };

                notif.showAlert(alert);


            } else {

                alert = {
                    message : event + " successful"
                };

                notif.showAlert(alert);

            }


        };


        var handleError = function (context) {

            var event = context.event,
                reason = context.reason;

            var errorCode = reason.status;
            var reasonText = reason.statusText;


            if(context.reason.data !== null && context.reason.data !== undefined) {

                if(context.reason.data.errorMessage !== null && context.reason.data.errorMessage !== undefined) {

                    reasonText = context.reason.data.errorMessage;
                }


            }


            var alert = {
                    level : "danger",
                    message : event + " failed",
                    errorCode : errorCode,
                    reason: reasonText
                };

            notif.showAlert(alert);

        };





        return {
            hasError : hasError,
            handleResponse : handleResponse,
            handleError : handleError

        };


    });


}) ();