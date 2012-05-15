package com.succez.wangzr.myHttpServer;

import java.util.LinkedList;

public class ThreadPool extends ThreadGroup {
	/**
	 * 记录线程池是否关闭
	 */
	private boolean isClosed = false;
	/**
	 * 任务队列
	 */
	private LinkedList<Runnable> taskQueue;
	/**
	 * 线程池的ID
	 */
	private static int threadPoolID=1;
	/**
	 * 线程池的构造函数
	 * @param poolSize 线程池中线程的数量
	 */
	public ThreadPool(int poolSize){
		super(threadPoolID+"Pool");//指定线程池的名称
//		setDaemon(true);//设置为守护线程
		taskQueue=new LinkedList<Runnable>();//创建任务队列
		for(int i=0;i<poolSize;i++){
			new workThread(i).start();
		}
	}
	/**
	 * 向任务队列中加入一个新任务,由工作线程去执行该任务
	 * @param task 要添加的任务
	 */
	public synchronized void execute(Runnable task){
		if(isClosed){
			throw new IllegalStateException();
		}
		if(task!=null){
			taskQueue.add(task);//向工作队列中添加一个任务
			notify();//唤醒一个正在getTask()方法中等待的工作线程
		}
	}
	/**
	 * 从任务队列中取出一个任务，工作线程会调用此方法
	 * @param threadid 
	 * @return 
	 * @throws InterruptedException
	 */
	public synchronized Runnable getTask(int threadid)throws InterruptedException{
		while(taskQueue.size()==0){
			if(isClosed){
				return null;
			}
			System.out.println("工作线程"+threadid+"等待任务...");
			wait();//如何任务队列中没有任务，就等待任务
		}
		System.out.println("工作线程"+threadid+"开始执行任务...");
		return taskQueue.removeFirst();
	}
	/**
	 *关闭线程池
	 */
	public synchronized void closePool(){
		if(!isClosed){
			waitFinish();//等待工作线程执行完毕
			isClosed=true;
			taskQueue.clear();//清空任务队列
			interrupt();
		}
	}
	/**
	 * 等待工作线程把所有的任务都执行完成
	 */
	public void waitFinish(){
		synchronized (this) {
			isClosed=true;
			notifyAll();// 唤醒所有还在getTask()方法中等待任务的工作线程
		}
		Thread[] threads=new Thread[activeCount()];//activeCount()返回该线程池中活动线程的估计值
		int count=enumerate(threads);//获得添加到数组中的活动线程的实际数目
		for(int i=0;i<count;i++){
			try{
				threads[i].join();
			}catch(InterruptedException ex){
				ex.printStackTrace();
			}
		}
	}
	/**
	 * 内部类，工作线程，负责从任务队列中取出任务并执行
	 * <p>Copyright: Copyright (c) 2012<p>
	 * <p>succez<p>
	 * @author feihu
	 * @createdate 2012-5-15
	 */
	private class workThread extends Thread{
		private int id;
		public workThread(int id){
			super(ThreadPool.this,id+"thread");
			this.id=id;
		}
		public void run(){
			while(!isInterrupted()){
				Runnable task=null;
				try{
					task=getTask(id);
				}catch(InterruptedException ex){
					ex.printStackTrace();
				}
				if(task==null)return;
				try{
					task.run();
				}catch(Throwable t){
					t.printStackTrace();
				}
			}
		}
	}
	
}
