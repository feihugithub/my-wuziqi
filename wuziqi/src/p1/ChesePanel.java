package p1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import p1.Ai.Point;

//棋盘类    负责绘制棋盘，棋子，显示胜利者
public class ChesePanel extends JPanel {
	
//	public int test=0;
	public ChessMethod method;
	private int space, radius;
	private int width, height;
	private JTextField txt;
	private Timer ai_timer = new Timer();

	public ChesePanel() {
		method = new ChessMethod();
		space = 40; // 棋盘格子间距
		radius = 15; // 棋子半径
		width = 640; // 棋盘宽度
		height = 640; // 棋盘高度
		txt = new JTextField();
		txt.setHorizontalAlignment(JTextField.CENTER);
		txt.setEditable(false);
		txt.setBorder(BorderFactory.createEmptyBorder());
		txt.setPreferredSize(new Dimension(200, 30));
		txt.setBackground(Color.gray);
		txt.setFont(new Font("楷体", Font.PLAIN, 20));
		this.setPreferredSize(new Dimension(width, height));
		this.setLayout(new FlowLayout());
		this.add(txt);
		this.addMouseListener(chess);
	}

	private MouseListener chess = new MouseListener() {

		@Override
		public void mouseReleased(MouseEvent e) {

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated metthod stub
			if (method.winer == 0) {
				if (method.mode == method.p_p) {
					method.play_p_p(e.getX(), e.getY(), space, radius);
					repaint();
				} else {
					boolean b=method.play_p_pc(e.getX(), e.getY(), space, radius);
					repaint();
					if(b&&method.winer==0){
						ai_timer.schedule(new TimerTask() {
							@Override
							public void run() {
								// TODO Auto-generated method stub
//								method.ai.table[0][test]=Ai.BLACK;
//								if(method.judge(0, test))method.winer=Ai.BLACK;
//								test++;
								Ai.Point p = method.ai.find_1();
								System.out.println(p.p_x);
								System.out.println(p.p_y);
								if (method.judge(p.p_x, p.p_y))
									method.winer = Ai.BLACK;
								repaint();
							}
						}, 500);
					}
				}
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

	//
	// public void update(Graphics g) {
	// paint(g);
	// };

	public void paint(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());// 不清楚
		g.setColor(Color.black);
		// 画棋盘
		paintpad(g);
		// 画棋子
		paintchess(g);

		if (method.winer != 0)
			showwiner();
	}

	// 显示胜利者
	public void showwiner() {
		if (method.winer == method.ai.BLACK)
			txt.setText("胜利者是黑棋棋手");
		else if (method.winer == method.ai.WHITE)
			txt.setText("胜利者是白棋棋手");
		else
			txt.setText("重新开始");

	}

	// 绘棋盘
	private void paintpad(Graphics g) {
		for (int i = space; i != width; i = i + space)
			g.drawLine(i, space, i, height - space);
		for (int j = space; j != height; j = j + space)
			g.drawLine(space, j, width - space, j);
	}

	// 绘棋子
	private void paintchess(Graphics g) {
		for (int i = space; i != width; i = i + space)
			for (int j = space; j != height; j = j + space) {
				if (method.ai.table[i / space - 1][j / space - 1] == method.ai.BLACK) {
					g.setColor(Color.black);
					g.fillOval(i - radius, j - radius, 2 * radius, 2 * radius);
				} else if (method.ai.table[i / space - 1][j / space - 1] == method.ai.WHITE) {
					g.setColor(Color.white);
					g.fillOval(i - radius, j - radius, 2 * radius, 2 * radius);
				}
			}
	}
}
