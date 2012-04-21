package com.succez.wangzr.wuziqi.tools;

/**
 * 用于保存常量的接口
 * <p>Copyright: Copyright (c) 2012<p>
 * <p>succez<p>
 * @author feihu
 * @createdate 2012-4-5
 */
public interface Constant {

	/**连子的尽头没有棋子*/
	public static final int EMPTY = 1;

	/**连子的尽头是不同的棋子*/
	public static final int CROWD = -1;

	/**黑棋*/
	public static final int BLACKCHESS = 1;

	/**白棋*/
	public static final int WHITECHESS = -1;

	/**越界*/
	public static final int CROSSBORDER = 0;

	/**两点棋子不同*/
	public static final int DIFFERENT = -1;

	/**两点棋子相同*/
	public static final int SAME = 1;

	/**表示人先手*/
	public static final int PEOPLEFIRST = 1;

	/**表示Ai先手*/
	public static final int PCFIRST = -1;

	/**表示对弈正在进行*/
	public static final int RUN = 1;

	/**表示对弈结束或者还没有开始*/
	public static final int STOP = -1;

	/**双人对战*/
	public static final int PTOP = 1;

	/**人机对战*/
	public static final int PTOPC = -1;

	/**初级电脑*/
	public static final int PRIMARY = 1;

	/**高级电脑*/
	public static final int ADVANCE = -1;

	public static final int WIN = 1000;

	public static final int LOSE = -1000;

}
