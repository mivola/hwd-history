package com.voigt.hwd.client.domain;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class HistoryDataTest {

	@Before
	public void setup() {
		HistoryData.reset();
	}

	@Test
	public void testAddingOneSeasons() throws Exception {

		addFirstSeason();

		Map<User, StatisticalAllTimeData> userData = HistoryData.getStatisticalAllTimeData();
		StatisticalAllTimeData hueni = userData.get(User.HUENI);
		assertEquals(1, hueni.getCntSeasons());
		assertEquals(1, hueni.getCntFirstPlace());
		assertEquals(0, hueni.getCntLastPlace());

		StatisticalAllTimeData stev = userData.get(User.STEV);
		assertEquals(1, stev.getCntSeasons());
		assertEquals(1, stev.getCntThirdPlace());
		assertEquals(1, stev.getCntLastPlace());

		List<Integer> standingsData = HistoryData.getStandingsData(User.STEV);
		assertEquals(Integer.valueOf(3), standingsData.get(0));
	}

	@Test

	public void testAddingTwoSeasons() throws Exception {

		addSecondSeason();

		addFirstSeason();

		Map<User, StatisticalAllTimeData> userData = HistoryData.getStatisticalAllTimeData();
		StatisticalAllTimeData hueni = userData.get(User.HUENI);
		assertEquals(2, hueni.getCntSeasons());
		assertEquals(1, hueni.getCntFirstPlace());
		assertEquals(1, hueni.getCntLastPlace());
		assertEquals(Double.valueOf(18f), Double.valueOf(hueni.getTotalPoints()));
		assertEquals(270, hueni.getTotalTippPoints());

		StatisticalAllTimeData stev = userData.get(User.STEV);
		assertEquals(2, stev.getCntSeasons());
		assertEquals(1, stev.getCntFirstPlace());
		assertEquals(1, stev.getCntThirdPlace());
		assertEquals(1, stev.getCntLastPlace());
		assertEquals(Double.valueOf(15f), Double.valueOf(stev.getTotalPoints()));
		assertEquals(239, stev.getTotalTippPoints());

		List<Integer> standingsDataHueni = HistoryData.getStandingsData(User.HUENI);
		assertEquals(Integer.valueOf(1), standingsDataHueni.get(0));
		assertEquals(Integer.valueOf(3), standingsDataHueni.get(1));

		List<Integer> standingsDataStev = HistoryData.getStandingsData(User.STEV);
		assertEquals(Integer.valueOf(3), standingsDataStev.get(0));
		assertEquals(Integer.valueOf(1), standingsDataStev.get(1));

	}

	@Test
	public void testAddingThreeSeasons() throws Exception {

		addSecondSeason();

		addFirstSeason();

		Season season2001 = new Season(2001);
		season2001.addUser(User.HUENI, new UserSeasonRecord(3, 9, 226))
				.addUser(User.MICHA, new UserSeasonRecord(2, 10, 237))
				.addUser(User.STEV, new UserSeasonRecord(4, 8, 262))
				.addUser(User.NICO, new UserSeasonRecord(1, 15, 294))
				.addUser(User.MARKUS, new UserSeasonRecord(5, 4, 177));
		HistoryData.addSeason(season2001);

		List<Integer> standingsDataHueni = HistoryData.getStandingsData(User.HUENI);
		assertEquals(Integer.valueOf(1), standingsDataHueni.get(0));
		assertEquals(Integer.valueOf(3), standingsDataHueni.get(1));
		assertEquals(Integer.valueOf(3), standingsDataHueni.get(2));

		List<Integer> standingsDataNico = HistoryData.getStandingsData(User.NICO);
		assertEquals(Integer.valueOf(-1), standingsDataNico.get(0));
		assertEquals(Integer.valueOf(-1), standingsDataNico.get(1));
		assertEquals(Integer.valueOf(1), standingsDataNico.get(2));

	}

	@Test(expected = InvalidBusinessDataException.class)
	public void testAddingTheSameUserTwice() throws Exception {
		Season season = new Season(2000);
		season.addUser(User.HUENI, new UserSeasonRecord(3, 10, 173)).addUser(User.HUENI,
				new UserSeasonRecord(2, 12, 177));
	}

	private void addSecondSeason() {
		Season season2000 = new Season(2000);
		season2000.addUser(User.HUENI, new UserSeasonRecord(3, 10, 173))
				.addUser(User.MICHA, new UserSeasonRecord(2, 12, 177))
				.addUser(User.STEV, new UserSeasonRecord(1, 12, 181));
		HistoryData.addSeason(season2000);
	}

	private void addFirstSeason() {
		Season season1999 = new Season(1999);
		season1999.addUser(User.HUENI, new UserSeasonRecord(1, 8, 97))
				.addUser(User.MICHA, new UserSeasonRecord(2, 7, 101))
				.addUser(User.STEV, new UserSeasonRecord(3, 3, 58));
		HistoryData.addSeason(season1999);
	}

}
