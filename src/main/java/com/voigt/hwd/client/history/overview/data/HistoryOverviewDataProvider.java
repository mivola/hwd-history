package com.voigt.hwd.client.history.overview.data;

import java.util.*;

import com.google.gwt.i18n.client.NumberFormat;
import com.voigt.hwd.client.domain.HistoryData;
import com.voigt.hwd.client.domain.Season;
import com.voigt.hwd.client.domain.User;

public class HistoryOverviewDataProvider {

	private static final String hwdBaseURL = "http://hwd.bts-computer.de/";
	private final List<HistoryOverviewData> historyOverviewList = new ArrayList<>();

	public HistoryOverviewDataProvider() {

		historyOverviewList.clear();
		List<Season> seasons = HistoryData.getSeasons();
		Collections.sort(seasons, new Comparator<Season>() {
			public int compare(Season o1, Season o2) {
				return o2.getYear() - o1.getYear();
			}
		});
		for (Season season : seasons) {
			String filenameAndUrlSuffix = getFilename(season.getYear());
			String imageFilename = filenameAndUrlSuffix + ".png";
			User[] winners = season.getWinners().toArray(new User[season.getWinners().size()]);
			String winnersString = Arrays.toString(winners).replace("[", "").replace("]", "");
			String url = season.hasLiveSystem() ? hwdBaseURL + filenameAndUrlSuffix : "";
			String maxPoints = season.getMaxPoints();
			String description = season.getDescription();
			int imageHeight = season.getImageHeight();
			int imageWidth = season.getImageWidth();
			HistoryOverviewData historyOverviewData = new HistoryOverviewData(season.getYear(), imageHeight, imageWidth,
					season.getCntUsers(), imageFilename, maxPoints, description, winnersString, url);
			historyOverviewList.add(historyOverviewData);
		}
	}

	private String getFilename(int year) {
		int startYear;
		if (year < 2000) {
			startYear = year - 1900;
		} else {
			startYear = year - 2000;
		}

		int endYear = startYear + 1;
		if (endYear > 99) {
			endYear = endYear - 100;
		} else if (endYear == 10) {
			endYear = endYear + 2000;
		}
		NumberFormat format = NumberFormat.getFormat("00");
		return "hwd" + format.format(startYear) + "_" + format.format(endYear);
	}

	public Collection<HistoryOverviewData> getSeasons() {
		return historyOverviewList;
	}

}
