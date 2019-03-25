package com.digital.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="blogcomments")
public class BlogComment {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
private int commentId;
private String commentTxt;
@ManyToOne
private User  commentedBy;
private Date commentedOn;
@ManyToOne
private Blog blog;
public int getCommentId() {
	return commentId;
}
public void setCommentId(int commentId) {
	this.commentId = commentId;
}
public String getCommentTxt() {
	return commentTxt;
}
public void setCommentTxt(String commentTxt) {
	this.commentTxt = commentTxt;
}
public User getCommentedBy() {
	return commentedBy;
}
public void setCommentedBy(User commentedBy) {
	this.commentedBy = commentedBy;
}
public Date getCommentedOn() {
	return commentedOn;
}
public void setCommentedOn(Date commentedOn) {
	this.commentedOn = commentedOn;
}
public Blog getBlogPost() {
	return blog;
}
public void setBlogPost(Blog blog) {
	this.blog = blog;
}
}
