package com.succez.wangzr.wuziqi.algorithm;

import org.slf4j.LoggerFactory;

import org.slf4j.Logger;

import com.succez.wangzr.wuziqi.tools.ChessStyle;
import com.succez.wangzr.wuziqi.tools.Constant;
import com.succez.wangzr.wuziqi.tools.Point;

/**
 * Ai查找最优点算法
 * <p>Copyright: Copyright (c) 2012<p>
 * <p>succez<p>
 * @author feihu
 * @createdate 2012-4-1
 */
public class Ai {
	private final static Logger AILO_LOGGER = LoggerFactory.getLogger(Ai.class);

	/**
	 * 棋盘的大小
	 */
	private int row;

	private int[][] table=new int[row][row];

	public Ai(int row, int[][] table) {
		this.row = row;
		this.table = table;
	}

	/**
	 * 比较两个位置的棋子是否相同
	 * @param currentPositionX  待比较的当前节点的横坐标
	 * @param currentPositionY  待比较的当前节点的纵坐标
	 * @param nextPositionX     待与当前节点比较的下一个节点的横坐标
	 * @param nextPositionY     待与当前节点比较的下一个节点的纵坐标 
	 * @return CROSS_BORDER，SAME，DIFFERENT三种情况之一，分别是越界，相同，不同
	 */
	private int compare(int currentPositionX, int currentPositionY, int nextPositionX, int nextPositionY) {
		if (nextPositionX < 0 || nextPositionX > row - 1)
			/**此种情况表明next位置在棋盘的左或者右方向的外部*/
			return Constant.CROSSBORDER;
		else if (nextPositionY < 0 || nextPositionY > row - 1)
			/**此种情况表明next位置在棋盘的上或者下方向的外部*/
			return Constant.CROSSBORDER;
		else if (table[currentPositionX][currentPositionY] == table[nextPositionX][nextPositionY])
			return Constant.SAME;
		return Constant.DIFFERENT;
	}

	/**
	 * 计数以判断在某一位置落子后横方向上的连子构成的棋型
	 * @param positionX  待判断的当前节点的横坐标
	 * @param positionY  待判断的当前节点的纵坐标
	 * @return  在横方向上对应的棋型
	 */
	private int getHorizontalChessStyle(int positionX, int positionY) {
		/**记录横方向上的连子个数*/
		int countH = 0;
		/**记录连子左边的情况，默认为无棋子*/
		int leftEmpty = Constant.EMPTY;
		/**记录连子右边的情况，默认为无棋子*/
		int rightEmpty = Constant.EMPTY;
		/**记录当前位置和下一位置棋子比较情况，默认为不同*/
		int comparison = Constant.DIFFERENT;
		/**
		 *该for循环从横向左边计数
		 *其中的switch根据comparison的情况设置leftEmpty的值
		 */
		for (int i = 1; i != 5; i++) {
			comparison = compare(positionX, positionY, positionX - i, positionY);
			switch (comparison) {
				case Constant.CROSSBORDER:
					leftEmpty = Constant.CROWD;
					break;
				case Constant.SAME:
					countH++;
					break;
				default:
					if (table[positionX - i][positionY] != 0)
						leftEmpty = Constant.CROWD;
			}
			if (comparison != Constant.SAME)
				break;
		}
		/**
		 *该for循环从横向右边计数
		 *其中的switch根据comparison的情况设置rightEmpty的值
		 */
		for (int i = 1; i != 5; i++) {
			comparison = compare(positionX, positionY, positionX + i, positionY);
			switch (comparison) {
				case Constant.CROSSBORDER:
					rightEmpty = Constant.CROWD;
					break;
				case Constant.SAME:
					countH++;
					break;
				default:
					if (table[positionX + i][positionY] != 0)
						rightEmpty = Constant.CROWD;
			}
			if (comparison != Constant.SAME)
				break;
		}
		return analysisChessStyle(countH, leftEmpty, rightEmpty);
	}

