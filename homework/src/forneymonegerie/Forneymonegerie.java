package forneymonegerie;

/*
 * Name: Forneymonegerie
 * Author: Patrick Utz 
 * Date: 9/28/2018
 * Description: A happy medium between arrays and sets of Strings used to model all of the 
 * different types of Forneymon in a user's collection.
 */

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
    
    /*
     * Returns true if the Forneymonegerie has no Forneymon inside, false otherwise
     */ 
    public boolean empty () {
        if (size == 0) {
        	return true;
        } else {
        	return false;
        }
    }
    
    /*
     * Returns the current size of the Forneymonegerie
     */
    public int size () {
        return size;
    }
    
    /*
     * Returns the number of unique Forneymon types in the Forneymonegerie
     */
    public int typeSize () {
        return typeSize;
    }
    
    /*
     * Adds the Forneymon type indicated by typeToAdd to the Forneymonegerie
     * Returns true if the typeAdded was not already in the Forneymonegerie, false otherwise
     */
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
    
    /*
     * Removes 1 Forneymon of the given typeToRelease from the Forneymonegerie, and returns 
     * true if at least 1 was removed this way, false otherwise
     */
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
    		return false;
    	} else {
    		return false;
    	}
    }
    
    /*
     * Removes ALL Forneymon of the given typeToNuke from the Forneymonegerie 
     */
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
    
    /*
     * Return the number of Forneymon of the given typeToCount found in the Forneymonegerie 
     */
    public int countType (String toCount) {
    	if (contains(toCount)) {
    		return collection[returnTypeIndex(toCount)].count;
    	}
    	return 0;
    }
    
    /*
     * Returns true if the given typeToCheck appears at least once inside of the Forneymonegerie
     */
    public boolean contains (String toCheck) {
    	for (int i = 0; i < typeSize; i++) {
    		if (collection[i] != null && collection[i].type.equals(toCheck)) {
    			return true;
    		}
    	}
    	return false;
    }
    
    /*
     * Returns type of nth Forneymon
     */
    public String nth (int n) {
    	// check first to see if argument is within current size
    	if (n < 0 || n > size) {
    		throw new IllegalArgumentException();
    	}
    	// find the type of the nth Forneymon
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
    
    /*
     * Returns the ForneymonType that occurs least frequently in the Forneymonegerie
     */
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
    
    /*
     * Returns a deep copy of this Forneymonegerie
     */
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
    
    /*
     * Swaps the contents of the calling Forneymonegerie and the other specified
     */
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
    
    /*
     * toString representation of the current Forneymonegerie
     */
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
    
    /*
     * Returns a *new* Forneymonegerie object consisting of all Forneymon from y1 that 
     * do NOT appear in y2
     */
    public static Forneymonegerie diffMon (Forneymonegerie y1, Forneymonegerie y2) {
    	Forneymonegerie diffMonegerie = new Forneymonegerie();
    	// iterate through each type in y1
    	for (int i = 0; i < y1.typeSize; i++) {
    		// compare type i from y1 to each type in y2
    		for (int j = 0; j < y2.typeSize; j++) {
    			// check if type and count are the same
    			if (y1.collection[i].type.equals(y2.collection[j].type) && 
    					(y1.collection[i].count == y2.collection[j].count)) {
    				break;
    				   // check if type is the same and y1 has more Forneymon than 
    				   // y2 of that type
    			} else if ((y1.collection[i].type.equals(y2.collection[j].type)) &&
    					 (y1.collection[i].count-y2.collection[j].count > 0)) {
    				for (int k = 0; k < y1.collection[i].count-y2.collection[j].count; k++) {
    					diffMonegerie.collect(y1.collection[i].type);
    				}
    				break;
    				   // check if the types are different and finished indexing y2 types 
    			} else if (j == y2.typeSize-1) {
    				for (int k = 0; k < y1.collection[i].count; k++) {
        				diffMonegerie.collect(y1.collection[i].type);
    				}
    				break;
    			}
    		}
    	}
    	return diffMonegerie;
    }
    
    /*
     * Returns true if y1 and y2 contain the exact same ForneymonTypes and number of Forneymon
     * in each type, though in any collection order
     */
    public static boolean sameCollection (Forneymonegerie y1, Forneymonegerie y2) {
    	int trackNumberOfTypes = 0;
    	for (int i = 0; i < y1.typeSize; i++) {
    		for (int j = 0; j < y2.typeSize; j++) {
    			if (y1.collection[i].type.equals(y2.collection[j].type) &&
    					y1.collection[i].count == y2.collection[j].count) {
    				trackNumberOfTypes++;
    				break;
    			}
    		}
    	}
    	if ((trackNumberOfTypes == y1.typeSize) && (trackNumberOfTypes == y2.typeSize)) {
    		return true;
    	}
    	return false;
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
