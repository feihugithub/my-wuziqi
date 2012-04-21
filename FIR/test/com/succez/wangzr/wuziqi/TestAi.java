package com.succez.wangzr.wuziqi;

import org.junit.Test;

import junit.framework.TestCase;

import com.succez.wangzr.wuziqi.algorithm.Ai;
import com.succez.wangzr.wuziqi.tools.ChessStyle;
import com.succez.wangzr.wuziqi.tools.Constant;
import com.succez.wangzr.wuziqi.ui.ChessPanel;

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
		ChessPanel chessPanel=new ChessPanel();
		Ai ai=new Ai();
		chessPanel.table[0][0] = Constant.BLACKCHESS;
		chessPanel.table[1][1] = Constant.BLACKCHESS;
		chessPanel.table[0][1] = Constant.WHITECHESS;
		chessPanel.table[1][0] = Constant.BLACKCHESS;

		assertEquals(Constant.CROSSBORDER, ai.compare(0, 0, -1, 0,chessPanel.table));
		assertEquals(Constant.CROSSBORDER, ai.compare(0, 0, 0, -1,chessPanel.table));
		assertEquals(Constant.CROSSBORDER, ai.compare(0, 0, -1, -1,chessPanel.table));
		assertEquals(Constant.CROSSBORDER, ai.compare(0, 0, 1, -1,chessPanel.table));
		assertEquals(Constant.CROSSBORDER, ai.compare(0, 0, -1, 1,chessPanel.table));

		assertEquals(Constant.DIFFERENT, ai.compare(0, 0, 3, 3,chessPanel.table));
		assertEquals(Constant.DIFFERENT, ai.compare(0, 0, 0, 1,chessPanel.table));

		assertEquals(Constant.SAME, ai.compare(0, 0, 1, 1,chessPanel.table));
		assertEquals(Constant.SAME, ai.compare(0, 0, 1, 0,chessPanel.table));
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
		ChessPanel chessPanel=new ChessPanel();
		Ai ai = new Ai();
		int test = -1;
		chessPanel.table[0][0] = Constant.BLACKCHESS;
		test = ai.getHorizChessStyle(0, 0,chessPanel.table);
		assertEquals(ChessStyle.SINGLE, test);
		chessPanel.table[1][0] = Constant.BLACKCHESS;
		test = ai.getHorizChessStyle(0, 0,chessPanel.table);
		assertEquals(ChessStyle.DEAD_2, test);
		chessPanel.table[2][0] = Constant.BLACKCHESS;
		test = ai.getHorizChessStyle(0, 0,chessPanel.table);
		assertEquals(ChessStyle.DEAD_3, test);
		chessPanel.table[3][0] = Constant.BLACKCHESS;
		test = ai.getHorizChessStyle(0, 0,chessPanel.table);
		assertEquals(ChessStyle.DEAD_4, test);
		chessPanel.table[4][0] = Constant.BLACKCHESS;
		test = ai.getHorizChessStyle(0, 0,chessPanel.table);
		assertEquals(ChessStyle.SUCCESS_5, test);
		chessPanel.table[0][0] = 0;
		test = ai.getHorizChessStyle(1, 0,chessPanel.table);
		assertEquals(ChessStyle.LIVE_4, test);
		chessPanel.table[4][0] = 0;
		test = ai.getHorizChessStyle(1, 0,chessPanel.table);
		assertEquals(ChessStyle.LIVE_3, test);
		chessPanel.table[3][0] = 0;
		test = ai.getHorizChessStyle(1, 0,chessPanel.table);
		assertEquals(ChessStyle.LIVE_2, test);
		chessPanel.table[2][0] = 0;
		test = ai.getHorizChessStyle(1, 0,chessPanel.table);
		assertEquals(ChessStyle.SINGLE, test);

		chessPanel.table[0][0] = Constant.WHITECHESS;
		test = ai.getHorizChessStyle(1, 0,chessPanel.table);
		assertEquals(ChessStyle.SINGLE, test);
		chessPanel.table[2][0] = Constant.BLACKCHESS;
		test = ai.getHorizChessStyle(1, 0,chessPanel.table);
		assertEquals(ChessStyle.DEAD_2, test);
		chessPanel.table[3][0] = Constant.BLACKCHESS;
		test = ai.getHorizChessStyle(1, 0,chessPanel.table);
		assertEquals(ChessStyle.DEAD_3, test);
		chessPanel.table[4][0] = Constant.BLACKCHESS;
		test = ai.getHorizChessStyle(1, 0,chessPanel.table);
		assertEquals(ChessStyle.DEAD_4, test);
	}

	@Test
	public void testpositionRate() {
		ChessPanel chessPanel=new ChessPanel();
		Ai ai = new Ai();
		int test = -1;
		chessPanel.table[0][0] = Constant.BLACKCHESS;
		test = ai.positionRate(0, 0,chessPanel.table);
		assertEquals(ChessStyle.SINGLE, test);
		chessPanel.table[1][0] = Constant.BLACKCHESS;
		test = ai.positionRate(0, 0,chessPanel.table);
		assertEquals(ChessStyle.DEAD_2, test);
		chessPanel.table[2][0] = Constant.BLACKCHESS;
		test = ai.positionRate(0, 0,chessPanel.table);
		assertEquals(ChessStyle.DEAD_3, test);
		chessPanel.table[3][0] = Constant.BLACKCHESS;
		test = ai.positionRate(0, 0,chessPanel.table);
		assertEquals(ChessStyle.DEAD_4, test);
		chessPanel.table[4][0] = Constant.BLACKCHESS;
		test = ai.positionRate(0, 0,chessPanel.table);
		assertEquals(ChessStyle.SUCCESS_5, test);

		chessPanel.table[0][0] = Constant.WHITECHESS;
		test = ai.positionRate(4, 0,chessPanel.table);
		assertEquals(ChessStyle.DEAD_4, test);

		chessPanel.table[2][1] = Constant.BLACKCHESS;
		chessPanel.table[3][2] = Constant.BLACKCHESS;
		chessPanel.table[4][3] = Constant.BLACKCHESS;
		test = ai.positionRate(1, 0,chessPanel.table);
		assertEquals(ChessStyle.DOUBLE_DEAD_4 + 10, test);

		chessPanel.table[4][4] = Constant.BLACKCHESS;
		chessPanel.table[4][5] = Constant.BLACKCHESS;
		test = ai.positionRate(4, 3,chessPanel.table);
		assertEquals(ChessStyle.DEAD_4_LIVE_3 + 20, test);

		chessPanel.table[1][0] = 0;
		test = ai.positionRate(4, 3,chessPanel.table);
		assertEquals(ChessStyle.DOUBLE_LIVE_3, test);

		chessPanel.table[1][0] = Constant.WHITECHESS;
		test = ai.positionRate(4, 3,chessPanel.table);
		assertEquals(ChessStyle.DEAD_3_LIVE_3, test);

		chessPanel.table[2][1] = 0;
		chessPanel.table[4][5] = 0;
		test = ai.positionRate(4, 3,chessPanel.table);
		assertEquals(ChessStyle.DOUBLE_LIVE_2, test);
	}

	@Test
	public void testpanelRate() {
		ChessPanel chessPanel=new ChessPanel();
		Ai ai = new Ai();
		chessPanel.table[7][7] = Constant.BLACKCHESS;
		chessPanel.table[8][6] = Constant.BLACKCHESS;
		chessPanel.table[7][8] = Constant.BLACKCHESS;
		chessPanel.table[7][6] = Constant.BLACKCHESS;
		chessPanel.table[6][7] = Constant.BLACKCHESS;
		chessPanel.table[8][5] = Constant.BLACKCHESS;
		chessPanel.table[6][6] = Constant.BLACKCHESS;
		chessPanel.table[9][6] = Constant.BLACKCHESS;
		chessPanel.table[9][7] = Constant.BLACKCHESS;
		chessPanel.table[9][9] = Constant.BLACKCHESS;
		chessPanel.table[10][9] = Constant.BLACKCHESS;

		chessPanel.table[8][8] = Constant.WHITECHESS;
		chessPanel.table[6][8] = Constant.WHITECHESS;
		chessPanel.table[7][9] = Constant.WHITECHESS;
		chessPanel.table[7][5] = Constant.WHITECHESS;
		chessPanel.table[6][9] = Constant.WHITECHESS;
		//		chessPanel.table[5][8]=Constant.WHITECHESS;
		chessPanel.table[8][9] = Constant.WHITECHESS;
		chessPanel.table[8][7] = Constant.WHITECHESS;
		chessPanel.table[10][6] = Constant.WHITECHESS;
		chessPanel.table[5][9] = Constant.WHITECHESS;
		chessPanel.table[4][9] = Constant.WHITECHESS;

		int test = ai.panelRate(Constant.BLACKCHESS,chessPanel.table);
		assertEquals(ChessStyle.DEAD_4_LIVE_3 + 20, test);
		test = ai.panelRate(Constant.WHITECHESS,chessPanel.table);
		assertEquals(-ChessStyle.SUCCESS_5, test);
		test = ai.maxRate(chessPanel.table);
		assertEquals(Constant.LOSE, test);
		chessPanel.table[5][6] = Constant.BLACKCHESS;
		test = ai.maxRate(chessPanel.table);
		assertEquals(Constant.LOSE, test);
		chessPanel.table[4][9] = 0;
		test = ai.maxRate(chessPanel.table);
		assertEquals(Constant.WIN, test);
	}

