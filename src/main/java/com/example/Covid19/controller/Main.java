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
//	Logger logger = LoggerFactory.getLogger(Main.class);
	 
	@Autowired
	private CoronaVirusDataService coronaVirusDataService;
	

	@GetMapping("/corona-data-confirm")
	public ArrayList<LocationStats> getConfirmData() {
		return coronaVirusDataService.getAllComfirmStats();
	}
	@GetMapping("/corona-data-death")
	public ArrayList<LocationStats> getDeathData() {
		
		return coronaVirusDataService.getAllDeathStats();
	}
	@GetMapping("/corona-data-recover")
	public ArrayList<LocationStats> getRecoverData() {
		return coronaVirusDataService.getAllRecoveryStats();
	}
}
