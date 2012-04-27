package com.succez.wangzr.wuziqi;

import java.io.IOException;
import java.util.Random;

import org.junit.Test;

import com.succez.wangzr.wuziqi.algorithm.ChessInfoIO;
import com.succez.wangzr.wuziqi.tools.InfoUnit;

public class TestChessInfoIO {
	private static InfoUnit[] info = new InfoUnit[225];
	@Test
	public void testChessInfoRead() throws IOException {
		int i = 0;
		ChessInfoIO.chessInfoRead("info.csv", info);
		while (i < 2) {
			System.out.print(info[i].point.positionX+" ");
			System.out.print(info[i].point.positionY+" ");
			System.out.print(info[i++].control);
			System.out.println();
		}
	}
	@Test
	public void testChessInfoWrite() throws IOException {
		String file = "info.csv";
		int length = 0;
		Random r = new Random();
		while (length < 2) {
			info[length] = new InfoUnit(r.nextInt(15), r.nextInt(15), 1);
			length++;
		}
		ChessInfoIO.chessInfoWrite(file, info, length);
	}
}
