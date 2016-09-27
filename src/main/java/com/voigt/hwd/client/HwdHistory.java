package com.voigt.hwd.client;

import java.util.Date;
import java.util.LinkedHashMap;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.RootPanel;
import com.smartgwt.client.core.KeyIdentifier;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.HeaderControls;
import com.smartgwt.client.types.TabBarControls;
import com.smartgwt.client.util.DateDisplayFormatter;
import com.smartgwt.client.util.DateParser;
import com.smartgwt.client.util.DateUtil;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ShowContextMenuEvent;
import com.smartgwt.client.widgets.events.ShowContextMenuHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.events.ChangeEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangeHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.MenuItemIfFunction;
import com.smartgwt.client.widgets.menu.events.ClickHandler;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;
import com.smartgwt.client.widgets.tree.Tree;
import com.smartgwt.client.widgets.tree.TreeNode;
import com.smartgwt.client.widgets.tree.events.LeafClickEvent;
import com.smartgwt.client.widgets.tree.events.LeafClickHandler;
import com.voigt.hwd.client.i18n.HwdMessages;
import com.voigt.hwd.client.i18n.HwdMessagesFactory;
import com.voigt.hwd.client.navigation.CommandTreeNode;
import com.voigt.hwd.client.navigation.ExplorerTreeNode;
import com.voigt.hwd.client.navigation.NavigationTree;

public class HwdHistory implements EntryPoint, ValueChangeHandler<String> {

	private static final String NORMAL_DATE_FORMAT = "dd.MM.yyyy HH:mm:ss";
	private static final String SHORT_DATE_FORMAT = "dd.MM.yyyy";
	private TabSet mainTabSet;
	private NavigationTree navigationTree;

