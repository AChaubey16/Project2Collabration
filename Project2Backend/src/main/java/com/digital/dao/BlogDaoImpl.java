package com.digital.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.digital.models.Blog;

public class BlogDaoImpl implements BlogDao {

	@Autowired
	private SessionFactory sessionFactory;
		public void addBlog(Blog blog) {
	    Session session= sessionFactory.getCurrentSession();
	    session.save(blog);
		}

		public List<Blog> getBlogsApproved() {
		Session session= sessionFactory.getCurrentSession();
		Query query= session.createQuery("from Blog where approved=true");
		List<Blog> blogsApproved =query.list();
			return blogsApproved;
		}

		public List<Blog> getBlogsWaitingForApproval() {
			Session session= sessionFactory.getCurrentSession();
			Query query=session.createQuery("from Blog where approved=false");
			List<Blog> blogsWaitingForApproval= query.list();
			return blogsWaitingForApproval;
		}

		public Blog getBlog(int blogId) {
			Session session =sessionFactory.getCurrentSession();
	        Blog blog=(Blog) session.get(Blog.class, blogId);
			return blog;
		}

		public void approveBlog(Blog blog) {
	        Session session= sessionFactory.getCurrentSession();
	        session.update(blog);
		}

		public void rejectBlog(Blog blog) {
			Session session= sessionFactory.getCurrentSession();
			session.delete(blog);
		}
}
