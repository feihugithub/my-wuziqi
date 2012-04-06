package com.succez.wangzr.wuziqi;

import junit.framework.TestCase;

import org.junit.Test;

import com.succez.wangzr.wuziqi.algorithm.Ai;
import com.succez.wangzr.wuziqi.tools.ChessStyle;
import com.succez.wangzr.wuziqi.tools.Constant;

public class TestAi extends TestCase {

	@Test
	public void testcompare() {
		Ai ai = new Ai();
		ai.table[0][0] = Constant.BLACKCHESS;
		ai.table[1][1] = Constant.BLACKCHESS;
		ai.table[0][1] = Constant.WHITECHESS;
		ai.table[1][0] = Constant.BLACKCHESS;

		assertEquals(Constant.CROSSBORDER, ai.compare(0, 0, -1, 0));
		assertEquals(Constant.CROSSBORDER, ai.compare(0, 0, 0, -1));
		assertEquals(Constant.CROSSBORDER, ai.compare(0, 0, -1, -1));
		assertEquals(Constant.CROSSBORDER, ai.compare(0, 0, 1, -1));
		assertEquals(Constant.CROSSBORDER, ai.compare(0, 0, -1, 1));

		assertEquals(Constant.DIFFERENT, ai.compare(0, 0, 3, 3));
		assertEquals(Constant.DIFFERENT, ai.compare(0, 0, 0, 1));

		assertEquals(Constant.SAME, ai.compare(0, 0, 1, 1));
		assertEquals(Constant.SAME, ai.compare(0, 0, 1, 0));
	}

	@Test
	public void testanalysisChessStyle() {
		Ai ai = new Ai();
		int test = -1;
		test = ai.analysisChessStyle(0, Constant.EMPTY, Constant.EMPTY);
		assertEquals(ChessStyle.SINGLE, test);
		test = ai.analysisChessStyle(0, Constant.CROWD, Constant.CROWD);
		assertEquals(ChessStyle.SINGLE, test);
		test = ai.analysisChessStyle(0, Constant.CROWD, Constant.EMPTY);
		assertEquals(ChessStyle.SINGLE, test);
		test = ai.analysisChessStyle(0, Constant.EMPTY, Constant.CROWD);
		assertEquals(ChessStyle.SINGLE, test);

		test = ai.analysisChessStyle(4, Constant.EMPTY, Constant.EMPTY);
		assertEquals(ChessStyle.SUCCESS_5, test);
		test = ai.analysisChessStyle(4, Constant.CROWD, Constant.EMPTY);
		assertEquals(ChessStyle.SUCCESS_5, test);
		test = ai.analysisChessStyle(4, Constant.EMPTY, Constant.CROWD);
		assertEquals(ChessStyle.SUCCESS_5, test);
		test = ai.analysisChessStyle(4, Constant.CROWD, Constant.CROWD);
		assertEquals(ChessStyle.SUCCESS_5, test);

		test = ai.analysisChessStyle(3, Constant.EMPTY, Constant.EMPTY);
		assertEquals(ChessStyle.LIVE_4, test);
		test = ai.analysisChessStyle(3, Constant.EMPTY, Constant.CROWD);
		assertEquals(ChessStyle.DEAD_4, test);
		test = ai.analysisChessStyle(3, Constant.CROWD, Constant.EMPTY);
		assertEquals(ChessStyle.DEAD_4, test);
		test = ai.analysisChessStyle(3, Constant.CROWD, Constant.CROWD);
		assertEquals(ChessStyle.SINGLE, test);

		test = ai.analysisChessStyle(2, Constant.EMPTY, Constant.EMPTY);
		assertEquals(ChessStyle.LIVE_3, test);
		test = ai.analysisChessStyle(2, Constant.EMPTY, Constant.CROWD);
		assertEquals(ChessStyle.DEAD_3, test);
		test = ai.analysisChessStyle(2, Constant.CROWD, Constant.EMPTY);
		assertEquals(ChessStyle.DEAD_3, test);
		test = ai.analysisChessStyle(2, Constant.CROWD, Constant.CROWD);
		assertEquals(ChessStyle.SINGLE, test);

		test = ai.analysisChessStyle(1, Constant.EMPTY, Constant.EMPTY);
		assertEquals(ChessStyle.LIVE_2, test);
		test = ai.analysisChessStyle(1, Constant.EMPTY, Constant.CROWD);
		assertEquals(ChessStyle.DEAD_2, test);
		test = ai.analysisChessStyle(1, Constant.CROWD, Constant.EMPTY);
		assertEquals(ChessStyle.DEAD_2, test);
		test = ai.analysisChessStyle(1, Constant.CROWD, Constant.CROWD);
		assertEquals(ChessStyle.SINGLE, test);
	}

	@Test
	public void testgetHorizChessStyle() {
		Ai ai = new Ai();
		int test = -1;
		ai.table[0][0] = Constant.BLACKCHESS;
		test = ai.getHorizChessStyle(0, 0);
		assertEquals(ChessStyle.SINGLE, test);
		ai.table[1][0] = Constant.BLACKCHESS;
		test = ai.getHorizChessStyle(0, 0);
		assertEquals(ChessStyle.DEAD_2, test);
		ai.table[2][0] = Constant.BLACKCHESS;
		test = ai.getHorizChessStyle(0, 0);
		assertEquals(ChessStyle.DEAD_3, test);
		ai.table[3][0] = Constant.BLACKCHESS;
		test = ai.getHorizChessStyle(0, 0);
		assertEquals(ChessStyle.DEAD_4, test);
		ai.table[4][0] = Constant.BLACKCHESS;
		test = ai.getHorizChessStyle(0, 0);
		assertEquals(ChessStyle.SUCCESS_5, test);
		ai.table[0][0] = 0;
		test = ai.getHorizChessStyle(1, 0);
		assertEquals(ChessStyle.LIVE_4, test);
		ai.table[4][0] = 0;
		test = ai.getHorizChessStyle(1, 0);
		assertEquals(ChessStyle.LIVE_3, test);
		ai.table[3][0] = 0;
		test = ai.getHorizChessStyle(1, 0);
		assertEquals(ChessStyle.LIVE_2, test);
		ai.table[2][0] = 0;
		test = ai.getHorizChessStyle(1, 0);
		assertEquals(ChessStyle.SINGLE, test);
	}


}
