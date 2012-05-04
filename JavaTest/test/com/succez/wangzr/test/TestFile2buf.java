package com.succez.wangzr.test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

public class TestFile2buf {

	@Test
	public void test() {
		byte[] byteinfo;
		File file1=new File("info.txt");
		File file2=new File("info1.txt");
		byteinfo=File2buf.file2buf(file1);
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
		byteinfo=File2buf.file2buf(file2);
		assertArrayEquals(null, byteinfo);
	}

}
