package com.succez.wangzr.wzqfornet.control;

import com.succez.wangzr.wzqfornet.tool.Constant;
import com.succez.wangzr.wzqfornet.tool.Point;

public class Control {
	/**棋盘大小*/
	private int row;

	/**标记胜利者*/
	private int winer = 0;

	/**控制ai的级别*/
	private int aiLevel = Constant.PRIMARY;

	/** 记录棋盘的信息*/
	private int[][] table;

	public Control(int row) {
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
	public int getAiLevel() {
		return aiLevel;
	}
	public int getWiner() {
		return winer;
	}

	/**
	 * 设置棋盘信息
	 * @param positionX 待设置的点的横坐标
	 * @param positionY 待设置的点的纵坐标
	 * @param color     待设置的棋子信息
	 */
	public void setable(int positionX, int positionY, int color) {
		table[positionX][positionY] = color;
	}

	public void setAiLevel(int aiLevel) {
		this.aiLevel = aiLevel;
	}

	public void setWiner(int winer) {
		this.winer = winer;
	}
	/**
	 * ai的控制函数
	 * @throws IOException 
	 */
	public Point pcPlay(){
		//记录Ai查到的点
		Point aiPostion;
		Ai ai = new Ai(row, table);
		if (getAiLevel() == Constant.PRIMARY) {
			aiPostion = ai.primaryFind();
		}
		else {
			aiPostion = ai.advancedFind(3);
		}
		setable(aiPostion.positionX, aiPostion.positionY, Constant.BLACKCHESS);
		if (isWin(aiPostion.positionX, aiPostion.positionY)) {
			setWiner(Constant.BLACKCHESS);
		}
		return aiPostion;
	}

	/**
	 * 重置棋盘信息
	 */
	public void resetChessPanel() {
		for (int i = 0; i != row; i++)
			for (int j = 0; j != row; j++)
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
	public boolean isWin(int positionX, int positionY) {
		//countH代表横向上的棋子个数
		int countH = 0;
		//countS代表竖向上的棋子个数
		int countS = 0;
		//countP代表撇向上棋子个数
		int countP = 0;
		//countN代表捺向上棋子的个数
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
}
