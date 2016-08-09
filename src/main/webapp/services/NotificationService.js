/*******************************************************************************
 ******************************************************************************/

(function () {

    'use strict';

    var app = angular.module("app-ui");

    app.service('NotificationService', function ($uibModal, toastr) {


        app.config(function(toastrConfig) {
            angular.extend(toastrConfig, {
                autoDismiss: false,
                containerId: 'toast-container',
                maxOpened: 0,
                newestOnTop: true,
                positionClass: 'toast-bottom-right',
                preventDuplicates: false,
                preventOpenDuplicates: false,
                target: 'body'
            });
        });

        app.config(function(toastrConfig) {
            angular.extend(toastrConfig, {
                allowHtml: false,
                closeButton: true,
                closeHtml: '<button>&times;</button>',
                extendedTimeOut: 1000,
                iconClasses: {
                    error: 'toast-error',
                    info: 'toast-info',
                    success: 'toast-success',
                    warning: 'toast-warning'
                },
                messageClass: 'toast-message',
                onHidden: null,
                onShown: null,
                onTap: null,
                progressBar: true,
                tapToDismiss: true,
                templates: {
                    toast: 'directives/toast/toast.html',
                    progressbar: 'directives/progressbar/progressbar.html'
                },
                timeOut: 2000,
                titleClass: 'toast-title',
                toastClass: 'toast'
            });


        });

        var notificationService = {};


        notificationService.modals = [];


        notificationService.showAlert = function (alert){

            var formattedMessage;
            var level;
            var dismiss;
            var timeout = 2000;

            if(alert.errorCode > 0){
                toastr.error(alert.reason ,  alert.errorCode+" Error");


            } else {
                toastr.success(alert.message, 'Success!');


            }

        };



        notificationService.openModal = function (templateUrl, controller, params){

            var modalInstance = $uibModal.open({
                animation: true,
                templateUrl: 'views/'+templateUrl,
                controller: controller,
                resolve: params,
                backdrop : 'static'
            });

            notificationService.modals.push(modalInstance);


            return modalInstance;

        };


        notificationService.closeModal = function (modalInstance){

            modalInstance.close();

            var index = 0;
            for(var i=0; i<notificationService.modals.length; i++) {

                if(notificationService.modals[i] == modalInstance) {
                    index = i;
                    break;
                }
            }

            notificationService.modals.splice(index, 1);
        };


        notificationService.closeAllModals = function (){

            for(var i=0; i<notificationService.modals.length; i++) {

                notificationService.modals[i].close();
            }

            notificationService.modals = [];
        };



        //Only expose the services. Do not expose data
        return {

            "openModal" : notificationService.openModal,
            "closeModal" : notificationService.closeModal,
            "closeAllModals" : notificationService.closeAllModals,
            "showAlert" : notificationService.showAlert

        };

    });






}) ();