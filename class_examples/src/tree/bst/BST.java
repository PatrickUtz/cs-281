package tree.bst;

import tree.binary.BinaryTreeNode;

public class BST {
	
	BSTNode root;
	
	BST () {}
	
	/*
	 * Add a new int i into the existing BST
	 */
	public void add (int i) {
		// Case: empty tree
		if (root == null) {
			root = new BSTNode(i);
			return;
		}
		
		// Case: nodes in the tree, find where new one
		// should live
		BSTNode current = root;
		while (current != null) {
			// Compare inserted value to that of current
			// Case: i is < current, so belongs left
			if (i < current.data) {
				// Case: OK to insert left
				if (current.left == null) {
					current.left = new BSTNode(i);
					return;
				}
				// Case: need to keep searching
				current = current.left;
			// Case: i is >= current, so belongs right
			} else {
				// Case: OK to insert right
				if (current.right == null) {
					current.right = new BSTNode(i);
					return;
				}
				// Case: need to keep searching
				current = current.right;
			}
		}
	}
	
	public boolean contains (int i) {
		BSTNode current = root;
		while (current != null) {
			if (current.data == i) { return true; }
			
			// Otherwise, look to appropriate subtree
			current = (i < current.data) ? current.left : current.right;
		}
		return false;
	}
	
	public void inOrderPrint () {
		inOrderPrint(root);
	}
	
	private static void inOrderPrint (BSTNode n) {
		// Base Case: done recursing, stop
		if (n == null) { return; }
		// Recursive case:
		inOrderPrint(n.left);
		// Visit node
		System.out.print(n.data + " ");
		// Recursive case: 
		inOrderPrint(n.right);
	}
	
	private class BSTNode {
		int data;
		BSTNode left, right;
		
		BSTNode (int d) {
			data = d;
		}
	}
	
	/*
	 * The count method that returns the number of odes in the tree that conatins the given
	 * String s. 
	 * 
	 * if (n == null) {return 0;}
	 * return ((n.data.equals(s)) ? 1 : 0) + count(n.left, s) + count(n.right, s);
	 */
	
	public static void main (String [] args) {
		BST tree = new BST();
		tree.add(10);
		tree.add(2);
		tree.add(12);
		tree.add(4);
		tree.add(3);
		tree.add(11);
		tree.inOrderPrint();
		System.out.println();
		System.out.println(tree.contains(10));
		System.out.println(tree.contains(3));
		System.out.println(tree.contains(13));
	}

}
