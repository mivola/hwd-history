package com.voigt.hwd.client.history.grid.data;

import java.util.ArrayList;
import java.util.List;

import com.voigt.hwd.client.domain.HistoryData;
import com.voigt.hwd.client.domain.StatisticalAllTimeData;
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

}