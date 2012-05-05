package com.succez.wangzr.test;

/**
 * 实现将整数转换成字符串
 * <p>Copyright: Copyright (c) 2012<p>
 * <p>succez<p>
 * @author feihu
 * @createdate 2012-5-4
 */
public class IntToHex {
	/**
	 * 方法中采用了StringBuffer，因为它的对象是个变量，并有方法添加元素和逆序功能
	 * @param number  要转换的整数
	 * @return  转换成的字符串
	 */
	public static String intToHex(int number) {
		StringBuilder sBuffer = new StringBuilder();
		boolean isnegative = number < 0;
		number = Math.abs(number);
		while (number != 0) {
			sBuffer.append(digital[number % 16]);
			number = number / 16;
		}
		if (isnegative) {
			sBuffer.append('-');
		}
		sBuffer = sBuffer.reverse();
		return sBuffer.toString();
	}

	final static char[] digital = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	public static void main(String[] args) {
		String str = IntToHex.intToHex(-15);
		System.out.println(str);
	}
}
