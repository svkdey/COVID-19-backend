package com.example.Covid19.utils.batchJob;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.Covid19.service.CoronaVirusDataService;

public class Reader implements ItemReader<String> {
	@Autowired
	private CoronaVirusDataService service;
	@Override
	public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		
		return null;
	}

}
