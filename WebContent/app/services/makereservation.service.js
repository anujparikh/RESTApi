(function() {
	'use strict';

	angular.module('hotelreservation').service('makeReservationService',
			makeReservationService);

	makeReservationService.$inject = [ '$q', '$http' ];

	function makeReservationService($q, $http) {
		var self = this;

		self.makeReservation = function(reservationObj) {
			var defer = $q.defer();

			reservationObj = changeFormat(reservationObj);

			var jsonObject = angular.toJson(reservationObj, false);

			$http
					.post(
							'api/reservations/makereservation',
							jsonObject).then(function(response) {
						console.log(response.data);
						defer.resolve(response.data);
					}, function(error) {
						defer.reject(error.status);
					});

			console.dir(jsonObject);
			console.dir(reservationObj);

			return defer.promise;
		}
	}

	changeFormat.$inject = [ 'reservationObj' ];

	function changeFormat(reservationObj) {
		var date = reservationObj.reservationDate;
		var toTime = reservationObj.toTime;
		var fromTime = reservationObj.fromTime;

		var dateObj = new Date(date);
		var dateMonth = dateObj.getMonth() + 1;
		var dateDay = dateObj.getDate();
		var dateYear = dateObj.getFullYear();
		var changedFormatDate = dateMonth + "-" + dateDay + "-" + dateYear;

		console.log("Change date after formating : " + changedFormatDate);

		reservationObj.reservationDate = changedFormatDate;
		
		return reservationObj;
	}
})();