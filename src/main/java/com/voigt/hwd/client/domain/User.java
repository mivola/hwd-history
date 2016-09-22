package com.voigt.hwd.client.domain;

import java.util.Date;

import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.i18n.shared.DefaultDateTimeFormatInfo;

public enum User {

	// @formatter:off
	HUENI(1, "hüni","05.02.2000"), 
	MICHA(2, "micha","05.02.2000"), 
	STEV(3, "stev","11.03.2000"), 
	NICO(4, "nico","09.08.2002"), 
	MARKUS(5, "markus","09.08.2002"), 
	TOBI(6, "tobi","09.08.2002"), 
	MARCEL(7, "marcelly","21.07.2005"), 
	JAN(8, "janosch","01.08.2006"), 
	PATZI(9, "patzi","01.08.2006"), 
	ROSSI(10, "rossi","20.07.2007"), 
	SVEN(11, "sven","20.07.2007"),
	CHAPPER(12, "chapper","18.08.2010"),
	SCHROE(13, "schroe89","18.07.2014");
	// @formatter:on

	private int id;
	private String name;
	private Date joined;
	
	private final DateTimeFormat dtf = new DateTimeFormat("dd.MM.yyyy", new DefaultDateTimeFormatInfo()) { /* empty */ };

	User(int id, String name, String joinedDate) {
		this.id = id;
		this.name = name;
		if (joinedDate != null && !"".equals(joinedDate)) {
			this.joined = dtf.parse(joinedDate);
		}
	}

	public static User getByName(String name) {
		User[] values = values();
		for (User user : values) {
			if (user.getName().equalsIgnoreCase(name)) {
				return user;
			}
		}
		return null;
	}
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Date getJoined() {
		return joined;
	}
	
	@Override
	public String toString() {
		return getName();
	}

}
