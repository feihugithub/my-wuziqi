package com.succez.wangzr.wuziqi.algorithm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

import com.succez.wangzr.wuziqi.tools.InfoUnit;

/**
 * 
 * <p>Copyright: Copyright (c) 2012<p>
 * <p>succez<p>
 * @author feihu
 * @createdate 2012-4-27
 */
public class ChessInfoIO {
	/**
	 * 实现将棋盘信息写入到文件中
	 * @param file   要写入的文件名
	 * @throws IOException 
	 */
	public static int chessInfoRead(File file, InfoUnit[] info) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(file));
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

	/**
	 * 实现将文件中的棋局信息读出
	 * @param file   信息从其指定的文件名出读出
	 * @throws IOException 
	 */
	public static void chessInfoWrite(File file, InfoUnit[] info,int length) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		int index=0;
		int[] temp=new int[3];
		temp[0]=info[index].point.positionX;
		temp[1]=info[index].point.positionY;
		temp[2]=info[index].control;
		index++;
		bw.write(Integer.toString(temp[0])+","+ Integer.toString(temp[1])+","+ Integer.toString(temp[2]));
		while(index<length){
			bw.newLine();
			temp[0]=info[index].point.positionX;
			temp[1]=info[index].point.positionY;
			temp[2]=info[index].control;
			index++;
			bw.write(Integer.toString(temp[0])+","+ Integer.toString(temp[1])+","+ Integer.toString(temp[2]));
		}
		bw.close();
	}
}
