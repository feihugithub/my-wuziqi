package com.succez.wangzr.myHttpServer;

import java.io.IOException;
import java.io.InputStream;

public class Request {
	/**
	 * 标记get方法
	 */
	private static final String GET = "GET";

	private InputStream input;

	private String uri;

	public Request(InputStream input) {
		this.input = input;
	}

	/**
	 * 分析http请求报文，获取URI
	 * @throws Exception 
	 */
	public void prase() throws Exception {
		int bufferSize = 2048;
		StringBuffer requestString = new StringBuffer(bufferSize);
		int requestLenght;
		byte[] buffer = new byte[bufferSize];
		try {
			requestLenght = input.read(buffer);
		}
		catch (IOException e) {
			e.printStackTrace();
			requestLenght = -1;
		}
		for (int i = 0; i < requestLenght; i++) {
			requestString.append((char) buffer[i]);
		}
		System.out.println(requestString.toString());
		uri = praseUri(requestString.toString());
		System.out.println("获得uri是："+uri);
	}

	/**
	 * 根据http请求报文字符串分析URI
	 * @param requestString Http请求报文
	 * @return  分析出来的URI
	 * @throws Exception 
	 */
	private String praseUri(String requestString) throws Exception {
		int index1, index2;
		index1 = requestString.indexOf(' ');
		if (index1 != -1) {
			if (GET.equals(requestString.substring(0, index1))) {
				index2 = requestString.indexOf(' ', index1 + 1);
				if (index2 > index1) {
					return requestString.substring(index1 + 1, index2);
				}
			}else{
				throw new Exception();
			}
		}
		return "index.html";
	}

	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
}
