package com.voigt.hwd.client.history.standings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

//import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.core.client.JavaScriptObject;
import com.googlecode.charts4j.AxisLabels;
import com.googlecode.charts4j.AxisLabelsFactory;
import com.googlecode.charts4j.Color;
import com.googlecode.charts4j.Data;
import com.googlecode.charts4j.GCharts;
import com.googlecode.charts4j.LineChart;
import com.googlecode.charts4j.Plot;
import com.googlecode.charts4j.Plots;
import com.googlecode.charts4j.Shape;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.voigt.hwd.client.AbstractBasePanel;
import com.voigt.hwd.client.PanelFactory;
import com.voigt.hwd.client.domain.HistoryData;
import com.voigt.hwd.client.domain.User;

public class HistoryStandingsChart extends AbstractBasePanel {

	private final static int CHART_WIDTH = 800;
	private final static int CHART_HEIGHT = 370; // no more than 300k px allowed
	private static final String PX = "px";

	private static final String DESCRIPTION = "<p>Die Platzierungen in den einzelnen Jahren im Überblick</p>";

	private static final Map<User, Plot> userPlots = new HashMap<>();
	private static final Map<User, Boolean> selectedUsers = new HashMap<>();

	static {
		for (User user : User.values()) {
			Data userData = getData(user);
			if (userData != null) {
				Color color = getColor(user);
				Plot plot = Plots.newPlot(userData, color);
				plot.addShapeMarkers(getShape(user), color, HistoryData.getSeasons().size());
				userPlots.put(user, plot);
				selectedUsers.put(user, Boolean.TRUE);
			}
		}
	}

	public static class Factory implements PanelFactory {
		private String id;

		public Canvas create() {
			HistoryStandingsChart panel = new HistoryStandingsChart();
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

		VLayout layout = new VLayout(15);

		Label label = new Label();
		label.setHeight(10);
		label.setWidth100();
		label.setContents("HWD - alle Platzierungen");
		layout.addMember(label);

		final Img img = new Img(getChartImageUrl());
		img.setSize(CHART_WIDTH + PX, CHART_HEIGHT + PX);
		img.setOverflow(Overflow.AUTO);

		ChangedHandler changedHandler = new CheckboxItemChangedHandler(img);
		List<FormItem> checkboxes = new ArrayList<>();
		for (User user : User.values()) {
			CheckboxItem checkbox = createCheckbox(user, changedHandler);
			checkboxes.add(checkbox);
		}

		DynamicForm form = new DynamicForm();

		FormItem[] fields = checkboxes.toArray(new FormItem[checkboxes.size()]);
		form.setFields(fields);

		VLayout usersVLayout = new VLayout();
		usersVLayout.addChild(form);

		HLayout hLayout = new HLayout();
		hLayout.addMember(img);
		hLayout.addMember(usersVLayout);

		layout.addMember(hLayout);

		return layout;
	}

	private CheckboxItem createCheckbox(User user, ChangedHandler changedHandler) {
		CheckboxItem checkbox = new CheckboxItem(user.getName());
		checkbox.setDefaultValue(Boolean.TRUE);
		checkbox.setTextBoxStyle(user.name().toLowerCase() + "CheckboxTextbox hwdCheckboxTextbox");
		checkbox.addChangedHandler(changedHandler);
		return checkbox;
	}

	private static Data getData(User user) {
		List<Integer> integers = HistoryData.getStandingsData(user);
		List<Integer> list = getLineDataAsList(integers);
		return Data.newData(list);
	}

	private static Shape getShape(User user) {
		switch (user) {
		case HUENI:
		case TOBI:
		case CHAPPER:
			return Shape.SQUARE;
		case MICHA:
		case MARKUS:
		case JAN:
			return Shape.CIRCLE;
		case STEV:
		case ROSSI:
		case SCHROE:
			return Shape.CROSS;
		case NICO:
		case SVEN:
			return Shape.DIAMOND;
		case PATZI:
		case MARCEL:
			return Shape.X;
		default:
			return Shape.CROSS;
		}
	}

