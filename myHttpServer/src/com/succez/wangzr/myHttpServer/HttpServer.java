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
	 * WEB_ROOT是D:\
	 */
	public static final String WEB_ROOT = System.getProperty("user.dir") + File.separator + "resource";
//	public static final String WEB_ROOT=new File("/").getAbsolutePath();

	/**
	 * 关闭服务器的命令
	 */
	private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";

	/**
	 * 记录是否有关闭服务器的命令
	 */
	private boolean shutdown = false;

	public static void main(String[] args) {
		HttpServer myServer = new HttpServer();
		myServer.await();
	}

	public void await() {
		ThreadPool threadPool = new ThreadPool(5);
		ServerSocket serverSocket = null;
		int port = 8080;
		try {
			serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
		}
		catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		//循环，等待客户端发来信息
		while (!shutdown) {
			Socket client;
			try {
				client = serverSocket.accept();
				threadPool.execute(createTask(client));
			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		threadPool.waitFinish();
		threadPool.closePool();
	}

	private Runnable createTask(final Socket client) {
		return new Runnable() {

			public void run() {
				// TODO Auto-generated method stub
				try {
					InputStream input = client.getInputStream();
					OutputStream output = client.getOutputStream();
					//创建Request对象并予以解析
					Request request = new Request(input);
					request.prase();
					//创建Response对象
					Response response = new Response(output);
					response.setRequest(request);
					response.sendResource();
					//关闭socket
					client.close();
					//检查URI是否为关闭服务器的命令
					shutdown = SHUTDOWN_COMMAND.equals(request.getUri());
				}
				catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		};
	}
}
