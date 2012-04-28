package com.succez.wangzr.wuziqi;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import com.succez.wangzr.wuziqi.algorithm.ChessMethod;
import com.succez.wangzr.wuziqi.tools.Constant;
import com.succez.wangzr.wuziqi.tools.Point;

/**
 * ChessMethod测试类
 * <p>Copyright: Copyright (c) 2012<p>
 * <p>succez<p>
 * @author feihu
 * @createdate 2012-4-9
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class TestChessMethod {

	private ChessMethod method = new ChessMethod(15);

	@Before
	public void init() {
		method.resetChessPanel();
		method.setOwner(Constant.WHITECHESS);
		method.setable(7, 7, Constant.BLACKCHESS);
	}

	@Test
	public void testPrimaryFind() throws IOException {
		Point[] testable = { new Point(6, 8), new Point(8, 8), new Point(7, 9), new Point(9, 7), new Point(8, 6),
				new Point(10, 8), new Point(8, 4), new Point(3, 9), new Point(7, 4), new Point(8, 7), new Point(8, 9),
				new Point(9, 9), new Point(9, 8), new Point(10, 7), new Point(9, 10), new Point(11, 6),
				new Point(11, 7), new Point(10, 9), new Point(9, 4), new Point(4, 9), new Point(3, 7) };
		int i = 0;
		while (method.getWiner() == 0 && i <= testable.length) {
			if (method.getOwner() == Constant.WHITECHESS) {
				method.setable(testable[i].positionX, testable[i].positionY, Constant.WHITECHESS);
				i++;
				method.setOwner(-method.getOwner());
			}
			else {
				method.pcPlay();
			}
		}
		assertEquals(Constant.BLACKCHESS, method.getWiner());
		assertEquals(testable.length, i);
	}

	@Test
	public void testAdvancedFind() throws IOException {
		Point[] testable = { new Point(6, 8), new Point(6, 7), new Point(7, 6), new Point(7, 5), new Point(8, 8),
				new Point(3, 9), new Point(5, 6), new Point(4, 6), new Point(4, 4), new Point(6, 5), new Point(3, 4),
				new Point(6, 3), new Point(1, 5), new Point(6, 9) };
		method.setAiLevel(Constant.ADVANCE);
		int i = 0;
		while (method.getWiner() == 0 && i <= testable.length) {
			if (method.getOwner() == Constant.WHITECHESS) {
				method.setable(testable[i].positionX, testable[i].positionY, Constant.WHITECHESS);
				i++;
				method.setOwner(-method.getOwner());
			}
			else {
				method.pcPlay();
			}
		}
		assertEquals(Constant.BLACKCHESS, method.getWiner());
		assertEquals(testable.length, i);
	}
}