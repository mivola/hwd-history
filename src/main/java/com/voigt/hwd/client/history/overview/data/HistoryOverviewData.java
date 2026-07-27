package com.voigt.hwd.client.history.overview.data;

public class HistoryOverviewData {

	private final int year;

	private final int imageHeight;

	private final int imageWidth;

	private final int cntParticipants;

	/*
	 * filename of the screenshot; no path needed; image must reside in a
	 * configured directory, e.g. public\images\hwd\history\
	 */
	private final String imageFilename;

	private final String maxPoints;

	private final String description;

	private final String winner;

	/* url to the live system */
	private final String url;

	public HistoryOverviewData(int year, int imageHeight, int imageWidth, int cntParticipants, String imageFilename,
			String maxPoints, String description, String winner, String url) {
		this.year = year;
		this.imageHeight = imageHeight;
		this.imageWidth = imageWidth;
		this.cntParticipants = cntParticipants;
		this.imageFilename = imageFilename;
		this.maxPoints = maxPoints;
		this.description = description;
		this.winner = winner;
		this.url = url;
	}

	public int getYear() {
		return year;
	}

	public int getImageHeight() {
		return imageHeight;
	}

	public int getImageWidth() {
		return imageWidth;
	}

	public int getCntParticipants() {
		return cntParticipants;
	}

	public String getImageFilename() {
		return imageFilename;
	}

	public String getMaxPoints() {
		return maxPoints;
	}

	public String getDescription() {
		return description;
	}

	public String getWinner() {
		return winner;
	}

	public String getUrl() {
		return url;
	}

}
