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
        throw new UnsupportedOperationException();
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
