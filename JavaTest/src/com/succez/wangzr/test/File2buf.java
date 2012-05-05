package com.succez.wangzr.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 实现了将文件内容转换成内存中的字节数组
 * <p>Copyright: Copyright (c) 2012<p>
 * <p>succez<p>
 * @author feihu
 * @createdate 2012-5-4
 */
public class File2buf {
	/**
	 * File提供了判断文件情况的很多方法，有它提供的方法可以知道给出的文件是否为空，是否存在，
	 * 是否只是一个目录。FileInputStream的方法read（byte b[], int off, int len）
	 * 返回值是读出的字节数，第三个参数的意思是最多读出len个字节。
	 * 
	 * @param fobj  要读的文件
	 * @return  转换成的数组
	 */
	public static byte[] file2buf(File fobj) {
		if (fobj == null || !fobj.exists() || fobj.isDirectory()) {
			return null;
		}
		try {
			FileInputStream fileStream = new FileInputStream(fobj);
			try {
				if (fobj.length() > Integer.MAX_VALUE) {
					throw new IOException("文件长度过长，无法读入内存");
				}
				int length = (int) fobj.length();
				byte[] byteInfo = new byte[length];
				int offset = 0;
				while (length > offset) {
					offset += fileStream.read(byteInfo, offset, length - offset);
				}
				return byteInfo;
			}
			finally {
				fileStream.close();
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return null;		
	}

	public static void main(String[] args) {
		byte[] byteinfo = File2buf.file2buf(new File("info.txt"));
		if (byteinfo != null) {
			int index = 0;
			while (index < byteinfo.length) {
				System.out.print(byteinfo[index++]);
				System.out.print(" ");
			}
			System.out.println();
			index = 0;
			while (index < byteinfo.length) {
				System.out.print((char) byteinfo[index++]);
				System.out.print(" ");
			}
		}
		else {
			System.out.println("文件不存在或者读入文件出错");
		}
	}
}
