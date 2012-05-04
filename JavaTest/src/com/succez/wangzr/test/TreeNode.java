package com.succez.wangzr.test;
/**
 * 完全二叉树节点的结构
 * <p>Copyright: Copyright (c) 2012<p>
 * <p>succez<p>
 * @author feihu
 * @createdate 2012-5-4
 */
public class TreeNode {
	public String value=null;

	public TreeNode left=null;

	public TreeNode right=null;
	

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public TreeNode getLeft() {
		return this.left;
	}

	public void setLeft(TreeNode left) {
		this.left = left;
	}

	public TreeNode getRight() {
		return this.right;
	}

	public void setRight(TreeNode right) {
		this.right = right;
	}
}