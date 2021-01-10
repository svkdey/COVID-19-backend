package com.example.Covid19.service;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.Covid19.exception.CSVnotfound;
import com.example.Covid19.models.LocationStats;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Service
public class CoronaVirusDataService {
	

	private String VIRUS_URL_Confirmed="https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
	
	private String VIRUS_URL_recovery="https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_recovered_global.csv" ;
	
	private String VIRUS_URL_death="https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_deaths_global.csv";

	private OkHttpClient httpClient = new OkHttpClient();
	private List<LocationStats> allConfirmStats = new ArrayList<>();
	private List<LocationStats> allDeathStats = new ArrayList<>();
	private List<LocationStats> allRecoveryStats = new ArrayList<>();

	public List<LocationStats> getAllComfirmStats() {
		return allConfirmStats;
	}

	public List<LocationStats> getAllDeathStats() {
		return allDeathStats;
	}

	public List<LocationStats> getAllRecoveryStats() {
		return allRecoveryStats;
	}

//	cron ->sec min hr day mon year
	@PostConstruct
	@Scheduled(cron = "* * 10 * * *")
	public void fetchConfirmData() throws IOException {
		Request request = new Request.Builder().url(VIRUS_URL_Confirmed) // add request headers
				.addHeader("User-Agent", "OkHttp Bot").build();

		try (Response response = httpClient.newCall(request).execute()) {

			if (!response.isSuccessful())
				throw new IOException("Unexpected code " + response);

			StringReader csvBodyReader = new StringReader(response.body().string());
			List<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader).getRecords();
			List<LocationStats> newStats = records.stream().map(record->{
				return mapper(record);
			}).collect(Collectors.toList());
			

			this.allConfirmStats = newStats;
		}
	}

	@PostConstruct
	@Scheduled(cron = "* * 10 * * *")
	public void fetchRecoveryData() throws IOException {
		Request request = new Request.Builder().url(VIRUS_URL_recovery) // add request headers
				.addHeader("User-Agent", "OkHttp Bot").build();

		try (Response response = httpClient.newCall(request).execute()) {

			if (!response.isSuccessful())
				throw new IOException("Unexpected code " + response);
			StringReader csvBodyReader = new StringReader(response.body().string());
			List<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader).getRecords();
			List<LocationStats> newStats = records.stream().map(record->{
				return mapper(record);
			}).collect(Collectors.toList());
			

			this.allRecoveryStats = newStats;
		}
	}

	@PostConstruct
	@Scheduled(cron = "* * 10 * * *")
	public void fetchDeathData() throws IOException {
		Request request = new Request.Builder().url(VIRUS_URL_death) // add request headers
				.addHeader("User-Agent", "OkHttp Bot").build();

		try (Response response = httpClient.newCall(request).execute()) {

			if (!response.isSuccessful())
				throw new IOException("Unexpected code " + response);

			// Get response body
//			ArrayList<LocationStats> newStats = new ArrayList<>();

			StringReader csvBodyReader = new StringReader(response.body().string());
			List<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader).getRecords();
			List<LocationStats> newStats = records.stream().map(record->{
				return mapper(record);
			}).collect(Collectors.toList());
			

			this.allDeathStats = newStats;
		}
	}
	
	private boolean isEmpty(String s) {
		if(null!=s && s.trim().length()>0) {
			return false;
		}
		return true;
	}
	
	private LocationStats mapper(CSVRecord record) {
		LocationStats locationStat=new LocationStats();
		if(!isEmpty(record.get("Lat"))) {
			locationStat.setLat(Double.parseDouble(record.get("Lat")));
		}
		if(!isEmpty(record.get("Long"))) {
			locationStat.setLon(Double.parseDouble(record.get("Long")));
		}
		if(!isEmpty(record.get("Province/State"))) {
			locationStat.setState(record.get("Province/State"));
		}
		if(!isEmpty(record.get("Country/Region"))) {
		locationStat.setCountry(record.get("Country/Region"));
		}
		if(!isEmpty(record.get(record.size() - 1)) && !isEmpty(record.get(record.size() - 2)) ) {
			int latestCases = Integer.parseInt(record.get(record.size() - 1));
			locationStat.setLatestTotal(latestCases);
			int prevDayCases = Integer.parseInt(record.get(record.size() - 2));
			locationStat.setDiffFromPrevDay(latestCases - prevDayCases);
		}
		return locationStat;
	}
}
