package tests;


public class Node {
	
	int data;
	Node next;
	
	Node(int d) {
		data = d;
		next = null;
	}
	
	public void addCycle () {
		next = next.next;
		next.next = next;
//		next.next = new Node(8);
//		next.next.next = new Node(3);
//		next.next.next.next = new Node(1);
	}
	
	public boolean checkCycle () {
		Node current = next;
		Node prev = this;
		boolean hasCycle = false;
		while (current != null) {
			if (prev == current) {
				hasCycle = true;
				break;
			}
			current = current.next;
			prev = prev.next;
		}
		return hasCycle;
	}
	
	public static void main (String[] args) {
		Node testNode = new Node(5);
		Node current = testNode;
		for (int i = 0; i < 5; i++) {
			current.next = new Node(i);
			current = current.next;
		}
		System.out.println(testNode.checkCycle());
		
		testNode.addCycle();
		System.out.println(testNode.checkCycle());
	}
}