	/**
	 * 计数以判断在某一位置落子后竖方向上的连子构成的棋型
	 * @param positionX 待判断的当前节点的横坐标
	 * @param positionY 待判断的当前节点的纵坐标
	 * @return  在竖方向上对应的棋型
	 */
	private int getVerticalChessStyle(int positionX, int positionY) {
		int countS = 0;
		int leftEmpty = Constant.EMPTY;
		int rightEmpty = Constant.EMPTY;
		int comparison = Constant.DIFFERENT;

		for (int i = 1; i != 5; i++) {
			comparison = compare(positionX, positionY, positionX, positionY - i);
			switch (comparison) {
				case Constant.CROSSBORDER:
					leftEmpty = Constant.CROWD;
					break;
				case Constant.SAME:
					countS++;
					break;
				default:
					if (table[positionX][positionY - i] != 0)
						leftEmpty = Constant.CROWD;
			}
			if (comparison != Constant.SAME)
				break;
		}
		for (int i = 1; i != 5; i++) {
			comparison = compare(positionX, positionY, positionX, positionY + i);
			switch (comparison) {
				case Constant.CROSSBORDER:
					rightEmpty = Constant.CROWD;
					break;
				case Constant.SAME:
					countS++;
					break;
				default:
					if (table[positionX][positionY + i] != 0)
						rightEmpty = Constant.CROWD;
			}
			if (comparison != Constant.SAME)
				break;
		}

		return analysisChessStyle(countS, leftEmpty, rightEmpty);

	}

	/**
	 * 计数以判断在某一位置落子后撇方向上的连子构成的棋型
	 * @param positionX  待判断的当前节点的横坐标
	 * @param positionY  待判断的当前节点的纵坐标
	 * @return  在撇方向上对应的棋型
	 */
	private int getPChessStyle(int positionX, int positionY) {
		int countP = 0;
		int leftEmpty = Constant.EMPTY;
		int rightEmpty = Constant.EMPTY;
		int comparison = Constant.DIFFERENT;

		for (int i = 1; i != 5; i++) {
			comparison = compare(positionX, positionY, positionX - i, positionY + i);
			switch (comparison) {
				case Constant.CROSSBORDER:
					leftEmpty = Constant.CROWD;
					break;
				case Constant.SAME:
					countP++;
					break;
				default:
					if (table[positionX - i][positionY + i] != 0)
						leftEmpty = Constant.CROWD;
			}
			if (comparison != Constant.SAME)
				break;
		}
		for (int i = 1; i != 5; i++) {
			comparison = compare(positionX, positionY, positionX + i, positionY - i);
			switch (comparison) {
				case Constant.CROSSBORDER:
					rightEmpty = Constant.CROWD;
					break;
				case Constant.SAME:
					countP++;
					break;
				default:
					if (table[positionX + i][positionY - i] != 0)
						rightEmpty = Constant.CROWD;
			}
			if (comparison != Constant.SAME)
				break;
		}

		return analysisChessStyle(countP, leftEmpty, rightEmpty);

	}

	/**
	 * 计数以判断在某一位置落子后捺方向上的连子构成的棋型
	 * @param positionX 待判断的当前节点的横坐标
	 * @param positionY 待判断的当前节点的纵坐标
	 * @return  在捺方向上对应的棋型
	 */
	private int getNChessStyle(int positionX, int positionY) {
		int countN = 0;
		int leftEmpty = Constant.EMPTY;
		int rightEmpty = Constant.EMPTY;
		int comparison = Constant.DIFFERENT;

		for (int i = 1; i != 5; i++) {
			comparison = compare(positionX, positionY, positionX - i, positionY - i);
			switch (comparison) {
				case Constant.CROSSBORDER:
					leftEmpty = Constant.CROWD;
					break;
				case Constant.SAME:
					countN++;
					break;
				default:
					if (table[positionX - i][positionY - i] != 0)
						leftEmpty = Constant.CROWD;
			}
			if (comparison != Constant.SAME)
				break;
		}
		for (int i = 1; i != 5; i++) {
			comparison = compare(positionX, positionY, positionX + i, positionY + i);
			switch (comparison) {
				case Constant.CROSSBORDER:
					rightEmpty = Constant.CROWD;
					break;
				case Constant.SAME:
					countN++;
					break;
				default:
					if (table[positionX + i][positionY + i] != 0)
						rightEmpty = Constant.CROWD;
			}
			if (comparison != Constant.SAME)
				break;
		}

		return analysisChessStyle(countN, leftEmpty, rightEmpty);

	}

	/**
	 * 根据某一方向上的连子数目和左右尽头的情况确定棋型
	 * @param num  某一方向上连子数目
	 * @param left 该连子在左尽头的情况
	 * @param right 该连子在右尽头的情况
	 * @return
	 */
	private int analysisChessStyle(int num, int left, int right) {
		if (num >= 4)
			return ChessStyle.SUCCESS_5;
		else
			switch (num) {
				case 3:
					if (left == Constant.EMPTY && right == Constant.EMPTY)
						return ChessStyle.LIVE_4;
					else if (left == Constant.EMPTY && right == Constant.CROWD)
						return ChessStyle.DEAD_4;
					else if (left == Constant.CROWD && right == Constant.EMPTY)
						return ChessStyle.DEAD_4;
					return ChessStyle.SINGLE;
				case 2:
					if (left == Constant.EMPTY && right == Constant.EMPTY)
						return ChessStyle.LIVE_3;
					else if (left == Constant.EMPTY && right == Constant.CROWD)
						return ChessStyle.DEAD_3;
					else if (left == Constant.CROWD && right == Constant.EMPTY)
						return ChessStyle.DEAD_3;
					return ChessStyle.SINGLE;
				case 1:
					if (left == Constant.EMPTY && right == Constant.EMPTY)
						return ChessStyle.LIVE_2;
					else if (left == Constant.EMPTY && right == Constant.CROWD)
						return ChessStyle.DEAD_2;
					else if (left == Constant.CROWD && right == Constant.EMPTY)
						return ChessStyle.DEAD_2;
					return ChessStyle.SINGLE;
				default:
					return ChessStyle.SINGLE;
			}
	}

