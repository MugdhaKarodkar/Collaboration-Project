'use strict';

app
		.factory(
				'ForumService',
				[
						'$http',
						'$q',
						'$rootScope',
						function($http, $q, $rootScope) {

							console.log("ForumService...")

							var BASE_URL = 'http://localhost:9001/CB'

							return {
								
								
								createForum : function(forum) {
									console.log("calling create forum")
									return $http
											.post(BASE_URL + '/forum/', forum)
											.then(
													function(response) {
														return response.data;
													},
													function(errResponse) {
														console
																.error('Error while creating forum');
														return $q
																.reject(errResponse);
													});
								},

								fetchAllForums : function() {
									console.log("calling fetchAllForums ")
									return $http
											.get(BASE_URL + '/forums')
											.then(
													function(response) {
														return response.data;
													},
													function(errResponse) {
														console
																.error('Error while fetching ForumDetails');
														return $q
																.reject(errResponse);
													});
								},
								
								getForum : function(forumId) {
									console.log("calling getForum ")
									return $http
											.get(BASE_URL + '/forum/'+forumId)
											.then(
													function(response) {
														$rootScope.selectedForum=response.data
														return response.data;
													},
													function(errResponse) {
														console
																.error('Error while fetching Forum');
														return $q.reject(errResponse);
													});
								},

								

								

							};

						} ]);