//	@Test
//	public void testDownsizing() {
//		Ai ai = new Ai();
//		Point[] array = { new Point(0, 0), new Point(0, 0), new Point(0, 0), new Point(0, 0), new Point(0, 0),
//				new Point(0, 0), new Point(0, 0), new Point(0, 0), new Point(0, 0), new Point(0, 0), };
//		ai.table[0][1] = Constant.BLACKCHESS;
//		ai.table[0][2] = Constant.BLACKCHESS;
//		ai.table[0][3] = Constant.BLACKCHESS;		
//		ai.table[1][2] = Constant.BLACKCHESS;
//		ai.table[1][3] = Constant.BLACKCHESS;
//		ai.table[1][4] = Constant.BLACKCHESS;
//		Point p=ai.downsizing(array);
//		System.out.println(p.rateValue);
//		System.out.println(p.positionX);
//		System.out.println(p.positionY);
//		p = array[0];
//		System.out.println(p.rateValue);
//		System.out.println(p.positionX);
//		System.out.println(p.positionY);
//		p = array[1];
//		System.out.println(p.rateValue);
//		System.out.println(p.positionX);
//		System.out.println(p.positionY);
//		p = array[2];
//		System.out.println(p.rateValue);
//		System.out.println(p.positionX);
//		System.out.println(p.positionY);
//		p = array[3];
//		System.out.println(p.rateValue);
//		System.out.println(p.positionX);
//		System.out.println(p.positionY);
//		p = array[4];
//		System.out.println(p.rateValue);
//		System.out.println(p.positionX);
//		System.out.println(p.positionY);
//		p = array[5];
//		System.out.println(p.rateValue);
//		System.out.println(p.positionX);
//		System.out.println(p.positionY);
//		p = array[6];
//		System.out.println(p.rateValue);
//		System.out.println(p.positionX);
//		System.out.println(p.positionY);
//		p = array[7];
//		System.out.println(p.rateValue);
//		System.out.println(p.positionX);
//		System.out.println(p.positionY);
//		p = array[8];
//		System.out.println(p.rateValue);
//		System.out.println(p.positionX);
//		System.out.println(p.positionY);
//		p = array[9];
//		System.out.println(p.rateValue);
//		System.out.println(p.positionX);
//		System.out.println(p.positionY);
//		
//		
//		ai.table[0][4] = Constant.BLACKCHESS;
//		p=ai.downsizing(array);
//		System.out.println(p.rateValue);
//		System.out.println(p.positionX);
//		System.out.println(p.positionY);
//		p = array[0];
//		System.out.println(p.rateValue);
//		System.out.println(p.positionX);
//		System.out.println(p.positionY);
//		p = array[1];
//		System.out.println(p.rateValue);
//		System.out.println(p.positionX);
//		System.out.println(p.positionY);
//		p = array[2];
//		System.out.println(p.rateValue);
//		System.out.println(p.positionX);
//		System.out.println(p.positionY);
//		p = array[3];
//		System.out.println(p.rateValue);
//		System.out.println(p.positionX);
//		System.out.println(p.positionY);
//		p = array[4];
//		System.out.println(p.rateValue);
//		System.out.println(p.positionX);
//		System.out.println(p.positionY);
//		p = array[5];
//		System.out.println(p.rateValue);
//		System.out.println(p.positionX);
//		System.out.println(p.positionY);
//		p = array[6];
//		System.out.println(p.rateValue);
//		System.out.println(p.positionX);
//		System.out.println(p.positionY);
//		p = array[7];
//		System.out.println(p.rateValue);
//		System.out.println(p.positionX);
//		System.out.println(p.positionY);
//		p = array[8];
//		System.out.println(p.rateValue);
//		System.out.println(p.positionX);
//		System.out.println(p.positionY);
//		p = array[9];
//		System.out.println(p.rateValue);
//		System.out.println(p.positionX);
//		System.out.println(p.positionY);
//	}
}
