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
    	size++;
        for (int i = 0; i < typeSize; i++) {
       		if (collection[i] != null && collection[i].type.equals(toAdd)) {
       			collection[i].count++;
       			return false;
       		}
       	}
        	
       	collection[typeSize] = new ForneymonType(toAdd, 1);
       	typeSize++;
       	return true;
    }
    
    public boolean release (String typeToRelease) {
    	// check first if the type is in the collection
    	if (contains(typeToRelease)) {
    		for (int i = 0; i < typeSize; i++) {
    			if (collection[i].type.equals(typeToRelease)) {
    				// check if there is more than one Forneymon of that type left
    				if (collection[i].count > 1) {
    					collection[i].count--;
    					size--;
    					return true;
    				} else {
    					// if there is only one Forneymon of that type just get 
    					// rid of that type in the overall collection
    					releaseType(typeToRelease);
    					return true;
    				}
    			}
    		}
    	}
    	return false;
    }
    
    public void releaseType (String toNuke) {
    	if (contains(toNuke)) {
    		int typeIndex = returnTypeIndex(toNuke);
    		size = size - collection[typeIndex].count;
    		for (int i = typeIndex; i < typeSize-1; i++) {
                collection[i] = collection[i+1];
            }
    		collection[typeSize-1] = null;
    		typeSize--;
    	}
    	return;
    }
    
    public int countType (String toCount) {
    	if (contains(toCount)) {
    		return collection[returnTypeIndex(toCount)].count;
    	}
    	// ***** CHECK WHAT SHOULD RETURN IF ELSE *****
    	return 0;
    }
    
    public boolean contains (String toCheck) {
    	for (int i = 0; i < typeSize; i++) {
    		if (collection[i] != null && collection[i].type.equals(toCheck)) {
    			return true;
    		}
    	}
    	return false;
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
        if (typeSize < collection.length) {
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
    
    /*
     * Returns the index in the collection of ForneymonTypes of the respective
     * type that is handed to the method. Returns 0 if type not found
     */
    private int returnTypeIndex (String typeToSearch) {
    	for (int i = 0; i < typeSize; i++) {
    		if (collection[i] != null && collection[i].type.equals(typeToSearch)) {
    			return i;
    		} else {
    			return 0;
    		}
    	}
    	return 0;
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
