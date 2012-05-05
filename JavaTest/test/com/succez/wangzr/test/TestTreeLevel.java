package com.succez.wangzr.test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestTreeLevel {

	@Test
	public void test() {
		String[] nodeInfo = { "A", "B", "D", "H", "I", "E", "J", "K", "C", "F", "L", "M", "G", "N", "O" };
		int height = (int) (Math.log(nodeInfo.length + 1) / Math.log(2));
		TreeNode rootNode = new TreeNode();
		String[] expecteds = { "H", "I", "J", "K", "L", "M", "N", "O" };
		String[] actuals = new String[8];
		TreeLevel.treeCreate(nodeInfo, rootNode, height);
		TreeLevel.treeLevel(rootNode, 4, actuals, 0, 8);
		assertEquals(expecteds, actuals);
		int i = 0;
		while (i < 8) {
			assertEquals(expecteds[i], actuals[i]);
			i++;
		}
	}
}
