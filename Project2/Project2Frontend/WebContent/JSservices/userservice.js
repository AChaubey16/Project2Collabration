/**
 * 
 */
app.factory('UserService', function($http){
	var userService={}
	userService.userRegistration=function(user){
    return $http.post("http://localhost:8080/Project2Middleware/userReg",user)		
	}
	userService.login=function(user){
	return $http.post("http://localhost:8080/Project2Middleware/userLogin",user)	
	}
    userService.userlogout=function(){
    return $http.put("http://localhost:8080/Project2Middleware/userLogout")	
    }
    userService.getUser=function(){
    return $http.get("http://localhost:8080/Project2Middleware/getuser")
    }
    userService.updateUserProfile=function(user){
    return $http.put("http://localhost:8080/Project2Middleware/updateuser",user)	
    }
	return userService
})