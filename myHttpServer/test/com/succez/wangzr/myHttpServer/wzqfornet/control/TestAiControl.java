package com.succez.wangzr.myHttpServer.wzqfornet.control;

import static org.junit.Assert.*;

import org.junit.Test;

import com.succez.wangzr.wzqfornet.control.Control;
import com.succez.wangzr.wzqfornet.tool.Constant;
import com.succez.wangzr.wzqfornet.tool.Point;

public class TestAiControl {

	@Test
	public void testIsWin() {
		testPcPlay(Constant.PRIMARY);
		testPcPlay(Constant.ADVANCE);
	}

	@Test
	public void testAiFind() {
		
	}
	private void testPcPlay(int aiLevel){
		Control control = new Control(15);
		control.setAiLevel(aiLevel);
		for (int i = 0; i < 4; i++) {
			control.setable(i, i, Constant.BLACKCHESS);
		}
		Point point=control.pcPlay();
		assertEquals(4, point.positionX);
		assertEquals(4, point.positionY);
		assertEquals(Constant.BLACKCHESS, control.getable(point.positionX, point.positionY));
	}

}
