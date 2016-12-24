'use strict';

app
		.factory(
				'EventService',
				[
						'$http',
						'$q',
						'$rootScope',
						function($http, $q, $rootScope) {

							console.log("EventService...")

							var BASE_URL = 'http://localhost:9001/CB'

							return {

								fetchAllEvents : function() {
									console.log("calling fetchAllEvents ")
									return $http
											.get(BASE_URL + '/events')
											.then(
													function(response) {
														return response.data;
													},
													function(errResponse) {
														console
																.error('Error while fetching EventDetails');
														return $q
																.reject(errResponse);
													});
								},
								
								getEvent : function(event_id) {
									console.log("calling getEvent ")
									return $http
											.get(BASE_URL + '/event/'+event_id)
											.then(
													function(response) {
														$rootScope.selectedEvent=response.data
														return response.data;
													},
													function(errResponse) {
														console
																.error('Error while fetching Event');
														return $q.reject(errResponse);
													});
								},

								createEvent : function(event) {
									console.log("calling create event")
									return $http
											.post(BASE_URL + '/event/', event)
											.then(
													function(response) {
														return response.data;
													},
													function(errResponse) {
														console
																.error('Error while creating event');
														return $q
																.reject(errResponse);
													});
								},

								

							};

						} ]);