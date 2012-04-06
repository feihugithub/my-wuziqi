package com.succez.wangzr.wuziqi.tools;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
/**
 * 用于显示JFrame
 * <p>Copyright: Copyright (c) 2012<p>
 * <p>succez<p>
 * @author feihu
 * @createdate 2012-4-4
 */
public class SwingConsole {
	public static void run(final JFrame f, final int width, final int height) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				f.setSize(width, height);
				f.setVisible(true);
				f.setResizable(false);
				f.setLocationRelativeTo(null);
			}
		});

	}
}
