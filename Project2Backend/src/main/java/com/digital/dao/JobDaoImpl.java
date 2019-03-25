package com.digital.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.digital.models.Job;

@Repository
@Transactional
public class JobDaoImpl implements JobDao {

	public JobDaoImpl()
	{
		System.out.println("jobDaoImpl bean is created");
	}
	@Autowired
 private SessionFactory sessionFactory;
	
	public void addJob(Job job) 
	{
		Session session= sessionFactory.getCurrentSession();
		session.save(job);
		
	}

	public List<Job> getAllJobs() 
	{
	    Session session =sessionFactory.getCurrentSession();
	    Query query =session.createQuery("from Job");
	    List<Job> jobs = query.list();
	    return jobs;
	    
	}

	public Job getJob(int jobId) 
	{
        Session session= sessionFactory.getCurrentSession();
        Job job=(Job)session.get( Job.class,jobId);
		return job;
	}

	public void deleteJob(int jobId) 
	{
	    Session session =sessionFactory.getCurrentSession();
	    Job job= (Job)session.get(Job.class, jobId);
	    session.delete(job);
	}

	public Job updateJob(Job job) 
	{
		Session session= sessionFactory.getCurrentSession();
		session.update(job);
		return job;
	}


}
