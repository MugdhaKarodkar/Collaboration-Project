'use strict';
app
		.controller(
				'EventController',
				[
						'$scope',
						'EventService',
						'$location',
						'$rootScope',
						'$cookieStore',
						'$http',
						function($scope, EventService, $location, $rootScope,
								$cookieStore,$http) {
							console.log("EventController...")
							var self = this;
							self.event = {
								event_id : '',
								title : '',
								description : '',
								user_id : '',
								createdAt : '',
								reason : ''
								
							};
							self.events = [];

							self.fetchAllEvents = function() {
								console.log("fetchAllEvents...")
								EventService
										.fetchAllEvents()
										.then(
												function(d) {
													self.events = d;
												},
												function(errResponse) {
													console
															.error('Error while fetching events');
												});
							};
							
							//self.fatchAllUsers();
							self.getSelectedEvent= getEvent 
							function getEvent(event_id)
							{
								console.log("getting event "+event_id)
								EventService
										.getEvent(event_id)
										.then(
												function(d) {
													self.event = d;
													$location.path=('/view_event');
												},
												function(errResponse) {
													console
															.error('Error while fetching events');
												});
							};

							self.createEvent = function(event) {
								console.log("createEvent...")
								EventService
										.createEvent(event)
										.then(
												self.fetchAllEvents,
												function(errResponse) {
													console
															.error('Error while creating Event.');
												});
							};
							self.submit = function() {
								{
									console.log('Saving New Event', self.event);
									self.createEvent(self.event);
								}
								
								self.reset();
							};
							
							self.reset = function() {
								self.event = {
									event_id : '',
									title : '',
									description : '',
									user_id : '',
									createdAt : '',
									reason : ''
									
								};
								$scope.myForm.$setPristine(); // reset Form
							};
							self.fetchAllEvents();

						} ]);