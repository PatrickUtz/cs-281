/* 
 * Author: Patrick Utz
 * Date: Nov 26, 2018
 * CS 281 - HW 4
 * Dr. Forney
 */

package dictreenary;

import java.util.ArrayList;

public class Dictreenary implements DictreenaryInterface {

    // Fields
    // -----------------------------------------------------------
	
    TTNode root;
    private String wordToAdd;
    private String wordToCheck;
    private String wordToSort;
    private int status;
    private boolean isValid;
    private ArrayList<String> sortedAlpha;
    
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
    	wordToCheck = normalizeWord(query);
    	status = 0;
    	if (isEmpty()) { 
    		return false;
    	} else {
    		return hasWord(root);
    	}
    }
    
    public String spellCheck (String query) {
    	String checkPossible;
    	if (hasWord(query)) {
        	return query;
        }
        if (query.length() == 1) { return null; }
        if (query.length() == 2) {
        	checkPossible = Character.toString(query.charAt(1)) + 
        			    Character.toString(query.charAt(0));
        	if (hasWord(checkPossible)) {
            	return checkPossible;
            } else {
            	return null;
            }
        }
        for (int i = 0; i < query.length()-1; i++) {
        	if (i == 0) {
        		checkPossible = Character.toString(query.charAt(i+1)) + 
            			Character.toString(query.charAt(i)) + 
            			query.substring(i+2, query.length());
        	} else {
        		checkPossible = query.substring(0, i) + 
        				Character.toString(query.charAt(i+1)) + 
            			Character.toString(query.charAt(i)) + 
            			query.substring(i+2, query.length());
        	}
        	if (hasWord(checkPossible)) {
            	return checkPossible;
            } 
        }
        return null;
    }
    
    public ArrayList<String> getSortedWords () {
    	sortedAlpha = new ArrayList<String>();
    	wordToSort = "";
    	if (isEmpty()) { return sortedAlpha; }
    	getSortedWords(root);
        return sortedAlpha;
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
    	// end case: added all letters of wordToAdd
    	if (status == wordToAdd.length()) { 
    		return;
    	// check if letter adding is name as n.letter
    	} else if (compareChars(wordToAdd.charAt(status), n.letter) == 0) {
    		// check if letter adding is last one in wordToAdd
    		if (status == wordToAdd.length()-1) {
    			// check if wordToAdd is just one letter
    			if (wordToAdd.length() == 1) {
    				n.wordEnd = true;
    				return;
    			}
    			// else check if adding a duplicate letter  
    			if (compareChars(wordToAdd.charAt(status), wordToAdd.charAt(status-1)) == 0) {
    				if (n.mid == null) {
    	        		n.mid = new TTNode(wordToAdd.charAt(status), checkWordEnd());
    	        		status++;
    	        	} else {
    	        		n.wordEnd = true;
    	        		return;
    	        	}
    	        	addWord(n.mid);
    	        	return;
    			}
    			n.wordEnd = true;
            	return;
    		}
    		// else if letter adding is not the last one in wordToAdd
    		if (status > 0) {
    			// if adding a duplicate letter
    			if (compareChars(wordToAdd.charAt(status), wordToAdd.charAt(status-1)) == 0) {
    				if (n.mid == null) {
    	        		n.mid = new TTNode(wordToAdd.charAt(status), checkWordEnd());
    	        		status++;
    	        	} else {
    	        		status++;
    	        	}
    	        	addWord(n.mid);
    	        	return;
    			}
    		}
    		// if adding a letter that is the same as n.letter and no prev conditions met
    		status++;
        	if (n.mid == null) {
        		n.mid = new TTNode(wordToAdd.charAt(status), checkWordEnd());
        		status++;
        	}
        	addWord(n.mid);
        	return;
   
        // if adding letter different than n.letter and node is alone
        } else if (!n.wordEnd && n.left == null && n.right == null && n.mid == null) {
        	n.mid = new TTNode(wordToAdd.charAt(status), checkWordEnd());
    		status++;
    		addWord(n.mid);
    	// if charToAdd is alphabetically less than current letter
        } else if (compareChars(wordToAdd.charAt(status), n.letter) < 0) {
        	if (n.left == null) {
        		n.left = new TTNode(wordToAdd.charAt(status), checkWordEnd());
        		status++;
        	}
        	addWord(n.left);
        // if charToAdd is alphabetically greater than current letter
        } else if (compareChars(wordToAdd.charAt(status), n.letter) > 0) {
        	if (n.right == null) {
        		n.right = new TTNode(wordToAdd.charAt(status), checkWordEnd());
        		status++;
        	}
        	addWord(n.right);
        }  
    }
    
    private boolean hasWord (TTNode n) {
    	if (status == wordToCheck.length() && isValid) { return true; }
    	if (status == wordToCheck.length() && !isValid) { return false; }
    	if (n == null) { return false; }
    	isValid = n.wordEnd;
    	if (compareChars(wordToCheck.charAt(status), n.letter) == 0) {
    		status++; 
    		return (hasWord(n.mid));
    	}
    	if (compareChars(wordToCheck.charAt(status), n.letter) < 0) {
    		return (hasWord(n.left));
    	}
    	if (compareChars(wordToCheck.charAt(status), n.letter) > 0) {
    		return (hasWord(n.right));
    	}
    	return false;
    }
    
    private void getSortedWords (TTNode n) {
    	if (n == null) { return; }
    	getSortedWords(n.left);
    	wordToSort = wordToSort.concat(Character.toString(n.letter));
    	if (n.wordEnd) { sortedAlpha.add(wordToSort); }
    	getSortedWords(n.mid);
    	wordToSort = wordToSort.substring(0,wordToSort.length()-1);
    	getSortedWords(n.right);
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