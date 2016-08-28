(function() {
    'use strict';

    angular
        .module('hotelreservation')
        .controller('MakeReservationController',MakeReservationControllerFn);

    MakeReservationControllerFn.$inject = ['makeReservationService'];

    function MakeReservationControllerFn(makeReservationService) {
        var resVm = this;
        resVm.result = {
        		showAlert: false,
        		successMsg : 'Your Reservation done successfully !!',
        		button: 'Clear'
        };

        resVm.makeResFn = function() {
            makeReservationService.makeReservation(resVm.reservation)
                .then(function(result) {
                    resVm.reservation = result;
                	resVm.result.successMsg = "Your Reservation done successfully !! You reservation id is " + resVm.reservation.reservationId;
                	resVm.result.showAlert = true;
                	resVm.result.button = "Make new Reservation"
                }, function(error) {
                    console.log(error);
                });
        }
        console.log("Inside make reservation controller");
    }
})();