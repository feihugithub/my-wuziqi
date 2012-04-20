package com.succez.wangzr.wuziqi;

import static org.junit.Assert.*;
import org.junit.Test;
import com.succez.wangzr.wuziqi.algorithm.ChessMethod;
import com.succez.wangzr.wuziqi.tools.Constant;
import com.succez.wangzr.wuziqi.tools.Point;
import com.succez.wangzr.wuziqi.ui.ChessPanel;

/**
 * ChessMethod测试类
 * <p>Copyright: Copyright (c) 2012<p>
 * <p>succez<p>
 * @author feihu
 * @createdate 2012-4-9
 */
public class TestChessMethod {
	ChessPanel chessPanel = new ChessPanel();
	ChessMethod method=new ChessMethod();

	@Test
	public void testreset() {
		chessPanel.table[0][0] = Constant.BLACKCHESS;
		chessPanel.table[1][1] = Constant.BLACKCHESS;
		chessPanel.table[2][2] = Constant.BLACKCHESS;
		assertEquals(Constant.BLACKCHESS, chessPanel.table[0][0]);
		assertEquals(Constant.BLACKCHESS, chessPanel.table[1][1]);
		assertEquals(Constant.BLACKCHESS, chessPanel.table[2][2]);
		method.resetChessPanel(chessPanel.table);
		assertEquals(0, chessPanel.table[0][0]);
		assertEquals(0, chessPanel.table[1][1]);
		assertEquals(0, chessPanel.table[2][2]);
	}

	@Test
	public void testisWin() {
		chessPanel.table[7][5] = Constant.BLACKCHESS;
		chessPanel.table[7][6] = Constant.BLACKCHESS;
		chessPanel.table[7][7] = Constant.BLACKCHESS;
		chessPanel.table[7][8] = Constant.BLACKCHESS;
		chessPanel.table[7][9] = Constant.BLACKCHESS;
		boolean b = method.isWin(7, 7,chessPanel.table);
		assertEquals(true, b);
		b = method.isWin(7, 5,chessPanel.table);
		assertEquals(true, b);
		b = method.isWin(7, 9,chessPanel.table);
		assertEquals(true, b);
		method.resetChessPanel(chessPanel.table);

		chessPanel.table[5][7] = Constant.BLACKCHESS;
		chessPanel.table[6][7] = Constant.BLACKCHESS;
		chessPanel.table[7][7] = Constant.BLACKCHESS;
		chessPanel.table[8][7] = Constant.BLACKCHESS;
		chessPanel.table[9][7] = Constant.BLACKCHESS;
		b = method.isWin(5, 7,chessPanel.table);
		assertEquals(true, b);
		b = method.isWin(7, 7,chessPanel.table);
		assertEquals(true, b);
		b = method.isWin(9, 7,chessPanel.table);
		assertEquals(true, b);
		method.resetChessPanel(chessPanel.table);

		chessPanel.table[5][5] = Constant.BLACKCHESS;
		chessPanel.table[6][6] = Constant.BLACKCHESS;
		chessPanel.table[7][7] = Constant.BLACKCHESS;
		chessPanel.table[8][8] = Constant.BLACKCHESS;
		chessPanel.table[9][9] = Constant.BLACKCHESS;
		b = method.isWin(5, 5,chessPanel.table);
		assertEquals(true, b);
		b = method.isWin(7, 7,chessPanel.table);
		assertEquals(true, b);
		b = method.isWin(9, 9,chessPanel.table);
		assertEquals(true, b);

		chessPanel.table[9][9] = Constant.WHITECHESS;
		b = method.isWin(7, 7,chessPanel.table);
		assertEquals(false, b);
		chessPanel.table[9][9] = 0;
		b = method.isWin(7, 7,chessPanel.table);
		assertEquals(false, b);
		method.resetChessPanel(chessPanel.table);

		chessPanel.table[5][9] = Constant.BLACKCHESS;
		chessPanel.table[6][8] = Constant.BLACKCHESS;
		chessPanel.table[7][7] = Constant.BLACKCHESS;
		chessPanel.table[8][6] = Constant.BLACKCHESS;
		chessPanel.table[9][5] = Constant.BLACKCHESS;
		b = method.isWin(5, 9,chessPanel.table);
		assertEquals(true, b);
		b = method.isWin(7, 7,chessPanel.table);
		assertEquals(true, b);
		b = method.isWin(9, 5,chessPanel.table);
		assertEquals(true, b);
		method.resetChessPanel(chessPanel.table);
	}

	@Test
	public void testcompare() {
		chessPanel.table[0][0] = Constant.BLACKCHESS;
		chessPanel.table[0][1] = Constant.BLACKCHESS;
		chessPanel.table[1][0] = Constant.WHITECHESS;
		boolean b = method.compare(0, 0, 0, -1,chessPanel.table);
		assertEquals(false, b);
		b = method.compare(0, 0, -1, 0,chessPanel.table);
		assertEquals(false, b);
		b = method.compare(0, 0, 1, 0,chessPanel.table);
		assertEquals(false, b);
		b = method.compare(0, 0, 0, 1,chessPanel.table);
		assertEquals(true, b);
		b = method.compare(0, 0, 1, 1,chessPanel.table);
		assertEquals(false, b);
	}

	@Test
	public void testpositionTransform() {
		int space = 40;
		int radius = 20;
		Point point = method.positionTransform(new Point(85, 85), space, radius);
		assertEquals(1, point.positionX);
		assertEquals(1, point.positionY);
		point = method.positionTransform(new Point(85, 65), space, radius);
		assertEquals(1, point.positionX);
		assertEquals(1, point.positionY);
		point = method.positionTransform(new Point(65, 85), space, radius);
		assertEquals(1, point.positionX);
		assertEquals(1, point.positionY);
		point = method.positionTransform(new Point(65, 65), space, radius);
		assertEquals(1, point.positionX);
		assertEquals(1, point.positionY);
		point = method.positionTransform(new Point(100, 100), space, radius);
		assertEquals(-1, point.positionX);
		assertEquals(-1, point.positionY);
	}
}