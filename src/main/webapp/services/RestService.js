(function () {

    'use strict';

    var module = angular.module("app-ui");

    module.service('RestService', function ($http, AuthService,
                                           $rootScope, NotificationService, $q) {


        var notif = NotificationService;

        var restService = {};
        restService.baseUrl = '/appw/v1/';

        var req = {
             headers : {},
             data : {}
        };

        restService.resources = {
                        LOGIN : 'login'
        };


        restService.begin = function (){

            if(req.loader) {

                notif.openModal('../common/views/progress.html', 'progressController', {});
            }
        };

        restService.end = function (){

            $rootScope.$broadcast('request-end');
        };


        var checkUnAuthorizedMessage = function(response) {

            if(response.data !== undefined) {

                if(response.data.errorCode == 401 || response.data.httpErrorCode == 401) {

                    console.log("Received unauthorized message");
                    $rootScope.$broadcast('session-expired');
                }

            }

        };

        var onSuccess = function (response) {

            console.log("Rest call succeeded!");
            console.dir(response);
            restService.end();

            checkUnAuthorizedMessage(response);
            return response.data;
        };

        var onError = function (reason) {

            console.log("Rest call failed");
            console.dir(reason);
            restService.end();

            checkUnAuthorizedMessage(reason);

            var deferred = $q.defer();
            deferred.reject(reason);
            return deferred.promise;
        };

        restService.execute = function (req){

            console.log("Url: " + req.url);
            restService.begin();
            return $http(req).then(onSuccess, onError);
        };


        restService.setUrl = function (resource, id){

            if(id === null || id === undefined) {
                req.url = restService.baseUrl + resource;

            } else {
                req.url = restService.baseUrl + resource + '/' + id;
            }

        };


        restService.setHeaders = function (isUpload, isDownload){

            if(isUpload){
                req.headers['Content-Type'] = undefined;

            } else{
                req.headers['Content-Type'] = 'application/json';

            }

            if(isDownload){
                req.headers.Accept =  'application/octet-stream';
                req.headers['Content-Type'] = 'application/json';
                req.responseType =  'arraybuffer';

            } else{
                req.headers.Accept =  'application/json';

            }


            req.headers['X-Capi-Request'] = AuthService.sessionKey;

        };

        restService.setAdditionalHeaders = function (request){
            console.log("Set additional headers");
        };

        restService.setMethod = function (method){
            req.method = method;
        };

        restService.setPayload = function (config){
            req.data = config;
        };

        restService.appendUrl = function (url){
            if(url !== null && url !== undefined) {
                req.url += url;
            }
        };

        restService.setLoader = function (loader) {
            req.loader = loader;
        };

        restService.setParams = function (method, request){

            var resource = request.resource;
            var id = request.id;
            var config = request.config;
            var url = request.append;
            var isUpload = request.upload;
            var isDownload = request.download;



            if(request.disableLoader) {
                restService.setLoader(false);
            } else {
                restService.setLoader(true);
            }

            restService.setMethod(method);
            restService.setHeaders(isUpload, isDownload);
            restService.setAdditionalHeaders(request);
            restService.setUrl(resource, id);
            restService.setPayload(config);
            restService.appendUrl(url);


        };



        /* Define the main REST requests */

        restService.get = function (request) {
            req = {
                headers : {},
                data : {}
            };
            restService.setParams('GET', request);
            return restService.execute(req);

        };

        restService.post = function (request) {

            req = {
                headers : {},
                data : {}
            };

            restService.setParams('POST', request);


            return restService.execute(req);

        };

        restService.put = function (request) {

            req = {
                headers : {},
                data : {}
            };

            restService.setParams('PUT', request);

            return restService.execute(req);

        };

        restService.del = function (request) {

            req = {
                headers : {},
                data : {}
            };

            restService.setParams('DELETE', request);

            return restService.execute(req);

        };



        return restService;


    });


}) ();
