package com.succez.wangzr.wuziqi;

import junit.framework.TestCase;
import org.junit.Test;
import com.succez.wangzr.wuziqi.algorithm.Ai;
import com.succez.wangzr.wuziqi.tools.ChessStyle;
import com.succez.wangzr.wuziqi.tools.Constant;

/**
 * 测试Ai类的方法
 * <p>Copyright: Copyright (c) 2012<p>
 * <p>succez<p>
 * @author feihu
 * @createdate 2012-4-6
 */

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

		ai.table[0][0] = Constant.WHITECHESS;
		test = ai.getHorizChessStyle(1, 0);
		assertEquals(ChessStyle.SINGLE, test);
		ai.table[2][0] = Constant.BLACKCHESS;
		test = ai.getHorizChessStyle(1, 0);
		assertEquals(ChessStyle.DEAD_2, test);
		ai.table[3][0] = Constant.BLACKCHESS;
		test = ai.getHorizChessStyle(1, 0);
		assertEquals(ChessStyle.DEAD_3, test);
		ai.table[4][0] = Constant.BLACKCHESS;
		test = ai.getHorizChessStyle(1, 0);
		assertEquals(ChessStyle.DEAD_4, test);
	}

	@Test
	public void testpositionRate() {
		Ai ai = new Ai();
		int test = -1;
		ai.table[0][0] = Constant.BLACKCHESS;
		test = ai.positionRate(0, 0);
		assertEquals(ChessStyle.SINGLE, test);
		ai.table[1][0] = Constant.BLACKCHESS;
		test = ai.positionRate(0, 0);
		assertEquals(ChessStyle.DEAD_2, test);
		ai.table[2][0] = Constant.BLACKCHESS;
		test = ai.positionRate(0, 0);
		assertEquals(ChessStyle.DEAD_3, test);
		ai.table[3][0] = Constant.BLACKCHESS;
		test = ai.positionRate(0, 0);
		assertEquals(ChessStyle.DEAD_4, test);
		ai.table[4][0] = Constant.BLACKCHESS;
		test = ai.positionRate(0, 0);
		assertEquals(ChessStyle.SUCCESS_5, test);

		ai.table[0][0] = Constant.WHITECHESS;
		test = ai.positionRate(4, 0);
		assertEquals(ChessStyle.DEAD_4, test);

		ai.table[2][1] = Constant.BLACKCHESS;
		ai.table[3][2] = Constant.BLACKCHESS;
		ai.table[4][3] = Constant.BLACKCHESS;
		test = ai.positionRate(1, 0);
		assertEquals(ChessStyle.DOUBLE_DEAD_4 + 10, test);

		ai.table[4][4] = Constant.BLACKCHESS;
		ai.table[4][5] = Constant.BLACKCHESS;
		test = ai.positionRate(4, 3);
		assertEquals(ChessStyle.DEAD_4_LIVE_3 + 20, test);

		ai.table[1][0] = 0;
		test = ai.positionRate(4, 3);
		assertEquals(ChessStyle.DOUBLE_LIVE_3, test);

		ai.table[1][0] = Constant.WHITECHESS;
		test = ai.positionRate(4, 3);
		assertEquals(ChessStyle.DEAD_3_LIVE_3, test);

		ai.table[2][1] = 0;
		ai.table[4][5] = 0;
		test = ai.positionRate(4, 3);
		assertEquals(ChessStyle.DOUBLE_LIVE_2, test);
	}

	@Test
	public void testpanelRate() {
		Ai ai = new Ai();
		ai.table[7][7]=Constant.BLACKCHESS;
		ai.table[8][6]=Constant.BLACKCHESS;
		ai.table[7][8]=Constant.BLACKCHESS;
		ai.table[7][6]=Constant.BLACKCHESS;
		ai.table[6][7]=Constant.BLACKCHESS;
		ai.table[8][5]=Constant.BLACKCHESS;
		ai.table[6][6]=Constant.BLACKCHESS;
		ai.table[9][6]=Constant.BLACKCHESS;
		ai.table[9][7]=Constant.BLACKCHESS;
		ai.table[9][9]=Constant.BLACKCHESS;
		ai.table[10][9]=Constant.BLACKCHESS;		
		
		ai.table[8][8]=Constant.WHITECHESS;
		ai.table[6][8]=Constant.WHITECHESS;
		ai.table[7][9]=Constant.WHITECHESS;
		ai.table[7][5]=Constant.WHITECHESS;
		ai.table[6][9]=Constant.WHITECHESS;
//		ai.table[5][8]=Constant.WHITECHESS;
		ai.table[8][9]=Constant.WHITECHESS;
		ai.table[8][7]=Constant.WHITECHESS;
		ai.table[10][6]=Constant.WHITECHESS;
		ai.table[5][9]=Constant.WHITECHESS;
		ai.table[4][9]=Constant.WHITECHESS;
		
		int test = ai.panelRate(Constant.BLACKCHESS);
		assertEquals(ChessStyle.DEAD_4_LIVE_3+20, test);
		test=ai.panelRate(Constant.WHITECHESS);
		assertEquals(-ChessStyle.SUCCESS_5, test);
	    test=ai.maxRate();
	    assertEquals(Constant.LOSE, test);
	    ai.table[5][6]=Constant.BLACKCHESS;
	    test=ai.maxRate();
	    assertEquals(Constant.LOSE, test);
	    ai.table[4][9]=0;
	    test=ai.maxRate();
	    assertEquals(Constant.WIN, test);
	}
}
