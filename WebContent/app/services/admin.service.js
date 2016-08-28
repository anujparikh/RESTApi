(function() {
	'use strict';
	
	angular
		.module('hotelreservation')
		.service('restaurantDetailService', restaurantDetailService);
	
	restaurantDetailService.$inject = ['$q', '$http'];
	
	function restaurantDetailService($q, $http) {
		var self = this;
		
		self.getRestaurantDetails = function() {
			var defer = $q.defer();
			
			$http
				.get('api/admin/profile')
				.then(function(response){
					defer.resolve(response.data);
					console.log(response.data);
				}, function(error){
					defer.reject(error.status);
				});
			
			return defer.promise;
		};
		
		self.editRestaurantDetails = function(restaurantObj) {
			var defer = $q.defer();
			
			$http
				.put('api/admin/resprofile', restaurantObj)
				.then(function(response){
					console.log(response.data);
					defer.resolve(response.data);
				}, function(error){
					defer.reject(error.status);
				});
			
			return defer.promise;
		}
	}
})();