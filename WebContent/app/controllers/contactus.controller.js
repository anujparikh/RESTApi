(function() {
    'use strict';

    angular
        .module('hotelreservation')
        .controller('ContactUsController',ContactUsControllerFn);

    ContactUsControllerFn.$inject = [];

    function ContactUsControllerFn() {
        var contactVm = this;

        console.log("Inside contact us controller");

    }
})();