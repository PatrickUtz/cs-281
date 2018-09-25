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
    	// check first to see if argument is within current size
    	if (n < 0 || n > size) {
    		throw new IllegalArgumentException();
    	}
    	// fine the type of the nth Forneymon
    	int currentNth = 0;
    	for (int i = 0; i < typeSize; i++) {
    		currentNth = currentNth + collection[i].count; 
    		if (n < currentNth) {
    			return collection[i].type;
    		}
    	}
    	// return null if nth Forneymon not found 
    	return null;
    }
    
    public String rarestType () {
    	if (empty()) {
    		return null;
    	}
    	int rarestCount = collection[0].count;
    	int rarestTypeIndex = 0;
    	for (int i = 1; i < typeSize; i++) {
    		if (collection[i].count <= rarestCount) {
    			rarestCount = collection[i].count;
    			rarestTypeIndex = i;
    		}
    	}
    	return collection[rarestTypeIndex].type;
    }
    
    public Forneymonegerie clone () {
    	Forneymonegerie clone = new Forneymonegerie();
    	clone.size = size;
    	clone.typeSize = typeSize;
    	for (int i = 0; i < typeSize; i++) {
    		int countCurrent = collection[i].count;
    		String typeToAdd = collection[i].type;
    		for (int j = 0; j < countCurrent; j++) {
    			clone.collect(typeToAdd);
    		}
    	}
    	return clone;
    }
    
    public void trade (Forneymonegerie other) {
    	Forneymonegerie placeHolder = new Forneymonegerie();
    	placeHolder.collection = collection;
    	placeHolder.size = size;
    	placeHolder.typeSize = typeSize;
    	collection = other.collection;
    	size = other.size;
    	typeSize = other.typeSize;
    	other.collection = placeHolder.collection;
    	other.size = placeHolder.size;
    	other.typeSize = placeHolder.typeSize;
    }
    
    public String toString () {
    	String outputCollection = "[";
    	for (int i = 0; i < typeSize; i++) {
    		if (i < typeSize-1) {
    			outputCollection = outputCollection + " \"" + collection[i].type + 
        				"\": " + collection[i].count + ",";
    		} else {
    			outputCollection = outputCollection + " \"" + collection[i].type + 
        				"\": " + collection[i].count + " ]";
    		}
    	}
    	return outputCollection;
    }
    
    // Static methods
    // ----------------------------------------------------------
    public static Forneymonegerie diffMon (Forneymonegerie y1, Forneymonegerie y2) {
    	Forneymonegerie diffMonegerie = new Forneymonegerie();
    	// iterate through each type in y1
    	for (int i = 0; i < y1.typeSize; i++) {
    		for (int j = 0; j < y2.typeSize; j++) {
    			// compare each type in y1 to y2
    			if (y1.collection[i].type.equals(y2.collection[j].type) && 
    					(y1.collection[i].count == y2.collection[j].count)) {
    				break;
    			} else if ((y1.collection[i].type.equals(y2.collection[j].type)) &&
    					 (y1.collection[i].count-y2.collection[j].count > 0)) {
    				for (int k = 0; k < y1.collection[i].count-y2.collection[j].count; k++) {
    					diffMonegerie.collect(y1.collection[i].type);
    				}
    				break;
    			} else {
    				for (int k = 0; k < y1.collection[i].count; k++) {
        				diffMonegerie.collect(y1.collection[i].type);
    				}
    				break;
    			}
    		}
    	}
    	return diffMonegerie;
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
