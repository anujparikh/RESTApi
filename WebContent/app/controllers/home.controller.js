(function() {
    'use strict';

    angular
        .module('hotelreservation')
        .controller('HomeController', HomeControllerFn);

    HomeControllerFn.$inject = [];

    function HomeControllerFn() {
        var homeVm = this;

        console.log("Inside home controller");
    }

})();