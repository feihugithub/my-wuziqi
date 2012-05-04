package com.succez.wangzr.test;

/**
 * 该类主要实现了输出完全二叉树的某一指定层次的全部几点，同时也提供了构造完全二叉树的方法，且构造和遍历的都采用了递归的方法
 * <p>Copyright: Copyright (c) 2012<p>
 * <p>succez<p>
 * @author feihu
 * @createdate 2012-5-4
 */
public class TreeLevel {

	/**
	 * 采用递归的方法实现了对某一指定层次的遍历
	 * @param rootNode 某棵树的根节点，递归的时候它代表的是上一层调用中的树的子树
	 * @param n  被指定的层数
	 */
	public static void treeLevel(TreeNode rootNode, int n) {
		if (n == 1) {
			System.out.print(rootNode.getValue());
			System.out.print(" ");
		}
		else {
			treeLevel(rootNode.getLeft(), n - 1);
			treeLevel(rootNode.getRight(), n - 1);
		}
	}

	/**
	 * 对给定点的树根节点，和树节点信息构造一个完全二叉树，但是节点信息的个数必须是2的n次幂减1
	 * @param nodeInfo 树节点的信息,此处的信息是先序序列
	 * @param rootNode 构造出来的树的根节点
	 * @param height 完全二叉树的高度
	 */
	public static void treeCreate(String[] nodeInfo, TreeNode rootNode, int height) {
		if (height == 1) {
			rootNode.setValue(nodeInfo[0]);
			rootNode.setLeft(null);
			rootNode.setRight(null);
		}
		else {
			rootNode.setValue(nodeInfo[0]);
			rootNode.setLeft(new TreeNode());
			rootNode.setRight(new TreeNode());
			int length = (int) (Math.pow(2, height - 1) - 1);
			String[] tempInfo = new String[length];
			System.arraycopy(nodeInfo, 1, tempInfo, 0, length);
			treeCreate(tempInfo, rootNode.getLeft(), height - 1);
			System.arraycopy(nodeInfo, length + 1, tempInfo, 0, length);
			treeCreate(tempInfo, rootNode.getRight(), height - 1);
		}
	}

	public static void main(String[] args) {
		String[] nodeInfo = { "A", "B", "G", "H", "D", "C", "F" };
		int height = (int) (Math.log(nodeInfo.length + 1) / Math.log(2));
		TreeNode rootNode = new TreeNode();
		TreeLevel.treeCreate(nodeInfo, rootNode, height);
		TreeLevel.treeLevel(rootNode, 3);
	}
}
