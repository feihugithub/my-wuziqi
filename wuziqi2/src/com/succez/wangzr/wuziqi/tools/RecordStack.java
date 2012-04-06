package com.succez.wangzr.wuziqi.tools;

/**
 * 在Ai使用博弈树时，要深度搜素depth深度，这条路径上的点要通过此数据结构记录下来
 */
public class RecordStack {
	public int positionX;

	public int positionY;

	/**记录回溯值*/
	public int backValue;

	/**
	 * 带参数的构造函数
	 */
	public RecordStack(int x, int y, int back) {
		positionX = x;
		positionY = y;
		backValue = back;
	}
}