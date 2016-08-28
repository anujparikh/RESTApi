(function() {
    'use strict';

    angular
        .module('hotelreservation',['ngRoute', 'ui.bootstrap'])
        .config(moduleConfig);

    moduleConfig.$inject = ['$routeProvider'];

    function moduleConfig($routeProvider) {
        $routeProvider
            .when('/home', {
                templateUrl: 'app/views/home.tmpl.html',
                controller: 'HomeController',
                controllerAs: 'homeVm'
            })
            .when('/makereservation', {
                templateUrl: 'app/views/custmakeres.tmpl.html',
                controller: 'MakeReservationController',
                controllerAs: 'resVm'
            })
            .when('/editreservation', {
                templateUrl: 'app/views/custeditres.tmpl.html',
                controller: 'EditReservationController',
                controllerAs: 'editVm'
            })
            .when('/contactus', {
                templateUrl: 'app/views/contactus.tmpl.html',
                controller: 'ContactUsController',
                controllerAs: 'contactVm'
            })
            .when('/partyorder', {
                templateUrl: 'app/views/partyorder.tmpl.html',
                controller: 'PartyOrderController',
                controllerAs: 'partyVm'
            })
            .when('/listofreservations', {
                templateUrl: 'app/views/listofreservations.tmpl.html',
                controller: 'ListReservationController',
                controllerAs: 'listVm'
            })
            .when('/viewseatingarea', {
                templateUrl: 'app/views/seatingarea.tmpl.html',
                controller: 'SeatingAreaController',
                controllerAs: 'seatVm'
            })
            .when('/adminreservation', {
                templateUrl: 'app/views/adminmakeres.tmpl.html',
                controller: 'MakeReservationController',
                controllerAs: 'resVm'
            })
            .when('/admineditreservation', {
                templateUrl: 'app/views/admineditres.tmpl.html',
                controller: 'EditReservationController',
                controllerAs: 'editVm'
            })
            .when('/profile', {
                templateUrl: 'app/views/adminprofile.tmpl.html',
                controller: 'ProfileController',
                controllerAs: 'prfVm'
            })
            .when('/profile/resprofile', {
                redirectTo: '/profile'
            })
            .when('/profile/assigntable', {
                templateUrl: 'app/views/assigntable.tmpl.html',
                controller: 'ProfileController',
                controllerAs: 'prfVm'
            })
            .when('/profile/ressettings', {
                templateUrl: 'app/views/ressetting.tmpl.html',
                controller: 'ProfileController',
                controllerAs: 'prfVm'
            })
            .when('/profile/contactlist', {
                templateUrl: 'app/views/contactlist.tmpl.html',
                controller: 'ContactController',
                controllerAs: 'cntVm'
            })
            .otherwise({
                redirectTo: '/home'
            });
        ;
    }
})();