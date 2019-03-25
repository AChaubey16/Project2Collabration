package com.digital.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="bloglikes")
public class BlogLike {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
private int likeId;
	@ManyToOne
private Blog blog;
	@ManyToOne
private User likedBy;
	public int getLikeId() {
		return likeId;
	}
	public void setLikeId(int likeId) {
		this.likeId = likeId;
	}
	public Blog getBlog() {
		return blog;
	}
	public void setBlog(Blog blog) {
		this.blog = blog;
	}
	public User getLikedBy() {
		return likedBy;
	}
	public void setLikedBy(User likedBy) {
		this.likedBy = likedBy;
	}
	
}
