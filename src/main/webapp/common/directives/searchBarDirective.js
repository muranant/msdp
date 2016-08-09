/*******************************************************************************
 ******************************************************************************/

(function (){

    'use strict';

    var app = angular.module("app-ui");


    app.directive("searchBar",

        function () {

            return {

                restrict : 'E',
                templateUrl : 'common/directives/searchBar.html'

            };

        });



}) ();