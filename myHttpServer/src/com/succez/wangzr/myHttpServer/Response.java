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
			File file = new File(HttpServer.WEB_ROOT, request.getUri());
			if (file.exists()) {
				fiStream = new FileInputStream(file);
				int ch = fiStream.read(bytes, 0, BUFFER_SIZE);
				while (ch != -1) {
					output.write(bytes);
					ch=fiStream.read(bytes, 0, ch);
				}
				output.flush();
			}
			else {
				String errorMessage = "<h1>404 File Not Found</h1>";
				output.write(errorMessage.getBytes());
			}
			if (fiStream != null) {
				fiStream.close();
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
