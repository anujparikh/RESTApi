(function() {
    'use strict';

    angular
        .module('hotelreservation')
        .controller('PartyOrderController',PartyOrderControllerFn);

    PartyOrderControllerFn.$inject = [];

    function PartyOrderControllerFn() {
        var partyVm = this;

        console.log("Inside party order controller");

    }
})();