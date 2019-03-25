package com.digital.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.digital.dao.BlogDao;
import com.digital.dao.NotificationDao;
import com.digital.dao.UserDao;
import com.digital.models.Blog;
import com.digital.models.ErrorClz;
import com.digital.models.Notification;
import com.digital.models.User;

@RestController
public class BlogController {
	@Autowired
	private BlogDao blogDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private NotificationDao notificationDao;


	@RequestMapping(value="/addBlog", method=RequestMethod.POST)
	ResponseEntity<?> addBlog(@RequestBody Blog blog, HttpSession session){
	String email=(String)session.getAttribute("loginId");
	if(email==null)
	{
		ErrorClz errorClz= new ErrorClz(1,"please login");
		return new ResponseEntity<ErrorClz>(errorClz, HttpStatus.UNAUTHORIZED);
	}
	blog.setPostedOn(new Date());
	User author= userDao.getUser(email);
	blog.setAuthor(author);
	User user =userDao.getUser(email);
	if(user.getRole().equals("ADMIN"))
		blog.setApproved(true);
	try {
	blogDao.addBlog(blog);	
	}
	catch(Exception e) {
		 ErrorClz errorClz= new ErrorClz(2,"blog could not post."+e.getMessage());
		 return new ResponseEntity<ErrorClz>(errorClz, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	return new ResponseEntity<Void>(HttpStatus.OK);
	}
	@RequestMapping(value="/blogsApproved", method=RequestMethod.GET)
	ResponseEntity<?> getBlogsApproved(HttpSession session){
	    String email=(String)session.getAttribute("loginId");
	 if(email==null)
	    {
			ErrorClz errorClz= new ErrorClz(3,"please login");
			return new ResponseEntity<ErrorClz>(errorClz, HttpStatus.UNAUTHORIZED);
		}
		List<Blog> blogs=blogDao.getBlogsApproved();
		return new ResponseEntity<List<Blog>>(blogs, HttpStatus.OK);
	}

	@RequestMapping(value="/blogsWaiting", method=RequestMethod.GET)
	ResponseEntity<?> getWaitingForApproval(HttpSession session){
	    String email=(String)session.getAttribute("loginId");
	 if(email==null)
	    {
			ErrorClz errorClz= new ErrorClz(4,"please login");
			return new ResponseEntity<ErrorClz>(errorClz, HttpStatus.UNAUTHORIZED);
		}
	  User user= userDao.getUser(email);
	  if(!user.getRole().equals("ADMIN")) {
		   ErrorClz errorClz= new ErrorClz(5,"please login");
		   return new ResponseEntity<ErrorClz>(errorClz, HttpStatus.UNAUTHORIZED);
		}
	  List<Blog> blogsWaiting=blogDao.getBlogsWaitingForApproval();
	  return new ResponseEntity<List<Blog>>(blogsWaiting, HttpStatus.OK);
	}

	   @RequestMapping(value="/getblog/{blogId}")
	   public ResponseEntity<?> getBlog(HttpSession session,@PathVariable int blogId){
		String email=(String)session.getAttribute("loginId");
		if(email==null){
			ErrorClz errorClz=new ErrorClz(5,"Please login..");
			return new ResponseEntity<ErrorClz>(errorClz,HttpStatus.UNAUTHORIZED);
		}
		Blog blog=blogDao.getBlog(blogId);
		return new ResponseEntity<Blog>(blog,HttpStatus.OK);
	   }
	   @RequestMapping(value="/approveblog",method=RequestMethod.PUT)
	   public ResponseEntity<?> approveBlog(HttpSession session,@RequestBody Blog blog){
	   		String email=(String)session.getAttribute("loginId");
	   		if(email==null){
	   			ErrorClz errorClz=new ErrorClz(5,"Please login..");
	   			return new ResponseEntity<ErrorClz>(errorClz,HttpStatus.UNAUTHORIZED);
	   		}
	   		
	   		User user=userDao.getUser(email);
	   		if(!user.getRole().equals("ADMIN")){
	   			ErrorClz errorClz=
	   				new ErrorClz(7,"Access Denied.. You are not authorized to view the blogs waiting for approval");
	   			return new ResponseEntity<ErrorClz>(errorClz,HttpStatus.UNAUTHORIZED);
	   		}
	   		blog.setApproved(true);
	   		blogDao.approveBlog(blog);
	   		Notification notification=new Notification();
		    notification.setApprovedOrRejected("Approved");
		    notification.setBlogTitle(blog.getBlogTitle());
	    	notification.setUserToBeNotified(blog.getAuthor());//AUTHOR OF THE BLOGPOST
			notificationDao.addNotification(notification);
			return new ResponseEntity<Void>(HttpStatus.OK);
	   }
	   @RequestMapping(value="/rejectblog/{blogId}/{rejectionReason}",method=RequestMethod.PUT)
	   public ResponseEntity<?> rejectBlog(HttpSession session,@PathVariable int blogId, @PathVariable String rejectionReason){
	   		String email=(String)session.getAttribute("loginId");
	   		if(email==null){
	   			ErrorClz errorClz=new ErrorClz(5,"Please login..");
	   			return new ResponseEntity<ErrorClz>(errorClz,HttpStatus.UNAUTHORIZED);
	   		}
	   		User user=userDao.getUser(email);
	   		if(!user.getRole().equals("ADMIN")){
	   			ErrorClz errorClz=
	   				new ErrorClz(7,"Access Denied.. You are not authorized to view the blogs waiting for approval");
	   			return new ResponseEntity<ErrorClz>(errorClz,HttpStatus.UNAUTHORIZED);
	   		}
	   		Blog blog=blogDao.getBlog(blogId);
	   		
	   Notification notification=new Notification();
	   notification.setApprovedOrRejected("Rejected");
	   notification.setBlogTitle(blog.getBlogTitle());
		notification.setUserToBeNotified(blog.getAuthor());
		notification.setRejectionReason(rejectionReason);
		notificationDao.addNotification(notification);
		blogDao.rejectBlog(blog);
		return new ResponseEntity<Void>(HttpStatus.OK);

	   }
}
