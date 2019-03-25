package com.digital.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.digital.dao.BlogLikeDao;
import com.digital.models.Blog;
import com.digital.models.BlogLike;
import com.digital.models.ErrorClz;

@RestController
public class BlogLikeController {
	@Autowired
	private BlogLikeDao blogLikeDao;

	@RequestMapping(value="/hasuserlikedblog/{blogId}",method=RequestMethod.GET)
	public ResponseEntity<?> hasUserLikedBlogPost(@PathVariable int blogId,HttpSession session){
			String email=(String)session.getAttribute("loginId");
			if(email==null){
				ErrorClz errorClz=new ErrorClz(5,"Please login..");
				return new ResponseEntity<ErrorClz>(errorClz,HttpStatus.UNAUTHORIZED);
			}
			BlogLike blogLike=blogLikeDao.hasUserLikedBlog(blogId, email);
			return new ResponseEntity<BlogLike>(blogLike,HttpStatus.OK);
	}
	@RequestMapping(value="/updatelikes/{blogId}",method=RequestMethod.PUT)
	public ResponseEntity<?> updateLikes(@PathVariable int blogId,HttpSession session){
				String email=(String)session.getAttribute("loginId");
				if(email==null){
					ErrorClz errorClz=new ErrorClz(5,"Please login..");
					return new ResponseEntity<ErrorClz>(errorClz,HttpStatus.UNAUTHORIZED);
				}
				Blog blog=blogLikeDao.updateLikes(blogId, email);
				return new ResponseEntity<Blog>(blog,HttpStatus.OK);
	}
}
