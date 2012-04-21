package com.succez.wangzr.wuziqi.ui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.succez.wangzr.wuziqi.tools.Constant;
import com.succez.wangzr.wuziqi.tools.SwingConsole;

/**
 * 绘制五子棋控制框架
 * <p>Copyright: Copyright (c) 2012<p>
 * <p>succez<p>
 * @author feihu
 * @createdate 2012-4-1
 */
public class App extends JFrame {

	private static final long serialVersionUID = 1L;

	/**棋盘*/
	private ChessPanel chessPanel;

	/**游戏模式选择按钮*/
	private JButton btnMode;

	/**重置按钮*/
	private JButton btnReset;

	/**先手选择按钮*/
	private JButton btnUphand;

	/**电脑级别选择按钮*/
	private JButton btnLevel;

	/**开始按钮*/
	private JButton btnStart;

	/**先手控制*/
	private int firsthand = Constant.PCFIRST;

	/**
	 * 开始按钮监听事件
	 */
	private ActionListener start = new ActionListener() {

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			chessPanel.setRunTimeStatus(Constant.RUN);
			btnStart.setVisible(false);
			btnReset.setVisible(true);
			if (chessPanel.getchessMethod().getGameMode() == Constant.PTOPC && firsthand == Constant.PCFIRST) {
				chessPanel.getchessMethod().setable(7, 7, Constant.BLACKCHESS);
				chessPanel.getchessMethod().setOwner(-chessPanel.getchessMethod().getOwner());
			}
			chessPanel.paint(chessPanel.getGraphics());
		}
	};

	/**
	 * 级别选择按钮监听事件
	 */
	private ActionListener level = new ActionListener() {

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (chessPanel.getRunTimeStatus() == Constant.STOP) {
				chessPanel.getchessMethod().setAiLevel(-chessPanel.getchessMethod().getAiLevel());
				if (chessPanel.getchessMethod().getAiLevel() == Constant.PRIMARY) {
					btnLevel.setText("初级");
				}
				else {
					btnLevel.setText("高级");
				}
			}
		}
	};

	/**
	 * 模式选择按钮监听事件
	 */
	private ActionListener mode = new ActionListener() {

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (chessPanel.getRunTimeStatus() == Constant.STOP) {
				chessPanel.getchessMethod().setGameMode(-chessPanel.getchessMethod().getGameMode());
				if (chessPanel.getchessMethod().getGameMode() == Constant.PTOP) {
					btnMode.setText("双人对战");
					btnUphand.setVisible(false);
					btnLevel.setVisible(false);
				}
				else {
					btnMode.setText("人机对战");
					btnUphand.setVisible(true);
					btnLevel.setVisible(true);
				}
			}
		}
	};

	/**
	 * 重置按钮监听事件
	 */
	private ActionListener reset = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			/**重置胜利者信息*/
			chessPanel.getchessMethod().setWiner(0);
			/**重置双人对战时下棋所有权*/
			if (chessPanel.getchessMethod().getGameMode() == Constant.PTOP) {
				chessPanel.getchessMethod().setOwner(Constant.BLACKCHESS);
			}
			else {
				if (firsthand == Constant.PCFIRST) {
					chessPanel.getchessMethod().setOwner(Constant.BLACKCHESS);
				}
				else {
					chessPanel.getchessMethod().setOwner(Constant.WHITECHESS);
				}
			}
			/** 重置现在运行情况*/
			chessPanel.setRunTimeStatus(Constant.STOP);
			btnStart.setVisible(true);
			/**重置棋盘信息*/
			chessPanel.getchessMethod().resetChessPanel();
			chessPanel.repaint();
			btnReset.setVisible(false);
		}
	};

	/**
	 * 先手按钮监听事件
	 */
	private ActionListener first = new ActionListener() {

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (chessPanel.getRunTimeStatus() == Constant.STOP) {
				firsthand = -firsthand;
				if (firsthand == Constant.PCFIRST) {
					btnUphand.setText("电脑先下");
					chessPanel.getchessMethod().setOwner(Constant.BLACKCHESS);
				}
				else {
					btnUphand.setText("棋手先下");
					chessPanel.getchessMethod().setOwner(Constant.WHITECHESS);
				}

			}
		}
	};

	/**
	 * 默认构造函数
	 */
	public App() {
		super("五子棋");
		chessPanel = new ChessPanel();
		btnStart = new JButton("开始");
		btnReset = new JButton("重新开始");
		btnMode = new JButton("双人对战");
		btnUphand = new JButton("电脑先下");
		btnLevel = new JButton("初级");
		btnReset.setVisible(false);
		btnUphand.setVisible(false);
		btnLevel.setVisible(false);
		btnMode.addActionListener(mode);
		btnReset.addActionListener(reset);
		btnUphand.addActionListener(first);
		btnLevel.addActionListener(level);
		btnStart.addActionListener(start);
		this.add(chessPanel);
		this.setLayout(new FlowLayout());
		this.add(btnStart);
		this.add(btnReset);
		this.add(btnMode);
		this.add(btnLevel);
		this.add(btnUphand);
		this.getContentPane().setBackground(Color.gray);
	}

	public static void main(String[] args) {
		App app = new App();
		SwingConsole.run(app, 640, 705);
	}
}
