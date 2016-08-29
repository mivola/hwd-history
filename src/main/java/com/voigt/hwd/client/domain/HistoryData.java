package com.voigt.hwd.client.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HistoryData {

	private static int startYear = Integer.MAX_VALUE;
	private static int endYear = 0;

	private static List<Season> seasons = new ArrayList<>();

	private static Map<User, CalculatedData> userData = new HashMap<>();

	public static void addSeason(Season season) {
		if (!seasons.contains(season)) {
			seasons.add(season);
			if (season.getYear() < startYear) {
				startYear = season.getYear();
			}
			if (season.getYear() > endYear) {
				endYear = season.getYear();
			}

			Map<User, UserSeasonRecord> users = season.getUsers();
			Set<User> keySet = users.keySet();
			for (User user : keySet) {
				UserSeasonRecord record = users.get(user);
				CalculatedData calculatedData = userData.get(user);
				if (calculatedData == null) {
					calculatedData = new CalculatedData();
					userData.put(user, calculatedData);
				}
				calculatedData.increaseCntSeasons().increaseTotalPoints(record.getPoints())
						.increaseTotalTippPoints(record.getTippPoints())
						.increaseCntPlace(record.getPlace(), season.getUsers().size());

			}

		}
	}

	public static List<Season> getSeasons() {
		return seasons;
	}

	public static Map<User, CalculatedData> getUserData() {
		return userData;
	}

	public static CalculatedData getUserData(User user) {
		return userData.get(user);
	}

	public static int getStartYear() {
		return startYear;
	}

	public static int getEndYear() {
		return endYear;
	}

	static void reset() {
		startYear = Integer.MAX_VALUE;
		endYear = 0;
		seasons = new ArrayList<>();
		userData = new HashMap<>();
	}

}
