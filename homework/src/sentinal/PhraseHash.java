package sentinal;

/*
 * To Do: add helper methods, account for index out of bounds for checkAndGrow, 
 * possibly modify hash method
 */

public class PhraseHash implements PhraseHashInterface {

    // -----------------------------------------------------------
    // Fields
    // -----------------------------------------------------------

    private final static int BUCKET_START = 1000;
    private final static double LOAD_MAX = 0.7;
    private int size, longest;
    private String[] buckets;


    // -----------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------

    PhraseHash () {
        size = 0;
        longest = 0;
        buckets = new String[BUCKET_START];
    }


    // -----------------------------------------------------------
    // Public Methods
    // -----------------------------------------------------------

    public int size () {
        return size;
    }

    public boolean isEmpty () {
        return size == 0;
    }

    public void put (String s) {
    	int index = hash(s);
    	checkAndGrow();
    	if (buckets[index] == null) {
    		size++;
        	String[] wordCount = s.split("\\s+");
        	if (wordCount.length > longest) {
        		longest = size;
        	}
        	buckets[index] = s;
    	}
    }

    public String get (String s) {
    	int index = hash(s);
    	if ( (buckets[index] == null) || (index > buckets.length) || (!buckets[index].equals(s)) ) { 
    		return null; 
    	}
    	return s;
    }

    public int longestLength () {
        return longest;
    }


    // -----------------------------------------------------------
    // Helper Methods
    // -----------------------------------------------------------

    private int hash (String s) {
    	int hashCode = 0;
    	byte[] strByte = s.getBytes();
		for (int i = 0; i < strByte.length; i++) {
			hashCode += Byte.toUnsignedInt(strByte[i]) * (i);
		}
		return hashCode % buckets.length;
    }

    private void checkAndGrow () {
        if (size/buckets.length > LOAD_MAX) {
        	String[] temp = buckets;
        	buckets = new String[temp.length * 2];
        	for (int i = 0; i < temp.length; i++) {
        		put(temp[i]);
        	}
        	buckets = temp;
        }
    }
}
