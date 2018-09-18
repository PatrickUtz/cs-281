package int_linkedlist;

public class IntLinkedList {
	
	private int size;
	private Node head;
	
	IntLinkedList () {
		head = null;
		size = 0;
	}
	
	public int getSize () {
		return size;
	}
	
	public void prepend (int item) {
		Node heldHead = head;
		head = new Node(item);
		head.next = heldHead;
		size++;
	}
	
	public Iterator getIteratorAt (int index) {
		if (index > size || index <0) {
			throw new IllegalArgumentException();
		}
		Iterator it = new Iterator();
		while (index > 0) {
			it.next();
			index--;
		}
		return it;
	}
	
	private class Node {
		
		int data;
		Node next;
		
		Node(int d) {
			data = d;
			next = null;
		}
		
	}
	
	public class Iterator {
	
		private Node current;
		
		Iterator () {
			current = head;
		}
		
		public boolean hasNext () {
			return current != null && current.next != null;
		}
		
		public void next () {
			if (current == null) {return;}
			current = current.next;		
		}
		
		public int getCurrentInt () {
			return current.data;
		}
	}
	
	public static void main (String[] args) {
		IntLinkedList coolJ = new IntLinkedList();
		coolJ.prepend(3);
		coolJ.prepend(2);
		coolJ.prepend(1);
		IntLinkedList.Iterator it = coolJ.getIteratorAt(0);
		System.out.println(it.getCurrentInt());
		it.next();
		System.out.println(it.getCurrentInt());
		it.next();
		System.out.println(it.getCurrentInt());
	}
}
