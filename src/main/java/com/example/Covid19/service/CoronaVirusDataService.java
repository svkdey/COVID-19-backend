package com.example.Covid19.service;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

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
	
	@Value("${VIRUS_URL_Confirmed}")
	private String VIRUS_URL_Confirmed;
	@Value("${VIRUS_URL_recovery}")
	private String VIRUS_URL_recovery;
	@Value("${VIRUS_URL_death}")
	private String VIRUS_URL_death;

	private OkHttpClient httpClient = new OkHttpClient();
	private ArrayList<LocationStats> allConfirmStats = new ArrayList<>();
	private ArrayList<LocationStats> allDeathStats = new ArrayList<>();
	private ArrayList<LocationStats> allRecoveryStats = new ArrayList<>();

	public ArrayList<LocationStats> getAllComfirmStats() {
		return allConfirmStats;
	}

	public ArrayList<LocationStats> getAllDeathStats() {
		return allDeathStats;
	}

	public ArrayList<LocationStats> getAllRecoveryStats() {
		return allRecoveryStats;
	}

//	cron ->sec min hr day mon year
	@PostConstruct
	@Scheduled(fixedDelay = 360000)
	public void fetchConfirmData() throws IOException {
		Request request = new Request.Builder().url(VIRUS_URL_Confirmed) // add request headers
				.addHeader("User-Agent", "OkHttp Bot").build();

		try (Response response = httpClient.newCall(request).execute()) {

			if (!response.isSuccessful())
				throw new IOException("Unexpected code " + response);

			// Get response body
			ArrayList<LocationStats> newStats = new ArrayList<>();

			StringReader csvBodyReader = new StringReader(response.body().string());
			Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
			for (CSVRecord record : records) {
				System.out.println(record);
				LocationStats locationStat = new LocationStats();
				locationStat.setLat(Double.parseDouble(record.get("Lat")));
				locationStat.setLon(Double.parseDouble(record.get("Long")));
				locationStat.setState(record.get("Province/State"));
				locationStat.setCountry(record.get("Country/Region"));
				int latestCases = Integer.parseInt(record.get(record.size() - 1));
				int prevDayCases = Integer.parseInt(record.get(record.size() - 2));
				locationStat.setLatestTotal(latestCases);
				locationStat.setDiffFromPrevDay(latestCases - prevDayCases);
				newStats.add(locationStat);
			}
			this.allConfirmStats = newStats;
		}
	}

	@PostConstruct
	@Scheduled(fixedDelay = 360000)
	public void fetchRecoveryData() throws IOException {
		Request request = new Request.Builder().url(VIRUS_URL_recovery) // add request headers
				.addHeader("User-Agent", "OkHttp Bot").build();

		try (Response response = httpClient.newCall(request).execute()) {

			if (!response.isSuccessful())
				throw new IOException("Unexpected code " + response);

			// Get response body
			ArrayList<LocationStats> newStats = new ArrayList<>();

			StringReader csvBodyReader = new StringReader(response.body().string());
			Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
			for (CSVRecord record : records) {
				System.out.println(record);
				LocationStats locationStat = new LocationStats();
				locationStat.setLat(Double.parseDouble(record.get("Lat")));
				locationStat.setLon(Double.parseDouble(record.get("Long")));
				locationStat.setState(record.get("Province/State"));
				locationStat.setCountry(record.get("Country/Region"));
				int latestCases = Integer.parseInt(record.get(record.size() - 1));
				int prevDayCases = Integer.parseInt(record.get(record.size() - 2));
				locationStat.setLatestTotal(latestCases);
				locationStat.setDiffFromPrevDay(latestCases - prevDayCases);
				newStats.add(locationStat);
			}
			this.allRecoveryStats = newStats;
		}
	}

	@PostConstruct
	@Scheduled(fixedDelay = 360000)
	public void fetchDeathData() throws IOException, CSVnotfound {
		Request request = new Request.Builder().url(VIRUS_URL_death) // add request headers
				.addHeader("User-Agent", "OkHttp Bot").build();

		try (Response response = httpClient.newCall(request).execute()) {

			if (!response.isSuccessful())
				throw new IOException("Unexpected code " + response);

			// Get response body
			ArrayList<LocationStats> newStats = new ArrayList<>();

			StringReader csvBodyReader = new StringReader(response.body().string());
			Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
			for (CSVRecord record : records) {
				System.out.println(record);
				LocationStats locationStat = new LocationStats();
				locationStat.setLat(Double.parseDouble(record.get("Lat")));
				locationStat.setLon(Double.parseDouble(record.get("Long")));
				locationStat.setState(record.get("Province/State"));
				locationStat.setCountry(record.get("Country/Region"));
				int latestCases = Integer.parseInt(record.get(record.size() - 1));
				int prevDayCases = Integer.parseInt(record.get(record.size() - 2));
				locationStat.setLatestTotal(latestCases);
				locationStat.setDiffFromPrevDay(latestCases - prevDayCases);
				newStats.add(locationStat);
			}
			this.allDeathStats = newStats;
		}
	}
}
