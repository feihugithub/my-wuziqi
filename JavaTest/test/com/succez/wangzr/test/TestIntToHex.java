package com.succez.wangzr.test;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestIntToHex {

	@Test
	public void test() {
		assertIntToHex(0);
		assertIntToHex(9);
		assertIntToHex(-9);
		assertIntToHex(19);
		assertIntToHex(-19);
		assertIntToHex(199);
		assertIntToHex(-199);
		assertIntToHex(1999);
		assertIntToHex(-1999);
		assertIntToHex(19999);
		assertIntToHex(-19999);
		assertIntToHex(199999);
		assertIntToHex(-199999);
		assertIntToHex(1999999);
		assertIntToHex(-1999999);
		assertIntToHex(19999999);
		assertIntToHex(-19999999);
		assertIntToHex(199999999);
		assertIntToHex(-199999999);
		assertIntToHex(1999999999);
		assertIntToHex(-1999999999);

		// 边界测试 测最大最小整数
		assertIntToHex(Integer.MAX_VALUE);
		assertIntToHex(Integer.MIN_VALUE);
	}

	private void assertIntToHex(int i) {
		assertEquals(i, Integer.parseInt(IntToHex.intToHex(i), 16));
	}

}
