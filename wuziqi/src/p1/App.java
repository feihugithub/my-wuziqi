package p1;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

//应用程序类
public class App extends JFrame {
	private JButton btn_P_P;
	private JButton btn_P_PC;
	private JButton btn_reset;
	private ChesePanel pad;
	private ActionListener pp = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			pad.method.mode = pad.method.p_p;
		}
	};
	private ActionListener ppc = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			pad.method.mode=pad.method.p_pc;
			pad.method.ai.table[7][7] = Ai.BLACK;
			pad.paint(pad.getGraphics());
		}
	};
	private ActionListener rst = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			pad.method.winer = 0;
			pad.method.owner = Ai.BLACK;
			pad.method.reset();
			pad.repaint();
			pad.showwiner();
		}
	};

	public App() {
		super("五子棋");
		pad = new ChesePanel();
		btn_reset = new JButton("重新开始");
		btn_P_P = new JButton("双人对战");
		btn_P_PC = new JButton("人机对战");
		btn_P_P.addActionListener(pp);
		btn_P_PC.addActionListener(ppc);
		btn_reset.addActionListener(rst);
		this.setLayout(new FlowLayout());
		this.add(pad);
		this.add(btn_reset);
		this.add(btn_P_P);
		this.add(btn_P_PC);
	}

	public static void main(String[] args) {
		App app = new App();
		SwingConsole.run(app, 640, 710);
	}
}
