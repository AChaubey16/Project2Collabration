package com.digital.dao;

import com.digital.models.Blog;
import com.digital.models.BlogLike;

public interface BlogLikeDao {
	BlogLike hasUserLikedBlog(int blogId,String email);
	Blog updateLikes(int blogId, String email);
}
