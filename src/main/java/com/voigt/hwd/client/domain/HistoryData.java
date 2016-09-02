package com.voigt.hwd.client.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HistoryData {

	private static int startYear = Integer.MAX_VALUE;
	private static int endYear = 0;

	private static List<Season> seasons = new ArrayList<>();

	private static Map<User, StatisticalAllTimeData> statisticalAllTimeData = new HashMap<>();
	private static Map<User, List<Integer>> standingsData = new HashMap<>();

	static {
		Season season2009 = new Season(2009, 717, 588, "hüni (21)", true);
		season2009.addUser(User.HUENI, new UserSeasonRecord(1, 80.5f, 300))
				.addUser(User.MICHA, new UserSeasonRecord(4, 65.5f, 296))
				.addUser(User.STEV, new UserSeasonRecord(2, 69.5f, 284))
				.addUser(User.NICO, new UserSeasonRecord(5, 60, 284))
				.addUser(User.MARKUS, new UserSeasonRecord(10, 43.5f, 242))
				.addUser(User.TOBI, new UserSeasonRecord(3, 69.5f, 269))
				.addUser(User.MARCEL, new UserSeasonRecord(11, 34, 239))
				.addUser(User.JAN, new UserSeasonRecord(8, 46.5f, 266))
				.addUser(User.PATZI, new UserSeasonRecord(9, 46, 259))
				.addUser(User.ROSSI, new UserSeasonRecord(6, 58, 283))
				.addUser(User.SVEN, new UserSeasonRecord(7, 54.5f, 266));
		HistoryData.addSeason(season2009);

		Season season2008 = new Season(2008, 987, 581, "janosch (21)", true);
		season2008.addUser(User.HUENI, new UserSeasonRecord(5, 59, 290))
				.addUser(User.MICHA, new UserSeasonRecord(2, 73, 296))
				.addUser(User.STEV, new UserSeasonRecord(8, 49, 273))
				.addUser(User.NICO, new UserSeasonRecord(1, 73.5f, 321))
				.addUser(User.MARKUS, new UserSeasonRecord(9, 43.5f, 215))
				.addUser(User.TOBI, new UserSeasonRecord(7, 51.5f, 226))
				.addUser(User.MARCEL, new UserSeasonRecord(11, 35.5f, 225))
				.addUser(User.JAN, new UserSeasonRecord(3, 68, 314))
				.addUser(User.PATZI, new UserSeasonRecord(6, 53.5f, 267))
				.addUser(User.ROSSI, new UserSeasonRecord(10, 40.5f, 262))
				.addUser(User.SVEN, new UserSeasonRecord(4, 68, 285));
		HistoryData.addSeason(season2008);

		Season season2007 = new Season(2007, 749, 577, "hüni, nico, markus, janosch (17)",
				true);
		season2007.addUser(User.HUENI, new UserSeasonRecord(9, 49, 252))
				.addUser(User.MICHA, new UserSeasonRecord(3, 67, 282))
				.addUser(User.STEV, new UserSeasonRecord(1, 87, 318))
				.addUser(User.NICO, new UserSeasonRecord(2, 64, 295))
				.addUser(User.MARKUS, new UserSeasonRecord(8, 56, 224))
				.addUser(User.TOBI, new UserSeasonRecord(4, 66.5f, 271))
				.addUser(User.MARCEL, new UserSeasonRecord(10, 48, 239))
				.addUser(User.JAN, new UserSeasonRecord(7, 57, 285))
				.addUser(User.PATZI, new UserSeasonRecord(11, 29, 174))
				.addUser(User.ROSSI, new UserSeasonRecord(6, 58.5f, 255))
				.addUser(User.SVEN, new UserSeasonRecord(5, 59, 261));
		HistoryData.addSeason(season2007);

		Season season2006 = new Season(2006, 874, 581, "hüni (25)", true);
		season2006.addUser(User.HUENI, new UserSeasonRecord(8, 21, 210))
				.addUser(User.MICHA, new UserSeasonRecord(7, 21.5f, 248))
				.addUser(User.STEV, new UserSeasonRecord(2, 35, 288))
				.addUser(User.NICO, new UserSeasonRecord(1, 35, 291))
				.addUser(User.MARKUS, new UserSeasonRecord(4, 29, 258))
				.addUser(User.TOBI, new UserSeasonRecord(5, 24, 242))
				.addUser(User.MARCEL, new UserSeasonRecord(9, 16.5f, 243))
				.addUser(User.JAN, new UserSeasonRecord(3, 30, 272))
				.addUser(User.PATZI, new UserSeasonRecord(6, 22, 257));
		HistoryData.addSeason(season2006);

		Season season2005 = new Season(2005, 727, 602, "micha (19)", true);
		season2005.addUser(User.HUENI, new UserSeasonRecord(4, 29.5f, 244))
				.addUser(User.MICHA, new UserSeasonRecord(3, 39, 292))
				.addUser(User.STEV, new UserSeasonRecord(2, 45.5f, 280))
				.addUser(User.NICO, new UserSeasonRecord(1, 60.5f, 325))
				.addUser(User.MARKUS, new UserSeasonRecord(5, 29.5f, 243))
				.addUser(User.TOBI, new UserSeasonRecord(7, 22.5f, 235))
				.addUser(User.MARCEL, new UserSeasonRecord(6, 23, 236));
		HistoryData.addSeason(season2005);

		Season season2004 = new Season(2004, 925, 655, "nico (17)", true);
		season2004.addUser(User.HUENI, new UserSeasonRecord(4, 9, 250))
				.addUser(User.MICHA, new UserSeasonRecord(1, 12, 273))
				.addUser(User.STEV, new UserSeasonRecord(2, 10.5f, 278))
				.addUser(User.NICO, new UserSeasonRecord(3, 9.5f, 293))
				.addUser(User.MARKUS, new UserSeasonRecord(6, 4, 240))
				.addUser(User.TOBI, new UserSeasonRecord(5, 5, 185));
		HistoryData.addSeason(season2004);

		Season season2003 = new Season(2003, 716, 617, "stev (17)", true);
		season2003.addUser(User.HUENI, new UserSeasonRecord(2, 10, 232))
				.addUser(User.MICHA, new UserSeasonRecord(3, 9.5f, 250))
				.addUser(User.STEV, new UserSeasonRecord(1, 14.5f, 262))
				.addUser(User.NICO, new UserSeasonRecord(4, 9, 261))
				.addUser(User.MARKUS, new UserSeasonRecord(6, 3.5f, 191))
				.addUser(User.TOBI, new UserSeasonRecord(5, 7, 208));
		HistoryData.addSeason(season2003);

		Season season2002 = new Season(2002, 1027, 876, "nico (17)", false);
		season2002.addUser(User.HUENI, new UserSeasonRecord(4, 7, 246))
				.addUser(User.MICHA, new UserSeasonRecord(3, 11, 243))
				.addUser(User.STEV, new UserSeasonRecord(5, 6.5f, 214))
				.addUser(User.NICO, new UserSeasonRecord(1, 13, 285))
				.addUser(User.MARKUS, new UserSeasonRecord(6, 5, 185))
				.addUser(User.TOBI, new UserSeasonRecord(2, 11, 244));
		HistoryData.addSeason(season2002);

		Season season2001 = new Season(2001, 636, 429, "micha, nico (19)", false);
		season2001.addUser(User.HUENI, new UserSeasonRecord(3, 9, 226))
				.addUser(User.MICHA, new UserSeasonRecord(2, 10, 237))
				.addUser(User.STEV, new UserSeasonRecord(4, 8, 262))
				.addUser(User.NICO, new UserSeasonRecord(1, 15, 294))
				.addUser(User.MARKUS, new UserSeasonRecord(5, 4, 177));
		HistoryData.addSeason(season2001);

		Season season2000 = new Season(2000, 636, 429, "hüni (18)", false);
		season2000.addUser(User.HUENI, new UserSeasonRecord(3, 10, 173))
				.addUser(User.MICHA, new UserSeasonRecord(2, 12, 177))
				.addUser(User.STEV, new UserSeasonRecord(1, 12, 181));
		HistoryData.addSeason(season2000);

		Season season1999 = new Season(1999, 1062, 633, "", false);
		season1999.addUser(User.HUENI, new UserSeasonRecord(1, 8, 97))
				.addUser(User.MICHA, new UserSeasonRecord(2, 7, 101))
				.addUser(User.STEV, new UserSeasonRecord(3, 3, 58));
		HistoryData.addSeason(season1999);
	}

	public static void addSeason(Season season) {
		if (seasons.contains(season)) {
			throw new InvalidBusinessDataException("season is already set: " + season);
		}
		seasons.add(season);
		if (season.getYear() < startYear) {
			startYear = season.getYear();
		}
		if (season.getYear() > endYear) {
			endYear = season.getYear();
		}

		Collections.sort(seasons, new Comparator<Season>() {
			public int compare(Season o1, Season o2) {
				return o1.getYear() - o2.getYear();
			}
		});

		Map<User, UserSeasonRecord> users = season.getUsers();
		Set<User> keySet = users.keySet();
		for (User user : keySet) {
			UserSeasonRecord record = users.get(user);
			StatisticalAllTimeData allTimeData = statisticalAllTimeData.get(user);
			if (allTimeData == null) {
				allTimeData = new StatisticalAllTimeData();
				statisticalAllTimeData.put(user, allTimeData);
			}
			allTimeData.increaseCntSeasons().increaseTotalPoints(record.getPoints())
					.increaseTotalTippPoints(record.getTippPoints())
					.increaseCntPlace(record.getPlace(), season.getUsers().size());
		}
	}

	public static List<Season> getSeasons() {
		return seasons;
	}

	public static Map<User, StatisticalAllTimeData> getStatisticalAllTimeData() {
		return statisticalAllTimeData;
	}

	public static StatisticalAllTimeData getStatisticalAllTimeData(User user) {
		return statisticalAllTimeData.get(user);
	}

	public static List<Integer> getStandingsData(User user) {

		List<Integer> list = standingsData.get(user);
		if (list == null) {
			list = new LinkedList<>();
			standingsData.put(user, list);

			for (Season season : seasons) {
				Set<User> users = season.getUsers().keySet();

				int place = 0;
				if (users.contains(user)) {
					UserSeasonRecord userSeasonRecord = season.getUsers().get(user);
					place = userSeasonRecord.getPlace();
				}
				list.add(Integer.valueOf(place));

			}
		}
		return list;
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
		statisticalAllTimeData = new HashMap<>();
		standingsData = new HashMap<>();
	}

}
