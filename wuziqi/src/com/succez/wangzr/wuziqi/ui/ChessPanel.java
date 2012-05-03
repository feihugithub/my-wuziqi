package com.succez.wangzr.wuziqi.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.succez.wangzr.wuziqi.algorithm.ChessInfoIO;
import com.succez.wangzr.wuziqi.algorithm.ChessMethod;
import com.succez.wangzr.wuziqi.tools.Constant;
import com.succez.wangzr.wuziqi.tools.InfoUnit;

/**
 * 绘制棋盘相关内容
 * <p>Copyright: Copyright (c) 2012<p>
 * <p>succez<p>
 * @author feihu
 * @createdate 2012-4-1
 */
public class ChessPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	/**棋盘算法*/
	private ChessMethod chessMethod;

	/**显示输赢的编辑框*/
	private JTextField txtShower;

	/**对ai进程的控制*/
	private Timer aiTimer = new Timer();

	/**棋盘的大小*/
	private int row;

	/**棋盘格子之间的宽度*/
	private int gridSpace;

	/**棋子的半径*/
	private int chessradius;

	/**棋盘的宽度*/
	private int chessPanelWidth;

	/**棋盘的高度*/
	private int chessPanelheight;

	/**记录程序运行情况*/
	private int runTimeStatus = Constant.STOP;

	public int getRunTimeStatus() {
		return runTimeStatus;
	}

	public void setRunTimeStatus(int runTimeStatus) {
		this.runTimeStatus = runTimeStatus;
	}

	public ChessPanel() {
		row = 15;
		gridSpace = 40;
		chessradius = gridSpace / 2;
		chessPanelWidth = (row + 1) * gridSpace;
		chessPanelheight = (row + 1) * gridSpace;
		chessMethod = new ChessMethod(row);
		txtShower = new JTextField();
		txtShower.setHorizontalAlignment(JTextField.CENTER);
		txtShower.setEditable(false);
		txtShower.setBorder(BorderFactory.createEmptyBorder());
		txtShower.setPreferredSize(new Dimension(500, 30));
		txtShower.setBackground(Color.gray);
		txtShower.setFont(new Font("楷体", Font.PLAIN, 20));
		this.setPreferredSize(new Dimension(chessPanelWidth, chessPanelheight));
		this.setLayout(new FlowLayout());
		this.add(txtShower);
		this.addMouseListener(chess);
	}

	public ChessMethod getchessMethod() {
		return chessMethod;
	}

	/**
	 * 鼠标监听事件
	 */
	private MouseListener chess = new MouseListener() {

		@Override
		public void mouseReleased(MouseEvent e) {

		}

		@Override
		public void mousePressed(MouseEvent e) {
			try {
				// TODO Auto-generated method stub
				if (chessMethod.getWiner() == 0 && runTimeStatus == Constant.RUN) {
					if (chessMethod.getGameMode() == Constant.PTOP) {
						chessMethod.playPToP(e.getX(), e.getY(), gridSpace, chessradius);
						repaint();
					}
					else if (chessMethod.getExclude() == Constant.PEOPLEOWN) {
						boolean b = chessMethod.pPlay(e.getX(), e.getY(), gridSpace, chessradius);
						repaint();
						if (b && chessMethod.getWiner() == 0) {
							aiTimer.schedule(new TimerTask() {
								@Override
								public void run() {
									// TODO Auto-generated method stub

									try {
										chessMethod.pcPlay();
									}
									catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}

									repaint();
								}
							}, 500);
						}
					}
				}
			}
			catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub

		}
	};

	/**
	 * 棋盘重绘时调用该函数，每次有人落子后重绘
	 */
	public void paint(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());// 不清楚
		g.setColor(Color.black);
		paintPanel(g);
		paintChess(g);
		showMessage();
	}

	/**
	 * 显示提示信息
	 */
	public void showMessage() {
		if (runTimeStatus == Constant.RUN) {
			if (chessMethod.getGameMode() == Constant.PTOPC) {
				if (chessMethod.getWiner() == Constant.BLACKCHESS) {
					txtShower.setText("遗憾！您输了");
				}
				else if (chessMethod.getWiner() == Constant.WHITECHESS) {
					txtShower.setText("祝贺！您获胜了");
				}
				else if (chessMethod.getOwner() == Constant.BLACKCHESS)
					txtShower.setText("电脑正在思考，请耐心等待！");
				else if (chessMethod.getOwner() == Constant.WHITECHESS)
					txtShower.setText("该您下棋");
			}
			else if (chessMethod.getGameMode() == Constant.PTOP) {
				if (chessMethod.getWiner() == Constant.BLACKCHESS) {
					txtShower.setText("祝贺黑棋棋手，您获胜了");
				}
				else if (chessMethod.getWiner() == Constant.WHITECHESS) {
					txtShower.setText("祝贺白棋棋手，您获胜了");
				}
				else if (chessMethod.getOwner() == Constant.BLACKCHESS)
					txtShower.setText("请黑棋棋手下棋");
				else if (chessMethod.getOwner() == Constant.WHITECHESS)
					txtShower.setText("请白棋棋手下棋");
			}
		}
	}

	/**
	 * 绘制棋盘
	 * @param g
	 */
	private void paintPanel(Graphics g) {
		for (int i = gridSpace; i != chessPanelWidth; i = i + gridSpace)
			g.drawLine(i, gridSpace, i, chessPanelheight - gridSpace);
		for (int j = gridSpace; j != chessPanelheight; j = j + gridSpace)
			g.drawLine(gridSpace, j, chessPanelWidth - gridSpace, j);
	}

	/**
	 * 绘制棋子
	 * @param g
	 */
	private void paintChess(Graphics g) {
		for (int i = gridSpace; i != chessPanelWidth; i = i + gridSpace)
			for (int j = gridSpace; j != chessPanelheight; j = j + gridSpace) {
				if (chessMethod.getable(i / gridSpace - 1, j / gridSpace - 1) == Constant.BLACKCHESS) {
					g.setColor(Color.black);
					g.fillOval(i - chessradius, j - chessradius, 2 * chessradius, 2 * chessradius);
				}
				else if (chessMethod.getable(i / gridSpace - 1, j / gridSpace - 1) == Constant.WHITECHESS) {
					g.setColor(Color.white);
					g.fillOval(i - chessradius, j - chessradius, 2 * chessradius, 2 * chessradius);
				}
			}
	}

	private void reShowMessage(int mode, int winer, int level) {
		if (mode == Constant.PTOP) {
			if (winer == Constant.BLACKCHESS) {
				txtShower.setText("双人对战，胜利者是黑棋棋手");
			}
			else {
				txtShower.setText("双人对战，胜利者是白棋棋手");
			}
		}
		else {
			if (level == Constant.PRIMARY) {
				if (winer == Constant.BLACKCHESS) {
					txtShower.setText("人机对战，初级电脑，胜利者是电脑");
				}
				else {
					txtShower.setText("人机对战，初级电脑，胜利者是棋手");
				}
			}
			else {
				if (winer == Constant.BLACKCHESS) {
					txtShower.setText("人机对战，高级电脑，胜利者是电脑");
				}
				else {
					txtShower.setText("人机对战，高级电脑，胜利者是棋手");
				}
			}
		}
	}

	/**
	 * 重现棋局
	 * @throws IOException 
	 */
	public void replay() throws IOException {
		InfoUnit[] infoReader = new InfoUnit[225];
		int infoLength = ChessInfoIO.chessInfoRead("chessinfo.csv", infoReader);
		int mode = infoReader[0].point.positionX;
		int winer = infoReader[0].point.positionY;
		int level = infoReader[0].control;
		reShowMessage(mode, winer, level);
		for (int i = 1; i < infoLength; i++) {
			try {
				Thread.sleep(500);
			}
			catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			chessMethod.setable(infoReader[i].point.positionX, infoReader[i].point.positionY, infoReader[i].control);
			this.repaint();
			update(this.getGraphics());
		}
		reShowMessage(mode, winer, level);
	}
}
