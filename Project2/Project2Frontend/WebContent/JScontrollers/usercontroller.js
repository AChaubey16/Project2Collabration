
/**
 * 
 */
   app.controller('UserCtrl', function($scope, UserService, $location, $rootScope ,$cookieStore){
	 $scope.userRegistration= function(user){
		 UserService.userRegistration(user).then(
		                      function(response){
		                    	  $scope.msg="Registration sucessful.. please LOGIN"
		                    	$location.path('/userLogin')  
		                      },
		                      
		                      function(response){
		                    	  console.log(response.data)
		                        if(response.status==400)
		              				$scope.message="CLIENT ERROR... BAD REQUEST"
		              			else
		                            $scope.error=response.data
		                      })
		 
	 }
	 
	 $scope.login=function(user){
		 UserService.login(user).then(
		  		      function(response){
		  		    	
		  		    	  $rootScope.user=response.data
		  		    	  $cookieStore.put('userDetails',response.data)
		  		    	  
    		  		      $location.path('/home')  
		  		      },
		              function(response){
                         
		  		    	  $scope.error=response.data
		  		      }
		 )
	 }
	 if($rootScope.user!=undefined){
	 UserService.getUser().then(
		 function(response){
		$scope.user=response.data	 
		 },
		 function(response){
			 console.log(response.status)
			 console.log(response.data)	 
		 }
	 )}
	 $scope.updateUserProfile=function(user){
		 UserService.updateUserProfile(user).then(
                 function(response){
		  		      $location.path('/home')  
 
                   },
                   function(response){
                       console.log(response.status)
		  		    	 console.log(response.data)
		  		    	  $scope.error=response.data
		  		      }
		 )
	 }
 })