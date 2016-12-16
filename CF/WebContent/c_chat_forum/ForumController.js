'use strict';
app
		.controller(
				'ForumController',
				[
						'$scope',
						'ForumService',
						'$location',
						'$rootScope',
						'$cookieStore',
						'$http',
						function($scope, ForumService, $location, $rootScope,
								$cookieStore,$http) {
							console.log("ForumController...")
							var self = this;
							self.forum= {
								forumId : '',
								userId : '',
								forumTitle : '',
								forumDescription: '',
								createdAt : '',
								status : ''
								
								
							};
							self.forums = [];
							
							self.submit = function() {
								{
									console.log('Saving New Forum', self.forum);
									self.createForum(self.forum);
								}
								
								self.reset();
							};
							self.createForum = function(forum) {
								console.log("createForum...")
								ForumService
										.createForum(forum)
										.then(
												self.fetchAllForums,
												function(errResponse) {
													console
															.error('Error while creating Forum...');
												});
							};
                            
							self.reset = function() {
								self.forum = {
										forumId : '',
										userId : '',
										forumTitle : '',
										forumDescription: '',
										createdAt : '',
										status : ''
									
								};
								$scope.myForm.$setPristine(); // reset Form
							};
							
							
							self.fetchAllForums= function() {
								console.log("fetchAllForums...")
								ForumService
										.fetchAllForums()
										.then(
												function(d) {
													self.forums = d;
												},
												function(errResponse) {
													console
															.error('Error while fetching forums');
												});
							};
							
							//self.fatchAllUsers();
							self.getSelectedForum= getForum 
							function getForum(forumId)
							{
								console.log("getting forum "+forumId)
								ForumService
										.getForum(forumId)
										.then(
												function(d) {
													self.forum = d;
													$location.path=('/view_forum');
												},
												function(errResponse) {
													console
															.error('Error while fetching blog');
												});
							};

							
							
							
							
							self.fetchAllForums();

						} ]);