package com.digital.dao;

import java.util.List;

import com.digital.models.Blog;

public interface BlogDao {
	public void addBlog(Blog blog);
	public List<Blog> getBlogsApproved();
	public List<Blog> getBlogsWaitingForApproval();
	Blog getBlog(int blogPostId);
	void approveBlog(Blog blog);
	void rejectBlog(Blog blog);
}
