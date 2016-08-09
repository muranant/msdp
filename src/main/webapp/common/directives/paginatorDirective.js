/*******************************************************************************
 ******************************************************************************/

(function (){

    'use strict';

    var app = angular.module("app-ui");


    app.directive("paginator",

        function () {

            return {

                restrict : 'E',
                templateUrl : 'common/directives/paginator.html',
                scope : {
                    data : "=",
                    itemsPerPage : "="
                },
                controller : function ($scope) {

                    var pageIndexStart = 0;
                    var pageIndexEnd = $scope.itemsPerPage-1;

                    $scope.originalData = angular.copy($scope.data);
                    $scope.data = $scope.originalData.slice(pageIndexStart, $scope.itemsPerPage);


                    $scope.pageChanged = function (){

                        pageIndexStart = (($scope.currentPage-1) * $scope.itemsPerPage > ($scope.originalData.length - 1)) ?
                        $scope.originalData.length - 1 : ($scope.currentPage-1) * $scope.itemsPerPage;

                        pageIndexEnd = (pageIndexStart + $scope.itemsPerPage-1 > ($scope.originalData.length - 1)) ?
                        $scope.originalData.length - 1 : pageIndexStart + $scope.itemsPerPage-1;

                        
                        $scope.data = $scope.originalData.slice(pageIndexStart, pageIndexEnd+1);


                    };
                }

            };

        });



}) ();