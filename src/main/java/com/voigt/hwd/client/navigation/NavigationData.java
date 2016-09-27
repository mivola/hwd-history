package com.voigt.hwd.client.navigation;

import java.util.HashMap;
import java.util.Map;

import com.voigt.hwd.client.history.grid.HistoryAllTimeTable;
import com.voigt.hwd.client.history.overview.HistoryOverview;
import com.voigt.hwd.client.history.standings.HistoryStandingsChart;

public class NavigationData {

	public static final String ROOT = "root";

	private static final Map<String, ExplorerTreeNode[]> navigationDataMap = new HashMap<>();

	private final String idSuffix;

	public NavigationData(String idSuffix) {
		this.idSuffix = idSuffix;
	}

	private ExplorerTreeNode[] getData() {

		ExplorerTreeNode[] data = navigationDataMap.get(idSuffix);

		if (data == null) {
			data = new ExplorerTreeNode[] {

					new ExplorerTreeNode("HWD History", "history", ROOT, "silk/house.png", null, true, idSuffix),
					new ExplorerTreeNode("Jahres&uuml;berblick", "history-overview", "history",
							"silk/calendar_view_day.png", new HistoryOverview.Factory(), true, idSuffix),
					new ExplorerTreeNode("Ewige Tabelle", "history-alltime-table", "history",
							"silk/calendar.png",
							new HistoryAllTimeTable.Factory(), true, idSuffix),
					new ExplorerTreeNode("Platzierungen", "history-standings-chart", "history",
							"silk/chart_bar.png", new HistoryStandingsChart.Factory(), true, idSuffix),

			};
			navigationDataMap.put(idSuffix, data);

		}
		return data;
	}

	public static ExplorerTreeNode[] getData(String idSuffix) {
		return new NavigationData(idSuffix).getData();
	}
}
