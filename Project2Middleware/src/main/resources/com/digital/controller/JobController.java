package com.digital.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.digital.dao.JobDao;
import com.digital.dao.UserDao;
import com.digital.models.ErrorClz;
import com.digital.models.Job;
import com.digital.models.User;

@RestController
public class JobController {
  
	public JobController()
	{
		System.out.println("Job controller bean is controller");
	}
	@Autowired
 private JobDao jobDao;	
	@Autowired
 private UserDao userDao;	
	
	@RequestMapping(value="/addjob",method=RequestMethod.POST)
	public ResponseEntity<?>  addJob(@RequestBody Job job, HttpSession session) 
	{
		String email=(String)session.getAttribute("loginId");
		if(email==null) {
			ErrorClz errorClz= new ErrorClz(1,"please login ");
			return new ResponseEntity<ErrorClz>(errorClz,HttpStatus.INTERNAL_SERVER_ERROR);	
		}
	   User user = userDao.getUser(email);
		if(!user.getRole().equals("ADMIN")) {
			ErrorClz errorClz= new ErrorClz(1," you are not an authenticated user");
			return new ResponseEntity<ErrorClz>(errorClz,HttpStatus.INTERNAL_SERVER_ERROR);	
		
		}
		try {
		job.setPostedOn(new Date());
		jobDao.addJob(job);
	}
		catch(Exception e)
		{
			ErrorClz errorClz= new ErrorClz(2,"job details could not inserted...Some thing went wrong "+e.getMessage());
			return new ResponseEntity<ErrorClz>(errorClz,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	return new ResponseEntity<Job>(job,HttpStatus.OK);
}
	@RequestMapping(value="/getalljobs",method=RequestMethod.GET)
	public ResponseEntity<?>  getAllJobs()
	{
		
    List<Job> jobs=jobDao.getAllJobs();
    if(jobs.isEmpty())
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	else
	return new ResponseEntity<List<Job>>(jobs, HttpStatus.OK) ;	
	}
	
	@RequestMapping(value="/updatejob",method=RequestMethod.PUT)
	public ResponseEntity<?> updateJob(@RequestBody Job job)
	{
		try {
	    jobDao.updateJob(job);
		}
		catch(Exception e)
		{
		ErrorClz errorClz= new ErrorClz(3,"could not update.. some thing went wrong "+e.getMessage());
		return new ResponseEntity<ErrorClz>(errorClz,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Job>(job,HttpStatus.OK);
	}
	@RequestMapping(value="/getjob",method=RequestMethod.GET)
	public ResponseEntity<?> getJob(@RequestParam int jobId)
	{
		Job job=jobDao.getJob(jobId);
	if(job==null)
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	else
		return new ResponseEntity<Job>(job,HttpStatus.OK);
	}
	@RequestMapping(value="/deletejob",method=RequestMethod.DELETE)
	public ResponseEntity<?> deleteJob(@RequestParam int jobId)
	{
		try {
			jobDao.deleteJob(jobId);
		}
		catch(Exception e){
			ErrorClz errorClz = new ErrorClz(4,"could not perform delete opration "+e.getMessage());
		return new ResponseEntity<ErrorClz>(errorClz,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