	public void onModuleLoad() {

		VLayout main = new VLayout();

		this.setDateFormats();

		main.setWidth100();
		main.setHeight100();
		main.setLayoutMargin(5);
		main.setStyleName("tabSetContainer");

		HLayout hLayout = new HLayout();
		hLayout.setWidth100();
		hLayout.setHeight100();

		VLayout sideNavLayout = new VLayout();
		sideNavLayout.setHeight100();
		sideNavLayout.setWidth(185);
		sideNavLayout.setShowResizeBar(true);

		navigationTree = new NavigationTree();

		navigationTree.addLeafClickHandler(new LeafClickHandler() {
			public void onLeafClick(LeafClickEvent event) {
				TreeNode node = event.getLeaf();
				showSample(node);
			}
		});

		String formattedDate = getBuildDate();
		Label buildDateLabel = new Label("erstellt: " + formattedDate);
		buildDateLabel.setHeight(20);
		buildDateLabel.setWrap(false);

		String buildVersion = getBuildVersion();
		Label versionLabel = new Label(buildVersion);
		versionLabel.setWidth100();
		versionLabel.setHeight(20);
		versionLabel.setAlign(Alignment.RIGHT);

		Tab treeTab = new Tab();
		treeTab.setPane(navigationTree);
		treeTab.setTitle("1");

		TabSet navTabSet = new TabSet();
		navTabSet.setHeight100();
		navTabSet.addTab(treeTab);

		sideNavLayout.addMember(navigationTree);

		HLayout versionLayout = new HLayout();
		versionLayout.setWidth100();
		versionLayout.addMember(buildDateLabel);
		versionLayout.addMember(versionLabel);

		sideNavLayout.addMember(versionLayout);

		hLayout.addMember(sideNavLayout);

		mainTabSet = new TabSet();

		// default is 22. required to increase to that select tab
		// control displays well
		mainTabSet.setTabBarThickness(24);
		mainTabSet.setWidth100();
		mainTabSet.setHeight100();

		LayoutSpacer layoutSpacer = new LayoutSpacer();
		layoutSpacer.setWidth(5);

		SelectItem selectItem = new SelectItem();
		selectItem.setWidth(130);
		LinkedHashMap<String, String> valueMap = new LinkedHashMap<>();
		valueMap.put("Enterprise", "Enterprise");
		valueMap.put("EnterpriseBlue", "Enterprise Blue");
		valueMap.put("SilverWave", "Silver Wave");
		valueMap.put("BlackOps", "Black Ops");
		valueMap.put("TreeFrog", "Tree Frog");
		valueMap.put("fleet", "Fleet");
		valueMap.put("Mobile", "Mobile");
		valueMap.put("Simplicity", "Simplicity");
		valueMap.put("Graphite", "Graphite");

		selectItem.setValueMap(valueMap);

		String currentSkin = Cookies.getCookie("skin");
		if (currentSkin == null) {
			currentSkin = "SilverWave";
		}
		selectItem.setDefaultValue(currentSkin);
		selectItem.setShowTitle(false);
		selectItem.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {
				Cookies.setCookie("skin", (String) event.getValue());
				com.google.gwt.user.client.Window.Location.reload();
			}
		});

		DynamicForm form = new DynamicForm();
		form.setPadding(0);
		form.setMargin(0);
		form.setCellPadding(1);
		form.setNumCols(1);
		form.setFields(selectItem);

		mainTabSet.setTabBarControls(TabBarControls.TAB_SCROLLER, TabBarControls.TAB_PICKER, layoutSpacer, form);

		final Menu contextMenu = createContextMenu();
		mainTabSet.addShowContextMenuHandler(new ShowContextMenuHandler() {
			public void onShowContextMenu(ShowContextMenuEvent event) {
				int selectedTab = mainTabSet.getSelectedTabNumber();
				if (selectedTab != 0) {
					contextMenu.showContextMenu();
				}
				event.cancel();
			}
		});

		Tab tab = new Tab();
		tab.setTitle("HWD History");
		// tab.setIcon("pieces/16/cube_green.png");

		final String title = "HWD History";
		Window window = new Window();
		window.setTitle(title);
		window.setHeaderControls(HeaderControls.HEADER_ICON, HeaderControls.HEADER_LABEL);
		window.setWidth(500);
		window.setHeight(375);

		HwdMessages messages = HwdMessagesFactory.getInstance();
		Label titleLabel = new Label(messages.welcomeNewsTitle());
		titleLabel.setWidth100();
		titleLabel.setMargin(15);
		titleLabel.setHeight(30);

		Label newsLabel = new Label(messages.welcomeNews());
		newsLabel.setWidth100();
		newsLabel.setMargin(15);

		window.addItem(titleLabel);
		window.addItem(newsLabel);

		window.setKeepInParentRect(true);
		window.setTop(30);
		window.setLeft(30);
		window.setCanDragResize(true);

		HLayout mainPanel = new HLayout();
		mainPanel.setHeight100();
		mainPanel.setWidth100();

		mainPanel.addChild(window);

		tab.setPane(mainPanel);

		mainTabSet.addTab(tab);

		Canvas canvas = new Canvas();
		canvas.setBackgroundImage("[SKIN]/shared/background.gif");
		canvas.setWidth100();
		canvas.setHeight100();
		canvas.addChild(mainTabSet);

		hLayout.addMember(canvas);
		main.addMember(hLayout);
		main.draw();

		History.addValueChangeHandler(this);

		RootPanel.get("loadingMsg").getElement().setInnerHTML("");
	}

	private String getBuildDate() {
		Version version = GWT.create(Version.class);
		String buildDateTimestamp = version.buildDate();
		Date buildDate = new Date(Long.valueOf(buildDateTimestamp));
		String formattedDate = DateTimeFormat.getFormat(SHORT_DATE_FORMAT).format(buildDate);
		return formattedDate;
	}

	private String getBuildVersion() {
		Version version = GWT.create(Version.class);
		String major = version.buildVersionMajor();
		String minor = version.buildVersionMinor();
		String micro = version.buildVersionMicro();
		String buildVersion = "v" + major + "." + minor + "." + micro;
		return buildVersion;
	}

	private void setDateFormats() {

		DateUtil.setDateParser(new DateParser() {
			public Date parse(String dateString) {
				final DateTimeFormat dateFormatter = DateTimeFormat.getFormat(SHORT_DATE_FORMAT);
				Date date = dateFormatter.parse(dateString);
				return date;
			}
		});

		DateUtil.setNormalDateDisplayFormatter(new DateDisplayFormatter() {
			public String format(Date date) {
				if (date == null) {
					return null;
				}
				final DateTimeFormat dateFormatter = DateTimeFormat.getFormat(NORMAL_DATE_FORMAT);
				String format = dateFormatter.format(date);
				return format;
			}
		});

		DateUtil.setShortDateDisplayFormatter(new DateDisplayFormatter() {
			public String format(Date date) {
				if (date == null) {
					return null;
				}
				final DateTimeFormat dateFormatter = DateTimeFormat.getFormat(SHORT_DATE_FORMAT);
				String format = dateFormatter.format(date);
				return format;
			}
		});

	}

	private Menu createContextMenu() {
		Menu contextMenu = new Menu();
		contextMenu.setWidth(140);

		MenuItemIfFunction enableCondition = new MenuItemIfFunction() {
			public boolean execute(Canvas target, Menu menu, MenuItem item) {
				int selectedTab = mainTabSet.getSelectedTabNumber();
				return selectedTab != 0;
			}
		};

		MenuItem closeItem = new MenuItem("<u>C</u>lose");
		closeItem.setEnableIfCondition(enableCondition);
		closeItem.setKeyTitle("Alt+C");
		KeyIdentifier closeKey = new KeyIdentifier();
		closeKey.setAltKey(true);
		closeKey.setKeyName("C");
		closeItem.setKeys(closeKey);
		closeItem.addClickHandler(new ClickHandler() {
			public void onClick(MenuItemClickEvent event) {
				int selectedTab = mainTabSet.getSelectedTabNumber();
				mainTabSet.removeTab(selectedTab);
				mainTabSet.selectTab(selectedTab - 1);
			}
		});

		MenuItem closeAllButCurrent = new MenuItem("Close All But Current");
		closeAllButCurrent.setEnableIfCondition(enableCondition);
		closeAllButCurrent.addClickHandler(new ClickHandler() {
			public void onClick(MenuItemClickEvent event) {
				int selected = mainTabSet.getSelectedTabNumber();
				Tab[] tabs = mainTabSet.getTabs();
				int[] tabsToRemove = new int[tabs.length - 2];
				int cnt = 0;
				for (int i = 1; i < tabs.length; i++) {
					if (i != selected) {
						tabsToRemove[cnt] = i;
						cnt++;
					}
				}
				mainTabSet.removeTabs(tabsToRemove);
			}
		});

		MenuItem closeAll = new MenuItem("Close All");
		closeAll.setEnableIfCondition(enableCondition);
		closeAll.addClickHandler(new ClickHandler() {
			public void onClick(MenuItemClickEvent event) {
				Tab[] tabs = mainTabSet.getTabs();
				int[] tabsToRemove = new int[tabs.length - 1];

				for (int i = 1; i < tabs.length; i++) {
					tabsToRemove[i - 1] = i;
				}
				mainTabSet.removeTabs(tabsToRemove);
				mainTabSet.selectTab(0);
			}
		});

		contextMenu.setItems(closeItem, closeAllButCurrent, closeAll);
		return contextMenu;
	}

	private void showSample(Record record) {

		boolean isExplorerTreeNode = record instanceof ExplorerTreeNode;
		if (record instanceof CommandTreeNode) {
			CommandTreeNode commandTreeNode = (CommandTreeNode) record;
			commandTreeNode.getCommand().execute();
		} else if (isExplorerTreeNode) {
			ExplorerTreeNode explorerTreeNode = (ExplorerTreeNode) record;
			PanelFactory factory = explorerTreeNode.getFactory();
			if (factory != null) {
				String panelID = factory.getID();
				Tab tab = null;
				if (panelID != null) {
					String tabID = panelID + "_tab";
					tab = mainTabSet.getTab(tabID);
				}
				if (tab == null) {
					Canvas panel = factory.create();
					tab = new Tab();
					tab.setID(factory.getID() + "_tab");
					String sampleName = explorerTreeNode.getName();

					String icon = explorerTreeNode.getIcon();
					if (icon == null) {
						icon = "silk/plugin.png";
					}
					String imgHTML = Canvas.imgHTML(icon);
					tab.setTitle("<span>" + imgHTML + "&nbsp;" + sampleName + "</span>");
					tab.setPane(panel);
					tab.setCanClose(true);
					mainTabSet.addTab(tab);
					mainTabSet.selectTab(tab);
					if (!SC.isIE()) {
						if (mainTabSet.getNumTabs() == 10) {
							mainTabSet.removeTabs(new int[] { 1 });
						}
					}
					History.newItem(explorerTreeNode.getNodeID(), false);
				} else {
					mainTabSet.selectTab(tab);
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.google.gwt.event.logical.shared.ValueChangeHandler#onValueChange(com.
	 * google.gwt.event.logical.shared.ValueChangeEvent)
	 */
	public void onValueChange(ValueChangeEvent<String> event) {
		// public void onHistoryChanged(String historyToken) {
		Object historyToken = event.getValue();
		if (historyToken == null || historyToken.equals("")) {
			mainTabSet.selectTab(0);
		} else {
			ExplorerTreeNode[] showcaseData = navigationTree.getNaviationData();
			for (ExplorerTreeNode explorerTreeNode : showcaseData) {
				if (explorerTreeNode.getNodeID().equals(historyToken)) {
					showSample(explorerTreeNode);
					navigationTree.selectRecord(explorerTreeNode);
					Tree tree = navigationTree.getData();
					TreeNode categoryNode = tree.getParent(explorerTreeNode);
					// TODO isRoot not working?
					// while (categoryNode != null &&
					// !tree.isRoot(categoryNode)) {
					while (categoryNode != null && !"/".equals(tree.getName(categoryNode))) {
						tree.openFolder(categoryNode);
						categoryNode = tree.getParent(categoryNode);
					}
				} else {
					navigationTree.selectRecord(explorerTreeNode, false);
				}
			}
		}
	}
}
