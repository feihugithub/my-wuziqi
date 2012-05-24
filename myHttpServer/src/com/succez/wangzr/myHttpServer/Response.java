package com.succez.wangzr.myHttpServer;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

public class Response {
	private static final int BUFFER_SIZE = 1024;

	private Request request;

	private OutputStream output;

	public Response(OutputStream output) {
		this.output = output;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public void sendResource() {
		byte[] bytes = new byte[BUFFER_SIZE];
		FileInputStream fiStream = null;
		try {
			String uri=request.getUri();
			int index=uri.indexOf('!');
			if (index!= -1) {
				output.write(uri.substring(index+1).getBytes());
				System.out.println("返回的信息是:"+uri.substring(index+1));
			}
			else {
				File file = new File(HttpServer.WEB_ROOT, request.getUri());
				if (!file.exists()) {
					file = new File(HttpServer.WEB_ROOT, "index.html");
				}
				fiStream = new FileInputStream(file);
				int ch = fiStream.read(bytes, 0, BUFFER_SIZE);
				while (ch != -1) {
					output.write(bytes,0,ch);
					ch = fiStream.read(bytes, 0, BUFFER_SIZE);
				}
				output.flush();
				if (fiStream != null) {
					fiStream.close();
				}
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
