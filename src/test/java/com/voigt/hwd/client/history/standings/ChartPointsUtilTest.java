package com.voigt.hwd.client.history.standings;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

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
		assertTrue(lineData > 40, "lineData: " + lineData);
		assertTrue(lineData < 60, "lineData: " + lineData);
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
