(function() {
	'use strict';

	angular.module('hotelreservation').controller('ProfileController',
			ProfileControllerFn);

	ProfileControllerFn.$inject = [ 'restaurantDetailService' ];

	function ProfileControllerFn(restaurantDetailService) {
		var prfVm = this;

		prfVm.settingObj = {
			value : false,
			daysclosed : []
		};

		restaurantDetailService.getRestaurantDetails().then(
				function(result) {
					prfVm.restaurant = result;
					prfVm.settingObj.daysclosed = prfVm.restaurant.daysClose.split(',');

					if (prfVm.restaurant.isAutoAssign === "true") {
						prfVm.settingObj.value = true;
					} else {
						prfVm.settingObj.value = false;
					}

				}, function(error) {
					console.log(error);
				});

		prfVm.editRestaurantDetails = function() {
			if (prfVm.settingObj.value === false) {
				prfVm.restaurant.isAutoAssign = "false";
			} else {
				prfVm.restaurant.isAutoAssign = "true";
			}

			var tempDaysClosedStr = "";
			var i;

			for (i = 0; i < prfVm.settingObj.daysclosed.length; i++) {
				tempDaysClosedStr = tempDaysClosedStr
						+ prfVm.settingObj.daysclosed[i] + ",";
			}
			
			// or use 
			//tempDaysClosedStr = prfVm.settingObj.daysclosed.join(",");

			prfVm.restaurant.daysClose = tempDaysClosedStr;
			console.log(prfVm.restaurant.daysClose);

			restaurantDetailService.editRestaurantDetails(prfVm.restaurant)
					.then(function(result) {
						console.log("Successfully Edited");
					}, function(error) {
					})
		}

		console.log("Inside profile controller ");

	}
})();
