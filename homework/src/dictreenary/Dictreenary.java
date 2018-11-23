package dictreenary;

import java.util.ArrayList;

public class Dictreenary implements DictreenaryInterface {

    // Fields
    // -----------------------------------------------------------
    TTNode root;
    private String wordToAdd;
    private int status;
    
    // Constructor
    // -----------------------------------------------------------
    Dictreenary () {}
    
    
    // Methods
    // -----------------------------------------------------------
    
    public boolean isEmpty () {
        return root == null;
    }
    
    public void addWord (String toAdd) {
    	wordToAdd = normalizeWord(toAdd);
    	status = 0;
    	if (isEmpty()) { 
    		root = new TTNode(wordToAdd.charAt(0), checkWordEnd());
    		status++;
    		addWord(root); 
    		return;
    	}
    	addWord(root);
    	return;
    }
    
    public boolean hasWord (String query) {
        throw new UnsupportedOperationException();
    }
    
    public String spellCheck (String query) {
        throw new UnsupportedOperationException();
    }
    
    public ArrayList<String> getSortedWords () {
        throw new UnsupportedOperationException();
    }
    
    
    // Helper Methods
    // -----------------------------------------------------------
    
    private String normalizeWord (String s) {
        // Edge case handling: empty Strings illegal
        if (s == null || s.equals("")) {
            throw new IllegalArgumentException();
        }
        return s.trim().toLowerCase();
    }
    
    /*
     * Returns:
     *   int less than 0 if c1 is alphabetically less than c2
     *   0 if c1 is equal to c2
     *   int greater than 0 if c1 is alphabetically greater than c2
     */
    private int compareChars (char c1, char c2) {
        return Character.toLowerCase(c1) - Character.toLowerCase(c2);
    }
    
    private void addWord (TTNode n) {
    	
    	// End case: added all letters of wordToAdd
    	if (status == wordToAdd.length()) { return; }
    	// if charToAdd is the same
        if (compareChars(wordToAdd.charAt(status), n.letter) == 0) {
        	status++;
        	if (n.mid == null) {
        		n.mid = new TTNode(wordToAdd.charAt(status), checkWordEnd());
        		status++;
        	}
        	addWord(n.mid);
        }
        
        // End case: added all letters of wordToAdd
        if (status == wordToAdd.length()) { return; }
        // if adding letter to mid that is not a word ending
        if (!n.wordEnd && n.left == null && n.right == null && n.mid == null) {
        	n.mid = new TTNode(wordToAdd.charAt(status), checkWordEnd());
    		status++;
    		addWord(n.mid);
        }
        
        // End case: added all letters of wordToAdd
        if (status == wordToAdd.length()) { return; }
        // if charToAdd is alphabetically less than current letter
        if (compareChars(wordToAdd.charAt(status), n.letter) < 0) {
        	if (n.left == null) {
        		n.left = new TTNode(wordToAdd.charAt(status), checkWordEnd());
        		status++;
        	}
        	addWord(n.left);
        }
        
        // End case: added all letters of wordToAdd
        if (status == wordToAdd.length()) { return; }
        // if charToAdd is alphabetically greater than current letter
        if (compareChars(wordToAdd.charAt(status), n.letter) > 0) {
        	if (n.right == null) {
        		n.right = new TTNode(wordToAdd.charAt(status), checkWordEnd());
        		status++;
        	}
        	addWord(n.right);
        }
    }
    
    private void addFirstWord (TTNode n) {
    	// End case: added all letters of wordToAdd
    	if (status == wordToAdd.length()) { return; }
        n.mid = new TTNode(wordToAdd.charAt(status), checkWordEnd());
        status++;
        addFirstWord(n.mid);
    }
    
    private boolean checkWordEnd () {
    	return (status == wordToAdd.length()-1);
    }
    
    // TTNode Internal Storage
    // -----------------------------------------------------------
    
    /*
     * Internal storage of Dictreenary words
     * as represented using a Ternary Tree with TTNodes
     */
    private class TTNode {
        
        boolean wordEnd;
        char letter;
        TTNode left, mid, right;
        
        TTNode (char c, boolean w) {
            letter  = c;
            wordEnd = w;
        }
        
    }
    
}
