    
/**
 * FriendService
 */
app.factory('FriendService',function($http){
	var friendService={}
	var BASE_URL ="http://localhost:8080/JobPortalMiddleware"
	
	friendService.getAllSuggestedUsers=function(){
		return $http.get(BASE_URL + "/suggestedusers")
	}
	
	
	friendService.addFriend=function(toId){
		return $http.post(BASE_URL + "/friendrequest",toId)
	}
	
	friendService.getPendingRequests=function(){
		return $http.get(BASE_URL + "/pendingrequests")
	}
	
     friendService.acceptRequest=function(pendingRequest){
    	 return $http.put(BASE_URL + "/acceptfriendrequest",pendingRequest)
     }
	
     friendService.deleteRequest=function(pendingRequest){
    	 return $http.put(BASE_URL +"/deletefriendrequest",pendingRequest)
     }
     
     
     friendService.getAllFriends=function(){
     	return $http.get(BASE_URL + "/listoffriends")
     }
	return friendService;
})
