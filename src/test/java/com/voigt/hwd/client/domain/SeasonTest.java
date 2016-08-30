package com.voigt.hwd.client.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class SeasonTest {

	@Before
	public void setup() {
		HistoryData.reset();
	}

	@Test
	public void testAddingOneSeason() throws Exception {
		
		HistoryData.addSeason(new Season(1999));

		assertEquals(1, HistoryData.getSeasons().size());
		assertEquals(1999, HistoryData.getEndYear());
		assertEquals(1999, HistoryData.getStartYear());
	}

	@Test
	public void testAddingSeveralSeasons() throws Exception {
		int startYear = 1979;
		int endYear = 2979;

		HistoryData.addSeason(new Season(1999));
		HistoryData.addSeason(new Season(endYear));
		HistoryData.addSeason(new Season(2000));
		HistoryData.addSeason(new Season(startYear));

		assertEquals(4, HistoryData.getSeasons().size());
		assertEquals(endYear, HistoryData.getEndYear());
		assertEquals(startYear, HistoryData.getStartYear());

		// check sorting
		int previousYear = 0;
		for (Season season : HistoryData.getSeasons()) {
			assertTrue(season.getYear() > previousYear);
			previousYear = season.getYear();
		}
		assertEquals(endYear, previousYear);
	}

	@Test(expected = InvalidBusinessDataException.class)
	public void testAddingTheSameSeasonTwice() throws Exception {
		Season season = new Season(2000);
		HistoryData.addSeason(season);
		HistoryData.addSeason(season);
	}

	@Test(expected = InvalidBusinessDataException.class)
	public void testAddingTheSimilarSeasonTwice() throws Exception {
		HistoryData.addSeason(new Season(2000));
		HistoryData.addSeason(new Season(2000));
	}

	@Test(expected = InvalidBusinessDataException.class)
	public void testAddingTheSamePlaceTwice() throws Exception {
		HistoryData.addSeason(new Season(2000).addUser(User.HUENI, new UserSeasonRecord(1, 0, 0)).addUser(User.STEV,
				new UserSeasonRecord(1, 0, 0)));
	}
	
}
