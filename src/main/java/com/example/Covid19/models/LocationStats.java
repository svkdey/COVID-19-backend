package com.example.Covid19.models;

public class LocationStats {
	private String state;
	private String country;
	private double lat;
	private double lon;
	private int latestTotal;
	private int diffFromPrevDay;
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLon() {
		return lon;
	}
	public void setLon(double lon) {
		this.lon = lon;
	}
	public int getLatestTotal() {
		return latestTotal;
	}
	public void setLatestTotal(int latestTotal) {
		this.latestTotal = latestTotal;
	}
	public LocationStats() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "locationStats [state=" + state + ", country=" + country + ", lat=" + lat + ", lon=" + lon
				+ ", latestTotal=" + latestTotal + "]";
	}
	public int getDiffFromPrevDay() {
		return diffFromPrevDay;
	}
	public void setDiffFromPrevDay(int diffFromPrevDay) {
		this.diffFromPrevDay = diffFromPrevDay;
	}
	
}
