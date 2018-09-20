package forneymonegerie;

public class Forneymonegerie implements ForneymonegerieInterface {

    // Fields
    // ----------------------------------------------------------
    private ForneymonType[] collection;
    private int size;
    private int typeSize;
    private static final int START_SIZE = 16;
    
    
    // Constructor
    // ----------------------------------------------------------
    Forneymonegerie () {
    	collection = new ForneymonType[START_SIZE];
    	size = 0;
    	typeSize = 0;
    }
    
    
    // Methods
    // ----------------------------------------------------------
    public boolean empty () {
        if (size == 0) {
        	return true;
        } else {
        	return false;
        }
    }
    
    public int size () {
        return size;
    }
    
    public int typeSize () {
        return typeSize;
    }
    
    public boolean collect (String toAdd) {
    	
    	checkAndGrow();
        if (toAdd.equals("Dampymon") || toAdd.equals("Burnymon") 
        		|| toAdd.equals("Zappymon")) {
        	
        	for (int i = 0; i < size; i++) {
        		if (collection[i].type.equals(toAdd)) {
        			collection[i].count++;
        			return true;
        		}
        	}
        	
        	collection[size-1] = new ForneymonType(toAdd, 1);
        	return true;
        }
        
        return false;
    }
    
    public boolean release (String toRemove) {
        throw new UnsupportedOperationException();
    }
    
    public void releaseType (String toNuke) {
        throw new UnsupportedOperationException();
    }
    
    public int countType (String toCount) {
        throw new UnsupportedOperationException();
    }
    
    public boolean contains (String toCheck) {
        throw new UnsupportedOperationException();
    }
    
    public String nth (int n) {
        throw new UnsupportedOperationException();
    }
    
    public String rarestType () {
        throw new UnsupportedOperationException();
    }
    
    public Forneymonegerie clone () {
        throw new UnsupportedOperationException();
    }
    
    public void trade (Forneymonegerie other) {
        throw new UnsupportedOperationException();
    }
    
    
    // Static methods
    // ----------------------------------------------------------
    public static Forneymonegerie diffMon (Forneymonegerie y1, Forneymonegerie y2) {
        throw new UnsupportedOperationException();
    }
    
    public static boolean sameCollection (Forneymonegerie y1, Forneymonegerie y2) {
        throw new UnsupportedOperationException();
    }
    
   
    // Private helper methods
    // ----------------------------------------------------------
    
    // TODO: Add yours here!

    /*
     * Expands the size of the collection whenever it is at
     * capacity
     */
    private void checkAndGrow () {
        // Case: big enough to fit another item, so no
        // need to grow
        if (size < collection.length) {
            return;
        }
       
        // Case: we're at capacity and need to grow
        // Step 1: create new, bigger array; we'll
        // double the size of the old one
        ForneymonType[] newItems = new ForneymonType[collection.length * 2];
       
        // Step 2: copy the items from the old array
        for (int i = 0; i < collection.length; i++) {
            newItems[i] = collection[i];
        }
       
        // Step 3: update collection reference
        collection = newItems;
    }
    
    // Private Classes
    // ----------------------------------------------------------
    private class ForneymonType {
        String type;
        int count;
        
        ForneymonType (String t, int c) {
            type = t;
            count = c;
        }
    }
}
