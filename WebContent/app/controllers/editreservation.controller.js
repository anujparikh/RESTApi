(function() {
	'use strict';

	angular.module('hotelreservation').controller('EditReservationController',
			EditReservationControllerFn);

	EditReservationControllerFn.$inject = [ 'editReservationService' ];

	function EditReservationControllerFn(editReservationService) {
		var editVm = this;
		editVm.result = {
			showAlert : false,
			successMsg : 'Your Reservation edited successfully !!',
			show : true
		};
		
		editVm.cancelResFn = function() {
			editReservationService.cancelReservation(editVm.reservation.reservationId).then(
					function(result) {
						console.log("Successfully Cancel");
						editVm.result.successMsg = "Your Reservation ( " + editVm.reservation.reservationId + " ) cancelled successfully !!"  ;
						editVm.result.showAlert = true;
					}, function(error) {
					})
		}

		editVm.fetchResFn = function() {
			editReservationService
					.fetchReservation(editVm.reservation.reservationId)
					.then(
							function(result) {
								editVm.result.show = !editVm.result.show;
								console.log("Successfully fetched");
								editVm.reservation = result;
								editVm.fullName = editVm.reservation.firstName
										+ " " + editVm.reservation.lastName;
							}, function(error) {
								console.log(error);
							});
		}

		editVm.editResFn = function() {
			editReservationService.editReservation(editVm.reservation).then(
					function(result) {
						console.log("Successfully Edited");
						editVm.result.successMsg = "Your Reservation ( " + editVm.reservation.reservationId + " ) edited successfully !!"  ;
						editVm.result.showAlert = true;
						editVm.result.show = !editVm.result.show;
					}, function(error) {
					})
		}

		console.log("Inside edit reservation controller");

	}
})();