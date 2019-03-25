/**
 * 
 */
app.factory('UserService', function($http){
	var userService={}
	userService.userRegistration=function(user){
    return $http.post("http://localhost:8080/JobPortalMiddleware/userReg",user)		
	}
	userService.login=function(user){
	return $http.post("http://localhost:8080/JobPortalMiddleware/userLogin",user)	
	}
    userService.userlogout=function(){
    return $http.put("http://localhost:8080/JobPortalMiddleware/userLogout")	
    }
    userService.getUser=function(){
    return $http.get("http://localhost:8080/JobPortalMiddleware/getuser")
    }
    userService.updateUserProfile=function(user){
    return $http.put("http://localhost:8080/JobPortalMiddleware/updateuser",user)	
    }
	return userService
})