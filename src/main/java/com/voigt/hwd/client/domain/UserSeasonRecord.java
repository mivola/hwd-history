package com.voigt.hwd.client.domain;

public class UserSeasonRecord {

	private int place;

	private float points;

	private int tippPoints;

	public UserSeasonRecord(int place, float points, int tippPoints) {
		super();
		this.place = place;
		this.points = points;
		this.tippPoints = tippPoints;
	}

	public int getPlace() {
		return place;
	}

	public void setPlace(int place) {
		this.place = place;
	}

	public float getPoints() {
		return points;
	}

	public void setPoints(float points) {
		this.points = points;
	}

	public int getTippPoints() {
		return tippPoints;
	}

	public void setTippPoints(int tippPoints) {
		this.tippPoints = tippPoints;
	}

}
