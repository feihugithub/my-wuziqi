package com.succez.wangzr.test;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestIntToHex {

	@Test
	public void test() {
		String str = IntToHex.intToHex(12345);
		String string = "12345";
		assertEquals(str, string);
	}

}
