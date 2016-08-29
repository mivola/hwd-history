package com.voigt.hwd.client.domain;

import static org.junit.Assert.assertEquals;

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

		Map<User, CalculatedData> userData = HistoryData.getUserData();
		CalculatedData hueni = userData.get(User.HUENI);
		assertEquals(1, hueni.getCntSeasons());
		assertEquals(1, hueni.getCntFirstPlace());
		assertEquals(0, hueni.getCntLastPlace());

		CalculatedData stev = userData.get(User.STEV);
		assertEquals(1, stev.getCntSeasons());
		assertEquals(1, stev.getCntThirdPlace());
		assertEquals(1, stev.getCntLastPlace());
	}

	@Test

	public void testAddingTwoSeasons() throws Exception {
		addFirstSeason();

		Season season2000 = new Season(2000);
		season2000.addUser(User.HUENI, new UserSeasonRecord(3, 10, 173))
				.addUser(User.MICHA, new UserSeasonRecord(2, 12, 177))
				.addUser(User.STEV, new UserSeasonRecord(1, 12, 181));
		HistoryData.addSeason(season2000);

		Map<User, CalculatedData> userData = HistoryData.getUserData();
		CalculatedData hueni = userData.get(User.HUENI);
		assertEquals(2, hueni.getCntSeasons());
		assertEquals(1, hueni.getCntFirstPlace());
		assertEquals(1, hueni.getCntLastPlace());
		assertEquals(Double.valueOf(18f), Double.valueOf(hueni.getTotalPoints()));
		assertEquals(270, hueni.getTotalTippPoints());

		CalculatedData stev = userData.get(User.STEV);
		assertEquals(2, stev.getCntSeasons());
		assertEquals(1, stev.getCntFirstPlace());
		assertEquals(1, stev.getCntThirdPlace());
		assertEquals(1, stev.getCntLastPlace());
		assertEquals(Double.valueOf(15f), Double.valueOf(stev.getTotalPoints()));
		assertEquals(239, stev.getTotalTippPoints());

	}

	private void addFirstSeason() {
		Season season1999 = new Season(1999);
		season1999.addUser(User.HUENI, new UserSeasonRecord(1, 8, 97))
				.addUser(User.MICHA, new UserSeasonRecord(2, 7, 101))
				.addUser(User.STEV, new UserSeasonRecord(3, 3, 58));
		HistoryData.addSeason(season1999);
	}

}
