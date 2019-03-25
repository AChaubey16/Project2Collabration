/**
 * Angular Module, configuration
 */
var app=angular.module("app",['ngRoute','ngCookies'])
app.config(function($routeProvider){
	$routeProvider
	.when('/addjob',{controller:'JobCtrl',templateUrl:'views/jobform.html'})
	.when('/getalljobs',{controller:'JobCtrl',templateUrl:'views/joblist.html'})
	.when('/registration',{controller:'UserCtrl',templateUrl:'views/registrationForm.html'})
	.when('/userLogin',{controller:'UserCtrl',templateUrl:'views/loginForm.html'})
	.when('/profile',{controller:'UserCtrl',templateUrl:'views/viewprofile.html'})
	.when('/updateuser',{controller:'UserCtrl',templateUrl:'views/updateUserDetails.html'})
    .when('/addblog',{controller:'BlogCtrl',templateUrl:'views/blogForm.html'})
	.when('/blogsapproved',{controller:'BlogCtrl',templateUrl:'views/blogsApprovedForm.html'})
	.when('/blogswaiting',{controller:'BlogCtrl',templateUrl:'views/blogsWaitingForm.html'})
    .when('/getblogwaitingforapproval/:blogId',{controller:'BlogIndetailCtrl', templateUrl:'views/blogapprovalform.html'})
    .when('/getblogapproved/:blogId',{controller:'BlogIndetailCtrl',templateUrl:'views/blogindetail.html'})
    .when('/home',{controller:'NotificationCtrl',templateUrl:'views/home.html'})
	.when('/getnotification/:notificationId',{controller:'NotificationCtrl',templateUrl:'views/notificationindetail.html'})
	.when('/uploadprofilepic',{templateUrl:'views/uploadprofilepic.html'})
	.when('/suggestedusers',{controller:'FriendCtrl',templateUrl:'views/suggesteduserslist.html'})
	.when('/pendingrequests',{controller:'FriendCtrl',templateUrl:'views/pendingrequests.html'})
	.when('/listoffriends',{controller:'FriendCtrl',templateUrl:'views/friendslist.html'})
	.when('/chat',{controller:'ChatCtrl',templateUrl:'views/chat.html'})
	.otherwise({controller:'NotificationCtrl',templateUrl:'views/home.html'})	
})
app.run(function($rootScope, UserService, $location,$cookieStore){
	if($rootScope.user==undefined)
		$rootScope.user=$cookieStore.get('userDetails')

$rootScope.userlogout=function(){
		UserService.userlogout().then(
			function(response){
				delete $rootScope.user
				$cookieStore.remove('userDetails')
				$location.path('/userLogin')	
			},
			function(response){
				if(response.status==401)
                $location.path('/userLogin')	
			})
}
})
