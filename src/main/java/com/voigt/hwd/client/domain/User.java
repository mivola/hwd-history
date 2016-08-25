package com.voigt.hwd.client.domain;

public enum User {

	// @formatter:off
	HUENI(1, "Hüni"), 
	MICHA(2, "Micha"), 
	STEV(3, "Stev"), 
	NICO(4, "Nico"), 
	MARKUS(5, "Markus"), 
	TOBI(6, "Tobi"), 
	MARCEL(7, "Marcel"), 
	JAN(8, "Jan"), 
	PATZI(9, "Patzi"), 
	ROSSI(10, "Rossi"), 
	SVEN(11, "Sven");
	// @formatter:on

	private int id;
	private String name;

	User(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public static User getByName(String name) {
		User[] values = values();
		// return Arrays.stream(values).filter(user ->
		// user.getName().equalsIgnoreCase(name)).findFirst().get();
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
	
	@Override
	public String toString() {
		return getName();
	}

}
