package com.digital.dao;

import java.util.List;

import com.digital.models.BlogComment;

public interface BlogCommentDao {
	void addBlogComment(BlogComment blogComment);
	List<BlogComment> getAllBlogComments(int blogPostId);
}
