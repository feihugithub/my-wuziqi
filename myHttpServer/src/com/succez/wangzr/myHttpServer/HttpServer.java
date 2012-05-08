package com.succez.wangzr.myHttpServer;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
	/**
	 * WEB_ROOT是HTML和其他文件的存放目录，该目录在myHttpSever工程目录下
	 */
	public static final String WEB_ROOT = System.getProperty("user.dir") + File.separator + "webroot";

	/**
	 * 关闭服务器的命令
	 */
	private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";

	/**
	 * 记录是否有关闭服务器的命令
	 */
	private boolean shutdown = false;

	public static void main(String[] args) throws IOException {
		HttpServer myServer=new HttpServer();
		myServer.await();
	}
	public void await() throws IOException{
		ServerSocket serverSocket=null;
		int port=8080;
		try {
			 serverSocket=new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
		}catch(IOException e){
			e.printStackTrace();
			System.exit(1);
		}
		//循环，等待客户端发来信息
		while(!shutdown){
			Socket socket=null;
			InputStream input=null;
			OutputStream output=null;
			try{
				socket=serverSocket.accept();
				input=socket.getInputStream();
				output=socket.getOutputStream();
				//创建Request对象并予以解析
				Request request=new Request(input);
				request.prase();
				//创建Response对象
				Response response=new Response(output);
				response.setRequest(request);
				response.sendResource();
				//检查URI是否为关闭服务器的命令
				shutdown=SHUTDOWN_COMMAND.equals(request.getUri());
			}catch(IOException e){
				e.printStackTrace();
				continue;
			}
			finally {
				//关闭socket
				socket.close();
			}
		}
	}
}
