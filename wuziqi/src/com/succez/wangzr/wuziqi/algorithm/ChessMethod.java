﻿package com.succez.wangzr.wuziqi.algorithm;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import com.succez.wangzr.wuziqi.tools.Constant;
import com.succez.wangzr.wuziqi.tools.Point;

/**
*控制棋盘下子以及判输赢的算法
 * <p>Copyright: Copyright (c) 2012<p>
 * <p>succez<p>
 * @author feihu
 * @createdate 2012-4-1
 */
public class ChessMethod {
	/**
	 * 用来记录棋局方法类的日志信息
	 */
	private final static Logger methodLogger = LoggerFactory.getLogger(ChessMethod.class);

	/**棋盘大小*/
	private int row;

	/**标记胜利者*/
	private int winer = 0;

	/**双人对战时下棋的控制权*/
	private int owner = Constant.BLACKCHESS;

	/**选择人机对战还是双人对战*/
	private int gameMode = Constant.PTOP;

	/**控制Ai的级别*/
	private int aiLevel = Constant.PRIMARY;

	/**
	 * 记录棋盘的信息
	 */
	private int[][] table;

	/**控制下棋过程的控制权*/
	private int exclude = Constant.PEOPLEOWN;

	public ChessMethod(int row) {
		this.row = row;
		table = new int[row][row];
	}

	/**
	 * 获取棋盘信息
	 * @return
	 */
	public int getable(int positionX, int positionY) {
		return table[positionX][positionY];
	}

	/**
	 * 
	 * @param positionX 待设置的点的横坐标
	 * @param positionY 待设置的点的纵坐标
	 * @param color     待设置的棋子信息
	 */
	public void setable(int positionX, int positionY, int color) {
		table[positionX][positionY] = color;
	}

	public int getRow() {
		return row;
	}

	public int getAiLevel() {
		return aiLevel;
	}

	public int getOwner() {
		return owner;
	}

	public int getWiner() {
		return winer;
	}

	public int getGameMode() {
		return gameMode;
	}

	public void setAiLevel(int aiLevel) {
		this.aiLevel = aiLevel;
	}

	public void setOwner(int owner) {
		this.owner = owner;
	}

	public void setWiner(int winer) {
		this.winer = winer;
	}

	public void setGameMode(int gameMode) {
		this.gameMode = gameMode;
	}

	public int getExclude() {
		return exclude;
	}

	public void setExclude(int exclude) {
		this.exclude = exclude;
	}

	/**
	 * 双人对战控制函数
	 * @param panelLocationX  棋手在物理棋盘上点下点的横坐标
	 * @param panelLocationY  棋手在物理棋盘上点下点的纵坐标
	 * @param space           棋盘的格子间距
	 * @param radius          棋子的半径
	 */
	public void playPToP(int panelLocationX, int panelLocationY, int space, int radius) {
		Point p = new Point(panelLocationX, panelLocationY);
		p = positionTransform(p, space, radius);
		if (p.positionX != -1) {
			if (getable(p.positionX, p.positionY) == 0) {
				setable(p.positionX, p.positionY, getOwner());
				setOwner(-getOwner());
				if (getOwner() == Constant.BLACKCHESS) {
					methodLogger.info("白棋棋手在({},{})落子", p.positionX, p.positionY);
				}
				else {
					methodLogger.info("黑棋棋手在({},{})落子", p.positionX, p.positionY);
				}
			}
			else {
				methodLogger.warn("您没有把棋子下到有效的区域内");
			}
			if (isWin(p.positionX, p.positionY)) {
				setWiner(-getOwner());
			}
		}
		else {
			methodLogger.warn("您没有把棋子下到有效的区域内");
		}

	}

	/**
	 * 人机对战时人控制函数      
	 * @param panelLocationX  棋手在物理棋盘上点下点的横坐标
	 * @param panelLocationY  棋手在物理棋盘上点下点的纵坐标
	 * @param space           棋盘格子间距
	 * @param radius          棋子半径
	 * @return  返回值表明人是否把棋子下到了有效的位置,true表示棋子下到了有效的位置，false表示没有
	 */
	public boolean pPlay(int panelLocationX, int panelLocationY, int space, int radius) {
		Point p = new Point(panelLocationX, panelLocationY);
		p = positionTransform(p, space, radius);
		if (p.positionX != -1) {
			if (getable(p.positionX, p.positionY) == 0) {
				setable(p.positionX, p.positionY, Constant.WHITECHESS);
				setExclude(Constant.PCOWN);
				if (isWin(p.positionX, p.positionY)) {
					setWiner(Constant.WHITECHESS);
					setExclude(Constant.PEOPLEOWN);
				}
				methodLogger.info("棋手在({},{})落子", p.positionX, p.positionY);
				setOwner(-getOwner());
				return true;
			}
			else {
				methodLogger.warn("您没有把棋子下到有效的区域内");
			}
		}
		methodLogger.warn("您没有把棋子下到有效的区域内");
		return false;
	}

