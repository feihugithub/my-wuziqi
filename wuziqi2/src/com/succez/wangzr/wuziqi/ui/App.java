package com.succez.wangzr.wuziqi.ui;

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
	/**
	 * 
	 */
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
			chessPanel.runTimeStatus = Constant.RUN;
			btnStart.setVisible(false);
			btnReset.setVisible(true);
			if (chessPanel.chessMethod.gameMode == Constant.PTOPC && firsthand == Constant.PCFIRST) {
				chessPanel.chessMethod.ai.table[7][7] = Constant.BLACKCHESS;
				chessPanel.paint(chessPanel.getGraphics());
			}

		}
	};

	/**
	 * 级别选择按钮监听事件
	 */
	private ActionListener level = new ActionListener() {

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			chessPanel.chessMethod.aiLevel = -chessPanel.chessMethod.aiLevel;
			if (chessPanel.chessMethod.aiLevel == Constant.PRIMARY) {
				btnLevel.setText("初级");
			}
			else {
				btnLevel.setText("高级");
			}
		}
	};

	/**
	 * 模式选择按钮监听事件
	 */
	private ActionListener mode = new ActionListener() {

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (chessPanel.runTimeStatus == Constant.STOP) {
				chessPanel.chessMethod.gameMode = -chessPanel.chessMethod.gameMode;
				if (chessPanel.chessMethod.gameMode == Constant.PTOP) {
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
			chessPanel.chessMethod.winer = 0;
			/**重置双人对战时下棋所有权*/
			chessPanel.chessMethod.owner = Constant.BLACKCHESS;
			/** 重置现在运行情况*/
			chessPanel.runTimeStatus = Constant.STOP;
			btnStart.setVisible(true);
			/**重置棋盘信息*/
			chessPanel.chessMethod.resetChessPanel();
			chessPanel.repaint();
			chessPanel.showwiner();
			btnReset.setVisible(false);
		}
	};

	/**
	 * 先手按钮监听事件
	 */
	private ActionListener first = new ActionListener() {

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			firsthand = -firsthand;
			if (firsthand == Constant.PCFIRST) {
				btnUphand.setText("电脑先下");
			}
			else
				btnUphand.setText("棋手先下");
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
	}

	public static void main(String[] args) {
		App app = new App();
		SwingConsole.run(app, 640, 705);
	}
}
