package com.succez.wangzr.wuziqi;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import org.junit.Test;

import com.succez.wangzr.wuziqi.algorithm.ChessInfoIO;
import com.succez.wangzr.wuziqi.tools.InfoUnit;

public class TestChessInfoIO {

	@Test
	public void testChessInfoIO() throws IOException {
		InfoUnit[] info = new InfoUnit[225];
		File file = File.createTempFile("info", ".csv");

		int length = 0;
		Random r = new Random();
		while (length < 2) {
			int a=r.nextInt(15),b=r.nextInt(15);
			info[length] = new InfoUnit(a, b, 1);
			System.out.println(a+" "+b+" "+"1");
			length++;
		}
		try {
			ChessInfoIO.chessInfoWrite(file, info, length);
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int j = 0; j < 2; j++) {
			info[j] = null;
		}
		int i = 0;
		try {
			ChessInfoIO.chessInfoRead(file, info);
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			file.deleteOnExit();
		}
		while (i < 2) {
			System.out.print(info[i].point.positionX + " ");
			System.out.print(info[i].point.positionY + " ");
			System.out.print(info[i++].control);
			System.out.println();
		}
	}
}
