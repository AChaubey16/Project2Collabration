
/**
 * NotificationCtrl
 *    /home,   NotificationCtrl function will get executed
 */
app.controller('NotificationCtrl',function($scope,$rootScope,NotificationService,$location,$routeParams){
	function getNotificationsNotViewed(){
	NotificationService.getNotificationsNotViewed().then(
			function(response){
				$rootScope.notifications=response.data
				$rootScope.notificationCount=$rootScope.notifications.length
			},
			function(response){
				if(response.status==401)
					$location.path('/userlogin')
			})
	}
	getNotificationsNotViewed()
    if($routeParams.notificationId!=undefined){
    	
    	NotificationService.getNotification($routeParams.notificationId).then(
    			function(response){
    				$scope.notification=response.data
    			},
    			function(response){
    				if(response.status==401)
    					$location.path('/userlogin')
    			})
    			
    	NotificationService.updateNotification($routeParams.notificationId).then(
    			function(response){
    				
    				getNotificationsNotViewed()
    			},
    			function(response){
    				if(response.status==401)
    					$location.path('/userlogin')
    			})
    }
})