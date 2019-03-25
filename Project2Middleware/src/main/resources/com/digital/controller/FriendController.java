package com.digital.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.digital.dao.FriendDao;
import com.digital.dao.UserDao;
import com.digital.models.ErrorClz;
import com.digital.models.Friend;
import com.digital.models.User;

@RestController
public class FriendController {
  
	@Autowired
private FriendDao friendDao;
	@Autowired
private UserDao userDao;
	@RequestMapping(value="/suggestedusers",method=RequestMethod.GET)
public ResponseEntity<?> getAllSuggestedUsers(HttpSession session)
{
	String email=(String)session.getAttribute("loginId");
	if(email==null){
		ErrorClz errorClz=new ErrorClz(6,"Please login...");
		return new ResponseEntity<ErrorClz>(errorClz,HttpStatus.UNAUTHORIZED);//login.html
	}
	List<User> suggestedUsers=friendDao.getAllSuggestedUsers(email);
	return new ResponseEntity<List<User>>(suggestedUsers,HttpStatus.OK);
}
	@RequestMapping(value="/friendrequest", method=RequestMethod.POST)
	public ResponseEntity<?> friendRequest(@RequestBody User toId, HttpSession session)
	{
		String email=(String)session.getAttribute("loginId");
		if(email==null){
			ErrorClz errorClz=new ErrorClz(6,"Please login...");
			return new ResponseEntity<ErrorClz>(errorClz,HttpStatus.UNAUTHORIZED);//login.html
		}
		Friend friend=new Friend();
		friend.setToId(toId);
		friend.setStatus('P');
		friend.setFromId(userDao.getUser(email));
		friendDao.friendRequest(friend);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	@RequestMapping(value="/pendingrequests", method=RequestMethod.GET)
	public ResponseEntity<?> pendingrequests( HttpSession session)
	{
		String email=(String)session.getAttribute("loginId");
		if(email==null){
			ErrorClz errorClz=new ErrorClz(6,"Please login...");
			return new ResponseEntity<ErrorClz>(errorClz,HttpStatus.UNAUTHORIZED);//login.html
		}
		List<Friend> pendingRequests=friendDao.pendingRequests(email);
		return new ResponseEntity<List<Friend>>(pendingRequests,HttpStatus.OK);
	}
	@RequestMapping(value="/acceptfriendrequest",method=RequestMethod.PUT)
	public ResponseEntity<?> acceptFriendRequest(@RequestBody Friend pendingRequest, HttpSession session)
	{
	String email=(String)session.getAttribute("loginId");
	if(email==null){
		ErrorClz errorClz=new ErrorClz(6,"Please login...");
		return new ResponseEntity<ErrorClz>(errorClz,HttpStatus.UNAUTHORIZED);//login.html
	}
	friendDao.acceptFriendRequest(pendingRequest);
	return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value="/deletefriendrequest",method=RequestMethod.PUT)
	public ResponseEntity<?> deleteFriendRequest(@RequestBody Friend friend, HttpSession session)
	{
	String email=(String)session.getAttribute("loginId");
	if(email==null){
		ErrorClz errorClz=new ErrorClz(6,"Please login...");
		return new ResponseEntity<ErrorClz>(errorClz,HttpStatus.UNAUTHORIZED);//login.html
	}
	friendDao.deleteFriendRequest(friend);
	return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value="/listoffriends", method=RequestMethod.GET)
	public ResponseEntity<?> listOfFriends(HttpSession session)
	{
	String email=(String)session.getAttribute("loginId");
	if(email == null)
		if(email==null){
			ErrorClz errorClz=new ErrorClz(6,"Please login...");
			return new ResponseEntity<ErrorClz>(errorClz,HttpStatus.UNAUTHORIZED);//login.html
		}
	List<User> friends=friendDao.listOfFriends(email);
	return new ResponseEntity<List<User>>(friends, HttpStatus.OK);
	}

}
