package com.voigt.hwd.client.history.grid.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.voigt.hwd.client.domain.StatisticalAllTimeData;
import com.voigt.hwd.client.domain.HistoryData;
import com.voigt.hwd.client.domain.Place;
import com.voigt.hwd.client.domain.User;
import com.voigt.hwd.client.history.grid.StatisticalGridRecord;

public class StatisticalGridRecordsProvider {

	private static StatisticalGridRecord[] records;

	public static StatisticalGridRecord[] getRecords() {
		if (records == null) {
			records = getNewRecords();
		}
		return records;
	}

	public static StatisticalGridRecord[] getNewRecords() {
		List<StatisticalGridRecord> statisticalGridRecords = new ArrayList<>();
		for (User user : User.values()) {
			StatisticalAllTimeData userData = HistoryData.getStatisticalAllTimeData(user);
			if (userData != null) {
				StatisticalGridRecord statisticalGridRecord = new StatisticalGridRecord(user.getId(), user.getName(), user.getJoined(),
						userData.getCntSeasons(), userData.getCntFirstPlace(), userData.getCntSecondPlace(),
						userData.getCntThirdPlace(), userData.getCntLastPlace(), userData.getTotalPoints(),
						userData.getTotalTippPoints());
				statisticalGridRecords.add(statisticalGridRecord);
			}
		}

		return statisticalGridRecords.toArray(new StatisticalGridRecord[statisticalGridRecords.size()]);
	}

	private StatisticalGridRecord[] dummy() {
		int cntTotalSeasons = 9;

		// 05.02.2000 (R�ckrunde Saison 1999/2000)
		Date hueniDate = new Date(2000 - 1900, 1, 5, 0, 0, 0);

		StatisticalGridRecord hueni = new StatisticalGridRecord(1, User.HUENI.toString(), hueniDate, cntTotalSeasons - 0, 1, 1, 2, 1,
				(8 + 10 + 7 + 10 + 9) + 29.5f + 21 + (49), (97 + 173 + 246 + 232 + 250) + 244 + 210 + (252));
		hueni.addSeason(2008, Place.NONE, 59, 290);

		StatisticalGridRecord micha = new StatisticalGridRecord(2, "micha", hueniDate, cntTotalSeasons - 0, 1, 3, 4, 0,
				(7 + 12 + 11 + 9.5f + 12) + 39 + 21.5f + (67), (101 + 177 + 243 + 250 + 273) + 292 + 248 + (282));
		micha.addSeason(2008, Place.SECOND, 73, 296);

		// 11.03.2000 (24. Spieltag Saison 1999/2000)
		Date stevDate = new Date(2000 - 1900, 2, 11);
		StatisticalGridRecord stev = new StatisticalGridRecord(3, "stev", stevDate, cntTotalSeasons - 0, 3, 3, 1, 1,
				(3 + 12 + 6.5f + 14.5f + 10.5f) + 45.5f + 35 + (87), (58 + 181 + 214 + 262 + 278) + 280 + 288 + (318));
		stev.addSeason(2008, Place.NONE, 49, 273);

		// 09.08.2002 (Saison 2002/2003)
		Date nicoDate = new Date(2002 - 1900, 7, 9);
		StatisticalGridRecord nico = new StatisticalGridRecord(4, "nico", nicoDate, cntTotalSeasons - 3, 4, 1, 1, 0,
				(13 + 9 + 9.5f) + 60.5f + 35 + (67), (285 + 261 + 293) + 325 + 291 + (295));
		nico.addSeason(2008, Place.FIRST, 73.5f, 321);

		StatisticalGridRecord markus = new StatisticalGridRecord(5, "markus", nicoDate, cntTotalSeasons - 3, 0, 0, 0, 4,
				(5 + 3.5f + 4) + 29.5f + 29 + (56), (185 + 191 + 240) + 243 + 258 + (224));
		markus.addSeason(2008, Place.NONE, 43.5f, 215);
		StatisticalGridRecord tobi = new StatisticalGridRecord(6, "tobi", nicoDate, cntTotalSeasons - 3, 0, 1, 0, 1,
				(11 + 7 + 5) + 22.5f + 24 + (66.5f), (244 + 208 + 185) + 235 + 242 + (271));
		tobi.addSeason(2008, Place.NONE, 51.5f, 226);

		// 21.07.2005 (Saison 2005/2006)
		Date marcelDate = new Date(2005 - 1900, 6, 21);
		StatisticalGridRecord marcel = new StatisticalGridRecord(7, "marcelly", marcelDate, cntTotalSeasons - 5, 0, 0, 0, 1,
				23 + 16.5f + (48), 236 + 243 + (239));
		marcel.addSeason(2008, Place.LAST, 35.5f, 225);

		// 01.08.2006 (Saison 2006/2007)
		Date patziDate = new Date(2006 - 1900, 7, 1);
		StatisticalGridRecord patzi = new StatisticalGridRecord(8, "patzi", patziDate, cntTotalSeasons - 6, 0, 0, 0, 1, 22 + (29),
				257 + (174));
		patzi.addSeason(2008, Place.NONE, 53.5f, 367);
		StatisticalGridRecord jan = new StatisticalGridRecord(9, "janosch", patziDate, cntTotalSeasons - 6, 0, 0, 1, 0, 30 + (57),
				272 + (285));
		jan.addSeason(2008, Place.NONE, 68, 314);

		// 20.07.2007 (Saison 2007/2008)
		Date rossiDate = new Date(2007 - 1900, 6, 20);
		StatisticalGridRecord rossi = new StatisticalGridRecord(10, "rossi", rossiDate, cntTotalSeasons - 7, 0, 0, 0, 0, 58.5f, 255);
		rossi.addSeason(2008, Place.NONE, 40.5f, 262);
		StatisticalGridRecord sven = new StatisticalGridRecord(11, "sven", rossiDate, cntTotalSeasons - 7, 0, 0, 0, 0, 59, 261);
		sven.addSeason(2008, Place.NONE, 68, 285);

		return new StatisticalGridRecord[] { hueni, micha, stev, nico, markus, tobi, marcel, patzi, jan, rossi, sven };

	}
}