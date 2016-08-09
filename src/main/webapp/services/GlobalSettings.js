/*******************************************************************************
 ******************************************************************************/

(function () {

    'use strict';

    var app = angular.module("app-ui");


    app.service('GlobalSettings', function ($cookieStore) {


        var settings = {};

        settings.menu = {

            startup_state : 'Expanded'
        };

        settings.session = {

            timeout : 30
        };

        settings.getMenuSettings = function (){

            if($cookieStore.get('X-Capi-Menu-Settings') === undefined) {

                $cookieStore.put('X-Capi-Menu-Settings',  settings.menu);

                return settings.menu;

            } else {

                return $cookieStore.get('X-Capi-Menu-Settings');
            }
        };

        settings.saveMenuSettings = function (menuState){

            settings.menu.startup_state = menuState;

            $cookieStore.put('X-Capi-Menu-Settings',  settings.menu);

        };

        settings.getSessionSettings = function (){

            if($cookieStore.get('X-Capi-Session-Settings') === undefined) {

                $cookieStore.put('X-Capi-Session-Settings',  settings.session);

                return settings.session;

            } else {

                return $cookieStore.get('X-Capi-Session-Settings');
            }
        };

        settings.saveSessionSettings = function (timeout){

            if(timeout > 5) {
                settings.session.timeout = timeout;
                $cookieStore.put('X-Capi-Session-Settings',  settings.session);
            } else {
                console.log("Timeout below 5 minutes not accepted!");
            }

        };

        return {

            "getMenuSettings" : settings.getMenuSettings,
            "saveMenuSettings" : settings.saveMenuSettings,
            "getSessionSettings" : settings.getSessionSettings,
            "saveSessionSettings" : settings.saveSessionSettings


        };

    });





}) ();