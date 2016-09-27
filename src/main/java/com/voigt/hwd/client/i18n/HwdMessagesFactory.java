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
		case 2010:
			return getInstance().season2010Desc();
		case 2011:
			return getInstance().season2011Desc();
		case 2012:
			return getInstance().season2012Desc();
		case 2013:
			return getInstance().season2013Desc();
		case 2014:
			return getInstance().season2014Desc();
		case 2015:
			return getInstance().season2015Desc();
		default:
			return "";
		}
	}

}
