package com.voigt.hwd.client.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Season {

	private static final int WINNER = 1;

	private int year;

	private Map<User, UserSeasonRecord> users = new HashMap<>();

	private List<Integer> places = new ArrayList<>();

	private List<User> winners = new ArrayList<>();

	private int imageHeight;

	private int imageWidth;

	private boolean hasLiveSystem;

	private String maxPoints;

	@Deprecated
	public Season(int year) {
		this.year = year;
	}

	public Season(int year, int imageHeight, int imageWidth, String maxPoints,
			boolean hasLiveSystem) {
		this.year = year;
		this.imageHeight = imageHeight;
		this.imageWidth = imageWidth;
		this.maxPoints = maxPoints;
		this.hasLiveSystem = hasLiveSystem;
	}

	public Season addUser(User user, UserSeasonRecord record) throws InvalidBusinessDataException {
		if (users.containsKey(user)) {
			throw new InvalidBusinessDataException("user is already set for this season: " + user);
		}
		if (places.contains(record.getPlace())) {
			throw new InvalidBusinessDataException("place is already set for this season: " + record.getPlace());
		}
		
		users.put(user, record);
		places.add(record.getPlace());
		
		if (record.getPlace() == WINNER) {
			winners.add(user);
		}
		return this;
	}

	public int getYear() {
		return year;
	}

	public Map<User, UserSeasonRecord> getUsers() {
		return users;
	}

	public int getCntUsers() {
		return users.size();
	}

	public List<User> getWinners() {
		return winners;
	}

	public int getImageHeight() {
		return imageHeight;
	}

	public int getImageWidth() {
		return imageWidth;
	}

	public String getMaxPoints() {
		return maxPoints;
	}

	public boolean hasLiveSystem() {
		return hasLiveSystem;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + year;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Season other = (Season) obj;
		if (year != other.year) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Season [year=" + year + "]";
	}

}
