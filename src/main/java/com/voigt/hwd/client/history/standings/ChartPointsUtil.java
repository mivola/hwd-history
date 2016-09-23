package com.voigt.hwd.client.history.standings;

import java.util.ArrayList;
import java.util.List;

import com.voigt.hwd.client.domain.User;

public class ChartPointsUtil {

	static List<Integer> getLineDataAsList(List<Integer> integers) {
		List<Integer> list = new ArrayList<>();
		for (Integer place : integers) {
			int cntUser = User.values().length;
			double stepSize = 100d / (cntUser - 1);
			int steps = cntUser - place.intValue();
			Double percentValue = steps * stepSize;
			list.add(percentValue.intValue());
		}
		return list;
	}
}
