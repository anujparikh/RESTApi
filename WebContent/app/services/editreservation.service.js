(function() {
	'use strict';

	angular.module('hotelreservation').service('editReservationService',
			editReservationService);

	editReservationService.$inject = [ '$q', '$http' ];

	function editReservationService($q, $http) {
		var self = this;

		self.cancelReservation = function(resId) {
			var defer = $q.defer();

			$http
				.post('api/reservations/' + resId)
				.then(function(response){
					defer.resolve(response.data);
				}, function(error){
					defer.reject(error.status);
				});

			return defer.promise;
		};
		
		self.fetchReservation = function(resId) {
			var defer = $q.defer();

			$http.get('api/reservations/' + resId).then(function(response) {
				console.log(response.data);
				defer.resolve(response.data);
			}, function(error) {
				defer.reject(error.status);
			});

			return defer.promise;
		};

		self.editReservation = function(reservationObj) {
			var defer = $q.defer();
			
			reservationObj = changeFormat(reservationObj);

			var jsonObject = angular.toJson(reservationObj, false);
			
			$http
				.put(
					'api/reservations/editreservation/'
							+ reservationObj.reservationId, jsonObject)
				.then(function(response){
					console.log(response.data);
					defer.resolve(response.data);
				}, function(error){
					defer.reject(error.status);
				});
			
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