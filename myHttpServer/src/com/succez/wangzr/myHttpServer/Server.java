package com.succez.wangzr.myHttpServer;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Server extends Thread {
	private Socket clientSocket;

	public Server(Socket clientSocket) throws Exception {
		this.clientSocket=clientSocket;
	}
	public void run(){
		try{
		InputStream input=clientSocket.getInputStream();
		OutputStream output=clientSocket.getOutputStream();
		//创建Request对象并予以解析
		Request request=new Request(input);
		request.prase();
		//创建Response对象
		Response response=new Response(output);
		response.setRequest(request);
		response.sendResource();
		//关闭socket
		clientSocket.close();
		
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
