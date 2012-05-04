package com.succez.wangzr.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * 实现了将文件内容转换成内存中的字节数组
 * <p>Copyright: Copyright (c) 2012<p>
 * <p>succez<p>
 * @author feihu
 * @createdate 2012-5-4
 */
public class File2buf {
	/**
	 * 采用了InputStream，因为它的read方法可以读出字节，实际上它的read方法可以直接将文件内容读入字节数组，
	 * 但是由于该方法要指定数组要读的内容的长度，但是文件的长度我们是不知道的，所以就没有了意义，因此我就new了
	 * 一个空间较大的临时字节数组，读取文件至其尾，并记录文件的长度，然后再new一个此长度的自己数组，将临时数组
	 * 中的内容全部复制到新建的数组中，然后返回
	 * @param fobj  要读的文件
	 * @return  转换成的数组
	 */
	public static byte[] file2buf(File fobj) {
		byte[] byteTemp = new byte[1000];
		int length = 0;
		InputStream inStream = null;
		try {
			inStream = new FileInputStream(fobj);
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			return null;
		}

		try {
			while ((byteTemp[length++] = (byte) inStream.read()) != -1) {
			}
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			return null;
		}
		try {
			inStream.close();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] byteInfo = new byte[length - 1];
		System.arraycopy(byteTemp, 0, byteInfo, 0, length - 1);
		return byteInfo;
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
