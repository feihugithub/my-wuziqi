package com.succez.wangzr.wuziqi;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.succez.wangzr.wuziqi.algorithm.Ai;
import com.succez.wangzr.wuziqi.algorithm.ChessMethod;
import com.succez.wangzr.wuziqi.tools.Constant;
import com.succez.wangzr.wuziqi.tools.InfoUnit;
import com.succez.wangzr.wuziqi.tools.Point;

public class TestPrimaryFind {

	public static int chessInfoRead(String file, InfoUnit[] info) throws IOException {
		InputStream in=TestPrimaryFind.class.getResourceAsStream(file);
		InputStreamReader inReader=new InputStreamReader(in);
		BufferedReader br = new BufferedReader(inReader);
		int length = 0;
		String line = null;
		while ((line = br.readLine()) != null) {
			StringTokenizer st = new StringTokenizer(line,",");
			int[] temp = new int[3];
			int i = 0;
			while (st.hasMoreTokens()) {
				temp[i++] = Integer.parseInt(st.nextToken());
			}
			info[length++] = new InfoUnit(temp[0], temp[1], temp[2]);
		}
		br.close();
		return length;
	}
	@Test
	public void testPrimaryFind() throws IOException {
		Logger Logger = LoggerFactory.getLogger(TestPrimaryFind.class);
		ChessMethod method = new ChessMethod(15);
		Ai ai = new Ai(15, new int[15][15]);
		InfoUnit[] info = new InfoUnit[225];
		int length = TestPrimaryFind.chessInfoRead("primary.csv", info);
		int index = 2;
		int winer = 0;
		if (info[0].control == Constant.PRIMARY) {
			ai.setable(info[1].point.positionX, info[1].point.positionY, info[1].control);
			while (index < length) {
				if (info[index].control == Constant.BLACKCHESS) {
					Point p = ai.primaryFind();
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
