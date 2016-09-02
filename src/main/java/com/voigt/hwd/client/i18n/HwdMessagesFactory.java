package com.voigt.hwd.client.i18n;

import com.google.gwt.core.client.GWT;

public class HwdMessagesFactory {

	private HwdMessagesFactory() {
		// private
	}

	public static HwdMessages getInstance() {
		return (HwdMessages) GWT.create(HwdMessages.class);
	}

	public static String getSeasonDescription(int year) {
		switch (year) {
		case 1999:
			return getInstance().season1999Desc();
		case 2000:
			return getInstance().season2000Desc();
		case 2001:
			return getInstance().season2001Desc();
		case 2002:
			return getInstance().season2002Desc();
		case 2003:
			return getInstance().season2003Desc();
		case 2004:
			return getInstance().season2004Desc();
		case 2005:
			return getInstance().season2005Desc();
		case 2006:
			return getInstance().season2006Desc();
		case 2007:
			return getInstance().season2007Desc();
		case 2008:
			return getInstance().season2008Desc();
		case 2009:
			return getInstance().season2009Desc();
		default:
			return "";
		}
	}

}
