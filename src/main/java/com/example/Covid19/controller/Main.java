package com.example.Covid19.controller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Covid19.models.LocationStats;
import com.example.Covid19.service.CoronaVirusDataService;


@RestController
public class Main {
	Logger logger = LoggerFactory.getLogger(Main.class);
	 
	@Autowired
	private CoronaVirusDataService coronaVirusDataService;
	

	@GetMapping("/corona-data-confirm")
	public ArrayList<LocationStats> getConfirmData() {
		logger.info("getConfirmData called");
		return coronaVirusDataService.getAllComfirmStats();
	}
	@GetMapping("/corona-data-death")
	public ArrayList<LocationStats> getDeathData() {
		logger.info("getDeathData called");
		return coronaVirusDataService.getAllDeathStats();
	}
	@GetMapping("/corona-data-recover")
	public ArrayList<LocationStats> getRecoverData() {
		logger.trace("getRecoverData called");
		return coronaVirusDataService.getAllRecoveryStats();
	}
}
