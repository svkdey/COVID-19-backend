package com.example.Covid19.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.Covid19.utils.batchJob.JobexecutionListenerImpl;
import com.example.Covid19.utils.batchJob.Processor;
import com.example.Covid19.utils.batchJob.Reader;
import com.example.Covid19.utils.batchJob.Writer;

//@Configuration
public class BatchConfigImpl {
	
	@Autowired
	private StepBuilderFactory sbf;

	
	@Autowired
	private JobBuilderFactory jbf;
	
	@Bean
	public Job job() {
		return jbf.get("job1")
				.incrementer(new RunIdIncrementer())
				.listener(listener())
				.start(step())
				.build();
	}

	@Bean
	public Step step() {
		return sbf.get("step1")
				.<String, String>chunk(1)
				.reader(reader())
				.processor(processor())
				.writer(writerJob())
				.build();
	}
	
	
	@Bean
	public Reader reader() {
		return new Reader();
	}

	@Bean
	public Writer writerJob() {
		return new Writer();
	}

	@Bean
	public Processor processor() {
		return new Processor();
	}

	@Bean
	public JobexecutionListenerImpl listener() {
		return new JobexecutionListenerImpl();
	}
}
