package com.digital.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.digital.models.Blog;
import com.digital.models.BlogLike;
import com.digital.models.User;

public class BlogLikeDaoImpl implements BlogLikeDao {

	@Autowired
	private SessionFactory sessionFactory;
	public BlogLike hasUserLikedBlog(int blogId, String email) {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery(
		"from BlogLike where blog.blogId=:blogId and likedBy.email=:email");
		query.setInteger("blogId", blogId);
		query.setString("email", email);
		BlogLike blogLikes=(BlogLike)query.uniqueResult();
		return blogLikes;
	}

	public Blog updateLikes(int blogId, String email) {
		Session session=sessionFactory.getCurrentSession();
		Blog blog=(Blog)session.get(Blog.class,blogId);
		User likedBy=(User)session.get(User.class, email);
		BlogLike blogLikes=hasUserLikedBlog(blogId, email);
		if(blogLikes==null)
		{
		   blogLikes=new BlogLike();
		   blogLikes.setBlog(blog);
		   blogLikes.setLikedBy(likedBy);
		   session.save(blogLikes);
		   blog.setLikes(blog.getLikes()+1);
		   	session.update(blog);
		}
		else{
   			session.delete(blogLikes);
   			blog.setLikes(blog.getLikes() - 1);
   			session.update(blog);
		}
		
		return blog;
	}

	
}
