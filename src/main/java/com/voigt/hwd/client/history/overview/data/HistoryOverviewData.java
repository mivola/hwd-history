package com.voigt.hwd.client.history.overview.data;

public class HistoryOverviewData {

	private int imageHeight;

	private int imageWidth;

	private int cntParticipants;

	/*
	 * filename of the screenshot; no path needed; image must reside in a
	 * configured directory, e.g. public\images\hwd\history\
	 */
	private String imageFilename;

	private String maxPoints;

	private String description;

	private String winner;

	/* url to the live system */
	private String url;

	public HistoryOverviewData(int imageHeight, int imageWidth, int cntParticipants, String imageFilename,
			String maxPoints, String description, String winner, String url) {
		this.imageHeight = imageHeight;
		this.imageWidth = imageWidth;
		this.cntParticipants = cntParticipants;
		this.imageFilename = imageFilename;
		this.maxPoints = maxPoints;
		this.description = description;
		this.winner = winner;
		this.url = url;
	}

	public int getImageHeight() {
		return imageHeight;
	}

	public void setImageHeight(int imageHeight) {
		this.imageHeight = imageHeight;
	}

	public int getImageWidth() {
		return imageWidth;
	}

	public void setImageWidth(int imageWidth) {
		this.imageWidth = imageWidth;
	}

	public int getCntParticipants() {
		return cntParticipants;
	}

	public void setCntParticipants(int cntParticipants) {
		this.cntParticipants = cntParticipants;
	}

	public String getImageFilename() {
		return imageFilename;
	}

	public void setImageFilename(String imageFilename) {
		this.imageFilename = imageFilename;
	}

	public String getMaxPoints() {
		return maxPoints;
	}

	public void setMaxPoints(String maxPoints) {
		this.maxPoints = maxPoints;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getWinner() {
		return winner;
	}

	public void setWinner(String winner) {
		this.winner = winner;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
