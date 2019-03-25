package com.digital.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.digital.dao.UserDao;
import com.digital.models.ErrorClz;
import com.digital.models.User;

@RestController
public class UserController {
	@Autowired 
	 private UserDao userDao;
		@RequestMapping(value="/userReg",method=RequestMethod.POST)
		public ResponseEntity<?> userRegistration(@RequestBody User user)
	     {
			System.out.println("1 " +user);
			if(!userDao.isEmailUnique(user.getEmail())){//if email is not unique
				System.out.println("Emailcon"+user.getEmail());
				ErrorClz errorClz=new ErrorClz(1,"Email already exists.. pls choose different email id");
				return new ResponseEntity<ErrorClz>(errorClz,HttpStatus.INTERNAL_SERVER_ERROR);
			}
			if(!userDao.isPhonenumberUnique(user.getPhonenumber())){
				System.out.println("phnnbr1");

				ErrorClz errorClz=new ErrorClz(2,"Phone number already exists.. pls enter another phonenumber");
				return new ResponseEntity<ErrorClz>(errorClz,HttpStatus.INTERNAL_SERVER_ERROR);
			}
			if(user.getRole()=="" || user.getRole()==null){
				ErrorClz errorClz=new ErrorClz(3,"Role cannot be null..");
				return new ResponseEntity<ErrorClz>(errorClz,HttpStatus.INTERNAL_SERVER_ERROR);
			}
			try
			{
				System.out.println("try");

			userDao.userRegistration(user);
			System.out.println("user"+user);

			}catch(Exception e)
				{
					ErrorClz errorClz = new ErrorClz(4,"could not insert user details" + e.getMessage());
				    return new ResponseEntity<ErrorClz>(errorClz,HttpStatus.INTERNAL_SERVER_ERROR);
				}
		    return new ResponseEntity<User>(user,HttpStatus.OK);
		}
		@RequestMapping(value="/userLogin",method=RequestMethod.POST)
		public ResponseEntity<?> login(@RequestBody User user, HttpSession session){
			User validUser= userDao.login(user);
			if(validUser== null){
				ErrorClz errorClz= new ErrorClz(5,"invalid email/password ");
				return new ResponseEntity<ErrorClz>(errorClz,HttpStatus.UNAUTHORIZED);
			}
			else {
				validUser.setOnline(true);
	            userDao.updateUser(validUser);
	            session.setAttribute("loginId", user.getEmail());
	            return new ResponseEntity<User>(validUser, HttpStatus.OK);
			}
	}
		@RequestMapping(value="/userLogout",method=RequestMethod.PUT)
		public ResponseEntity<?> logout (HttpSession session)	
		{
		String email=(String)session.getAttribute("loginId");
		if(email==null)
		{
			ErrorClz errorClz= new ErrorClz(6," please login");
			return new ResponseEntity<ErrorClz>(errorClz,HttpStatus.UNAUTHORIZED);
		}
		
			User user =userDao.getUser(email);
		     user.setOnline(false);
		     userDao.updateUser(user);
		     session.removeAttribute(email);
		     session.invalidate();
				return new ResponseEntity<Void>(HttpStatus.OK);
		}
	  
		@RequestMapping(value="/getuser",method= RequestMethod.GET)
		public ResponseEntity<?> getUser(HttpSession session){
			String email=(String) session.getAttribute("loginId");
			if(email==null) {
				ErrorClz errorClz= new ErrorClz(7,"please Login");
				return new ResponseEntity<ErrorClz>(errorClz, HttpStatus.UNAUTHORIZED);
			}
			User user= userDao.getUser(email);
			
			
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
		@RequestMapping(value="/updateuser", method=RequestMethod.PUT )
		public ResponseEntity<?> updateUser(@RequestBody User user, HttpSession session){
		String email=(String)session.getAttribute("loginId");
			
		if(email==null) {
			ErrorClz errorClz= new ErrorClz(7,"please Login");
			return new ResponseEntity<ErrorClz>(errorClz, HttpStatus.UNAUTHORIZED);
		}
			
			if(!userDao.isPhonenumberUnique(user.getPhonenumber())) {
				ErrorClz errorClz =new ErrorClz(9,"phone number must be unique");
				return new ResponseEntity<ErrorClz>(errorClz, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			if(user.getRole()=="" || user.getRole()==null){
				ErrorClz errorClz=new ErrorClz(3,"Role cannot be null..");
				return new ResponseEntity<ErrorClz>(errorClz,HttpStatus.INTERNAL_SERVER_ERROR);
			}
			userDao.updateUser(user);
			return new ResponseEntity<Void>(HttpStatus.OK);
	  }
	}	
		
		

