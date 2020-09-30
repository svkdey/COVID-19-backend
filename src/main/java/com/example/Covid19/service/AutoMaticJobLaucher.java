package com.example.Covid19.service;

import javax.annotation.PostConstruct;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

//@Service
public class AutoMaticJobLaucher {
	@Autowired
	JobLauncher laucher;
	@Autowired
	Job job;
	
	@PostConstruct
	@Scheduled(fixedDelay = (24*36000))
	public void runJob() throws JobExecutionAlreadyRunningException, JobRestartException,
			JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		JobParameters jp = new JobParametersBuilder().addLong("acbac", System.currentTimeMillis()).toJobParameters();
		laucher.run(job, jp);
	}
}
