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
    Forneymonegerie () {}
    
    
    // Methods
    // ----------------------------------------------------------
    public boolean empty () {
        throw new UnsupportedOperationException();
    }
    
    public int size () {
        throw new UnsupportedOperationException();
    }
    
    public int typeSize () {
        throw new UnsupportedOperationException();
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
