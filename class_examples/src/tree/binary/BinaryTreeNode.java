package tree.binary;

/**
 * @author patrickutz
 * Binary tree of Strings
 */

public class BinaryTreeNode {

	private String data;
	private BinaryTreeNode left, right;
	
	BinaryTreeNode (String s) {
		data = s;
	}
	
	/*
	 * s is the new BinaryTreeNode's data
	 * child is either "L" or "R" indicating which 
	 * child it is meant to be added at
	 */
	public void add (String s, String child) {
		if (child.equals("L")) {
			left = new BinaryTreeNode(s);
		} else if (child.equals("R")) {
			right = new BinaryTreeNode(s);
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	public BinaryTreeNode getChild (String child) {
		return (child.equals("L") ? left : right);
	}
	
	public String getString () {
		return data;
	}
	
	public static void main (String[] args) {
		BinaryTreeNode root = new BinaryTreeNode("A");
		root.add("B", "L");
		root.add("C", "R");
		BinaryTreeNode lefty = root.getChild("L");
		lefty.add("D", "L");
		lefty.add("E", "R");
	}
}
