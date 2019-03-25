
/**
 * BlogPostCtrl
 */
app.controller('BlogCtrl',function($scope, BlogService, $location, $rootScope) {

					$scope.addBlog = function(blog) {
						BlogService
								.addBlog(blog)
								.then(
										function(response) {
											alert('BlogPost added successfully and it is waiting for approval')
											$location.path('/home')
										}, function(response) {
											if (response.status == 401)
												$location.path('/login')
											$scope.error = response.data

										})
					}

					if ($rootScope.user != undefined)
						BlogService.getBlogsApproved().then(function(response) {
							$scope.blogsApproved = response.data
						}, function(response) {
							if (response.status == 401)
								$location.path('/login')
						})

					if ($rootScope.user.role == 'ADMIN')
						BlogService.getBlogsWaiting().then(
								function(response) {
									$scope.blogsWaiting = response.data
								},
								function(response) {
									if (response.status == 401
											&& response.data.errorCode == 5)
										$location.path('/login')

									$scope.error = response.data
								})
				})
