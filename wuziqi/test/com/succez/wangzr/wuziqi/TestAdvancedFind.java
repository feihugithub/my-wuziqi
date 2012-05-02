package com.succez.wangzr.wuziqi;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.succez.wangzr.wuziqi.algorithm.Ai;
import com.succez.wangzr.wuziqi.algorithm.ChessInfoIO;
import com.succez.wangzr.wuziqi.algorithm.ChessMethod;
import com.succez.wangzr.wuziqi.tools.Constant;
import com.succez.wangzr.wuziqi.tools.InfoUnit;
import com.succez.wangzr.wuziqi.tools.Point;

public class TestAdvancedFind {

	@Test
	public void testAdvancedFind() throws IOException {
		Logger Logger = LoggerFactory.getLogger(TestAdvancedFind.class);
		ChessMethod method = new ChessMethod(15);
		Ai ai = new Ai(15, new int[15][15]);
		InfoUnit[] info = new InfoUnit[225];
		int length = ChessInfoIO.chessInfoRead("test/com/succez/wangzr/wuziqi/advanced.csv", info);
		int index = 2;
		int winer = 0;
		if (info[0].control == Constant.ADVANCE) {
			ai.setable(info[1].point.positionX, info[1].point.positionY, info[1].control);
			while (index < length) {
				if (info[index].control == Constant.BLACKCHESS) {
					Point p = ai.advancedFind(3);
					ai.setable(p.positionX, p.positionY, Constant.BLACKCHESS);
					Logger.info("测试时ai找到的点是({},{})", p.positionX, p.positionY);
					Logger.info("记录中ai下的点是({},{})", info[index].point.positionX, info[index].point.positionY);
					method.setTable(ai.getTable());
					method.isWin(p.positionX, p.positionY);
					winer = Constant.BLACKCHESS;
				}
				else {
					ai.setable(info[index].point.positionX, info[index].point.positionY, info[index].control);
					Logger.info("记录中人下的点是({},{})", info[index].point.positionX, info[index].point.positionY);
					method.setTable(ai.getTable());
					method.isWin(info[index].point.positionX, info[index].point.positionY);
					winer = Constant.WHITECHESS;
				}
				index++;
			}

		}
		assertEquals(info[0].point.positionY, winer);
	}
}
