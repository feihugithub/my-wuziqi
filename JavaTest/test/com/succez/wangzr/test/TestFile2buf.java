package com.succez.wangzr.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.Test;

public class TestFile2buf {
	@Test
	public void test() {
		byte[] byteinfo;
		byte[] bytetemp = { 72, 101, 108, 108, 111, 32, 87, 111, 114, 108, 100, 33};
		byte[] bytetemp2 = { 'H', 'e', 'l', 'l', 'o', ' ', 'W', 'o', 'r', 'l', 'd', '!'};
		File file = null;
		byteinfo = File2buf.file2buf(file);
		assertArrayEquals(null, byteinfo);
		try {
			file = File.createTempFile("test", ".txt");
			FileOutputStream outStream=new FileOutputStream(file);
			outStream.write(bytetemp);
			outStream.flush();
			outStream.close();
			byteinfo = File2buf.file2buf(file);
			assertArrayEquals(bytetemp, byteinfo);
			FileOutputStream outStream2=new FileOutputStream(file);
			outStream2.write(bytetemp2);
			outStream2.flush();
			outStream2.close();
			byteinfo = File2buf.file2buf(file);
			assertArrayEquals(bytetemp2, byteinfo);
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			file.deleteOnExit();
		}
	}
}
