package com.voigt.hwd.client.history.overview;

import com.google.gwt.core.client.JavaScriptObject;
import com.smartgwt.client.types.TabBarControls;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;
import com.voigt.hwd.client.AbstractBasePanel;
import com.voigt.hwd.client.PanelFactory;
import com.voigt.hwd.client.domain.HistoryData;
import com.voigt.hwd.client.history.overview.data.HistoryOverviewData;
import com.voigt.hwd.client.history.overview.data.HistoryOverviewDataProvider;
import com.voigt.hwd.client.i18n.HwdMessages;
import com.voigt.hwd.client.i18n.HwdMessagesFactory;

import java.util.Collection;

public class HistoryOverview extends AbstractBasePanel {

	private static final String DESCRIPTION = "<p>die einzelnen Jahre im Überblick ...</p>";

	public static class Factory implements PanelFactory {
		private String id;

		public Canvas create() {
			HistoryOverview panel = new HistoryOverview();
			id = panel.getID();
			return panel;
		}

		public String getID() {
			return id;
		}

		public String getDescription() {
			return DESCRIPTION;
		}
	}

	@Override
	public Canvas getViewPanel() {

		final VLayout layout = new VLayout(15);

		Label label = new Label();
		label.setHeight(10);
		label.setWidth100();
		// label.setWidth(250);
		label.setContents("HWD - die einzelnen Jahre");
		layout.addMember(label);

		TabSet tabSet = new TabSet();

		tabSet.setTabBarThickness(24);
		tabSet.setWidth100();
		tabSet.setHeight100();

		LayoutSpacer layoutSpacer = new LayoutSpacer();
		layoutSpacer.setWidth(5);

		tabSet.setTabBarControls(TabBarControls.TAB_SCROLLER, TabBarControls.TAB_PICKER, layoutSpacer);

		HwdMessages messages = HwdMessagesFactory.getInstance();
		HistoryOverviewDataProvider dataProvider = new HistoryOverviewDataProvider();
		Collection<HistoryOverviewData> seasons = dataProvider.getSeasons();
		for (HistoryOverviewData data : seasons) {
			Tab tab = new Tab();
			int year = data.getYear();
			String seasonString = messages.seasonString(year, (year + 1));
			tab.setTitle(seasonString);

			Window wnd = new HistoryOverviewWindow(data);
			tab.setPane(wnd);
			tabSet.addTab(tab);
		}

		layout.addMember(tabSet);
		return layout;
	}

	@SuppressWarnings("unused")
	private static native JavaScriptObject getJson(String varName)
	/*-{ return $wnd[varName]; }-*/
	;

}