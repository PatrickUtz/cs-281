package linked_forneymonegerie;

import java.util.NoSuchElementException;

public class LinkedForneymonegerie implements LinkedForneymonegerieInterface {

    // Fields
    // -----------------------------------------------------------
    private ForneymonType head;
    private int size, typeSize, modCount;
    
    
    // Constructor
    // -----------------------------------------------------------
    LinkedForneymonegerie () {
        head = null;
        size = 0;
        typeSize = 0;
        modCount = 0;
    }
    
    
    // Methods
    // -----------------------------------------------------------
    public boolean empty () {
    	return size == 0;
    }
    
    public int size () {
        return size;
    }
    
    public int typeSize () {
    	return typeSize;
    }
    
    public boolean collect (String toAdd) {
        modCount++;
                
        if (empty()) {
        	head = new ForneymonType(toAdd, 1);
    		head.prev = null;
    		head.next = null;
            size++;
    		typeSize++;
    		return true;
        }
        
        size++;
        ForneymonType typeToAdd = getType(toAdd);
        if (typeToAdd != null) {
        	typeToAdd.count++;
        	return false;
        } else {
        	ForneymonType oldEnd = getEnd();
        	ForneymonType newEnd = new ForneymonType(toAdd, 1);
        	oldEnd.next = newEnd;
        	newEnd.prev = oldEnd;
        	typeSize++;
        	return true;
        }
    }
    
    public boolean release (String toRemove) {
    	modCount++;
    	ForneymonType typeToRelease = getType(toRemove);
    	if (typeToRelease != null) {
    		if (typeToRelease.count == 1) {
    			releaseType(toRemove);
    		} else {
    			typeToRelease.count--;
    			size--;
    		}
			return true;
    	}
    	return false;
    }
    
    public void releaseType (String toNuke) {
    	modCount++;
    	ForneymonType typeToNuke = getType(toNuke);
    	if (typeToNuke != null) {
    		typeSize--;
    		size = size - typeToNuke.count;
    		remove(typeToNuke);
    	}
    	return;
    }
    
    public int countType (String toCount) {
    	ForneymonType typeToCount = getType(toCount);
    	if (typeToCount != null) {
    		return typeToCount.count;
    	}
    	return 0;
    }
    
    public boolean contains (String toCheck) {
        if (getType(toCheck) != null) {
        	return true;
        }
        return false;
    }
    
    public String rarestType () {
    	ForneymonType current = head;
    	String rarest = null;
        int rarestCount = current.count;
        while (current != null) {
        	if (current.count <= rarestCount) {
        		rarest = current.type;
        		rarestCount = current.count;
        	}
        	current = current.next;
        }
        return rarest;
    }
    
    public LinkedForneymonegerie clone () {
    	LinkedForneymonegerie clone = new LinkedForneymonegerie();
    	ForneymonType current = head;
    	ForneymonType cloneCurrent = null;
    	ForneymonType clonePrev = null;
    	while (current != null) {
    		if (clone.head == null) {
    			clone.head = new ForneymonType(head.type, head.count);
    			cloneCurrent = clone.head;
    		} else {
    			cloneCurrent.next = new ForneymonType(current.type, current.count);
        		cloneCurrent.next.prev = cloneCurrent; 
        		cloneCurrent = cloneCurrent.next;
    		}
    		current = current.next;
    	}
    	clone.modCount = modCount;
    	clone.size = size;
    	clone.typeSize = typeSize;
    	return clone;
    }
    
    public void trade (LinkedForneymonegerie other) {
    	ForneymonType tempHead = head;
    	int tempSize = size;
    	int tempTypeSize = typeSize;
    	int tempModCount = modCount;
    	
    	head = other.head;
    	size = other.size;
    	typeSize = other.typeSize;
    	modCount = other.modCount;
    	
    	other.head = tempHead;
    	other.size = tempSize;
    	other.typeSize = tempTypeSize;
    	other.modCount = tempModCount;
    	
    	modCount++;
    	other.modCount++;
    }
    
    @Override
    public String toString() {
    	throw new UnsupportedOperationException();
    }
    
    public LinkedForneymonegerie.Iterator getIterator () {
        throw new UnsupportedOperationException();
    }
    
    
    // -----------------------------------------------------------
    // Static methods
    // -----------------------------------------------------------
    
    public static LinkedForneymonegerie diffMon (LinkedForneymonegerie y1, LinkedForneymonegerie y2) {
        throw new UnsupportedOperationException();
    }
    
    public static boolean sameCollection (LinkedForneymonegerie y1, LinkedForneymonegerie y2) {
        throw new UnsupportedOperationException();
    }
    
    // Private helper methods
    // -----------------------------------------------------------

    // TODO: Your helper methods here!
    
    private ForneymonType getType (String typeToFind) {
    	if (empty()) {
    		return null;
    	}
    	ForneymonType current = head;
    	while (current != null) {
    		if (current.type.equals(typeToFind)) {
    			return current;
    		}
    		current = current.next;
    	}
    	return null;
    }
    
    private void remove (ForneymonType toRemove) {
    	if (toRemove == head) {
    		if (head.next != null) {
    			head.next.prev = null;
    			head = head.next;
    			return;
    		} else {
    			head = null;
    		}
    	} else if (isEnd(toRemove)) {
    		toRemove.prev.next = null;
    	} else {
    		toRemove.prev.next = toRemove.next;
    		toRemove.next.prev = toRemove.prev;
    	}
    	return;
    }
    
    private boolean isEnd (ForneymonType toCheck) {
    	if (toCheck.next == null) {
    		return true;
    	}
    	return false;
    }
    
    private ForneymonType getEnd () {
    	ForneymonType current = head;
    	while (!isEnd(current)) {
    		current = current.next;
    	}
    	return current;
    }
    
    
    // Inner Classes
    // -----------------------------------------------------------
    
    public class Iterator implements LinkedForneymonegerieIteratorInterface {
        LinkedForneymonegerie owner;
        ForneymonType current;
        int itModCount;
        
        Iterator (LinkedForneymonegerie y) {
            // TODO
        }
        
        public boolean hasNext () {
            throw new UnsupportedOperationException();
        }
        
        public boolean hasPrev () {
            throw new UnsupportedOperationException();
        }
        
        public boolean isValid () {
            throw new UnsupportedOperationException();
        }
        
        public String getType () {
            throw new UnsupportedOperationException();
        }

        public void next () {
            throw new UnsupportedOperationException();
        }
        
        public void prev () {
            throw new UnsupportedOperationException();
        }
        
        public void replaceAll (String toReplaceWith) {
            throw new UnsupportedOperationException();
        }
        
    }
    
    private class ForneymonType {
        ForneymonType next, prev;
        String type;
        int count;
        
        ForneymonType (String t, int c) {
            type = t;
            count = c;
        }
    }
    
}