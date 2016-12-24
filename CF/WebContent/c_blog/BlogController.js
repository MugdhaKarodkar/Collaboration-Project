'use strict';
app
		.controller(
				'BlogController',
				[
						'$scope',
						'BlogService',
						'$location',
						'$rootScope',
						'$cookieStore',
						'$http',
						function($scope, BlogService, $location, $rootScope,
								$cookieStore,$http) {
							console.log("BlogController...")
							var self = this;
							self.blog = {
								blog_id : '',
								title : '',
								description : '',
								user_id : '',
								createdAt : '',
								status : '',
								reason : ''
								
							};
							self.blogs = [];

							self.fetchAllBlogs = function() {
								console.log("fetchAllBlogs...")
								BlogService
										.fetchAllBlogs()
										.then(
												function(d) {
													self.blogs = d;
												},
												function(errResponse) {
													console
															.error('Error while fetching blogs');
												});
							};
							
							//self.fatchAllUsers();
							self.getSelectedBlog= getBlog 
							function getBlog(blog_id)
							{
								console.log("getting blog "+blog_id)
								BlogService
										.getBlog(blog_id)
										.then(
												function(d) {
													self.blog = d;
													$location.path=('/view_blog');
												},
												function(errResponse) {
													console
															.error('Error while fetching blog');
												});
							};

							self.createBlog = function(blog) {
								console.log("createBlog...")
								BlogService
										.createBlog(blog)
										.then(
												self.fetchAllBlogs,
												function(errResponse) {
													console
															.error('Error while creating Blog.');
												});
							};
							self.submit = function() {
								{
									console.log('Saving New Blog', self.blog);
									self.createBlog(self.blog);
								}
								
								self.reset();
							};
							
							self.reset = function() {
								self.blog = {
									blog_id : '',
									title : '',
									description : '',
									user_id : '',
									createdAt : '',
									status : '',
									reason : ''
									
								};
								$scope.myForm.$setPristine(); // reset Form
							};
							self.fetchAllBlogs();

						} ]);