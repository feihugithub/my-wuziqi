package com.succez.wangzr.wuziqi.tools;
/**
 * 棋盘信息在文件中的存储单元
 * <p>Copyright: Copyright (c) 2012<p>
 * <p>succez<p>
 * @author feihu
 * @createdate 2012-4-27
 */
public class InfoUnit {
	public Point point;

	public int control;

	public InfoUnit(int x,int y,int control){
		point=new Point(x, y);
		this.control=control;
	}
}
