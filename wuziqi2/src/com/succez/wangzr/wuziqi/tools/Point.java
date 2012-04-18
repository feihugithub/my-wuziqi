package com.succez.wangzr.wuziqi.tools;

/**
 * 构造此数据结构方便AI查找算法返回最优点的坐标
 */
public class Point {
	public int positionX;

	public int positionY;
	
	public int rateValue;

	/**
	 * 带参数的构造函数
	 */
	public Point(int x, int y) {
		this(x,y,0);
	}
	public Point(int x, int y,int value) {
		positionX = x;
		positionY = y;
		rateValue=value;
	}
}