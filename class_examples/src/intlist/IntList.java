package intlist;

public class IntList implements IntListInterface {
	
	private int[] items;
	private int size;
	private static final int START_SIZE = 8;
	
	IntList () {
		size = 0;
		items = new int[START_SIZE];
	}
	
	public void append (int toAdd) {
		checkAndGrow();
		items[size] = toAdd;
		size++;
	}
	
	public void removeAt (int index) {
		shiftLeft(index);
		size--;
	}
	
	public void insertAt (int toAdd, int index) {
		throw new UnsupportedOperationException();
	}
	
	public int getAt (int index) {
		if (index >= size) {
			throw new ArrayIndexOutOfBoundsException();
		}
		return items[index];
	}
	
	private void checkAndGrow () {
		// Case: big enough to fit another item
		if (size < items.length) {
			return;
		}
		
		// Case: not big enough to fit another item;
		// "grow" our items array
		int[] newItems = new int[items.length * 2];
		
		// Copy old items into new
		for (int i = 0; i < items.length; i++) {
			newItems[i] = items[i];
		}
		
		// Change the items reference to the new larger array
		items = newItems;
	}
	
	public void shiftLeft (int index) {
		for (int i = index; i < size -1; i++) {
			items[i] = items[i + 1];
		}
	}

	public static void main (String[] args) {
		IntList intyFresh = new IntList();
		for (int i = 0; i < 10; i++) {
			intyFresh.append(i);
			System.out.println(intyFresh.getAt(i));
		}
	}
}