	/**
	 * 根据某一点落子后能够获得的棋型的好坏类给该点打分
	 * @param positionX 待打分的节点的横坐标
	 * @param positionY 待打分的节点的纵坐标
	 * @return  该节点考虑了四个方向以后的棋型
	 */
	private int positionRate(int positionX, int positionY) {
		/**用于记录四个方向中最好的两个棋型*/
		int[] temp = { -1, -1 };
		/**考虑了两个方向的棋型的估值*/
		int two = 0;
		/**记录了某个方向的棋型*/
		int situation = getHorizontalChessStyle(positionX, positionY);
		/**
		 * switch中假若有某一个方向落子后形成了成五或者活四，就不用再考虑多方向的情况了，直接返回。
		 * 若不是就要把四个方向中最好的两个棋型记录在temp数组中，且保持由大到小的顺序
		 */
		switch (situation) {
			case ChessStyle.SUCCESS_5:
				return ChessStyle.SUCCESS_5;
			case ChessStyle.LIVE_4:
				return ChessStyle.LIVE_4;
			default:
				if (situation > temp[1])
					temp[1] = situation;
				if (temp[0] < temp[1]) {
					situation = temp[0];
					temp[0] = temp[1];
					temp[1] = situation;
				}
		}
		situation = getVerticalChessStyle(positionX, positionY);
		switch (situation) {
			case ChessStyle.SUCCESS_5:
				return ChessStyle.SUCCESS_5;
			case ChessStyle.LIVE_4:
				return ChessStyle.LIVE_4;
			default:
				if (situation > temp[1])
					temp[1] = situation;
				if (temp[0] < temp[1]) {
					situation = temp[0];
					temp[0] = temp[1];
					temp[1] = situation;
				}
		}
		situation = getPChessStyle(positionX, positionY);
		switch (situation) {
			case ChessStyle.SUCCESS_5:
				return ChessStyle.SUCCESS_5;
			case ChessStyle.LIVE_4:
				return ChessStyle.LIVE_4;
			default:
				if (situation > temp[1])
					temp[1] = situation;
				if (temp[0] < temp[1]) {
					situation = temp[0];
					temp[0] = temp[1];
					temp[1] = situation;
				}
		}
		situation = getNChessStyle(positionX, positionY);
		switch (situation) {
			case ChessStyle.SUCCESS_5:
				return ChessStyle.SUCCESS_5;
			case ChessStyle.LIVE_4:
				return ChessStyle.LIVE_4;
			default:
				if (situation > temp[1])
					temp[1] = situation;
				if (temp[0] < temp[1]) {
					situation = temp[0];
					temp[0] = temp[1];
					temp[1] = situation;
				}
		}
		two = temp[0] + temp[1];
		if (two > 35) {
			switch (two) {
			/**DOUBLE_DEAD_4,DEAD_4_LIVE_3,DOUBLE_LIVE_3情况是等价的所以给予的估分值一样*/
				case ChessStyle.DOUBLE_DEAD_4:
					return ChessStyle.DOUBLE_DEAD_4 + 10;
				case ChessStyle.DEAD_4_LIVE_3:
					return ChessStyle.DEAD_4_LIVE_3 + 20;
				case ChessStyle.DOUBLE_LIVE_3:
					return ChessStyle.DOUBLE_LIVE_3;
				case ChessStyle.DEAD_3_LIVE_3:
					return ChessStyle.DEAD_3_LIVE_3;
				case ChessStyle.DOUBLE_LIVE_2:
					return ChessStyle.DOUBLE_LIVE_2;
				default:
					return temp[0];
			}
		}
		return temp[0];
	}

