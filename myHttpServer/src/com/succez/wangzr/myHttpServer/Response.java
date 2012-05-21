package com.succez.wangzr.myHttpServer;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import com.succez.wangzr.wzqfornet.control.AiAction;

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
			if (uri.indexOf('!') != -1) {
				output.write(uri.getBytes());
			}
			else {
				File file = new File(HttpServer.WEB_ROOT, request.getUri());
				if (!file.exists()) {
					file = new File(HttpServer.WEB_ROOT, "index.html");
				}
				fiStream = new FileInputStream(file);
				int ch = fiStream.read(bytes, 0, BUFFER_SIZE);
				while (ch != -1) {
					output.write(bytes);
					ch = fiStream.read(bytes, 0, ch);
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
