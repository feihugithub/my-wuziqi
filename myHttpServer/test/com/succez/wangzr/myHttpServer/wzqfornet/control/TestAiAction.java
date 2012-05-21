package com.succez.wangzr.myHttpServer.wzqfornet.control;

import static org.junit.Assert.*;

import org.junit.Test;

import com.succez.wangzr.wzqfornet.control.AiAction;
import com.succez.wangzr.wzqfornet.tool.Constant;

public class TestAiAction {

	@Test
	public void testGetParameter() {
		AiAction aiAction = new AiAction("x=1&y=2&winer=3");
		assertEquals("3", aiAction.getParameter("winer"));
		assertEquals("2", aiAction.getParameter("y"));
		assertEquals("1", aiAction.getParameter("x"));
		assertEquals(null, aiAction.getParameter("isstart"));
		aiAction = new AiAction("x=15&y=14&winer=2&ailevel=1");
		assertEquals("15", aiAction.getParameter("x"));
		assertEquals("14", aiAction.getParameter("y"));
		assertEquals("2", aiAction.getParameter("winer"));
		assertEquals("1", aiAction.getParameter("ailevel"));
	}

	@Test
	public void testAction() {
		AiAction aiAction = new AiAction("isStart=1&x=1&y=2&aiLevel=1");
		assertEquals("!x=7&y=7&winer=0", aiAction.action());
		for (int i = 0; i < 4; i++) {
			aiAction.getControl().setable(0, i, Constant.WHITECHESS);
		}
		aiAction = new AiAction("x=0&y=4");
		assertEquals("!winer=-1", aiAction.action());
		aiAction = new AiAction("x=8&y=8");
		aiAction.getControl().resetChessPanel();
		for (int j = 0; j < 3; j++) {
			aiAction.getControl().setable(0, j, Constant.BLACKCHESS);
		}
		assertEquals("!x=0&y=3&winer=0", aiAction.action());
		aiAction.getControl().setable(0, 3, Constant.BLACKCHESS);
		assertEquals("!x=0&y=4&winer=1", aiAction.action());
	}
}