	/**
	 * 初级Ai查找最优下棋点，对于每一个点，分别测试Ai和人下棋后得到的估分值，其中Ai得到的估分值加1，
	 * 取这两个值中的最大值为该点的估分值，遍历table找出其中没有下棋的点中的估分最大的点。
	 * @return  查找到的点
	 */
	public Point primaryFind() {
		/**记录最大估分值*/
		int maxscore = -1;
		int tempscore = 0;
		int maxPositionX = 0, maxPositionY = 0;
		for (int i = 0; i != row; i++) {
			for (int j = 0; j != row; j++) {
				if (table[i][j] == 0) {
					table[i][j] = Constant.WHITECHESS;
					tempscore = positionRate(i, j);
					if (tempscore > maxscore) {
						maxscore = tempscore;
						maxPositionX = i;
						maxPositionY = j;
					}
					table[i][j] = Constant.BLACKCHESS;
					tempscore = positionRate(i, j) + 1;
					if (tempscore > maxscore) {
						maxscore = tempscore;
						maxPositionX = i;
						maxPositionY = j;
					}
					table[i][j] = 0;
				}
			}
		}
		Point point = new Point(maxPositionX, maxPositionY);
		AILO_LOGGER.info("ai找到的点的估值是:{}", maxscore);
		return point;
	}

	/**
	 * 高级电脑查找最优下棋点，采用博弈树
	 * @param depth 博弈树的深度
	 * @return  返回最优下棋点
	 */
	public Point advancedFind(int depth) {
		Point point = new Point(-1, -1);
		int max = -2000;
		for (int i = 0; i != row; i++)
			for (int j = 0; j != row; j++) {
				if (table[i][j] == 0) {
					table[i][j] = Constant.WHITECHESS;
					int mustbe=positionRate(i,j);
					table[i][j]=Constant.BLACKCHESS;
					int temp=positionRate(i, j)+1;
					if(temp>mustbe){
						mustbe=temp;
					}
					if (mustbe >= ChessStyle.DOUBLE_LIVE_3) {
						point.positionX = i;
						point.positionY = j;
						table[i][j] = 0;
						return point;
					}
					else {
						int backValue = minSearch(depth - 1, max);
						if (backValue > max) {
							max = backValue;
							point.positionX = i;
							point.positionY = j;
						}
						table[i][j] = 0;
					}
				}
			}
		AILO_LOGGER.info("ai找到的点的回溯值是:{}", max);
		return point;
	}

	/**
	 * 求取棋手落子后棋局的回溯值，该回溯值死其子节点的回溯值的最大值，其子节点是ai下棋后的棋局
	 * @return  该棋局的子节点的最大的回溯值
	 */
	private int maxSearch(int depth, int α) {
		int max = -2000;
		int backValue;
		for (int i = 0; i != row; i++)
			for (int j = 0; j != row; j++)
				if (table[i][j] == 0) {
					table[i][j] = Constant.BLACKCHESS;
					if (depth == 1) {
						backValue = maxRate();
					}
					else {
						backValue = minSearch(depth - 1, max);
					}
					/**
					 * α剪枝
					 */
					if (backValue >= α) {
						table[i][j] = 0;
						return backValue;
					}
					if (backValue > max) {
						max = backValue;
					}
					table[i][j] = 0;
				}
		return max;
	}

	/**
	 * 求取ai落子后棋局的回溯值，该回溯值是其子节点的回溯值的最小值，其子节点是棋手下棋后的棋局
	 * @return 该棋局的子节点的最小的回溯值
	 */
	private int minSearch(int depth, int β) {
		int min = 2000;
		int backValue;
		for (int i = 0; i != row; i++)
			for (int j = 0; j != row; j++) {
				if (table[i][j] == 0) {
					table[i][j] = Constant.WHITECHESS;
					if (depth == 1) {
						backValue = maxRate();
					}
					else {
						backValue = maxSearch(depth - 1, min);
					}
					/**
					 * β剪枝
					 */
					if (backValue <= β) {
						table[i][j] = 0;
						return backValue;
					}
					if (backValue < min) {
						min = backValue;
					}
					table[i][j] = 0;
				}
			}
		return min;
	}

	/**
	 * 对某一个棋盘，根据参数给定的执棋者，找出其中估值最大的点的估值
	 * @param color  指定执棋者
	 * @return  返回对局势评估的值
	 */
	private int panelRate(int color) {
		int rate = -1000;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < row; j++) {
				if (table[i][j] == color) {
					int temp = positionRate(i, j);
					if (temp > rate) {
						rate = temp;
					}
				}
			}
		}
		if (color == Constant.BLACKCHESS) {
			return rate;
		}
		return -rate;
	}

	/**
	 * 为ai的当前局势估分
	 * @return 所估的分数
	 */
	private int maxRate() {
		int maxrate = panelRate(Constant.BLACKCHESS);
		int minrate = panelRate(Constant.WHITECHESS);
		int situation = maxrate + minrate;
		return situation;
	}
}