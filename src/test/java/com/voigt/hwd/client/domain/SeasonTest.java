package com.voigt.hwd.client.domain;

import static org.junit.Assert.assertEquals;

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

		HistoryData.addSeason(new Season(1999));
		HistoryData.addSeason(new Season(2000));
		HistoryData.addSeason(new Season(2979));
		HistoryData.addSeason(new Season(2000));
		HistoryData.addSeason(new Season(1979));
		HistoryData.addSeason(new Season(2979));

		assertEquals(4, HistoryData.getSeasons().size());
		assertEquals(2979, HistoryData.getEndYear());
		assertEquals(1979, HistoryData.getStartYear());
	}
	
}