	private static Color getColor(User user) {
		switch (user) {
		// those colors need to match the CSS colors defined in hwd.css (e.g.
		// hueniCheckboxTextbox)
		case HUENI:
			return Color.newColor("00FF00");
		case MICHA:
			return Color.newColor("8A2BE2");
		case STEV:
			return Color.newColor("FF7F50");
		case NICO:
			return Color.newColor("00FFFF");
		case MARKUS:
			return Color.newColor("A52A2A");
		case TOBI:
			return Color.newColor("1E90FF");
		case MARCEL:
			return Color.newColor("00FF7F");
		case JAN:
			return Color.newColor("FF0000");
		case PATZI:
			return Color.newColor("D2B48C");
		case ROSSI:
			return Color.newColor("8FBC8F");
		case SVEN:
			return Color.newColor("FF00FF");
		case CHAPPER:
			return Color.newColor("da4f49");
		case SCHROE:
			return Color.newColor("c4e3f3");
		default:
			return Color.POWDERBLUE;
		}
	}

	private class CheckboxItemChangedHandler implements ChangedHandler {

		private final Img img;

		public CheckboxItemChangedHandler(Img img) {
			this.img = img;
		}

		public void onChanged(ChangedEvent event) {

			final Window winModal = new Window();
			winModal.setShowModalMask(Boolean.TRUE);
			winModal.setWidth(360);
			winModal.setHeight(150);
			winModal.setTitle("Berechne ...");
			winModal.setShowMinimizeButton(Boolean.FALSE);
			winModal.setShowCloseButton(Boolean.FALSE);
			winModal.setIsModal(Boolean.TRUE);
			winModal.centerInPage();

			HLayout layout = new HLayout();
			layout.setAlign(Alignment.CENTER);
			layout.setAlign(VerticalAlignment.CENTER);
			Label label = new Label("Berechne Diagram ... ");
			label.setWidth100();
			label.setAlign(Alignment.CENTER);
			layout.addChild(label);
			winModal.addItem(layout);

			winModal.show();

			CheckboxItem checkboxItem = (CheckboxItem) event.getSource();
			String name = checkboxItem.getName();
			Boolean value = (Boolean) event.getValue();
			selectedUsers.put(User.getByName(name), value);
			img.setSrc(getChartImageUrl());
			img.resetSrc();

			winModal.destroy();
		}

	}

	public String getChartImageUrl() {

		LineChart lineChart = GCharts.newLineChart(getSelectedPlots());
		lineChart.setSize(CHART_WIDTH, CHART_HEIGHT);

		// lineChart.addHorizontalRangeMarker(33.3, 66.6, LIGHTBLUE);
		int seasons = HistoryData.getEndYear() - HistoryData.getStartYear();
		lineChart.setGrid(100d / seasons, 100d / User.values().length + 1, 3, 3);

		List<String> xLabels = new ArrayList<>();
		for (int i = HistoryData.getStartYear(); i <= HistoryData.getEndYear(); i++) {
			xLabels.add(String.valueOf(i));
		}
		lineChart.addXAxisLabels(AxisLabelsFactory.newAxisLabels(xLabels));

		List<String> yLabels = new ArrayList<>();
		for (int i = User.values().length; i > 0; i--) {
			yLabels.add(String.valueOf(i));
		}
		AxisLabels yAxisLabels = AxisLabelsFactory.newAxisLabels(yLabels);
		lineChart.addRightAxisLabels(yAxisLabels);
		lineChart.addYAxisLabels(yAxisLabels);

		String lineChartUrl = lineChart.toURLString();
		// Log.debug(lineChartUrl);
		return lineChartUrl;
	}

	private List<Plot> getSelectedPlots() {

		List<Plot> plots = new ArrayList<>();
		Set<User> users = selectedUsers.keySet();
		for (User user : users) {
			Boolean selected = selectedUsers.get(user);
			if (selected.booleanValue()) {
				Plot plot = userPlots.get(user);
				plots.add(plot);
			}
		}
		return plots;

	}

	private static List<Integer> getLineDataAsList(List<Integer> integers) {
		List<Integer> list = new ArrayList<>();
		for (Integer i : integers) {
			i = Integer.valueOf(110 - (i.intValue() * 10));
			list.add(i);
		}
		return list;
	}

	@SuppressWarnings("unused")
	private static native JavaScriptObject getJson(String varName)
	/*-{ return $wnd[varName]; }-*/
	;
}