	/**
	 * 人机对战是电脑的控制函数
	 */
	public void pcPlay() {
		Ai ai = new Ai(row, table);
		/**记录Ai查到的点*/
		Point aiPostionP;
		if (getAiLevel() == Constant.PRIMARY) {
			aiPostionP = ai.primaryFind();
		}
		else {
			aiPostionP = ai.advancedFind(3);
		}
		setable(aiPostionP.positionX, aiPostionP.positionY, Constant.BLACKCHESS);
		if (isWin(aiPostionP.positionX, aiPostionP.positionY)) {
			setWiner(Constant.BLACKCHESS);
		}
		setOwner(-getOwner());
		setExclude(Constant.PEOPLEOWN);
		methodLogger.info("ai在({},{})落子", aiPostionP.positionX, aiPostionP.positionY);
		if (getWiner() == Constant.BLACKCHESS) {
			methodLogger.info("ai胜利了");
		}
	}

	/**
	 * 重置棋盘信息
	 */
	public void resetChessPanel() {
		for (int i = 0; i != 15; i++)
			for (int j = 0; j != 15; j++)
				if (getable(i, j) != 0) {
					setable(i, j, 0);
				}
		setWiner(0);
	}

	/**
	 * 判赢算法
	 * @param positionX 待带判断的当前节点的横坐标
	 * @param positionY 待判断的当前节点的纵坐标
	 * @return 返回值是一boolean值，true表示该点使得给用户胜利，false表示没有胜利
	 */
	private boolean isWin(int positionX, int positionY) {
		/**countH代表横向上的棋子个数*/
		int countH = 0;
		/**countS代表竖向上的棋子个数*/
		int countS = 0;
		/**countP代表撇向上棋子个数*/
		int countP = 0;
		/**countN代表捺向上棋子的个数*/
		int countN = 0;
		for (int i = 1; i != 5; i++) {
			if (compare(positionX, positionY, positionX + i, positionY))
				countH++;
			else
				break;
		}
		for (int i = 1; i != 5; i++) {
			if (compare(positionX, positionY, positionX - i, positionY))
				countH++;
			else
				break;
		}
		if (countH >= 4)
			return true;
		for (int i = 1; i != 5; i++) {
			if (compare(positionX, positionY, positionX, positionY + i))
				countS++;
			else
				break;
		}
		for (int i = 1; i != 5; i++) {
			if (compare(positionX, positionY, positionX, positionY - i))
				countS++;
			else
				break;
		}
		if (countS >= 4)
			return true;
		for (int i = 1; i != 5; i++) {
			if (compare(positionX, positionY, positionX + i, positionY - i))
				countP++;
			else
				break;
		}
		for (int i = 1; i != 5; i++) {
			if (compare(positionX, positionY, positionX - i, positionY + i))
				countP++;
			else
				break;
		}
		if (countP >= 4)
			return true;
		for (int i = 1; i != 5; i++) {
			if (compare(positionX, positionY, positionX - i, positionY - i))
				countN++;
			else
				break;
		}
		for (int i = 1; i != 5; i++) {
			if (compare(positionX, positionY, positionX + i, positionY + i))
				countN++;
			else
				break;
		}
		if (countN >= 4)
			return true;
		return false;
	}

	/**
	 * 比较两个位置上的棋子颜色时候一样
	 * @param currentPositionX   待比较的当前节点的横坐标
	 * @param currentPositionY   待比较的当前节点的纵坐标
	 * @param nextPositionX      待与当前节点比较的下一个节点的横坐标
	 * @param nextPositionY      待与当前节点比较的下一个节点的纵坐标
	 * @return  返回两个节点上棋子颜色的异同情况
	 */
	private boolean compare(int currentPositionX, int currentPositionY, int nextPositionX, int nextPositionY) {
		if (nextPositionX < 0 || nextPositionX > row - 1)
			return false;
		else if (nextPositionY < 0 || nextPositionY > row - 1)
			return false;
		else if (getable(currentPositionX, currentPositionY) == getable(nextPositionX, nextPositionY))
			return true;
		return false;
	}

	/**
	 * 将棋盘上物理的位置转换成table数组对应的位置
	 * @param locationP 棋盘上的物理位置
	 * @param space     棋盘格子的宽度
	 * @param radius    棋子的半径
	 * @return        该物理位置在table中对应的点
	 */
	private Point positionTransform(Point locationP, int space, int radius) {
		if (locationP.positionX % space < radius && locationP.positionY % space < radius) {
			locationP.positionX = locationP.positionX / space - 1;
			locationP.positionY = locationP.positionY / space - 1;
			return locationP;
		}

		else if (locationP.positionX % space > (space - radius) && locationP.positionY % space > (space - radius)) {
			locationP.positionX = locationP.positionX / space;
			locationP.positionY = locationP.positionY / space;
			return locationP;

		}
		else if (locationP.positionX % space > (space - radius) && locationP.positionY % space < radius) {
			locationP.positionX = locationP.positionX / space;
			locationP.positionY = locationP.positionY / space - 1;
			return locationP;

		}
		else if (locationP.positionX % space < radius && locationP.positionY % space > (space - radius)) {
			locationP.positionX = locationP.positionX / space - 1;
			locationP.positionY = locationP.positionY / space;
			return locationP;

		}
		else {
			locationP.positionX = -1;
			locationP.positionY = -1;
			return locationP;
		}
	}
}