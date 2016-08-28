(function() {
	'use strict;'
	
	angular
		.module('hotelreservation')
		.directive('formatDate', formatDateFn);

		formatDateFn.$inject = [];
		
		function formatDateFn() {
			return {
				require : 'ngModel',
				link : function(scope, elem, attr, modelCtrl) {
					modelCtrl.$formatters.push(function(modelValue) {
						return new Date(modelValue);
					})
				}
			}
		}
})();