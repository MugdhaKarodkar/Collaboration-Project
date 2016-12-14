'use strict';

app
		.factory(
				'BlogService',
				[
						'$http',
						'$q',
						'$rootScope',
						function($http, $q, $rootScope) {

							console.log("BlogService...")

							var BASE_URL = 'http://localhost:9001/CB'

							return {

								fetchAllBlogs : function() {
									console.log("calling fetchAllBlogs ")
									return $http
											.get(BASE_URL + '/blogs')
											.then(
													function(response) {
														return response.data;
													},
													function(errResponse) {
														console
																.error('Error while fetching BlogDetails');
														return $q
																.reject(errResponse);
													});
								},
								
								getBlog : function(blog_id) {
									console.log("calling getBlog ")
									return $http
											.get(BASE_URL + '/blog/'+blog_id)
											.then(
													function(response) {
														$rootScope.selectedBlog=response.data
														return response.data;
													},
													function(errResponse) {
														console
																.error('Error while fetching Blog');
														return $q.reject(errResponse);
													});
								},

								createBlog : function(blog) {
									console.log("calling create blog")
									return $http
											.post(BASE_URL + '/blog/', blog)
											.then(
													function(response) {
														return response.data;
													},
													function(errResponse) {
														console
																.error('Error while creating blog');
														return $q
																.reject(errResponse);
													});
								},

								

							};

						} ]);