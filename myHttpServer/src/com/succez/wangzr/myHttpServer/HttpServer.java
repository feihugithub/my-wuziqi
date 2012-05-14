package com.succez.wangzr.myHttpServer;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
public class HttpServer {
	/**
	 * WEB_ROOT是HTML和其他文件的存放目录，该目录在myHttpSever工程目录下
	 */
	public static final String WEB_ROOT = System.getProperty("user.dir") + File.separator + "resource";

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

			try {
				Server server = new Server(serverSocket.accept());
				server.start();
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
