package com.example.Covid19.controller;

import java.beans.Transient;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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
	@Cacheable("confirm-cache")
	public ArrayList<LocationStats> getConfirmData() {
		return coronaVirusDataService.getAllComfirmStats();
	}
	@GetMapping("/corona-data-death")
	@Cacheable("death-cache")
	public ArrayList<LocationStats> getDeathData() {
		
		return coronaVirusDataService.getAllDeathStats();
	}
	@GetMapping("/corona-data-recover")
	@Cacheable("recovery-cache")
	public ArrayList<LocationStats> getRecoverData() {
		return coronaVirusDataService.getAllRecoveryStats();
	}
}
