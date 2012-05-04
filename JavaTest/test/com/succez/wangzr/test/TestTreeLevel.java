package com.succez.wangzr.test;


import org.junit.Test;

public class TestTreeLevel {

	@Test
	public void test() {
		String[] nodeInfo = { "A", "B", "D", "H", "I", "E", "J","K","C","F","L","M","G","N","O" };
		int height = (int) (Math.log(nodeInfo.length + 1) / Math.log(2));
		TreeNode rootNode = new TreeNode();
		TreeLevel.treeCreate(nodeInfo, rootNode, height);
		TreeLevel.treeLevel(rootNode, 4);
	}

}
