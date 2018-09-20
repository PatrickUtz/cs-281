package intlist;

/*
 * Name: IntList
 * Authors: Patrick Utz / Jeremy Goldberg / Matt Stein
 * Date: 9/20/2018
 * Description: Provides a more usable "array" to be used to store ints
 */

public class IntList {
 
    // Fields
    private int[] items;
    private int size;
    private static final int START_SIZE = 8;
   
    // Constructor
    IntList () {
        items = new int[START_SIZE];
        size  = 0;
    }
 
    public int getAt(int index) {
        indexValidityCheck(index);
        return items[index];
    }
 
    public void append(int toAdd) {
        checkAndGrow();
        items[size] = toAdd;
        size++;
    }
   
    public void removeAt(int index) {
        shiftLeft(index);
        size--;
    }
   
    private void indexValidityCheck (int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }
   
    /*
     * Expands the size of the list whenever it is at
     * capacity
     */
    private void checkAndGrow () {
        // Case: big enough to fit another item, so no
        // need to grow
        if (size < items.length) {
            return;
        }
       
        // Case: we're at capacity and need to grow
        // Step 1: create new, bigger array; we'll
        // double the size of the old one
        int[] newItems = new int[items.length * 2];
       
        // Step 2: copy the items from the old array
        for (int i = 0; i < items.length; i++) {
            newItems[i] = items[i];
        }
       
        // Step 3: update IntList reference
        items = newItems;
    }
   
    /*
     * Shifts all elements to the right of the given
     * index one left
     */
    private void shiftLeft (int index) {
    	indexValidityCheck(index);
        for (int i = index; i < size-1; i++) {
            items[i] = items[i+1];
        }
    }
    
    /*
     * Shifts all elements to the right of the given
     * index one right
     */
    private void shiftRight (int index) {
    	indexValidityCheck(index);
        checkAndGrow();
        for (int i = size; i > index; i--) {
            items[i] = items[i-1];
        }
    }
   
    /*
     * Adds the given int toAdd to the first position in the IntList
     */
    public void prepend (int toAdd) {
        insertAt(toAdd, 0);
    }
   
    /*
     * Inserts the given int toAdd at the specified index within the IntList
     */
    public void insertAt (int toAdd, int index) {
        shiftRight(index);
        items[index] = toAdd;
        size++;
    }
   
    /*
     * Removes all instances of the given int toRemove in the IntList
     */
    public void removeAll (int toRemove) {
        for (int i = 0; i < size; i++) {
        	if (items[i] == toRemove) {
        		removeAt(i);
        		i--;
        	}
        }
    }
    
    /*
     * Prints all elements in the IntList to the console for debugging purposes
     */
    public void printIntList () {
    	System.out.print("[ ");
    	for (int i = 0; i < size; i++) {
    		System.out.print(items[i] + " ");
    	}
    	System.out.print("]");
    	return;
    }
   
    /*
     * Used to test methods
     */
    public static void main(String[] args) {
        IntList inty = new IntList();
        for (int i = 0; i < 5; i++) {
                inty.append(i);
        }
        
        // Check constructor
        inty.printIntList();
        
        // Check prepend method
        inty.prepend(5);
        inty.printIntList();
        
        // Check insertAt method
        inty.insertAt(5, 3);
        inty.insertAt(5, 3);
        inty.insertAt(5, 3);
        inty.printIntList();
        
        // Check removeAll method
        inty.removeAll(5);
        inty.printIntList();
    }
}
  