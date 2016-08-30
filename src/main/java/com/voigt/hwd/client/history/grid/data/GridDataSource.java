package com.voigt.hwd.client.history.grid.data;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceDateField;
import com.smartgwt.client.data.fields.DataSourceFloatField;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.widgets.form.validator.FloatPrecisionValidator;
import com.voigt.hwd.client.history.grid.StatisticalGridRecord;

public class GridDataSource extends DataSource {

	private static GridDataSource instance = null;

	public static GridDataSource getInstance() {
		if (instance == null) {
			instance = new GridDataSource("hwdHistoryLocalDS");
		}
		return instance;
	}

	public GridDataSource(String id) {

		setID(id);
		DataSourceIntegerField pkField = new DataSourceIntegerField(StatisticalGridRecord.USER_ID_FIELD);
		pkField.setHidden(true);
		pkField.setPrimaryKey(true);

		FloatPrecisionValidator precisionValidator = new FloatPrecisionValidator();
		precisionValidator.setPrecision(2);
		precisionValidator.setRoundToPrecision(2);

		DataSourceTextField userNicknameField = new DataSourceTextField(StatisticalGridRecord.USER_NICKNAME_FIELD, "Name", 20,
				true);
		DataSourceDateField joinedField = new DataSourceDateField(StatisticalGridRecord.JOINED_FIELD, "dabei seit", 10, true);
		DataSourceIntegerField cntSeasonsField = new DataSourceIntegerField(StatisticalGridRecord.CNT_SEASONS_FIELD, "Saisons",
				2, true);
		DataSourceIntegerField cntAllTimePoints = new DataSourceIntegerField(StatisticalGridRecord.CNT_ALL_TIME_POINTS_FIELD,
				"Ewige Punkte", 2, true);
		DataSourceIntegerField cntFirstPlaceField = new DataSourceIntegerField(StatisticalGridRecord.CNT_FIRST_PLACE_FIELD,
				"Meister", 2, true);
		DataSourceIntegerField cntSecondPlaceField = new DataSourceIntegerField(StatisticalGridRecord.CNT_SECOND_PLACE_FIELD,
				"VizeKusen", 2, true);
		DataSourceIntegerField cntThirdPlaceField = new DataSourceIntegerField(StatisticalGridRecord.CNT_THIRD_PLACE_FIELD,
				"Bonze", 2, true);
		DataSourceIntegerField cntLastPlaceField = new DataSourceIntegerField(StatisticalGridRecord.CNT_LAST_PLACE_FIELD,
				"Letzter!", 2, true);
		DataSourceFloatField totalPointsField = new DataSourceFloatField(StatisticalGridRecord.TOTAL_POINTS_FIELD,
				"Gesamtpunkte", 4, true);
		DataSourceFloatField pointsPerSeasonField = new DataSourceFloatField(StatisticalGridRecord.POINTS_PER_SEASON_FIELD,
				"Gesamtpunkte/Saison", 2, true);
		DataSourceIntegerField totalTippPointsField = new DataSourceIntegerField(StatisticalGridRecord.TOTAL_TIPP_POINTS_FIELD,
				"Gesamttipppunkte", 4, true);
		DataSourceFloatField tippPointsPerSeasonField = new DataSourceFloatField(
				StatisticalGridRecord.TIPP_POINTS_PER_SEASON_FIELD, "Tipppunkte/Saison", 3, true);

		setFields(pkField, userNicknameField, joinedField, cntAllTimePoints, cntSeasonsField, cntFirstPlaceField,
				cntSecondPlaceField, cntThirdPlaceField, cntLastPlaceField, totalPointsField, pointsPerSeasonField,
				totalTippPointsField, tippPointsPerSeasonField);

		setClientOnly(Boolean.TRUE);
		setTestData(StatisticalGridRecordsProvider.getRecords());
	}
}
