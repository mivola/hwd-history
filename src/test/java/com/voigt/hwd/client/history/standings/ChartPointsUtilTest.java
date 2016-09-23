package com.voigt.hwd.client.history.standings;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.voigt.hwd.client.domain.User;

public class ChartPointsUtilTest {

	@Test
	public void testConvertionOfFirstPlace() throws Exception {
		List<Integer> lineDataAsList = getLineData(1);
		assertEquals(Integer.valueOf(100), lineDataAsList.get(0));
	}

	@Test
	public void testConvertionOfLastPlace() throws Exception {
		List<Integer> lineDataAsList = getLineData(User.values().length);
		assertEquals(Integer.valueOf(0), lineDataAsList.get(0));
	}

	@Test
	public void testConvertionOfMiddlePlace() throws Exception {
		List<Integer> lineDataAsList = getLineData(User.values().length / 2);
		Integer lineData = lineDataAsList.get(0);
		assertTrue("lineData: " + lineData, lineData > 40);
		assertTrue("lineData: " + lineData, lineData < 60);
	}

	private List<Integer> getLineData(int... places) {
		List<Integer> list = getList(places);
		List<Integer> lineDataAsList = ChartPointsUtil.getLineDataAsList(list);
		return lineDataAsList;
	}

	private List<Integer> getList(int... places) {
		List<Integer> list = new ArrayList<>();
		for (int i : places) {
			list.add(i);
		}
		return list;
	}

}
