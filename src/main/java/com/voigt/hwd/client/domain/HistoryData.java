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

	static {
		Season season2001 = new Season(2001);
		season2001.addUser(User.HUENI, new UserSeasonRecord(3, 9, 226))
				.addUser(User.MICHA, new UserSeasonRecord(2, 10, 237))
				.addUser(User.STEV, new UserSeasonRecord(4, 8, 262))
				.addUser(User.NICO, new UserSeasonRecord(1, 15, 294))
				.addUser(User.MARKUS, new UserSeasonRecord(5, 4, 177));
		HistoryData.addSeason(season2001);

		Season season2000 = new Season(2000);
		season2000.addUser(User.HUENI, new UserSeasonRecord(3, 10, 173))
				.addUser(User.MICHA, new UserSeasonRecord(2, 12, 177))
				.addUser(User.STEV, new UserSeasonRecord(1, 12, 181));
		HistoryData.addSeason(season2000);

		Season season1999 = new Season(1999);
		season1999.addUser(User.HUENI, new UserSeasonRecord(1, 8, 97))
				.addUser(User.MICHA, new UserSeasonRecord(2, 7, 101))
				.addUser(User.STEV, new UserSeasonRecord(3, 3, 58));
		HistoryData.addSeason(season1999);
	}

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
