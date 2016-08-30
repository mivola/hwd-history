package com.voigt.hwd.client.domain;

public class StatisticalAllTimeData {

	private int cntSeasons;
	private int cntFirstPlace;
	private int cntSecondPlace;
	private int cntThirdPlace;
	private int cntLastPlace;
	private float totalPoints;
	private int totalTippPoints;

	public int getCntSeasons() {
		return cntSeasons;
	}

	public void setCntSeasons(int cntSeasons) {
		this.cntSeasons = cntSeasons;
	}

	public StatisticalAllTimeData increaseCntSeasons() {
		this.cntSeasons++;
		return this;
	}

	public int getCntFirstPlace() {
		return cntFirstPlace;
	}

	public void setCntFirstPlace(int cntFirstPlace) {
		this.cntFirstPlace = cntFirstPlace;
	}

	public StatisticalAllTimeData increaseCntFirstPlace() {
		this.cntFirstPlace++;
		return this;
	}

	public int getCntSecondPlace() {
		return cntSecondPlace;
	}

	public void setCntSecondPlace(int cntSecondPlace) {
		this.cntSecondPlace = cntSecondPlace;
	}

	public StatisticalAllTimeData increaseCntSecondPlace() {
		this.cntSecondPlace++;
		return this;
	}

	public int getCntThirdPlace() {
		return cntThirdPlace;
	}

	public void setCntThirdPlace(int cntThirdPlace) {
		this.cntThirdPlace = cntThirdPlace;
	}

	public StatisticalAllTimeData increaseCntThirdPlace() {
		this.cntThirdPlace++;
		return this;
	}

	public int getCntLastPlace() {
		return cntLastPlace;
	}

	public void setCntLastPlace(int cntLastPlace) {
		this.cntLastPlace = cntLastPlace;
	}

	public StatisticalAllTimeData increaseCntLastPlace() {
		this.cntLastPlace++;
		return this;
	}

	public float getTotalPoints() {
		return totalPoints;
	}

	public void setTotalPoints(float totalPoints) {
		this.totalPoints = totalPoints;
	}

	public StatisticalAllTimeData increaseTotalPoints(float points) {
		this.totalPoints = this.totalPoints + points;
		return this;
	}

	public int getTotalTippPoints() {
		return totalTippPoints;
	}

	public void setTotalTippPoints(int totalTippPoints) {
		this.totalTippPoints = totalTippPoints;
	}

	public StatisticalAllTimeData increaseTotalTippPoints(int tippPoints) {
		this.totalTippPoints = this.totalTippPoints + tippPoints;
		return this;
	}

	public float getPointsPerSeason() {
		return getTotalPoints() / getCntSeasons();
	}

	public int getTippPointsPerSeason() {
		return getTotalTippPoints() / getCntSeasons();
	}

	public StatisticalAllTimeData increaseCntPlace(int place, int totalUsers) {
		switch (place) {
		case 1:
			increaseCntFirstPlace();
			break;
		case 2:
			increaseCntSecondPlace();
			break;
		case 3:
			increaseCntThirdPlace();
			break;
		default:
			break;
		}
		if (place == totalUsers) {
			increaseCntLastPlace();
		}
		return this;
	}
}
