package com.voigt.hwd.client.domain;

import java.util.HashMap;
import java.util.Map;

public class Season {

	private int year;

	private Map<User, UserSeasonRecord> users = new HashMap<>();

	public Season(int year) {
		this.year = year;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Season addUser(User user, UserSeasonRecord record) {
		users.put(user, record);

		return this;
	}

	public Map<User, UserSeasonRecord> getUsers() {
		return users;
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

}
